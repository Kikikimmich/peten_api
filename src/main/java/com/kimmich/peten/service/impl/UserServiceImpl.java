package com.kimmich.peten.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.common.exception.ApiAsserts;
import com.kimmich.peten.constant.PathConst;
import com.kimmich.peten.constant.UserConst;
import com.kimmich.peten.jwt.JwtUtil;
import com.kimmich.peten.mapper.FollowMapper;
import com.kimmich.peten.mapper.TopicMapper;
import com.kimmich.peten.mapper.UserMapper;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.LoginDTO;
import com.kimmich.peten.model.dto.RegisterDTO;
import com.kimmich.peten.model.dto.article.SimpleArticleDTO;
import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import com.kimmich.peten.model.entity.Article;
import com.kimmich.peten.model.entity.Follow;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.model.vo.ProfileVO;
import com.kimmich.peten.service.IArticleService;
import com.kimmich.peten.service.IFollowService;
import com.kimmich.peten.service.IUserService;
import com.kimmich.peten.utils.CommonUtil;
import com.kimmich.peten.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IFollowService followService;

    @Resource
    IArticleService articleService;


    @Override
    public List<ArticleBO> getMyFollowPost(String userId) {
        List<String> myFocus = getMyFocus(userId);
        return getContentByAuthor(myFocus);
    }

    @Override
    public ListPageDTO<Object> myFollowNews(List<String> userId) {
        return null;
    }

    @Override
    public List<SimpleUserDTO> getMyFollow(String userId) {
        List<String> myFocus = getMyFocus(userId);
        return getSimpleInfo(myFocus);
    }

    @Override
    public List<SimpleUserDTO> recommend(String userId) {

        if (StrUtil.isBlank(userId)){
            return new ArrayList<>();
        }

        List<String> idList = forecastInterestedUserId(userId);

        // 不够五个就凑齐5个
        if (idList == null || idList.size() < 5){
            addForecast(userId, idList);
        }

        return getSimpleInfo(idList);
    }


    private List<ArticleBO> getContentByAuthor(List<String> userId){
        if (CollectionUtil.isEmpty(userId)){
            return new ArrayList<>();
        }
        List<ArticleBO> result = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (String id : userId) {
            QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Article::getAuthor, id).orderByDesc(Article::getCreateTime);
            List<Article> articleList = articleService.list(queryWrapper);
            ArticleBO bo = new ArticleBO();
            SimpleUserDTO userDTO = getSimpleInfo(id);
            for (Article article : articleList) {
                BeanUtils.copyProperties(article, bo);
                bo.setAuthorInfo(userDTO);


                bo.setCreateTime(formatter.format(article.getCreateTime()));

                result.add(bo);
            }
        }

        return result;
    }

    private void addForecast(String userId, List<String> idList) {
        List<String> myFocus = getMyFocus(userId);
        myFocus.addAll(idList);

        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(Follow::getUserId)
                .notIn(Follow::getUserId, userId)
                .groupBy(Follow::getUserId);

        queryWrapper.select("count(follow) num").orderByDesc("num").last("limit 100");

        List<Follow> list = followService.list(queryWrapper);

        List<String> topUser = list.stream().map(Follow::getFollow).collect(Collectors.toList());

        idList.addAll(topUser.subList(0, 5- idList.size()));
    }

    private List<String> forecastInterestedUserId(String userId) {

        // 获取全部关注
        List<String> myFocus = getMyFocus(userId);
        if (myFocus == null || myFocus.size() < UserConst.ITEM_SET_THRESHOLD){
            // 没有预测价值。
            return new ArrayList<>();
        }

        File file = CommonUtil.getSystemFile(PathConst.FREQUENT_ITEM_SET);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> forecast = new ArrayList<>();
            while (br.ready()) {
                String s = br.readLine();
                String[] itemSet = s.split(" ");
                int count = UserConst.ITEM_SET_THRESHOLD;
                for (String item : itemSet) {
                    if (myFocus.contains(item)) {
                        count--;
                    }
                    // 频繁项集匹配成功
                    if (!myFocus.contains(item) && count <= 0) {
                        forecast.add(item);
                    }
                }
            }
            return forecast;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<String> getMyFocus(String userId) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(Follow::getFollow).eq(Follow::getUserId, userId);
        List<Follow> list = followService.list(queryWrapper);
        return list.stream().map(Follow::getFollow).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllId() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getId).ge(User::getCreateTime, "2023-04-06");
        return this.list(queryWrapper).stream().map(User::getId).collect(Collectors.toList());
    }

    @Override
    public List<SimpleUserDTO> getSimpleInfo(List<String> id) {
        if (id == null || id.size() == 0){
            return new ArrayList<>();
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(User::getId, id);
        List<User> list = list(queryWrapper);

        List<SimpleUserDTO> result = new ArrayList<>();
        for (User user : list) {
            result.add(SimpleUserDTO.builder()
                    .id(user.getId())
                    .name(user.getUsername())
                    .avatar(user.getAvatar())
                    .build());
        }

        return result;
    }

    @Override
    public SimpleUserDTO getSimpleInfo(String id) {
        User user = getById(id);
        return SimpleUserDTO.builder()
                .id(id)
                .name(user.getUsername())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public User getUserByUserId(String userId) {
        return baseMapper.selectById(userId);
    }

    @Override
    public User executeRegister(RegisterDTO dto) {
        //查询是否有相同用户名的用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getName()).or().eq(User::getEmail, dto.getEmail());
        User user = baseMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(user)) {
            ApiAsserts.fail("账号或邮箱已存在！");
        }
        User addUser = User.builder()
                .username(dto.getName())
                .alias(dto.getName())
                .password(MD5Utils.getPwd(dto.getPass()))
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);

        return addUser;
    }
    @Override
    public User getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }
    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try {
            User user = getUserByUsername(dto.getUsername());
            String encodePwd = MD5Utils.getPwd(dto.getPassword());
            if(!encodePwd.equals(user.getPassword()))
            {
                throw new Exception("密码错误");
            }
            token = JwtUtil.generateToken(user.getUsername(), user.getId());
        } catch (Exception e) {
            log.warn("用户不存在or密码验证失败=======>{}", dto.getUsername());
        }
        return token;
    }
    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profile = new ProfileVO();
        User user = baseMapper.selectById(id);
        BeanUtils.copyProperties(user, profile);
        // 用户文章数
//        int count = topicMapper.selectCount(new LambdaQueryWrapper<Post>().eq(Post::getUserId, id));
        profile.setTopicCount(0);

        // 粉丝数
//        int followers = followMapper.selectCount((new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, id)));
        profile.setFollowerCount(0);

        return profile;
    }
}
