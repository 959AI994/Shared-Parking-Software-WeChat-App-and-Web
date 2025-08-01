package com.lbk.sharper.res;

import lombok.Data;

/**
 * @Author: WJX
 * @Date: 2023/5/26 21:57
 * @Description 查收订单报表查询
 * @Version 1.0
 */
//这个类的作用是传递订单报告的数据。它包含了两个字段 months 和 numbers，分别表示月份和数量。
//此类还包含一个构造函数，接受月份和数量作为参数，并将它们赋值给对应的字段。
@Data
public class ResOrderReportVo {
    private Integer months;

    private Integer numbers;


    public ResOrderReportVo(int months, int numbers) {
        this.months = months;
        this.numbers = numbers;
    }
}
