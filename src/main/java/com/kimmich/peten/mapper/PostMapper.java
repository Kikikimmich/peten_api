package com.kimmich.peten.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimmich.peten.model.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {

    @Select("SELECT * FROM tbl_post WHERE groupId = #{groupId}")
    Page<Post> getList(@Param("groupId") String groupId, Page<Post> postPage);
}
