package com.kimmich.peten.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.constant.CommonConst;
import com.kimmich.peten.mapper.ProductMapper;
import com.kimmich.peten.model.bo.shop.ProductBO;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.common.PageInfo;
import com.kimmich.peten.model.entity.shop.Product;
import com.kimmich.peten.model.vo.product.ProductVO;
import com.kimmich.peten.service.ICategoryService;
import com.kimmich.peten.service.IProductService;
import com.kimmich.peten.wrapper.ProductWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    ProductMapper productMapper;

    @Resource
    ProductWrapper productWrapper;

    @Resource
    ICategoryService categoryService;

    @Override
    public Integer getStockForUpdate(String productId) {
        return productMapper.getStockForUpdate(productId);
    }

    @Override
    public ProductVO getDetails(String productId) {
        if (StrUtil.isBlank(productId)){
            throw new ApiException("参数不能为空！");
        }
        ProductBO productBO = productMapper.getProductById(productId);
        if (ObjectUtil.isNull(productBO) || productBO.getStatus() != CommonConst.STATUS_ON){
            throw new ApiException("商品不存在或已下架！");
        }

        // 两个实体对象没有什么不同，主要是增加了 images 属性的处理
        ProductVO product = ProductVO.builder().build();
        BeanUtils.copyProperties(productBO, product);

        String images = productBO.getImages();
        List<String> imagesList = new ArrayList<>();
        if (StrUtil.isNotBlank(images)){
            String[] strings = images.split(";");
            imagesList = Arrays.asList(strings);
        }
        product.setImages(imagesList);
        return product;
    }

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

    @Override
    public List<ProductVO> getProduct(List<String> productId) {
        List<Product> list = productWrapper.getProduct(productId);
        List<ProductVO> voList = new ArrayList<>();
        for (Product product : list) {
            ProductVO productVO = ProductVO.builder().build();
            BeanUtils.copyProperties(product, productVO, "images");
            voList.add(productVO);
        }
        return voList;
    }

    @Override
    public ProductVO getProduct(String productId) {
        Product product = productWrapper.getProduct(productId);
        ProductVO productVO = ProductVO.builder().build();
        BeanUtils.copyProperties(product, productVO, "images");
        return productVO;
    }
}
