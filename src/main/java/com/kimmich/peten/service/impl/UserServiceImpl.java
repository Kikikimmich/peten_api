package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiAsserts;
import com.kimmich.peten.jwt.JwtUtil;
import com.kimmich.peten.mapper.FollowMapper;
import com.kimmich.peten.mapper.TopicMapper;
import com.kimmich.peten.mapper.UserMapper;
import com.kimmich.peten.model.dto.LoginDTO;
import com.kimmich.peten.model.dto.RegisterDTO;
import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import com.kimmich.peten.model.entity.Follow;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.model.vo.ProfileVO;
import com.kimmich.peten.service.IUserService;
import com.kimmich.peten.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private FollowMapper followMapper;

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
        int followers = followMapper.selectCount((new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, id)));
        profile.setFollowerCount(followers);

        return profile;
    }
}
