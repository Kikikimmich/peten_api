package com.kimmich.peten;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ESTest {

    @Autowired
    RestHighLevelClient highLevelClient;

    @Test
    public void testESClient() throws IOException {
        GetRequest getRequest= new GetRequest("kibana_sample_data_ecommerce", "V5z1f28BdseAsPClo7bC");
        GetResponse getResponse = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getIndex());
        System.out.println(getResponse.toString());
    }

}
