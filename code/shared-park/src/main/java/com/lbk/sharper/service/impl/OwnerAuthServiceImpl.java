package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbk.sharper.common.*;
import com.lbk.sharper.entity.*;
import com.lbk.sharper.mapper.OwnerAuthMapper;
import com.lbk.sharper.service.IOwnerAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.service.IParkingInfoService;
import com.lbk.sharper.service.IParkingSpaceService;
import com.lbk.sharper.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 业主信息表 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-5-19
 */
@Service
public class OwnerAuthServiceImpl extends ServiceImpl<OwnerAuthMapper, OwnerAuth> implements IOwnerAuthService {
    @Autowired
    OwnerAuthMapper ownerAuthMapper;
    @Autowired
    IParkingInfoService iParkingInfoService;
    @Autowired
    IParkingSpaceService iParkingSpaceService;

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    @Value("${prop.imgPathPatterns}")
    private String IMG_PATH;


    @Override
    public IPage<OwnerAuth> getPageList(OwnerAuthVo ownerAuthVo) {
        IPage<OwnerAuth> page = new Page<>(ownerAuthVo.getCurrent(), ownerAuthVo.getPageSize());
        QueryWrapper<OwnerAuth> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(ownerAuthVo.getParkingTileNumber())){
            queryWrapper.eq("parkingTileNumber",ownerAuthVo.getParkingTileNumber());
        }
        if (!StringUtils.isEmpty(ownerAuthVo.getOwnerName())){
            queryWrapper.eq("ownerName",ownerAuthVo.getOwnerName());
        }
        if (!StringUtils.isEmpty(ownerAuthVo.getPhone())){
            queryWrapper.eq("phone",ownerAuthVo.getPhone());
        }
        if (!StringUtils.isEmpty(ownerAuthVo.getState())){
            queryWrapper.eq("state",ownerAuthVo.getState());
        }
        if (!StringUtils.isEmpty(ownerAuthVo.getIdNumber())){
            queryWrapper.eq("idNumber",ownerAuthVo.getIdNumber());
        }
        if (!StringUtils.isEmpty(ownerAuthVo.getVillageName())){
            queryWrapper.eq("villageName",ownerAuthVo.getOwnerName());
        }
        return this.page(page, queryWrapper);
    }

    @Override
    public List<OwnerAuth> getList() {
        return this.list();
    }

    /**
     * 从数据库中查询符合给定 openId 的 OwnerAuth 记录，并返回该记录。
     * 这样可以根据用户的 openId 获取对应的业主认证信息。
     * @param openId
     * @return
     */
    @Override
    public OwnerAuth getOwnerAuth(String openId) {
        QueryWrapper<OwnerAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openId", openId);
        return this.getOne(queryWrapper);
    }

    /**
     * 添加业主信息
     * @param ownerAuthVo
     * @param openId
     * @return
     */
    @Override // @Override 注解用于明确标识方法的覆盖关系
    public boolean add(OwnerAuthVo ownerAuthVo,String openId) {
        OwnerAuth ownerAuthDto = BeanCopyUtils.copyProperties(ownerAuthVo, OwnerAuth::new);

        ParkingInfo parkingInfo =iParkingInfoService.getParkingInfo(ownerAuthVo.getVillageName());
        if(parkingInfo==null){
            throw new CustomException("请输入启用的小区");
        }

        if (ownerAuthVo.getOwnerAuthId()!=null){
            ownerAuthDto.setState(BizState.toAudit);
            return this.updateById(ownerAuthDto);
        }

        //写入业主信息
        ownerAuthDto.setOpenId(openId);
        ownerAuthDto.setState(BizState.toAudit);
        boolean save = save(ownerAuthDto);


        //写入车位信息
        ParkingSpace parkingSpace = new ParkingSpace();
//        parkingSpace.setParkingSpaceId(55);
        parkingSpace.setParkingInfoId(parkingInfo.getParkingInfoId());
        parkingSpace.setParkingSpaceNumber(ownerAuthVo.getParkingSpaceNumber());
        parkingSpace.setIsTakeUp(0);
        parkingSpace.setState(BizState.notShar);
        parkingSpace.setOwnerAuthId(ownerAuthDto.getOwnerAuthId());
        LocalDateTime newTime = LocalDateTime.now();
        parkingSpace.setShareStartTime(newTime);
        parkingSpace.setShareEndTime(newTime);
        parkingSpace.setPrice(0F);
        return iParkingSpaceService.add(parkingSpace);
    }

    @Override
    public boolean arrces(OwnerAuthVo ownerAuthVo) {
        UpdateWrapper<OwnerAuth> updateWrapper = new UpdateWrapper<>();
        OwnerAuth ownerAuth = new OwnerAuth();
        if (!ownerAuthVo.getNotThroughReason().isEmpty()) {
            ownerAuth.setNotThroughReason(ownerAuthVo.getNotThroughReason());
            ownerAuth.setState(BizState.notThrough);
        }else {
            ownerAuth.setState(BizState.normal);
            ownerAuth.setNotThroughReason(null);
        }

        ownerAuth.setOwnerAuthId(ownerAuthVo.getOwnerAuthId());
        updateWrapper.eq("ownerAuthId", ownerAuthVo.getOwnerAuthId());
        return this.updateById(ownerAuth);
    }

    @Override
    public boolean updates(OwnerAuthVo ownerAuthVo) {

        return false;
    }

    @Override
    public boolean delete(OwnerAuthVo ownerAuthVo) {
        return this.removeById(ownerAuthVo.getOwnerAuthId());
    }

    /**
     * 业主从微信小程序申请成为业主时，进行图片上传的操作
     * @param files
     * @return
     */
    @Override
    public Result uploadImg(MultipartFile files) { //传过来的文件是一个业主的图片

        if (files == null) {
            return ResultUtil.defineFail(0, "请选择要上传的图片");
        }
        if (files.getSize() > 1024 * 1024 * 10) {
            return ResultUtil.defineFail(0, "文件大小不能大于10M");
        }
        //获取文件后缀，并校验文件的格式是否满足条件
        String suffix = files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf(".") + 1, files.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return ResultUtil.defineFail(0, "请选择jpg,jpeg,gif,png格式的图片");
        }
        String savePath = UPLOAD_FOLDER;
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            //将文件保存指定目录
            files.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.defineFail(0, "保存文件异常");
        }
//        File file = new File(savePath + filename);
        //返回文件名称
        Map<String,String> map =new HashMap<String, String>() ;//创建一个 Map 对象 map，将文件的路径和文件名存储在该 Map 中。
        map.put("filePath",IMG_PATH+filename);
        map.put("fileName",filename);
        return ResultUtil.success(map);
    }
}
