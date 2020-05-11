package cn.dk.shiro.web.controller;

import cn.dk.shiro.dao.AccessoryInfoMapper;
import cn.dk.shiro.domain.AccessoryInfo;
import cn.dk.shiro.domain.SupplierInfo;
import cn.dk.shiro.realm.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * AccessoryController
 * @author yf
 * @version 1.0
 * @date 2020-03-20
 * @description 配件Controller
 */
@Controller
public class AccessoryController {

    @Autowired
    AccessoryInfoMapper accessoryInfoMapper;

    @RequestMapping(value = "listAccessoryInfo" , produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("accessoryInfo:findAll")
    @PermissionName("查询所有配件信息")
    @ResponseBody
    public List<AccessoryInfo> listAccessoryInfo(HttpServletRequest request, HttpServletResponse response){
        List<AccessoryInfo> all = accessoryInfoMapper.findAll();
        return all;
    }

    @RequestMapping(value ="selectAccessoryInfoByParam", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("accessoryInfo:findByParam")
    @PermissionName("通过条件查询供货商信息")
    @ResponseBody
    public List<AccessoryInfo> selectAccessoryInfoByParam(HttpServletRequest request, HttpServletResponse response, String param, String value ) throws JsonProcessingException {
        System.out.println("--------selectAccessoryInfoByParam--------");
        System.out.println("param:"+param+"   value:"+value);
        List<AccessoryInfo> accessoryInfos = accessoryInfoMapper.findByCondi(param, value);
        return accessoryInfos;
    }

    @RequestMapping(value = "addAccessoryInfo",produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("accessoryInfo:add")
    @PermissionName("添加配件信息")
    public String addSupplierInfo(HttpServletRequest request, HttpServletResponse response ,String aName, String aSId,
                                  String aDescribe ,String aSalePrice ,String aIncomingPrice){
        AccessoryInfo accessoryInfo = new AccessoryInfo();
        accessoryInfo.setaNum(0);
        accessoryInfo.setaDescribe(aDescribe);
        accessoryInfo.setaLastSupplyTime(new Date());
        accessoryInfo.setaIncomingPrice(Double.parseDouble(aIncomingPrice));
        accessoryInfo.setaSalePrice(Double.parseDouble(aSalePrice));
        accessoryInfo.setaName(aName);
        accessoryInfo.setaSId(Integer.parseInt(aSId));

        int insert = accessoryInfoMapper.insert(accessoryInfo);
        if (insert <= 0){
            System.out.println("添加失败！！！");
        }
        return "accessory_Manage";
    }

    @RequestMapping(value ="selectAccessoryInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("accessoryInfo:findById")
    @PermissionName("通过id查询供货商信息")
    @ResponseBody
    public AccessoryInfo selectAccessoryInfoById(HttpServletRequest request, HttpServletResponse response, Integer aId) throws JsonProcessingException {
        System.out.println("--------selectAccessoryInfoById--------");
        AccessoryInfo accessoryInfo = accessoryInfoMapper.selectByPrimaryKey(aId);
        return accessoryInfo;
    }

    @RequestMapping(value ="editAccessoryInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("accessoryInfo:edit")
    @PermissionName("修改配件信息")
    public String editAccessoryInfo(HttpServletRequest request, HttpServletResponse response ,String aName,Integer aId, Integer aSId,
                                    String aDescribe ,Double aSalePrice ,Double aIncomingPrice, String aLastSupplyTime ,Integer aNum) throws JsonProcessingException {
        AccessoryInfo accessoryInfo =new AccessoryInfo();
        accessoryInfo.setaId(aId);
        accessoryInfo.setaSId(aSId);
        accessoryInfo.setaNum(aNum);
        accessoryInfo.setaDescribe(aDescribe);
        accessoryInfo.setaSalePrice(aSalePrice);
        accessoryInfo.setaIncomingPrice(aIncomingPrice);
        accessoryInfo.setaLastSupplyTime(new Date());
        accessoryInfo.setaName(aName);
        Integer result = accessoryInfoMapper.updateByPrimaryKey(accessoryInfo);
        if (result == null || result <= 0){
            System.out.println("修改失败");
        }
        return "accessory_Manage";
    }

    @RequestMapping(value ="deleteAccessoryInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("accessoryInfo:delById")
    @PermissionName("通过主键删除配件信息")
    @ResponseBody
    public List<AccessoryInfo> deleteAccessoryInfoById(HttpServletRequest request, HttpServletResponse response, Integer aId ) throws JsonProcessingException {
        System.out.println("--------deleteAccessoryInfoById--------");

        int x = accessoryInfoMapper.deleteByPrimaryKey(aId);
        List<AccessoryInfo> accessoryInfos = accessoryInfoMapper.findAll();
        return accessoryInfos;
    }

    //杨凤测试
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public void test(@RequestParam("list") List<String> list,String type){
        System.out.println(list.get(0).toString());
        System.out.println(list.get(1).toString());
        System.out.println(type+"成功");
    }

}
