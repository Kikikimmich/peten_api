package com.kimmich.peten.webSocket.dto;

import com.kimmich.peten.model.dto.comment.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTypeDTO {
    private Integer type;
    private CommonMessageDTO message;
}
