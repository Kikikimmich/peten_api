package com.kimmich.peten.es.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoBO {
    private Long page;
    private Long pageSize;
    private Long totalRow;
}
