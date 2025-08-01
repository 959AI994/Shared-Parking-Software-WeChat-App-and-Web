package com.lbk.sharper.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.entity.AccessRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbk.sharper.res.ResAccessRecordsVo;
import com.lbk.sharper.res.ResParkingOrderVO;
import com.lbk.sharper.vo.AccessRecordsVo;
import com.lbk.sharper.vo.ParkingOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 出入小区记录 Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-5-25
 */
@Mapper
public interface AccessRecordsMapper extends BaseMapper<AccessRecords> {
    /**
     * 分页查询出入记录
     * @param page
     * @param dto
     * @return
     */
    IPage<ResAccessRecordsVo> getAccessListPage(IPage<AccessRecordsVo> page, @Param("dto") AccessRecordsVo dto);

}
