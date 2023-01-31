package com.kimmich.peten.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.ImageURLMapper;
import com.kimmich.peten.model.entity.ImageURL;
import com.kimmich.peten.service.IImageURLService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ImageURLServiceImpl extends ServiceImpl<ImageURLMapper, ImageURL> implements IImageURLService {

    @Resource
    ImageURLMapper imageURLMapper;

    @Override
    public String getURL(String md5) {
        QueryWrapper<ImageURL> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .select(ImageURL::getUrl)
                .eq(ImageURL::getMd5, md5);
        ImageURL imageURL = imageURLMapper.selectOne(queryWrapper);
        String url = null;
        if (ObjectUtil.isNotNull(imageURL)){
            url = imageURL.getUrl();
        }
        return url;
    }

    @Override
    public void add(String md5, String url) {
        ImageURL imageURL = ImageURL.builder()
                .md5(md5)
                .url(url)
                .build();
        imageURLMapper.insert(imageURL);
    }
}
