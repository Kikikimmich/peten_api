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

            /*

            这个公式的设计是基于时间衰减的热度计算方法。具体来说，文章的热度值不仅应该考虑文章当前的访问量和用户行为，还应该考虑到文章发布到现在的时间。
            因为随着时间的推移，文章的热度会逐渐降低，新发布的文章可能会更快地获得高热度值，而老旧的文章则需要靠用户行为维持热度。

            这个公式中的views、likes、favorites、comments参数分别代表了文章的浏览量、点赞数、收藏数、评论数，这些参数可以比较准确地反映出文章的受欢迎程度。
            同时，使用log10函数可以让访问量的影响更加平滑，避免访问量过大对热度值的影响过于显著。

            age参数表示文章发布到现在的小时数，使用age作为除数可以实现时间衰减。
            具体来说，age越大，热度值越小，从而使得老旧的文章热度变化更为缓慢。同时，我们也可以通过调整age参数的权重来控制热度值的增长速度，达到更好的效果。

            * */
            double hots =  (Math.log10(article.getViews()) + article.getLikes() + article.getFavorites() + article.getComments()) / age;

            article.setHots(hots);
        }

        articleService.saveOrUpdateBatch(list);
    }


}
