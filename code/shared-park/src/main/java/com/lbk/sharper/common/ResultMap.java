package com.lbk.sharper.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author LBK
 * @Description 接口返回对象
 * @Date 2021-08-03
 * @Time 22:25
 */
@Component
public class ResultMap extends HashMap<String, Object> {
    public ResultMap() {
    }

    public ResultMap success() {
        this.put("result", "success");
        return this;
    }

    public ResultMap fail() {
        this.put("result", "fail");
        return this;
    }

    public ResultMap code(int code) {
        this.put("code", code);
        return this;
    }

    public ResultMap message(Object message) {
        this.put("message", message);
        return this;
    }
}

