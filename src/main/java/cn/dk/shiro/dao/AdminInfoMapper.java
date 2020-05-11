package cn.dk.shiro.dao;

import cn.dk.shiro.domain.AdminInfo;
import cn.dk.shiro.domain.CarInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adId);

    List<AdminInfo> findAll();

    int insert(AdminInfo record);

    /**
     * 条件查询车辆信息
     * @param param
     * @param value
     * @return
     */
    List<AdminInfo> findAdminInfoByCondi(@Param("param1")String param, @Param("value1")String value);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);
}