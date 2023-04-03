package com.kimmich.peten.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.fetch.subphase.highlight.Highlighter;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Component
public class ESTest3 {

    private RestHighLevelClient client;

    public void getClient(){
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "http")));
    }



    public void matchDoc(){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米");
        commonSearch(queryBuilder);
    }


    public void matchAllDoc(){
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        commonSearch(queryBuilder);
    }

    /**
     * 含 分页 排序逻辑
     * @param queryBuilder
     */
    private void commonSearch(QueryBuilder queryBuilder) {
        //1，构建SearchRequest请求对象，指定索引库
        SearchRequest searchRequest = new SearchRequest("huizi");
        //2,构建SearchSourceBuilder查询对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //3，构建QueryBuilder对象指定查询方式和查询条件

        //4,将QuseryBuilder对象设置到SearchSourceBuilder对象中
        sourceBuilder.query(queryBuilder);

        //字段过滤
        sourceBuilder.fetchSource(new String[]{"title","price","band","category","id"},new String[]{"images"});
        //排序
        sourceBuilder.sort("price", SortOrder.DESC);
        //分页
        sourceBuilder.from(0);
        sourceBuilder.size(2);

        //5，将SearchSourceBuilder设置到SearchRequest中
        searchRequest.source(sourceBuilder);

        try {
            //6,调用方法查询数据
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            //7,解析返回结果
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (int i = 0; i < hits.length; i++) {
                System.out.println("返回的结果： " + hits[i].getSourceAsString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 高亮的处理以及解析
     */

    public void hightLight() {

        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米");

        //1，构建SearchRequest请求对象，指定索引库
        SearchRequest searchRequest = new SearchRequest("huizi");
        //2,构建SearchSourceBuilder查询对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<front color='red'>");
        highlightBuilder.postTags("</front>");
        highlightBuilder.field("title");
        sourceBuilder.highlighter(highlightBuilder);
        //4,将QuseryBuilder对象设置到SearchSourceBuilder对象中
        sourceBuilder.query(queryBuilder);

        //5，将SearchSourceBuilder设置到SearchRequest中
        searchRequest.source(sourceBuilder);

        try {
            //6,调用方法查询数据
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            //7,解析返回结果
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (int i = 0; i < hits.length; i++) {
                SearchHit hit = hits[i];
                System.out.println("返回的结果： " + hit.getSourceAsString());
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField highlightField = highlightFields.get("title");
                Text[] fragments = highlightField.getFragments();
                for (Text fragment : fragments) {
                    System.out.println("高亮的信息： "+fragment);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //多索引查询

    public void mulitIndexSearch(){
        SearchRequest searchRequest = new SearchRequest(new String[]{"huizi","huizi2"});
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "手机");
        matchQueryBuilder.minimumShouldMatch("80%");
        sourceBuilder.query(matchQueryBuilder);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            //7,解析返回结果
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (int i = 0; i < hits.length; i++) {
                System.out.println("返回的结果： " + hits[i].getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量查询
     */

    public void mulitIndexSearch2(){
        MultiSearchRequest request = new MultiSearchRequest();
        SearchRequest firstSearchRequest = new SearchRequest(new String[]{"huizi","huizi2"});
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("title", "小米"));
        firstSearchRequest.source(searchSourceBuilder);
        request.add(firstSearchRequest);
        SearchRequest secondSearchRequest = new SearchRequest(new String[]{"huizi","huizi2"});
        searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("band", "大米"));
        secondSearchRequest.source(searchSourceBuilder);
        request.add(secondSearchRequest);
        try {
            MultiSearchResponse multiSearchResponse = client.msearch(request, RequestOptions.DEFAULT);
            MultiSearchResponse.Item[] responses = multiSearchResponse.getResponses();
            for (MultiSearchResponse.Item respons : responses) {
                SearchHit[] hits = respons.getResponse().getHits().getHits();
                for (int i = 0; i < hits.length; i++) {
                    System.out.println("返回的结果： " + hits[i].getSourceAsString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 布尔组合查询
     */
    public void mulitIndexSearch3(){
        SearchRequest searchRequest = new SearchRequest("huizi");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        //名字-小米
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "小米").minimumShouldMatch("80%");
        //品牌-小米
        MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("band", "小米").operator(Operator.AND);
        //分类-化妆品
        MatchQueryBuilder matchQueryBuilder3 = QueryBuilders.matchQuery("category", "化妆品");
        //图片路径- jjj模糊
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("title", "小米");
        //价格 必须 2699
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("price", "2699");

        boolQueryBuilder.must(matchQueryBuilder);//必须
        boolQueryBuilder.must(termQueryBuilder);//必须
//        boolQueryBuilder.mustNot(matchQueryBuilder2);//不能
        boolQueryBuilder.should(matchQueryBuilder3);//可以
//        boolQueryBuilder.filter(fuzzyQueryBuilder);//结果中过滤

        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (int i = 0; i < hits.length; i++) {
                System.out.println("返回的结果： " + hits[i].getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close(){
        if(null!= client){
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
