package com.kimmich.peten.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.kimmich.peten.es.bo.ArticleSearchBO;
import com.kimmich.peten.es.bo.PageInfoBO;
import com.kimmich.peten.es.domain.ESArticle;
import com.kimmich.peten.es.service.ESArticleService;
import com.kimmich.peten.mapper.ArticleMapper;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.common.PageInfo;
import com.kimmich.peten.model.dto.content.ContentResultDTO;
import com.kimmich.peten.model.dto.user.SearchUserDTO;
import com.kimmich.peten.model.entity.Article;
import com.kimmich.peten.model.vo.product.ProductVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Resource
    ESArticleService esArticleService;

    // 小心循环依赖
    @Resource
    ArticleMapper articleMapper;

    @Resource
    IArticleService articleService;

    @Resource
    IProductService productService;


    @Deprecated
    public Object commonSearch(String query, Integer type, Long page, Long pageSize) {

        return null;
    }

    // 通过用户名查询用户
    public ListPageDTO<SearchUserDTO> searchUser(String keyword, Integer page, Integer pageSize){
        // todo
        return null;
    }

    public ListPageDTO<ArticleBO> searchContent(String keyword, Integer page, Integer pageSize) {
        if (StrUtil.isBlank(keyword)) {
            return ListPageDTO.<ArticleBO>builder()
                    .list(new ArrayList<>())
                    .pageInfo(PageInfo.builder()
                            .page(page.longValue())
                            .pageSize(pageSize.longValue())
                            .totalRow(0L)
                            .build())
                    .build();
        }

        ArticleSearchBO articleSearchBO = esArticleService.searchByTitle(keyword, page, pageSize);
        if (ObjectUtil.isNull(articleSearchBO)) {
            return ListPageDTO.<ArticleBO>builder()
                    .list(new ArrayList<>())
                    .pageInfo(PageInfo.builder()
                            .page(page.longValue())
                            .pageSize(pageSize.longValue())
                            .totalRow(0L)
                            .build())
                    .build();
        }

        List<ESArticle> list = articleSearchBO.getList();
        PageInfoBO pageInfo = articleSearchBO.getPageInfo();

        if (list == null || list.isEmpty()) {
            return ListPageDTO.<ArticleBO>builder()
                    .list(new ArrayList<>())
                    .pageInfo(PageInfo.builder()
                            .page(page.longValue())
                            .pageSize(pageSize.longValue())
                            .totalRow(0L)
                            .build())
                    .build();
        }

        List<String> idList = list.stream().map(ESArticle::getId).collect(Collectors.toList());
        Map<String, ESArticle> articleMap = list.stream().collect(Collectors.toMap(ESArticle::getId, Function.identity(), (o1, o2) -> o1, LinkedHashMap::new));

        List<Article> articles = articleService.listByIds(idList);

        List<ArticleBO> result = new ArrayList<>();

        for (Article article : articles) {
            ESArticle esArticle = articleMap.get(article.getId());

            ArticleBO detail = articleService.getDetail(esArticle.getId());
            detail.setTitle(esArticle.getTitle());
            result.add(detail);
        }

        return ListPageDTO.<ArticleBO>builder()
                .list(result)
                .pageInfo(PageInfo.builder()
                        .page(page.longValue())
                        .pageSize(pageSize.longValue())
                        .totalRow(pageInfo.getTotalRow())
                        .build())
                .build();
    }

    public ListPageDTO<ProductVO> searchGoods(String keyword, Integer page, Integer pageSize) {
        return productService.getProductByKeyword(keyword, (long) page, (long) pageSize);
    }
}
