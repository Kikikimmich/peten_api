package com.kimmich.peten.controller;

import com.kimmich.peten.emun.JwtConst;
import com.kimmich.peten.jwt.JwtUtil;
import com.kimmich.peten.model.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class BaseController {

    public String getLoginUserId() {
        return getLoginUser().getId();
    }

    public String getLoginUserName() {
        return getLoginUser().getUsername();
    }

    private User getLoginUser() {
        String token = getHttpServletRequest().getHeader(JwtConst.HEADER_STRING);
        token = token.replace(JwtConst.TOKEN_PREFIX, "");
        Claims claims = JwtUtil.decodeToken(token);
        String userName = claims.get(JwtConst.USER_NAME, String.class);
        String id = claims.get(JwtConst.USER_ID, String.class);
        return User.builder()
                .username(userName)
                .id(id)
                .build();
    }

    //获取当前登录用户的token  可以通过这个
    private static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

}
