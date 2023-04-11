package com.kimmich.peten.task;

import com.kimmich.peten.model.entity.Article;
import com.kimmich.peten.service.IArticleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ArticleTask {

    @Resource
    IArticleService articleService;

    // 计算热度，每小时执行一遍。
    @Scheduled(cron = "0 0 * * * *")
    public void executeArticleHot() {
        // 只计算最近三天的发布的热度。
        List<Article> list = articleService.getTopHop(-1);
        if (list == null || list.isEmpty()) {
            return;
        }

        for (Article article : list) {

            double age = TimeUnit.MILLISECONDS.toHours(new Date().getTime() - article.getCreateTime().getTime());

            double hots =  (Math.log10(article.getViews()) + article.getLikes() + article.getFavorites() + article.getComments()) / age;

            article.setHots(hots);
        }

        articleService.saveOrUpdateBatch(list);
    }


}
