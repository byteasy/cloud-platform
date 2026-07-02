package com.byteasy.platform.dto;

import com.google.common.collect.Lists;
import com.byteasy.platform.entity.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceDTO extends Resource {

    private String appName;
    private String parentTitle;
    private String parentName;
    private List<ResourceDTO> childrenList = Lists.newArrayList();
}
