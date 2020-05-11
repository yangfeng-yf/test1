package cn.dk.shiro.web.controller;

import cn.dk.shiro.dao.MaintenanceRegistrationLogMapper;
import cn.dk.shiro.domain.*;
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
 * @Date:Create in 20:06:2019/3/14/014
 */
@Controller
public class MRLogController {
    public static final  Integer IN_CAMPANY = 1;
    public static final  Integer OUT_CAMPANY = 2;

    @Autowired
    MaintenanceRegistrationLogMapper maintenanceRegistrationLogMapper;

    @RequestMapping(value ="listMRLogInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("mrlogInfo:list")
    @PermissionName("查找所有维修信息")
    @ResponseBody
    public List<MaintenanceRegistrationLog> listUserInfo(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        List<MaintenanceRegistrationLog> maintenanceRegistrationLogList = maintenanceRegistrationLogMapper.findAll();
        System.out.println("插入订单，测试时间问题========================");
        System.out.println(maintenanceRegistrationLogList.get(0).getmRegiterTime());
        return maintenanceRegistrationLogList;
    }

    @RequestMapping(value ="selectMrlogInfoByParam", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("mrlogInfo:findByParam")
    @PermissionName("通过条件查询维修日志信息")
    @ResponseBody
    public List<MaintenanceRegistrationLog> selectMrlogInfoByParam(HttpServletRequest request, HttpServletResponse response, String param,String value ) throws JsonProcessingException {
        System.out.println("--------selectMrlogInfoByParam--------");
        System.out.println("param:"+param+"   value:"+value);
        List <MaintenanceRegistrationLog> maintenanceRegistrationLogList = maintenanceRegistrationLogMapper.findMrInfoByCondi(param,value);
        return maintenanceRegistrationLogList;
    }

    @RequestMapping(value ="selectMrlogInfoByParams", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("mrlogInfo:findByParams")
    @PermissionName("多条件查询维修日志信息")
    @ResponseBody
    public List<MaintenanceRegistrationLog> selectMrlogInfoByParams(HttpServletRequest request, HttpServletResponse response, String param,String value ) throws JsonProcessingException {
        System.out.println("--------selectMrlogInfoByParam--------");
        System.out.println("param:"+param+"   value:"+value);
        AdminInfo adminInfo = (AdminInfo) SecurityUtils.getSubject().getPrincipal();
        //通过shiro 获取当前登陆的管理员信息 并作为查询条件
        MaintenanceRegistrationLog maintenanceRegistrationLog = new MaintenanceRegistrationLog();
        maintenanceRegistrationLog.setmUser(adminInfo.getAdName());
        if ("m_c_count_id".equals(param)){
            maintenanceRegistrationLog.setmCCountId(Integer.parseInt(value));
        }
        if ("m_u_id".equals(param)){
            maintenanceRegistrationLog.setmRemark(adminInfo.getAdName());
        }
        if ("m_maintenanceman_id".equals(param)){
            maintenanceRegistrationLog.setmStuta(Integer.parseInt(value));
        }
        List <MaintenanceRegistrationLog> maintenanceRegistrationLogList = maintenanceRegistrationLogMapper.findMrInfoByCondi(param,value);
        return maintenanceRegistrationLogList;
    }


    @RequestMapping(value ="editMRLogTypeStatus", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("mrlogInfo:editStatus")
    @PermissionName("修改出入厂状态")
    @Transactional
    public String editMRLogTypeStatus(HttpServletRequest request, HttpServletResponse response ,
                                        Integer countId, Integer status) throws JsonProcessingException {
        //查询到当前日志
        MaintenanceRegistrationLog maintenanceRegistrationLog = maintenanceRegistrationLogMapper.selectByPrimaryKey(countId);
        //获取当前用户信息
        AdminInfo adminInfo  = (AdminInfo) SecurityUtils.getSubject().getPrincipal();

        if (status == IN_CAMPANY){
            //修改日子的维修员 ，进出场日期， 状态
            maintenanceRegistrationLogMapper.updateTypeAndInDateByPrimaryKey(countId,new Date(),status,adminInfo.getAdId());
        }
        if (status == OUT_CAMPANY){
            maintenanceRegistrationLogMapper.updateTypeAndOutDateByPrimaryKey(countId,new Date(), status,adminInfo.getAdId());
        }

        request.setAttribute("msg","修改成功！");
        return "changeMrLogType";
    }



}
