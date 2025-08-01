package com.lbk.sharper.req;

import com.lbk.sharper.common.validator.Cid;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Author: WJX
 * @Date: 2023/6/4 0:07
 * @Description 用户完善信息接受类
 * @Version 1.0
 */
@Data
//数据校验：该类使用了注解进行数据校验，如 @NotBlank、@Cid 和 @Pattern 等。
// 这些注解能够对表单数据进行验证，确保提交的数据符合一定的规则和要求。
// 通过在属性上添加这些注解，可以在表单数据传递过程中对数据进行验证，提高数据的准确性和完整性。
public class UserAuthFrom implements Serializable {
//实现 Serializable 接口，
// 使得该类的对象可以在网络传输或持久化存储时进行序列化和反序列化。
// 这样可以方便地将表单数据进行传输和存储
    @ApiModelProperty(value = "真实姓名")
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @ApiModelProperty(value = "身份证号")
    @NotBlank(message = "身份证号不能为空")
    @Cid
    private String idNumber;

    @ApiModelProperty(value = "电话")
    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "联系电话格式错误")
    private String phone;

    @ApiModelProperty(value = "车牌号")
    @NotBlank(message = "车牌号不能为空")
    private String carNumber;

}
