package com.lbk.sharper.dto;

import lombok.Data;

/**
 * @Author: WJX
 * @Date: 2023/5/19 13:21
 * @Description
 * @Version 1.0
 */
//DTOs一般用于在不同层次的应用程序中传输数据。
// UserDto用于在应用程序的服务层和数据访问层之间传输用户数据，或者在网络通信中作为API的请求或响应的一部分。
@Data
public class UserDto {
    private String userName;
    private String password;
    private String phone;
}
//用户注册或登录时，从客户端收集和传输用户输入的用户名、密码和电话号码。
//在服务层处理用户数据，如验证、密码散列等，然后再将处理过的数据传给数据访问层进行持久化操作（如存储到数据库中）。
//当查询用户信息时，从数据访问层获取用户信息（用户名和电话），然后将这些数据封装在UserDto对象中，返回给调用者（如控制层或前端）。