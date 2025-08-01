package com.lbk.sharper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.common.Result;
import com.lbk.sharper.entity.NormalUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.req.UserAuthFrom;
import com.lbk.sharper.util.OpenIdJson;
import com.lbk.sharper.vo.UserVo;

/**
 * <p>
 * 普通用户表 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-6-8
 */
public interface INormalUserService extends IService<NormalUser> {

    /**
     * 分页获取用户信息
     * @param userVo
     * @return
     */
    IPage<NormalUser> getPageList(UserVo userVo);

    /**
     * 查询用户信息
     * @param openId
     * @return 用户信息对象
     */
    NormalUser getUserInfo(String openId);


    /**
     * 根据code获取openId
     * @param code
     * @return
     */
    OpenIdJson getOpenId(String code);

    /**
     * 查询用户信息
     * @param openId
     * @return
     */
    NormalUser getUser(String openId);


    /**
     * 用户完善信息
     * @param userAuthFrom
     * @return
     */
    boolean userAuth(UserAuthFrom userAuthFrom,String openId);


    boolean paymentBalance(String openId, Float balanceVar);

}
