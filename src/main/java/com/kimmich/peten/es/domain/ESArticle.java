package com.kimmich.peten.es.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="article")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ESArticle  implements Persistable<String> {

    //@Id 文档主键 唯一标识
    @Id
    private String id;
    @Field(index=true,analyzer="ik_smart",store=true,searchAnalyzer="ik_smart",type = FieldType.Text)
    private String title;
    @Field(index=true,analyzer="ik_smart",store=true,searchAnalyzer="ik_smart",type = FieldType.Text)
    private String content;

    @Override
    public boolean isNew() {
        return null == id;
    }
}