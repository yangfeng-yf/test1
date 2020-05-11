package cn.dk.shiro.web.controller;

import cn.dk.shiro.dao.CarInfoMapper;
import cn.dk.shiro.domain.CarInfo;
import cn.dk.shiro.domain.UserInfo;
import cn.dk.shiro.realm.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mysql.jdbc.CacheAdapter;
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
 * @Date:Create in 15:20:2019/3/14/014
 */
@Controller
public class CarInfoController {

    @Autowired
    CarInfoMapper carInfoMapper;

    @RequestMapping(value="listCarInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("carInfo:findAll")
    @PermissionName("查询所有车辆信息")
    @ResponseBody
    public List<CarInfo> listCarInfo(HttpServletRequest request, HttpServletResponse response){
        List<CarInfo> all = carInfoMapper.findAll();
        return all;
    }


    @RequestMapping(value ="selectCarInfoByParam", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("carInfo:findByParam")
    @PermissionName("通过条件查询车辆信息")
    @ResponseBody
    public List<CarInfo> selectCarInfoByParam(HttpServletRequest request, HttpServletResponse response, String param, String value ) throws JsonProcessingException {
        System.out.println("--------selectCarInfoByParam--------");
        System.out.println("param:"+param+"   value:"+value);
        List<CarInfo> carInfoByCondi = carInfoMapper.findCarInfoByCondi(param, value);
        return carInfoByCondi;
    }


    @RequestMapping(value ="selectCarInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("carInfo:findById")
    @PermissionName("通过id查询车辆信息")
    @ResponseBody
    public CarInfo selectCarInfoById(HttpServletRequest request, HttpServletResponse response, Integer countId) throws JsonProcessingException {
        System.out.println("--------selectCarInfoById--------");
        CarInfo carInfo = carInfoMapper.selectByPrimaryKey(countId);
        return carInfo;
    }


    @RequestMapping(value ="editCarInfo", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("carInfo:edit")
    @PermissionName("修改车辆信息")
    public String editCarInfo(HttpServletRequest request, HttpServletResponse response ,String countId,String cId,String cUId ,
                                 String cTardemark, String cEngineNumber) throws JsonProcessingException {
        CarInfo carInfo =new CarInfo();
        carInfo.setCountId(Integer.parseInt(countId));
        carInfo.setcType(cId);
        carInfo.setcBrand(cId);
        carInfo.setcPerson(cTardemark);
        carInfo.setcCreatetime(new Date());
        Integer result = carInfoMapper.updateByPrimaryKey(carInfo);

        if (result == null || result <= 0){
            System.out.println("修改失败");
        }
        return "car_Manage";
    }
    @RequestMapping(value ="deleteCarInfoById", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions("carInfo:delById")
    @PermissionName("通过主键删除用户信息")
    @ResponseBody
    public List<CarInfo> deleteCarInfoById(HttpServletRequest request, HttpServletResponse response, String countId ) throws JsonProcessingException {
        System.out.println("--------deleteUserInfoById--------");

        int x = carInfoMapper.deleteByPrimaryKey(Integer.parseInt(countId));
        List<CarInfo> carInfos = carInfoMapper.findAll();
        return carInfos;
    }

}
