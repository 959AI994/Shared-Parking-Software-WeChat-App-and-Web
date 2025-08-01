package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbk.sharper.common.BeanCopyUtils;
import com.lbk.sharper.common.CustomException;
import com.lbk.sharper.common.ResponseEnums;
import com.lbk.sharper.config.WechatConfig;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.mapper.NormalUserMapper;
import com.lbk.sharper.req.UserAuthFrom;
import com.lbk.sharper.service.INormalUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.util.HttpUtil;
import com.lbk.sharper.util.OpenIdJson;
import com.lbk.sharper.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 普通用户表 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-6-1
 */
@Slf4j
@Service
public class NormalUserServiceImpl extends ServiceImpl<NormalUserMapper, NormalUser> implements INormalUserService {

    @Override
    public IPage<NormalUser> getPageList(UserVo userVo) {

        IPage<NormalUser> page = new Page<>(userVo.getCurrent(), userVo.getPageSize());
        QueryWrapper<NormalUser> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(userVo.getOpenId())){
            queryWrapper.eq("openId",userVo.getOpenId());
            //queryWrapper.eq("openId", userVo.getOpenId()) 时，就是在创建一个查询条件，
            // 表示要查找数据库中 openId 等于 userVo.getOpenId() 的记录。
        }
        if (!StringUtils.isEmpty(userVo.getNickName())){
            queryWrapper.like("nickName",userVo.getNickName());
        }
        if (!StringUtils.isEmpty(userVo.getPhone())){
            queryWrapper.eq("phone",userVo.getPhone());
        }
        if (!StringUtils.isEmpty(userVo.getState())){
            queryWrapper.eq("state",userVo.getState());
        }
        return this.page(page, queryWrapper);
    }

    @Override
    ////这个方法是通过openId获取用户信息，调用了父类的getById方法。
    public NormalUser getUserInfo(String openId) {
        return this.getById(openId);
    }

    @Override
    //这个方法用于通过微信的code获取openId，
    // 主要流程是通过HTTP请求向微信服务器发送获取openId的请求，并处理返回的JSON数据。
    public OpenIdJson getOpenId(String code) {
        String result = "";
        try{//请求微信服务器，用code换取openid
            result = HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + WechatConfig.APPID + "&secret="
                            + WechatConfig.SECRET + "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = new OpenIdJson();
        try { //处理返回的结果
            openIdJson = mapper.readValue(result, OpenIdJson.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  //将数据输出到控制台中
        //日志进行输出
        log.info(result.toString());
        System.out.println(result.toString());
        System.out.println(openIdJson.getOpenid());
        System.out.println(result);
        return openIdJson;  //将json数据返回
    }

    @Override
    //这个方法用于获取用户信息，如果openId为空则抛出异常。
    // 使用QueryWrapper添加openId等于给定值的查询条件，
    // 然后调用this.getOne(queryWrapper)获取并返回满足条件的一个用户。
    public NormalUser getUser(String openId) {
        if (openId == null) {
            throw new CustomException(ResponseEnums.SERVER_ERROR);
        }
        return this.getOne(new QueryWrapper<NormalUser>().eq("openId",openId));
    }

    @Override
    //这个方法用于用户认证。认证成功的用户要归属到normalUser中
    //它使用BeanCopyUtils.copyProperties方法把UserAuthFrom对象的属性复制到NormalUser对象中；
    // 然后设置openId，最后调用this.updateById(normalUser)更新数据库并返回操作结果。
    public boolean userAuth(UserAuthFrom userAuthFrom,String openId) {
        NormalUser normalUser = BeanCopyUtils.copyProperties(userAuthFrom, NormalUser::new);
        normalUser.setOpenId(openId);
        return this.updateById(normalUser);
    }

    @Override
    //这个方法调用NormalUserMapper的paymentBalance方法进行支付余额的操作。
    //baseMapper 是 MyBatis Plus 提供的一个封装了常用数据库操作方法的对象
    public boolean paymentBalance(String openId, Float balanceVar) {
        return baseMapper.paymentBalance(openId,balanceVar);
    }
}
