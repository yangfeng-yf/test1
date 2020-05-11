package cn.dk.shiro.web.controller;

import cn.dk.shiro.dao.AccessoryInfoMapper;
import cn.dk.shiro.dao.PurchaseLogMapper;
import cn.dk.shiro.domain.AccessoryInfo;
import cn.dk.shiro.domain.AdminInfo;
import cn.dk.shiro.domain.PurchaseLog;
import cn.dk.shiro.realm.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.xml.internal.bind.v2.model.core.ID;
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
 * @Date:Create in 16:48:2019/3/19/019
 */
@Controller
public class PurchaseLogController {
    public  static  final Integer IN_APPROVAL = 0;
    public  static  final Integer PASS = 1;
    public  static  final Integer NOT_PASS = 2;

    @Autowired
    PurchaseLogMapper purchaseLogMapper;

    @Autowired
    AccessoryInfoMapper accessoryInfoMapper;


    @RequestMapping("listPurchaseLogInfo")
    @RequiresPermissions("purchaseLog:findAll")
    @PermissionName("查询所有进货信息")
    @ResponseBody
    public List<PurchaseLog> listPurchaseLogInfo(HttpServletRequest request, HttpServletResponse response){
        List<PurchaseLog> all = purchaseLogMapper.findAll();
        return all;
    }

    @RequestMapping(value ="selectPurchaseLogByParam", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("purchaseLog:findByParam")
    @PermissionName("通过条件查询进货信息")
    @ResponseBody
    public List<PurchaseLog> selectPurchaseLogByParam(HttpServletRequest request, HttpServletResponse response, String param, String value ) throws JsonProcessingException {
        System.out.println("--------selectPurchaseLogByParam--------");
        System.out.println("param:"+param+"   value:"+value);
        List<PurchaseLog> purchaseLogs = purchaseLogMapper.findByCondi(param, value);
        return purchaseLogs;
    }


    @RequestMapping(value ="addPurchaseLog", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("purchaseLog:addInfo")
    @PermissionName("添加进货信息")
    public String addPurchaseLog(HttpServletRequest request, HttpServletResponse response ,
                               Integer aId, Integer num) throws JsonProcessingException {
        //查询出配件信息 获取单价 防止篡改
        AccessoryInfo accessoryInfo = accessoryInfoMapper.selectByPrimaryKey(aId);
        //获取当前操作员信息
        AdminInfo adminInfo  = (AdminInfo) SecurityUtils.getSubject().getPrincipal();
        //计算总价
        double price = num * accessoryInfo.getaIncomingPrice();

        PurchaseLog purchaseLog = new PurchaseLog();
        purchaseLog.setaId(aId);
        purchaseLog.setRequestPeppleId(adminInfo.getAdId());
        purchaseLog.setLastentertime(new Date());
        purchaseLog.setNum(num);
        purchaseLog.setPrice(price);
        purchaseLog.setpStatus(IN_APPROVAL);
        int insert = purchaseLogMapper.insert(purchaseLog);
        if (insert <= 0){
            System.out.println("添加失败");
        }
        return "purchaselog_Manage";
    }

    @RequestMapping(value ="editPurchaseLogStatus", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("purchaseLog:editStatus")
    @PermissionName("审批进货信息")
    @Transactional
    public String editPurchaseLogStatus(HttpServletRequest request, HttpServletResponse response ,
                                 Integer countId, Integer status) throws JsonProcessingException {
        AdminInfo adminInfo = (AdminInfo) SecurityUtils.getSubject().getPrincipal();
        PurchaseLog purchaseLog = purchaseLogMapper.selectByPrimaryKey(countId);
        if (purchaseLog.getpStatus()!= 0){
            request.setAttribute("msg","已审批，不可再次审批！");
            return "approvalPurchaseLog_Manage";
        }
        //这里修改日志的状态
        int i = purchaseLogMapper.updatePStatusAndApproverIdByPrimaryKey(countId, status,adminInfo.getAdId());

        //获取进货数量
        Integer num = purchaseLog.getNum();
        //获取所进的配件的编号
        Integer id = purchaseLog.getaId();
        AccessoryInfo accessoryInfo = accessoryInfoMapper.selectByPrimaryKey(id);
        num = accessoryInfo.getaNum() + num;
        int i1 = accessoryInfoMapper.updateNumByPrimaryKey(num, id);

        if (i < 0 ){
            System.out.println("修改失败");
        }
        request.setAttribute("msg","审批成功！");
        return "approvalPurchaseLog_Manage";
    }
}
