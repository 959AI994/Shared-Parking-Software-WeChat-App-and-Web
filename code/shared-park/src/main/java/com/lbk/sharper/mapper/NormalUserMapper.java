package com.lbk.sharper.mapper;

import com.lbk.sharper.entity.NormalUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 普通用户表 Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-5-13
 */
@Mapper
public interface NormalUserMapper extends BaseMapper<NormalUser> {


    Boolean paymentBalance(String openId, Float balanceVar);

}
