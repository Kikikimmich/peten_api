package com.kimmich.peten.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimmich.peten.model.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author Knox 2020/11/7
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
