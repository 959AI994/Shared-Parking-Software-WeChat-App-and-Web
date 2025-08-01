package com.lbk.sharper.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wjx
 * @Date: 2023/5/14 22:39
 * @Description
 * @Version 1.0
 */
@Data
public class ReportConditionVo implements Serializable {
    /**
     * 年份
     */
    private String year;

    /**
     * 停车场ID
     */
    private Integer parkingId;


}
