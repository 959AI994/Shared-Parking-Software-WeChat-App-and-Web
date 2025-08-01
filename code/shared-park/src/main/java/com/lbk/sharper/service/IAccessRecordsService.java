package com.lbk.sharper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.entity.AccessRecords;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.res.ResAccessRecordsVo;
import com.lbk.sharper.vo.AccessRecordsVo;

/**
 * <p>
 * 出入小区记录 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
public interface IAccessRecordsService extends IService<AccessRecords> {
    /**
     * 分页查询出入小区记录列表
     * IPage是MyBatis Plus框架提供的一个接口，用于包装分页查询的结果。
     * @param  accessRecordsVo
     * @return IPage
     */
    IPage<ResAccessRecordsVo> getPageList(AccessRecordsVo accessRecordsVo);

    /**
     * 修改出入小区记录信息
     * @param  accessRecordsVo
     * @return boolean
     */
    boolean add(AccessRecordsVo accessRecordsVo);

    /**
     * 参数是AccessRecordsVo对象，包含了要添加的记录的所有信息。
     * 如果添加成功，方法会返回true
     * 修改出入小区记录信息
     * @param  accessRecordsVo
     * @return boolean
     */
    boolean updates(AccessRecordsVo accessRecordsVo);

    /**
     * 删除出入小区记录信息
     * @param  accessRecordsVo
     * @return boolean
     */
    boolean delete(AccessRecordsVo accessRecordsVo);

}
