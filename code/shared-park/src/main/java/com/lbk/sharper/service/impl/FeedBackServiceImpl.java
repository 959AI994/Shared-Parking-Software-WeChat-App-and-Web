package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbk.sharper.entity.FeedBack;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.mapper.FeedBackMapper;
import com.lbk.sharper.service.IFeedBackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.vo.FeedBackVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjx
 * @since 2023-6-6
 */
//ServiceImpl类为我们提供了一些常用的数据库操作方法，例如save，page，removeById等。基类自带一些对数据库操作的方法
@Service
public class FeedBackServiceImpl extends ServiceImpl<FeedBackMapper, FeedBack> implements IFeedBackService {

    @Override  //这部分代码会在管理员的页面进行调用
    public IPage<FeedBack> getPageList(FeedBackVo vo) { //这个方法是用来获取分页的反馈列表的。它使用了MyBatis Plus的page方法
        IPage<FeedBack> page = new Page<>(vo.getCurrent(), vo.getPageSize());//先创建一个Page对象，它需要当前页码和每页的大小
        QueryWrapper<FeedBack> queryWrapper = new QueryWrapper<>();//创建一个QueryWrapper对象，这个对象用于构建查询条件
        return this.page(page, queryWrapper); //调用this.page(page, queryWrapper)执行查询并返回结果
    }

    @Override
    //这个方法用于添加一条新的反馈记录。它先设置反馈记录的openId和createTime，
    // 然后调用this.save(feedBackVo)将反馈记录保存到数据库。如果保存成功，返回true
    public boolean add(FeedBackVo feedBackVo,String openId) {
        feedBackVo.setOpenId(openId);
        feedBackVo.setCreateTime(LocalDateTime.now());
        return this.save(feedBackVo);
    }

    @Override
    //这个方法用于删除一条反馈记录。它调用this.removeById(id)将id对应的记录从数据库中删除。
    public boolean delete(Integer id) {
        return this.removeById(id);
    }
    //this关键字指代的是当前类FeedBackServiceImpl的实例。
    // 因为FeedBackServiceImpl继承了ServiceImpl，所以可以直接调用ServiceImpl提供的方法。
}
