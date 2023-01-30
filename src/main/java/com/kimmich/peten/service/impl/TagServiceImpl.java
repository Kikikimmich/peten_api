package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.TagMapper;
import com.kimmich.peten.model.entity.Post;
import com.kimmich.peten.model.entity.Tag;
import com.kimmich.peten.service.ITagService;
import com.kimmich.peten.service.IPostService;
import com.kimmich.peten.service.ITopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Tag 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Autowired
    private ITopicTagService ITopicTagService;

    @Autowired
    private IPostService IPostService;


    @Override
    public List<Tag> insertTags(List<String> tagNames) {
        List<Tag> tagList = new ArrayList<>();
        for (String tagName : tagNames) {
            Tag tag = this.baseMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName, tagName));
            if (tag == null) {
                tag = Tag.builder().name(tagName).build();
                this.baseMapper.insert(tag);
            } else {
                tag.setTopicCount(tag.getTopicCount() + 1);
                this.baseMapper.updateById(tag);
            }
            tagList.add(tag);
        }
        return tagList;
    }

    @Override
    public Page<Post> selectTopicsByTagId(Page<Post> topicPage, String id) {

        // 获取关联的话题ID
        Set<String> ids = ITopicTagService.selectTopicIdsByTagId(id);
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Post::getId, ids);

        return IPostService.page(topicPage, wrapper);
    }

}
