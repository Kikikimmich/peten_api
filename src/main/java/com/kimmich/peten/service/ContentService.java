package com.kimmich.peten.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimmich.peten.constant.ContentConst;
import com.kimmich.peten.mapper.ArticleMapper;
import com.kimmich.peten.model.bo.article.ArticleBO;
import com.kimmich.peten.model.bo.article.ListBO;
import com.kimmich.peten.model.common.ListPageDTO;
import com.kimmich.peten.model.common.PageInfo;
import com.kimmich.peten.model.dto.content.ContentDTO;
import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import com.kimmich.peten.model.entity.Article;
import com.kimmich.peten.wrapper.ArticleWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ContentService {

    @Resource
    ArticleWrapper articleWrapper;

    @Resource
    IUserService userService;

    public ListPageDTO<ContentDTO> getList(Long page, Long pageSize, Integer contentType, String author) {

        List<ContentDTO> contentList = new ArrayList<>();

        // 其他暂时不支持
        if (Objects.equals(contentType, ContentConst.TYPE_ARTICLE)) {
            Page<Article> list = articleWrapper.getList(page, pageSize, author);
            List<Article> records = list.getRecords();
            for (Article article : records) {

                SimpleUserDTO authorInfo = userService.getSimpleInfo(article.getAuthor());

                contentList.add(ContentDTO.builder()
                        .id(article.getId())
                        .cover(article.getCover())
                        .title(article.getTitle())
                        .author(authorInfo)
                        .contentType(contentType)
                        // todo
                        .introduction("这是一个简介")
                        .hits(0)
                        .comments(0)
                        .postTime(article.getCreateTime().toString())
                        .videoLength("")
                        .build());
            }

            return ListPageDTO.<ContentDTO>builder()
                    .list(contentList)
                    .pageInfo(PageInfo.builder()
                            .page(page)
                            .pageSize(pageSize)
                            .totalRow(list.getTotal())
                            .build())
                    .build();
        }


        return null;
    }
}
