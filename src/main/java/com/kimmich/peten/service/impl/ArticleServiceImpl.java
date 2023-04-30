package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.algorithm.nlp.FeatureExtractor;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.mapper.ArticleMapper;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.bo.article.ListBO;
import com.kimmich.peten.model.common.PageInfo;
import com.kimmich.peten.model.dto.article.ArticleDTO;
import com.kimmich.peten.model.dto.article.FeatureMapBO;
import com.kimmich.peten.model.dto.article.SimpleArticleDTO;
import com.kimmich.peten.model.entity.Article;
import com.kimmich.peten.service.IArticleService;
import com.kimmich.peten.service.IUserService;
import com.kimmich.peten.utils.CommonUtil;
import com.kimmich.peten.utils.DateUtil;
import com.kimmich.peten.wrapper.ArticleWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Resource
    ArticleMapper articleMapper;
    @Resource
    ArticleWrapper articleWrapper;

    @Resource
    IUserService userService;

    @Override
    public List<SimpleArticleDTO> recommend(String id) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("LIMIT 100");
        List<Article> list = list(queryWrapper);
        StringBuilder sb = new StringBuilder();
        for (Article article : list) {
            sb.append(article.getTitle());
            sb.append(article.getContent());
        }
        String contentGroup = sb.toString();

        Map<String, Map<String, Double>> articleFeatureMap = new LinkedHashMap<>();

        for (Article article : list) {
            Map<String, Double> map = FeatureExtractor.extractFeatures(article.getTitle() + article.getContent(), contentGroup, list.size());
            articleFeatureMap.put(article.getId(), map);
        }

        // 本文特征
        Map<String, Double> myFeature = articleFeatureMap.get(id);
        articleFeatureMap.remove(id);

        List<FeatureMapBO> similarityList = new ArrayList<>();

        for (Map.Entry<String, Map<String, Double>> entry : articleFeatureMap.entrySet()) {
            String key = entry.getKey();
            Map<String, Double> value = entry.getValue();

            double similarity = FeatureExtractor.cosineSimilarity(myFeature, value);
            similarityList.add(FeatureMapBO.builder()
                    .id(key)
                    .similarity(similarity)
                    .build());
        }

        similarityList = similarityList.stream().sorted(Comparator.comparingDouble(FeatureMapBO::getSimilarity).reversed()).collect(Collectors.toList());

        Map<String, Article> map = list.stream().collect(Collectors.toMap(Article::getId, Function.identity()));
        List<SimpleArticleDTO> result = new ArrayList<>();
        for (FeatureMapBO bo : similarityList) {
            if (Objects.equals(id, bo.getId())){
                continue;
            }
            Article article = map.get(bo.getId());
            result.add(SimpleArticleDTO.builder()
                    .id(bo.getId())
                    .cover(article.getCover())
                    .title(article.getTitle())
                    .authorInfo(userService.getSimpleInfo(article.getAuthor()))
                    .build());
        }
        if (result.size() > 10){
            return result.subList(0, 10);
        }
        return result;
    }

    @Override
    // 获取近三天的最热们的前 {limit} 条
    public List<Article> getTopHop(Integer limit) {

        if (limit == null || limit <= 0) {
            limit = Integer.MAX_VALUE;
        }

        // 三天前
        String date = LocalDate.now().minusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.le(Article::getCreateTime, date).orderByDesc(Article::getHots).last("limit " + limit);

        return list(queryWrapper);
    }

    @Override
    public Boolean exists(String id) {
        return articleWrapper.exists(id);
    }

    @Override
    public ArticleBO getDetail(String id) {
        Article article = articleMapper.selectById(id);
        ArticleBO result = new ArticleBO();


        // 更新浏览记录, 这里图个方便，肯定是不合理的
        // todo
        article.setViews(article.getViews() + 1);
        saveOrUpdate(article);

        // 目前他们的属性是一样的，将来一定不同
        BeanUtils.copyProperties(article, result);
        result.setAuthorInfo(userService.getSimpleInfo(article.getAuthor()));
        result.setCreateTime(article.getCreateTime().toString());
        return result;
    }

    @Override
    public ListBO getList(Long page, Long pageSize, String query) {
        // todo query 搞定查询条件
        IPage<ArticleBO> pageInfo = new Page<>(page, pageSize);
        IPage<ArticleBO> res = articleMapper.getList(pageInfo, query);
        List<ArticleBO> list = res.getRecords();
        for (ArticleBO bo : list) {
            bo.setAuthorInfo(userService.getSimpleInfo(bo.getAuthor()));
        }
        return ListBO.builder()
                .list(res.getRecords())
                .pageInfo(PageInfo.builder()
                        .page(page)
                        .pageSize(pageSize)
                        .totalRow(res.getTotal())
                        .build())
                .build();
    }


    @Override
    @Transactional
    public void add(ArticleDTO dto, String authorId) {
        if (!CommonUtil.notNull(dto.getContent(), dto.getTitle(), dto.getCover(), dto.getTags())) {
            throw new ApiException("参数不能为空");
        }

        Article article = Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(authorId)
                .cover(dto.getCover())
                .createTime(new Date())
                .build();
        this.save(article);
    }
}
