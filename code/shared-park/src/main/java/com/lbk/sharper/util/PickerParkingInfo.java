package com.lbk.sharper.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: WJX
 * @Date: 2023/6/11 16:23
 * @Description 停车场下拉组件
 * @Version 1.0
 */
@Data
public class PickerParkingInfo implements Serializable {
    private String label;
    private Integer value;
}
