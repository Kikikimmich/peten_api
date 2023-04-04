package com.kimmich.peten.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.ImageURLMapper;
import com.kimmich.peten.model.entity.ImageURL;
import com.kimmich.peten.service.IImageURLService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImageURLServiceImpl extends ServiceImpl<ImageURLMapper, ImageURL> implements IImageURLService {

    @Resource
    ImageURLMapper imageURLMapper;

    @Override
    public Map<String, String> getURLById(List<String> id) {
        if (CollectionUtil.isEmpty(id)){
            return new LinkedHashMap<>();
        }
        List<ImageURL> imageURLs = this.listByIds(id);
        if (CollectionUtil.isEmpty(imageURLs)){
            return new LinkedHashMap<>();
        }
        return imageURLs.stream().collect(Collectors.toMap(ImageURL::getId, ImageURL::getUrl, (o1, o2)->o2, LinkedHashMap::new));
    }

    @Override
    public String getURLById(String id) {
        if (StrUtil.isBlank(id)) {
            return "";
        }
        ImageURL imageURL = imageURLMapper.selectById(id);
        if (ObjectUtil.isNull(imageURL)){
            return "";
        }
        return imageURL.getUrl();
    }

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
