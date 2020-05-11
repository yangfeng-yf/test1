package cn.dk.shiro.web.controller;

import cn.dk.shiro.dao.SupplierInfoMapper;
import cn.dk.shiro.domain.CarInfo;
import cn.dk.shiro.domain.SupplierInfo;
import cn.dk.shiro.realm.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Author:Create by dk
 * @Date:Create in 15:10:2019/3/19/019
 */
@Controller
public class SupplierController {

    @Autowired
    SupplierInfoMapper supplierInfoMapper;

    @RequestMapping(value = "listSupplierInfo" ,produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("supplierInfo:findAll")
    @PermissionName("查询所有供货商信息")
    @ResponseBody
    public List<SupplierInfo> listSupplierInfo(HttpServletRequest request, HttpServletResponse response){
        List<SupplierInfo> all = supplierInfoMapper.findAll();
        return all;
    }

    @RequestMapping("addSupplierInfo")
    @RequiresPermissions("supplierInfo:add")
    @PermissionName("添加供货商信息")
    public String addSupplierInfo(HttpServletRequest request, HttpServletResponse response ,String sCampanyname, String sAddress,
                                              String sPhone ,String sPersonLiable){
        SupplierInfo supplierInfo = new SupplierInfo();
        supplierInfo.setsAddress(sAddress);
        supplierInfo.setsCampanyname(sCampanyname);
        supplierInfo.setsPersonLiable(sPersonLiable);
        supplierInfo.setsPhone(sPhone);

        int insert = supplierInfoMapper.insert(supplierInfo);
        if (insert <= 0){
            System.out.println("添加失败！！！");
        }
        return "supplier_Manage";
    }

    @RequestMapping(value ="selectSupplierInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("supplierInfo:findById")
    @PermissionName("通过id查询供货商信息")
    @ResponseBody
    public SupplierInfo selectSupplierInfoById(HttpServletRequest request, HttpServletResponse response, Integer sId) throws JsonProcessingException {
        System.out.println("--------selectSupplierInfoById--------");
        SupplierInfo supplierInfo = supplierInfoMapper.selectByPrimaryKey(sId);
        return supplierInfo;
    }


    @RequestMapping(value ="selectSupplierInfoByParam", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("supplierInfo:findByParam")
    @PermissionName("通过条件查询供货商信息")
    @ResponseBody
    public List<SupplierInfo> selectSupplierInfoByParam(HttpServletRequest request, HttpServletResponse response, String param, String value ) throws JsonProcessingException {
        System.out.println("--------selectsupplierInfoByParam--------");
        System.out.println("param:"+param+"   value:"+value);
        List<SupplierInfo> supplierInfos = supplierInfoMapper.findSupplierInfoByCondi(param, value);
        return supplierInfos;
    }


    @RequestMapping(value ="editSupplierInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("supplierInfo:edit")
    @PermissionName("修改供货商信息")
    public String editSupplierInfo(HttpServletRequest request, HttpServletResponse response ,Integer sId,String sCampanyname, String sAddress,
                                   String sPhone ,String sPersonLiable) throws JsonProcessingException {
        SupplierInfo supplierInfo =new SupplierInfo();
        supplierInfo.setsId(sId);
        supplierInfo.setsCampanyname(sCampanyname);
        supplierInfo.setsPhone(sPhone);
        supplierInfo.setsAddress(sAddress);
        supplierInfo.setsPersonLiable(sPersonLiable);
        Integer result = supplierInfoMapper.updateByPrimaryKey(supplierInfo);

        if (result == null || result <= 0){
            System.out.println("修改失败");
        }
        return "supplier_Manage";
    }


    @RequestMapping(value ="deleteSupplierInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("supplierInfo:delById")
    @PermissionName("通过主键删除用户信息")
    @ResponseBody
    public List<SupplierInfo> deleteSupplierInfoById(HttpServletRequest request, HttpServletResponse response, String sId ) throws JsonProcessingException {
        System.out.println("--------deleteSupplierInfoById--------");

        int x = supplierInfoMapper.deleteByPrimaryKey(Integer.parseInt(sId));
        List<SupplierInfo> supplierInfos = supplierInfoMapper.findAll();
        return supplierInfos;
    }
}
