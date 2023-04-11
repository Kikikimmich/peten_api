package com.kimmich.peten.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimmich.peten.common.exception.ApiException;
import com.kimmich.peten.mapper.ArticleMapper;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.bo.article.ListBO;
import com.kimmich.peten.model.common.PageInfo;
import com.kimmich.peten.model.dto.article.ArticleDTO;
import com.kimmich.peten.model.entity.Article;
import com.kimmich.peten.service.IArticleService;
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
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Resource
    ArticleMapper articleMapper;
    @Resource
    ArticleWrapper articleWrapper;

    @Override
    // 获取近三天的最热们的前 {limit} 条
    public List<Article> getTopHop(Integer limit) {

        if (limit == null || limit <= 0){
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

        // 目前他们的属性是一样的，将来一定不同
        BeanUtils.copyProperties(article, result);
        return result;
    }

    @Override
    public ListBO getList(Long page, Long pageSize, String query) {
        // todo query 搞定查询条件
        IPage<ArticleBO> pageInfo = new Page<>(page, pageSize);
        IPage<ArticleBO> res = articleMapper.getList(pageInfo, query);
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
    public void add(ArticleDTO dto,  String authorId) {
        if (!CommonUtil.notNull(dto.getContent(), dto.getTitle(), dto.getCover(), dto.getTags())){
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
