package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.PostMapper;
import com.kimmich.peten.mapper.TagMapper;
import com.kimmich.peten.mapper.TopicMapper;
import com.kimmich.peten.mapper.UserMapper;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.dto.CreateTopicDTO;
import com.kimmich.peten.model.dto.post.PostDTO;
import com.kimmich.peten.model.entity.Post;
import com.kimmich.peten.model.entity.Tag;
import com.kimmich.peten.model.entity.TopicTag;
import com.kimmich.peten.model.entity.User;
import com.kimmich.peten.model.vo.PostVO;
import com.kimmich.peten.model.vo.ProfileVO;
import com.kimmich.peten.service.IPostService;
import com.kimmich.peten.service.ITagService;
import com.kimmich.peten.service.ITopicTagService;
import com.kimmich.peten.service.IUserService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl extends ServiceImpl<TopicMapper, Post> implements IPostService {

    @Resource
    PostMapper postMapper;

    @Override
    public ListPageDTO<PostDTO> getList(String groupId, Long page, Long pageSize) {

        Page<Post> postPage = new Page<>(page, pageSize);
        postPage = postMapper.getList(groupId, postPage);

        return null;
    }
}
