package com.kimmich.peten.model.bo.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentBO {
    private String id;
    private String content;
    private String userId;
    private String articleId;
    private String rootCommentId;
    private Date createTime;
}
