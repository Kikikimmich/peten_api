package com.kimmich.peten.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.mapper.ShoppingCartMapper;
import com.kimmich.peten.model.entity.shop.ShoppingCart;
import com.kimmich.peten.model.vo.product.ProductVO;
import com.kimmich.peten.model.vo.shoppingCart.ShoppingCartVO;
import com.kimmich.peten.service.IProductService;
import com.kimmich.peten.service.IShoppingCartService;
import com.kimmich.peten.utils.CommonUtil;
import com.kimmich.peten.wrapper.ShoppingCartWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements IShoppingCartService {

    @Resource
    ShoppingCartMapper shoppingCartMapper;

    @Resource
    ShoppingCartWrapper shoppingCartWrapper;

    @Resource
    IProductService productService;

    @Override
    @Transactional
    public void updateShoppingCart(String userId, String productId, Integer count) {
        if (!CommonUtil.notNull(userId, productId)){
            throw new ApiException("参数不完整");
        }
        ShoppingCart shoppingCart = shoppingCartWrapper.getShoppingCart(userId, productId);
        ProductVO product = productService.getProduct(productId);
        if (ObjectUtil.isNull(shoppingCart) || ObjectUtil.isNull(product) || count <= 0 || count > product.getLimitCount()){
            throw new ApiException("参数不合法");
        }
        shoppingCart.setCount(count);
        this.saveOrUpdate(shoppingCart);
    }

    @Override
    @Transactional
    public void deleteShoppingCart(String id) {
        if (StrUtil.isBlank(id)){
            throw new ApiException("参数不完整");
        }
        shoppingCartMapper.deleteById(id);
    }


    @Override
    public List<ShoppingCartVO> getShoppingCart(String userId) {
        if (StrUtil.isBlank(userId)){
            throw new ApiException("参数不完整");
        }
        // 1. 获取购物车
        List<ShoppingCart> shoppingCart = shoppingCartWrapper.getShoppingCart(userId);
        Map<String, ShoppingCart> map = shoppingCart.stream().collect(Collectors.toMap(ShoppingCart::getProductId, Function.identity()));

        List<String> productIdList = shoppingCart.stream().map(ShoppingCart::getProductId).distinct().collect(Collectors.toList());

        // 2. 获取商品信息
        List<ProductVO> productList = productService.getProduct(productIdList);
        Map<String, ProductVO> productMap = productList.stream().collect(Collectors.toMap(ProductVO::getId, Function.identity()));

        List<ShoppingCartVO> result = new ArrayList<>();
        for (String id : productIdList) {
            ProductVO product = productMap.get(id);
            ShoppingCart cart = map.get(product.getId());

            ShoppingCartVO vo = ShoppingCartVO.builder()
                    .id(cart.getId())
                    .productId(product.getId())
                    .productName(product.getName())
                    .productCover(product.getCover())
                    .price(product.getPrice())
                    .specialPrice(product.getSpecialPrice())
                    .count(cart.getCount())
                    .limitCount(product.getLimitCount())
                    .build();
            result.add(vo);
        }
        return result;
    }

    @Override
    @Transactional
    public ShoppingCartVO addShoppingCart(String userId, String productId) {

        if (!CommonUtil.notNull(userId, productId)){
            throw new ApiException("参数不完整！");
        }
        ShoppingCart shoppingCart = shoppingCartWrapper.getShoppingCart(userId, productId);

        ProductVO product = productService.getProduct(productId);

        if (ObjectUtil.isNull(shoppingCart) || StrUtil.isBlank(shoppingCart.getId())){
            shoppingCart = ShoppingCart.builder()
                    .userId(userId)
                    .productId(productId)
                    .createTime(new Date())
                    .build();
        }else {

            // 我认为，这里出现脏读的概率极低，对业务影响不大
            if (shoppingCart.getCount() < product.getLimitCount()){
                shoppingCart.setCount(shoppingCart.getCount() + 1);
            }
        }
        saveOrUpdate(shoppingCart);

        // 这里为了适配前端，返回购物车信息
        // todo 添加购物车优化
        return ShoppingCartVO.builder()
                .id(shoppingCart.getId())
                .productId(productId)
                .productName(product.getName())
                .productCover(product.getCover())
                .price(product.getPrice())
                .specialPrice(product.getSpecialPrice())
                .count(shoppingCart.getCount())
                .limitCount(product.getLimitCount())
                .build();
    }
}
