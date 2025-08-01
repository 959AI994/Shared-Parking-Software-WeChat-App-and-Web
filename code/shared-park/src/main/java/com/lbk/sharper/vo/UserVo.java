package com.lbk.sharper.vo;

import com.lbk.sharper.entity.NormalUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: WJX
 * @Date: 2023/6/3 17:35
 * @Description
 * @Version 1.0
 */
@Data
//@Data 是一个 Lombok 注解，会在编译时自动为类生成 getter、setter 方法，以及 equals、hashCode 和 toString 方法。
//这个 UserVo 类通常被用于接收和传递用户请求数据，比如作为控制器方法的参数，从 HTTP 请求中接收数据；
// 或者作为服务方法的参数，向下传递数据。通过这样的方式，可以将一个 HTTP 请求中的多个数据打包成一个对象，更方便地在应用的各个层之间进行传递。
public class UserVo extends NormalUser {
    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    @ApiModelProperty(value = "页条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "code")
    private String code;
    //MyBatis Plus 通过提供了一些方便的工具和方法，使得在 Java 程序中进行分页查询变得更加简单和高效。
}
