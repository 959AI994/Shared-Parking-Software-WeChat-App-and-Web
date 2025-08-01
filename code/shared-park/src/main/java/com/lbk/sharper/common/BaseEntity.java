package com.lbk.sharper.common;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: wjx
 * @Date: 2023/5/25 22:18
 * @Description
 * @Version 1.0
 */
@Data
public class BaseEntity implements Serializable {

    private String name;
    private Integer page = 1;
    private Integer limit = 10;
    private LocalDateTime createTime;

}
