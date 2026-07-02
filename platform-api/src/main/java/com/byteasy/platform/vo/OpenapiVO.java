package com.byteasy.platform.vo;

import com.byteasy.annotations.FieldDisplay;
import com.byteasy.dto.Column;
import com.byteasy.platform.entity.Openapi;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@FieldDisplay(name = "包含参数明细表的API定义")
public class OpenapiVO extends Openapi {

    @FieldDisplay(showOrder = 107, name = "Http Header Params")
    private List<Column> headerParams;
    @FieldDisplay(showOrder = 108, name = "Http Query Params")
    private List<Column> queryParams;
    @FieldDisplay(showOrder = 109, name = "Http Form Params")
    private List<Column> formParams;
}
