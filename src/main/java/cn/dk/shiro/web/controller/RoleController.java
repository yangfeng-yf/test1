package cn.dk.shiro.web.controller;

import cn.dk.shiro.dao.RoleMapper;
import cn.dk.shiro.domain.AdminInfo;
import cn.dk.shiro.domain.Role;
import cn.dk.shiro.realm.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:Create by dk
 * @Date:Create in 9:02:2019/3/19/019
 */
@Controller
public class RoleController {

    @Autowired
    RoleMapper roleMapper;

    @RequestMapping(value ="listRole", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("role:list")
    @PermissionName("查找所有角色")
    @ResponseBody
    public List<Role> listRole(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        List<Role> roles = roleMapper.findAll();
        return roles;
    }
}
