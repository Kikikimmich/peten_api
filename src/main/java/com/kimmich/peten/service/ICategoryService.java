package com.kimmich.peten.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kimmich.peten.model.entity.shop.Category;

import java.util.List;

public interface ICategoryService extends IService<Category> {

    List<Category> getList();

    void add(String name);
}
