package com.kimmich.peten.model.dto.content;

import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {
    private String id;
    private String cover;
    private String title;
    private SimpleUserDTO author;
    private Integer contentType;
    private String introduction;
    private Integer hits;
    private Integer comments;
    private String postTime;
    private String videoLength;
}
