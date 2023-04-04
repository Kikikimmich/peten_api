package com.kimmich.peten.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimmich.peten.model.entity.group.Post;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {

    @Select("SELECT * FROM tbl_post WHERE groupId = #{groupId} ORDER BY modify_time, create_time")
    Page<Post> getList(@Param("groupId") String groupId, Page<Post> postPage);
}
