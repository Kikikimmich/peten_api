package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.post.PostDTO;
import com.kimmich.peten.model.entity.Post;



public interface IPostService extends IService<Post> {

    ListPageDTO<PostDTO> getList(String groupId, Long page, Long pageSize);


}
