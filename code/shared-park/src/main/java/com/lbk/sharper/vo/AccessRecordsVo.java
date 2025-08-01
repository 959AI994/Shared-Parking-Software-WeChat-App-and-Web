package com.lbk.sharper.vo;

import com.lbk.sharper.entity.AccessRecords;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: wjx
 * @Date: 2023/5/18 11:33
 * @Description 出入记录VO
 * @Version 1.0
 */
@Data
public class AccessRecordsVo extends AccessRecords {
    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    @ApiModelProperty(value = "页条数")
    private Integer pageSize = 10;


    @ApiModelProperty(value = "小区名称")
    private String villageName;
}
//这个类的主要作用是：
//
//传递数据：它可以将门禁记录的数据从一个地方传递到另一个地方。
// 比如，从数据库获取门禁记录，然后通过服务层传递给控制器层，最后展示在前端界面上。
//
//分页功能：我们知道门禁记录可能非常多，如果一次性展示所有记录，会导致界面很长。
// 所以，我们可以使用 AccessRecordsVo 类的 current 和 pageSize 字段来实现分页功能。
// 比如，可以告诉程序你想看第几页的记录，每页显示多少条记录。
//
//查询过滤：有时候，我们只关心某个特定小区的门禁记录。AccessRecordsVo 类的 villageName 字段可以用来指定查询的小区名称，
// 这样就可以只获取该小区的记录。
//
//方便开发：@ApiModelProperty 注解是用来生成接口文档的说明。它可以帮助开发人员理解每个字段的含义，并在开发过程中提供示例和提示。