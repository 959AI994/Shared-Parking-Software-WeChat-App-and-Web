package com.lbk.sharper.util;

import java.util.HashMap;


/**
 * Map工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
        //它调用了父类的 put 方法，
        // 将指定的键和值存储到 MapUtils 对象中。
        // 然后，它返回了 this，即当前的 MapUtils 对象，以支持链式调用。
    }
}
