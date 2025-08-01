package com.lbk.sharper.mapper;

import com.lbk.sharper.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统权限 Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-6-4
 * */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
