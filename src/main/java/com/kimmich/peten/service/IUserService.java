package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.common.api.ApiResult;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.common.PageInfo;
import com.kimmich.peten.model.dto.LoginDTO;
import com.kimmich.peten.model.dto.RegisterDTO;
import com.kimmich.peten.model.dto.article.SimpleArticleDTO;
import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.model.vo.ProfileVO;

import java.util.List;


public interface IUserService extends IService<User> {

    List<ArticleBO> getMyFollowPost(String userId);

    ListPageDTO<Object> myFollowNews(List<String> userId);

    List<SimpleUserDTO> getMyFollow(String userId);

    List<SimpleUserDTO> recommend(String userId);

    List<String> getAllId();

    List<SimpleUserDTO> getSimpleInfo(List<String> id);

    SimpleUserDTO getSimpleInfo(String id);
    User getUserByUserId(String userId);
    /**
     * 注册功能
     *
     * @param dto
     * @return 注册对象
     */
    User executeRegister(RegisterDTO dto);
    /**
     * 获取用户信息
     *
     * @param username
     * @return dbUser
     */
    User getUserByUsername(String username);
    /**
     * 用户登录
     *
     * @param dto
     * @return 生成的JWT的token
     */
    String executeLogin(LoginDTO dto);

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    ProfileVO getUserProfile(String id);
}
