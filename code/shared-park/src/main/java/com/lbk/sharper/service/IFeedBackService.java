package com.lbk.sharper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.entity.FeedBack;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.vo.FeedBackVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WJX
 * @since 2023-5-16
 */
public interface IFeedBackService extends IService<FeedBack> {

    /**
     * 分页反馈信息列表
     * @return IPage
     */
    IPage<FeedBack> getPageList(FeedBackVo vo);

    /**
     * 添加反馈信息
     * @param feedBackVo
     * @return boolean
     */
    boolean add(FeedBackVo feedBackVo,String openId);


    /**
     * 删除反馈信息
     * @param id
     * @return
     */
    boolean delete(Integer id);

}
