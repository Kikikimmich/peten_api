package com.kimmich.peten.model.vo.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private String id;
    private String content;
    private String articleId;
    private UserVO from;
    private UserVO to;
    private String rootCommentId;
    private Date createTime;

    private List<CommentVO> reply;
}
