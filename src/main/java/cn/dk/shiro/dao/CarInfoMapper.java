package cn.dk.shiro.dao;

import cn.dk.shiro.domain.CarInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CarInfoMapper {
    int deleteByPrimaryKey(Integer countId);

    int insert(CarInfo record);

    /**
     * 查询所有车辆信息
     * @return
     */
    List<CarInfo> findAll();

    /**
     * 条件查询车辆信息
     * @param param
     * @param value
     * @return
     */
    List<CarInfo> findCarInfoByCondi(@Param("param1")String param, @Param("value1")String value);

    int insertSelective(CarInfo record);

    List<Map> findAllType();

    CarInfo selectByPrimaryKey(Integer countId);

    int updateByPrimaryKeySelective(CarInfo record);

    int updateByPrimaryKey(CarInfo record);
}