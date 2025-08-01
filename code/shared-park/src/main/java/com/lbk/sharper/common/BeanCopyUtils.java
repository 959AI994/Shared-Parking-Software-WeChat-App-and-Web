package com.lbk.sharper.common;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 自定义拷贝工具类，用来拷贝list以及自定义转换方法等
 */
public class BeanCopyUtils extends BeanUtils {
    /**
     * 数据拷贝
     * @param source: 数据源类
     * @param target: 目标类::new(eg: UserDto::new)
     * @return
     */
    public static <S, T> T copyProperties(S source, Supplier<T> target) {
        T t = target.get();
        BeanUtils.copyProperties(source,t);
        return t;
    }
    /**
     * 数据拷贝
     * @param source: 数据源类
     * @param target: 目标类::new(eg: UserDto::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> T copyProperties(S source, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        T t = target.get();
        BeanUtils.copyProperties(source,t);
        if (callBack != null) {
            // 回调
            callBack.callBack(source, t);
        }
        return t;
    }
    /**
     * 集合数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserDto::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserDto::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }
}
