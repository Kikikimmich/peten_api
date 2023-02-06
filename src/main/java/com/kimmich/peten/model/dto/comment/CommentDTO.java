package com.kimmich.peten.model.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String content;
    private String articleId;
    private String rootComment;
    private Integer type;
}
