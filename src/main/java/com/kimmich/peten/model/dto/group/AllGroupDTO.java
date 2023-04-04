package com.kimmich.peten.model.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllGroupDTO {
    private String id;
    private String name;
    private String total;
    private List<GroupDTO> groups;
}
