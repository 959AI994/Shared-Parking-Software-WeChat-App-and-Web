package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbk.sharper.entity.AccessRecords;
import com.lbk.sharper.mapper.AccessRecordsMapper;
import com.lbk.sharper.res.ResAccessRecordsVo;
import com.lbk.sharper.service.IAccessRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.vo.AccessRecordsVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入小区记录 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-5-14
 */
@Service
public class AccessRecordsServiceImpl extends ServiceImpl<AccessRecordsMapper, AccessRecords> implements IAccessRecordsService {

    @Override
    public IPage<ResAccessRecordsVo> getPageList(AccessRecordsVo accessRecordsVo) {
        IPage<AccessRecordsVo> page = new Page<>(accessRecordsVo.getCurrent(), accessRecordsVo.getPageSize());
        return baseMapper.getAccessListPage(page, accessRecordsVo);
    }

    @Override
    public boolean add(AccessRecordsVo accessRecordsVo) {
        return this.save(accessRecordsVo);
    }

    @Override
    public boolean updates(AccessRecordsVo accessRecordsVo) {
        return this.updateById(accessRecordsVo);
    }

    @Override
    public boolean delete(AccessRecordsVo accessRecordsVo) {
        return this.removeById(accessRecordsVo.getAccessRecordsId());
    }
}
