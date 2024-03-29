package com.kimmich.peten.model.dto.post;

import com.kimmich.peten.model.dto.group.GroupDTO;
import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String id;
    private String title;
    private Map<String, String> images;
    private String content;
    private SimpleUserDTO author;
    private String comments;
    private String like;
    private GroupDTO group;

}
