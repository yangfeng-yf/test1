package cn.dk.shiro.web.controller;

import cn.dk.shiro.dao.CarInfoMapper;
import cn.dk.shiro.dao.MaintenanceRegistrationLogMapper;
import cn.dk.shiro.dao.UseAccessoriesLogMapper;
import cn.dk.shiro.dao.UserInfoMapper;
import cn.dk.shiro.realm.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Create by dk
 * @Date:Create in 9:12:2019/3/25/025
 */
@Controller
public class MainTableController {

    @Autowired
    MaintenanceRegistrationLogMapper maintenanceRegistrationLogMapper;
    @Autowired
    CarInfoMapper carInfoMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

//    @RequestMapping("findUALogCount")
//    @RequiresPermissions("mainTable:findUALogCount")
//    @PermissionName("查询")
//    @ResponseBody
//    public void findUALogCount(){
//
//    }
//
    @RequestMapping(value = "findMRLogCount" , produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("mainTable:findUALogCount")
    @PermissionName("查询维修登记表状态数量")
    @ResponseBody
    public List findMRLogCount(){
        List<Map> allType = maintenanceRegistrationLogMapper.findAllType();
        return  allType;
    }


    @RequestMapping(value = "findAllTardemarkInfo" , produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("mainTable:findAllTardemarkInfo")
    @PermissionName("车辆品牌信息统计")
    @ResponseBody
    public List findAllTardemarkInfo(){
        List<Map> allType = carInfoMapper.findAllType();
        return  allType;
    }

    @RequestMapping(value = "findAllUserAddressInfo" , produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("mainTable:findAllUserAddressInfo")
    @PermissionName("车主各省分布")
    @ResponseBody
    public List findAllUserAddressInfo(){
        List<Map> allType = userInfoMapper.findAllType();
        return  allType;
    }
}
