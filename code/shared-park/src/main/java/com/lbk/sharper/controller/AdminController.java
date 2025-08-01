package com.lbk.sharper.controller;

import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.entity.NormalUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author WJX
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/getUser")
    @RequiresRoles("admin")
    public Result getUser() {
        NormalUser user = (NormalUser) SecurityUtils.getSubject().getPrincipal();
        return ResultUtil.success("2222");
    }

}
