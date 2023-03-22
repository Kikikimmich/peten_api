package com.kimmich.peten.webSocket.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonReplyDTO {
    private Integer code;
    private String msg;
    private CommonMessageDTO data;


    public static String succeed(CommonMessageDTO data) {
        return JSON.toJSONString(CommonReplyDTO.builder()
                .data(data)
                .msg("succeed")
                .code(200).build());
    }
}
