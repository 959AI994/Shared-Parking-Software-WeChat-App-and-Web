package com.lbk.sharper.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: WJX
 * @Date: 2023/5/24 22:50
 * @Description 查收财务报表查询
 * @Version 1.0
 */
@Data
public class ResReportVo implements Serializable {

    private Integer months;

    private Double outcome;


    public ResReportVo(int months, Double outcome) {
        this.months = months;
        this.outcome = outcome;
    }
}
