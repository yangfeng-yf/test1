package cn.dk.shiro.web.controller;

import cn.dk.shiro.dao.AdminInfoMapper;
import cn.dk.shiro.domain.AdminInfo;
import cn.dk.shiro.domain.CarInfo;
import cn.dk.shiro.domain.UserInfo;
import cn.dk.shiro.realm.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * AdminInfoController
 * @author yf
 * @version 1.0
 * @date 2020-03-17
 * @description 管理员Controller
 */
@Controller
public class AdminInfoController {

    @Autowired
    AdminInfoMapper adminInfoMapper;

    @RequestMapping(value ="getAdminInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("adminInfo:getAdminInfo")
    @PermissionName("通过id查询缓存中的管理员信息")
    @ResponseBody
    public AdminInfo getAdminInfo(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        AdminInfo admin = (AdminInfo) SecurityUtils.getSubject().getPrincipal();

        return admin;
    }

    @RequestMapping(value ="selectAdminInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("adminInfo:findById")
    @PermissionName("通过id查询数据库中的管理员信息")
    @ResponseBody
    public AdminInfo selectAdminInfoById(HttpServletRequest request, HttpServletResponse response, Integer adId) throws JsonProcessingException {
        System.out.println("--------selectAdminInfoById--------");
        AdminInfo adminInfo = adminInfoMapper.selectByPrimaryKey(adId);
        return adminInfo;
    }

    @RequestMapping(value ="saveAdminInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("adminInfo:saveAdminInfo")
    @PermissionName("保存管理员信息")
    public String saveAdminInfo(HttpServletRequest request, HttpServletResponse response, String adId,
                                   String adPhone, String adAddress,String adName, String adPassword) throws JsonProcessingException {
        AdminInfo adminInfo = (AdminInfo)SecurityUtils.getSubject().getPrincipal();
            if(adPassword.equals(adminInfo.getAdPassword())){

            }else{
                //将密码转换为hash值
                Md5Hash hash = new Md5Hash(adPassword,null,3);
                System.out.println("修改后的密码为:"+hash.toString());
                adPassword = hash.toString();
            }
        adminInfo.setAdPhone(adPhone);
        adminInfo.setAdPassword(adPassword);
        adminInfo.setAdName(adName);
        adminInfoMapper.updateByPrimaryKey(adminInfo);
        request.setAttribute("msg","修改成功");
        return "adminInfo";
    }


    @RequestMapping(value ="listAdminInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("adminInfo:list")
    @PermissionName("查找所有管理员信息")
    @ResponseBody
    public List<AdminInfo> listAdminInfo(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        List<AdminInfo> adminInfos = adminInfoMapper.findAll();
        return adminInfos;
    }


    @RequestMapping(value ="selectAdminInfoByParam", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("adminInfo:findByParam")
    @PermissionName("通过条件查询管理员信息")
    @ResponseBody
    public List<AdminInfo> selectAdminInfoByParam(HttpServletRequest request, HttpServletResponse response, String param, String value ) throws JsonProcessingException {
        System.out.println("--------selectAdminInfoByParam--------");
        System.out.println("param:"+param+"   value:"+value);
        List<AdminInfo> adminInfos = adminInfoMapper.findAdminInfoByCondi(param, value);
        return adminInfos;
    }

    @RequestMapping(value ="editAdminInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("adminInfo:edit")
    @PermissionName("修改管理员信息")
    public String editAdminInfo(HttpServletRequest request, HttpServletResponse response, String adId,
                                String adPhone, String adAddress,String adName, String adLastlogintime, String adPassword,long adType) throws JsonProcessingException {
        //用于存储数据
        AdminInfo adminInfo = new AdminInfo();
        //比对密码是否修改过
        AdminInfo adminInfo1 = adminInfoMapper.selectByPrimaryKey(Integer.parseInt(adId));
        if(adPassword.equals(adminInfo1.getAdPassword())){

        }else{
            //将密码转换为hash值
            Md5Hash hash = new Md5Hash(adPassword,null,3);
            adPassword = hash.toString();
        }
        System.out.println("修改后的密码为:"+adPassword);

        adminInfo.setAdId(Integer.parseInt(adId));
        adminInfo.setAdPhone(adPhone);
        adminInfo.setAdType(adType);
        adminInfo.setAdLastlogintime(new Date());
        adminInfo.setAdPassword(adPassword);
        adminInfo.setAdName(adName);
        adminInfoMapper.updateByPrimaryKey(adminInfo);

        return "admin_Manage";
    }



    @RequestMapping(value ="deleteAdminInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("adminInfo:delById")
    @PermissionName("通过主键删除管理员信息")
    @ResponseBody
    public List<AdminInfo> deleteAdminInfoById(HttpServletRequest request, HttpServletResponse response, String adId ) throws JsonProcessingException {
        System.out.println("--------deleteAdminInfoById--------");

        int x = adminInfoMapper.deleteByPrimaryKey(Integer.parseInt(adId));
        List<AdminInfo> adminInfos = adminInfoMapper.findAll();
        return adminInfos;
    }

    @RequestMapping(value ="addAdminInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("adminInfo:addInfo")
    @PermissionName("添加管理员信息")
    public String addAdminInfo(HttpServletRequest request, HttpServletResponse response ,
                               String adPhone,String adName, String adPassword,String adType) throws JsonProcessingException {
        System.out.println("--------addAdminInfo--------");
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdType(Long.valueOf(adType));
        adminInfo.setAdPhone(adPhone);
        adminInfo.setAdPassword((new Md5Hash(adPassword, null ,3)).toString());
        adminInfo.setAdName(adName);
        adminInfo.setAdLastlogintime(new Date());
        int insert = adminInfoMapper.insert(adminInfo);
        if (insert <= 0){
            System.out.println("添加失败");
        }
        return "admin_Manage";
    }
}
