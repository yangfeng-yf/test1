package cn.dk.shiro.web.controller;


import cn.dk.shiro.domain.AdminInfo;
import cn.dk.shiro.utils.MyDate;
import cn.dk.shiro.dao.CarInfoMapper;
import cn.dk.shiro.dao.MaintenanceRegistrationLogMapper;
import cn.dk.shiro.dao.UserInfoMapper;
import cn.dk.shiro.domain.CarInfo;
import cn.dk.shiro.domain.MaintenanceRegistrationLog;
import cn.dk.shiro.domain.UserInfo;
import cn.dk.shiro.realm.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Author:Create by dk
 * @Date:Create in 10:50:2019/3/11/011
 */
@Controller
public class UserInfoController {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    CarInfoMapper carInfoMapper;
    @Autowired
    MaintenanceRegistrationLogMapper maintenanceRegistrationLogMapper;

    @RequestMapping("saveUserInfo")
    @RequiresPermissions("userInfo:save")
    @PermissionName("添加用户信息")
    public String saveUserInfo(HttpServletRequest request, HttpServletResponse response, String uName,String uPhone ,
                               String uAddress,String cType,String cBrand,String mRemark){
        //获取当前登陆的管理员
        AdminInfo adminInfo = (AdminInfo) SecurityUtils.getSubject().getPrincipal();

        //添加客户个人信息
        UserInfo userInfo = new UserInfo();
        int uId = getId();//生成主键
        userInfo.setuId(uId);
        userInfo.setuName(uName);
        userInfo.setuPhone(uPhone);
        userInfo.setuAddress(uAddress);
        userInfo.setuCreatetime(new Date());

        //  添加客户车辆信息
        CarInfo carInfo =new CarInfo();
        int count_id = getId();
        carInfo.setCountId(count_id);
        carInfo.setcType(cType);
        carInfo.setcBrand(cBrand);
        carInfo.setcPerson(uName);
        carInfo.setcCreatetime(new Date());

        //添加维修订单信息
        MaintenanceRegistrationLog maintenanceRegistrationLog  = new MaintenanceRegistrationLog();
        //车辆id，客户，接待，状态，登记时间，描述，已有
        //维修员，开始时间，结束时间，缺少
        maintenanceRegistrationLog.setmCCountId(count_id);
        maintenanceRegistrationLog.setmUser(uName);
        maintenanceRegistrationLog.setmSalesman(adminInfo.getAdName());
        maintenanceRegistrationLog.setmStuta(0);
        maintenanceRegistrationLog.setmRegiterTime(new Date());
        maintenanceRegistrationLog.setmRemark(mRemark);

        userInfoMapper.insert(userInfo);
        carInfoMapper.insert(carInfo);
        maintenanceRegistrationLogMapper.insert(maintenanceRegistrationLog);
        return "redirect:/jumpMrLogManage";
    }

    @RequestMapping(value ="listUserInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("userInfo:list")
    @PermissionName("查找所有用户信息")
    @ResponseBody
    public List<UserInfo> listUserInfo(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        System.out.println("创建时间：+++++++++++++++++++++++");
        System.out.println(userInfos.get(0).getuCreatetime());

        return userInfos;
    }

    @RequestMapping(value ="editUserInfoIm", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("userInfo:edit")
    @PermissionName("修改用户信息")
    public String editUserInfoIm(HttpServletRequest request, HttpServletResponse response ,String uId,String uName,String uPhone ,
                                   String uAddress) throws JsonProcessingException {
        UserInfo userInfo = new UserInfo();
        userInfo.setuName(uName);
        userInfo.setuPhone(uPhone);
        userInfo.setuAddress(uAddress);
        userInfo.setuId(Integer.parseInt(uId));
        userInfo.setuPassword("123456");
        userInfo.setuCreatetime(new Date());
        Integer result = userInfoMapper.updateByPrimaryKey(userInfo);

        if (result == null || result <= 0){
            System.out.println("修改失败");
        }
        return "product_Manage";
    }


    @RequestMapping(value ="selectUserInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("userInfo:findById")
    @PermissionName("通过id查询用户信息")
    @ResponseBody
    public UserInfo selectUserInfoById(HttpServletRequest request, HttpServletResponse response, Integer uId) throws JsonProcessingException {
        System.out.println("--------selectUserInfoById--------");
        System.out.println("uId:"+uId);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(uId);
        return userInfo;
    }


    @RequestMapping(value ="selectUserInfoByParam", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("userInfo:findByParam")
    @PermissionName("通过条件查询用户信息")
    @ResponseBody
    public List<UserInfo> selectUserInfoByParam(HttpServletRequest request, HttpServletResponse response, String param,String value ) throws JsonProcessingException {
        System.out.println("--------selectUserInfoByParam--------");
        System.out.println("param:"+param+"   value:"+value);
        List <UserInfo> userInfo = userInfoMapper.selectByCondition(param,value);
        return userInfo;
    }

    @RequestMapping(value ="deleteUserInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("userInfo:delById")
    @PermissionName("通过主键删除用户信息")
    @ResponseBody
    public List<UserInfo> deleteUserInfoById(HttpServletRequest request, HttpServletResponse response, String uId ) throws JsonProcessingException {
        System.out.println("--------deleteUserInfoById--------");

        int x = userInfoMapper.deleteByPrimaryKey(Integer.parseInt(uId));
        List<UserInfo> userInfo = userInfoMapper.selectAll();
        return userInfo;
    }


    /**
     * 主键生成策略
     * @return
     */
    public  int getId(){
        long time = new Date().getTime();
        System.out.println(time);
        time = time - 1500000000000l;
        System.out.println(time);
        int data = (int) time;
        return data;
    }
}
