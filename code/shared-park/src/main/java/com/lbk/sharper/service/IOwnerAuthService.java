package com.lbk.sharper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.common.Result;
import com.lbk.sharper.entity.OwnerAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.vo.OwnerAuthVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 业主信息表 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
public interface IOwnerAuthService extends IService<OwnerAuth> {
    /**
     * 分页查询小区停车场信息列表
     * @param  ownerAuthVo
     * @return IPage
     */
    IPage<OwnerAuth> getPageList(OwnerAuthVo ownerAuthVo);


    /**
     * 查询全部小区停车场信息列表
     * @return List
     */
    List<OwnerAuth> getList();


    /**
     * 查询用户是否是业主
     * @return
     */
    OwnerAuth getOwnerAuth(String openId);

    /**
     * 增加业主信息
     * @param  ownerAuthVo
     * @return boolean
     */
    boolean add(OwnerAuthVo ownerAuthVo,String openId);

    /**
     * 修改小区停车场信息
     * @param  ownerAuthVo
     * @return boolean
     */
    boolean updates(OwnerAuthVo ownerAuthVo);

    /**
     * 认证业主信息
     * @param  ownerAuthVo
     * @return boolean
     */
    boolean arrces(OwnerAuthVo ownerAuthVo);


    /**
     * 删除小区停车场信息
     * @param  ownerAuthVo
     * @return boolean
     */
    boolean delete(OwnerAuthVo ownerAuthVo);

    /**
     * 上传业主证明图片
     * @param files
     * @return
     */
    Result uploadImg(MultipartFile files);

}
