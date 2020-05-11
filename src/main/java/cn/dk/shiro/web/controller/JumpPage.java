package cn.dk.shiro.web.controller;

import cn.dk.shiro.realm.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:Create by dk
 * @Date:Create in 20:22:2019/3/10/010
 */
@Controller
public class JumpPage {

    @RequestMapping("jumpIndexHome")
    @RequiresPermissions("jumpIndexHome")
    @PermissionName("跳转主页")
    public String jumpIndexHome(HttpServletRequest request, HttpServletResponse response){
        return "index_home";
    }

    @RequestMapping("jumpProductManage")
    @RequiresPermissions("jumpProductManage")
    @PermissionName("跳转客户信息")
    public String jumpProductManage(HttpServletRequest request, HttpServletResponse response){
        return "product_Manage";
    }

    @RequestMapping("jumpMrLogManage")
    @RequiresPermissions("jumpMrLogManage")
    @PermissionName("跳转我的订单管理")
    public String jumpMrLogManage(HttpServletRequest request, HttpServletResponse response){
        return "mrlog_Manage";
    }


    @RequestMapping("jumpCarManage")
    @RequiresPermissions("jumpCarManage")
    @PermissionName("跳转车辆信息")
    public String jumpCarManage(HttpServletRequest request, HttpServletResponse response){
        return "car_Manage";
    }

    @RequestMapping("jumpAddUserInfo")
    @RequiresPermissions("jumpAddUserInfo")
    @PermissionName("跳转添加用户信息")
    public String jumpAddUserInfo(HttpServletRequest request, HttpServletResponse response){
        return "addUserInfo";
    }

    @RequestMapping("jumpEditUserInfo")
    @RequiresPermissions("jumpEditUserInfo")
    @PermissionName("跳转修改用户信息设置")
    public String jumpEditUserInfo(HttpServletRequest request, HttpServletResponse response,String uId){
        request.setAttribute("uId",uId);
        return "editUserInfo";
    }
    @RequestMapping("jumpEditCarInfo")
    @RequiresPermissions("jumpEditCarInfo")
    @PermissionName("跳转修改车辆信息设置")
    public String jumpEditCarInfo(HttpServletRequest request, HttpServletResponse response,String countId){
        request.setAttribute("countId",countId);
        return "editCarInfo";
    }
    @RequestMapping("jumpEditAdminInfo")
    @RequiresPermissions("jumpEditAdminInfo")
    @PermissionName("跳转修改管理员信息设置")
    public String jumpEditAdminInfo(HttpServletRequest request, HttpServletResponse response,String adId){
        request.setAttribute("adId",adId);
        return "editAdminInfo";
    }

    @RequestMapping("jumpAdminInfo")
    @RequiresPermissions("jumpAdminInfo")
    @PermissionName("转跳管理员个人信息")
    public String jumpAdminInfo(HttpServletRequest request, HttpServletResponse response){
        return "adminInfo";
    }

    @RequestMapping("jumplistAdminInfo")
    @RequiresPermissions("jumplistAdminInfo")
    @PermissionName("转跳管理员信息")
    public String jumplistAdminInfo(HttpServletRequest request, HttpServletResponse response){
        return "admin_Manage";
    }

    @RequestMapping("jumpListAccessoryInfo")
    @RequiresPermissions("jumpListAccessoryInfo")
    @PermissionName("转跳配件信息")
    public String jumpListAccessoryInfo(HttpServletRequest request, HttpServletResponse response){
        return "accessory_Manage";
    }

    @RequestMapping("jumplistSupplierInfo")
    @RequiresPermissions("jumplistSupplierInfo")
    @PermissionName("转跳供应商信息")
    public String jumplistSupplierInfo(HttpServletRequest request, HttpServletResponse response){
        return "supplier_Manage";
    }

    @RequestMapping("jumpAddAdminInfo")
    @RequiresPermissions("jumpAddAdminInfo")
    @PermissionName("转跳添加管理员")
    public String jumpAddAdminInfo(HttpServletRequest request, HttpServletResponse response){
        return "addAdminInfo";
    }

    @RequestMapping("jumpAddSupplierInfo")
    @RequiresPermissions("jumpAddSupplierInfo")
    @PermissionName("转跳添加供货商")
    public String jumpAddSupplierInfo(HttpServletRequest request, HttpServletResponse response){
        return "addSupplierInfo";
    }
    @RequestMapping("jumpEditSupplierInfo")
    @RequiresPermissions("jumpEditSupplierInfo")
    @PermissionName("跳转修改供货商信息设置")
    public String jumpEditSupplierInfo(HttpServletRequest request, HttpServletResponse response,String sId){
        request.setAttribute("sId",sId);
        return "editSupplierInfo";
    }

    @RequestMapping("jumpPurchaseLog")
    @RequiresPermissions("jumpPurchaseLog")
    @PermissionName("转跳供货日志")
    public String jumpPurchaseLog(HttpServletRequest request, HttpServletResponse response){
        return "purchaselog_Manage";
    }

    @RequestMapping("jumpAddAccessoryInfo")
    @RequiresPermissions("jumpAddAccessoryInfo")
    @PermissionName("转跳添加配件信息")
    public String jumpAddAccessoryInfo(HttpServletRequest request, HttpServletResponse response){
        return "addAccessoryInfo";
    }

    @RequestMapping("jumpEditAccessoryInfo")
    @RequiresPermissions("jumpEditAccessoryInfo")
    @PermissionName("转跳修改配件信息")
    public String jumpEditAccessoryInfo(HttpServletRequest request, HttpServletResponse response,String aId){
        request.setAttribute("aId",aId);
        return "editAccessoryInfo";
    }

    @RequestMapping("jumpAddPurchaseLogInfo")
    @RequiresPermissions("jumpAddPurchaseLogInfo")
    @PermissionName("转跳进货")
    public String jumpAddMrLogInfo(HttpServletRequest request, HttpServletResponse response){
        return "addPurchaseLogInfo";
    }
    @RequestMapping("jumpAddUseAccessoryiesLogInfo")
    @RequiresPermissions("jumpAddUseAccessoryiesLogInfo")
    @PermissionName("转跳申请配件")
    public String jumpAddUseAccessoryiesLogInfo(HttpServletRequest request, HttpServletResponse response){
        return "addUseAccessoryiesLogInfo";
    }

    @RequestMapping("jumpApprovalPurchaseLogManage")
    @RequiresPermissions("jumpApprovalPurchaseLogManage")
    @PermissionName("转跳进货审批")
    public String jumpApprovalPurchaseLogManage(HttpServletRequest request, HttpServletResponse response){
        return "approvalPurchaseLog_Manage";
    }



    @RequestMapping("jumpUseAccessoriesLogManage")
    @RequiresPermissions("jumpUseAccessoriesLogManage")
    @PermissionName("转跳配件申请审批")
    public String jumpUseAccessoriesLogManage(HttpServletRequest request, HttpServletResponse response){
        return "useAccessoriesLog_Manage";
    }


    @RequestMapping("jumpMyUseAccessoriesLogManage")
    @RequiresPermissions("jumpMyUseAccessoriesLogManage")
    @PermissionName("转跳配件申请日志(维修)")
    public String jumpMyUseAccessoriesLogManage(HttpServletRequest request, HttpServletResponse response){
        return "myUseAccessoriesLog_Manage";
    }

    @RequestMapping("jumpChangeType")
    @RequiresPermissions("jumpChangeType")
    @PermissionName("转跳改变维修状态")
    public String jumpChangeType(HttpServletRequest request, HttpServletResponse response){
        return "changeMrLogType";
    }
}
