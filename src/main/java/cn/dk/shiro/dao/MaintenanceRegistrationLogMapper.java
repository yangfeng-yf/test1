package cn.dk.shiro.dao;

import cn.dk.shiro.domain.CarInfo;
import cn.dk.shiro.domain.MaintenanceRegistrationLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MaintenanceRegistrationLogMapper {
    int deleteByPrimaryKey(Integer mId);

    /**
     * 条件查询维修记录信息
     * @param param
     * @param value
     * @return
     */
    List<MaintenanceRegistrationLog> findMrInfoByCondi(@Param("param1")String param, @Param("value1")String value);

    /**
     * 多条件查询
     * @param maintenanceRegistrationLog
     * @return
     */
    List<MaintenanceRegistrationLog> findMrInfoByObj(MaintenanceRegistrationLog maintenanceRegistrationLog);
    /**
     * 查询所有维修记录信息
     * @return
     */
    List<MaintenanceRegistrationLog> findAll();

    /**
     * 入库
     * @param countId
     * @param date
     * @param type
     * @return
     */
    int  updateTypeAndInDateByPrimaryKey(@Param("countId") Integer countId, @Param("mdate")Date date, @Param("type")Integer type,@Param("mMaintenancemanId")Integer mMaintenancemanId);

    /**
     * 出库
     * @param countId
     * @param date
     * @param type
     * @return
     */
    int  updateTypeAndOutDateByPrimaryKey(@Param("countId") Integer countId, @Param("mdate")Date date, @Param("type")Integer type,@Param("mMaintenancemanId")Integer mMaintenancemanId);

    List<Map> findAllType();


    int insert(MaintenanceRegistrationLog record);

    int insertSelective(MaintenanceRegistrationLog record);

    MaintenanceRegistrationLog selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(MaintenanceRegistrationLog record);

    int updateByPrimaryKey(MaintenanceRegistrationLog record);
}