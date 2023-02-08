package com.kimmich.peten.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.mapper.CategoryMapper;
import com.kimmich.peten.model.entity.shop.Category;
import com.kimmich.peten.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getList() {
        return categoryMapper.getList();
    }

    @Override
    @Transactional
    public void add(String name) {
        if (StrUtil.isBlank(name)){
            throw new ApiException("参数不能为空！");
        }
        Category category = Category.builder().name(name).build();
        baseMapper.insert(category);
    }
}
