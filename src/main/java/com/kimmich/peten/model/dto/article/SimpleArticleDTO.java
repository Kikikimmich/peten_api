package com.kimmich.peten.model.dto.article;

import com.kimmich.peten.model.dto.user.SimpleUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleArticleDTO {
    private String id;
    // 封面
    private String cover;
    // 话题标签
    // 标题
    private String title;

    private SimpleUserDTO authorInfo;
}
