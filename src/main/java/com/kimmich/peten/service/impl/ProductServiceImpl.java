package com.kimmich.peten.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.mapper.CategoryMapper;
import com.kimmich.peten.mapper.ProductMapper;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.common.PageInfo;
import com.kimmich.peten.model.entity.shop.Product;
import com.kimmich.peten.model.vo.product.ProductVO;
import com.kimmich.peten.service.ICategoryService;
import com.kimmich.peten.service.IProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    ProductMapper productMapper;
    @Resource
    ICategoryService categoryService;

    @Override
    public ListPageDTO<ProductVO> getProduct(Long page, Long pageSize, String categoryId) {
        IPage<ProductVO> pageInfo = new Page<>(page,pageSize);
        pageInfo = productMapper.getProductByCategoryId(pageInfo, categoryId);
        return ListPageDTO.<ProductVO>builder()
                .list(pageInfo.getRecords())
                .pageInfo(PageInfo.builder()
                        .page(page)
                        .pageSize(pageSize)
                        .totalRow(pageInfo.getTotal())
                        .build())
                .build();
    }
}
