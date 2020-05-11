package cn.dk.shiro.web.controller;

import cn.dk.shiro.dao.AccessoryInfoMapper;
import cn.dk.shiro.dao.PurchaseLogMapper;
import cn.dk.shiro.dao.UseAccessoriesLogMapper;
import cn.dk.shiro.domain.AccessoryInfo;
import cn.dk.shiro.domain.AdminInfo;
import cn.dk.shiro.domain.PurchaseLog;
import cn.dk.shiro.domain.UseAccessoriesLog;
import cn.dk.shiro.realm.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Author:Create by dk
 * @Date:Create in 17:37:2019/3/23/023
 */
@Controller
public class UseAccessoryiesLogController {
    public  static  final Integer IN_APPROVAL = 0;
    public  static  final Integer PASS = 1;
    public  static  final Integer NOT_PASS = 2;

    @Autowired
    UseAccessoriesLogMapper useAccessoriesLogMapper;

    @Autowired
    AccessoryInfoMapper accessoryInfoMapper;

    @RequestMapping(value = "listUseAccessoryiesLogInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("useAccessoryies:findAll")
    @PermissionName("查询所有车辆维修配件信息")
    @ResponseBody
    public List<UseAccessoriesLog> listPurchaseLogInfo(HttpServletRequest request, HttpServletResponse response){
        List<UseAccessoriesLog> all = useAccessoriesLogMapper.findAll();
        System.out.println(all.size()+"查出的长度==================");
        return all;
    }

    @RequestMapping(value ="selectUseAccessoriesLogByParam", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("useAccessoryies:findByParam")
    @PermissionName("通过条件查询车辆维修配件信息")
    @ResponseBody
    public List<UseAccessoriesLog> selectUseAccessoriesLogByParam(HttpServletRequest request, HttpServletResponse response, String param, String value ) throws JsonProcessingException {
        System.out.println("--------selectPurchaseLogByParam--------");
        System.out.println("param:"+param+"   value:"+value);
        List<UseAccessoriesLog> useAccessoriesLogs = useAccessoriesLogMapper.findByCondi(param, value);
        return useAccessoriesLogs;
    }


    @RequestMapping(value ="addUseAccessoryiesLogInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("useAccessoryies:addInfo")
    @PermissionName("添加配件申请信息")
    public String addPurchaseLog(HttpServletRequest request, HttpServletResponse response ,
                                 Integer aId, Integer num, Integer cCountid) throws JsonProcessingException {
        //查询出配件信息 获取单价 防止篡改
        //根据id查找配件的价格
        AccessoryInfo accessoryInfo = accessoryInfoMapper.selectByPrimaryKey(aId);
        //获取当前操作员信息
        AdminInfo adminInfo  = (AdminInfo) SecurityUtils.getSubject().getPrincipal();
        //添加配件申请日志
        UseAccessoriesLog useAccessoriesLog = new UseAccessoriesLog();
        useAccessoriesLog.setUsername(adminInfo.getAdName());
        useAccessoriesLog.setApplicationTime(new Date());
        useAccessoriesLog.setAccessory(accessoryInfo.getaName());
        useAccessoriesLog.setcCountid(cCountid);
        useAccessoriesLog.setGetType(IN_APPROVAL);
        useAccessoriesLog.setNum(num);
        //计算总价
        useAccessoriesLog.setPrice(accessoryInfo.getaSalePrice() * num);
        int insert = useAccessoriesLogMapper.insert(useAccessoriesLog);
        if (insert <= 0){
            System.out.println("添加失败");
        }
        request.setAttribute("msg","添加成功!");
        return "myUseAccessoriesLog_Manage";
    }

    @RequestMapping(value ="editUseAccessoriesStatus", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("useAccessoryies:editStatus")
    @PermissionName("审批申请配件")
    @Transactional
    public String editPurchaseLogStatus(HttpServletRequest request, HttpServletResponse response ,
                                        Integer countId, Integer status) throws JsonProcessingException {
        UseAccessoriesLog useAccessoriesLog = useAccessoriesLogMapper.selectByPrimaryKey(countId);

        if (useAccessoriesLog.getGetType()!=0){
            request.setAttribute("msg","审批失败，已审批！");
            return "myUseAccessoriesLog_Manage";
        }
        //获取需要的数量
        Integer num = useAccessoriesLog.getNum();
        //获取所进的配件
        //Integer id = useAccessoriesLog.getaId();
        String accessoryName=useAccessoriesLog.getUsername();
        //AccessoryInfo accessoryInfo = accessoryInfoMapper.selectByPrimaryKey(id);

        AccessoryInfo accessoryInfo=accessoryInfoMapper.selectByName(accessoryName);

        Integer have = accessoryInfo.getaNum();

        if (status == 1 && have < num){
            System.out.println("修改失败，仓库存货不足。");
            request.setAttribute("msg","申请失败,仓库存货不足！");
            return "useAccessoriesLog_Manage";
        }
        //这里修改了货物
        int i = useAccessoriesLogMapper.updateTypeByPrimaryKey(countId, status);
        int i1 = accessoryInfoMapper.updateNumByPrimaryKey(accessoryInfo.getaNum() - num, accessoryInfo.getaId());
        if (i < 0 || i1< 0){
            System.out.println("修改失败");
        }
        request.setAttribute("msg","审批成功！");
        return "useAccessoriesLog_Manage";
    }


}
