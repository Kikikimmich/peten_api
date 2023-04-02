package com.kimmich.peten.model.dto.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentResultDTO {
    private String id;
    private String cover;
    private String title;
    private String author;
    private String type;
    private String introduction;
    private String hits;
    private String comments;
    private String postTime;
    private String videoLength;
}
