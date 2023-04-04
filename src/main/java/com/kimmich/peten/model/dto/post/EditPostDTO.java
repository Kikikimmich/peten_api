package com.kimmich.peten.model.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditPostDTO {
    private String id;
    private String title;
    // id 集， ;分割
    private String images;
    private String content;
    private String authorId;
    private String comments;
    private String like;
    private String groupId;
}
