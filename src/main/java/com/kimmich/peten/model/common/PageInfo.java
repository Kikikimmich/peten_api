package com.kimmich.peten.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
    private Long page;
    private Long pageSize;
    private Long totalRow;
}
