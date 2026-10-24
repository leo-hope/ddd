package com.runssnail.ddd.demo.infra.dataobject;

import com.runssnail.ddd.common.dto.BaseDTO;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 15:16
 **/
@Data
public class ProductDO extends BaseDTO {
    private String id;

    private String name;

    private String description;

    private Integer status;

    private boolean deleted;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 最近操作人
     */
    private String operator;
}
