package cn.dk.shiro.dao;

import cn.dk.shiro.domain.AccessoryInfo;
import cn.dk.shiro.domain.UseAccessoriesLog;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UseAccessoriesLogMapper {
    int deleteByPrimaryKey(Integer countId);

    int insert(UseAccessoriesLog record);

    List<UseAccessoriesLog> findAll();

    List<UseAccessoriesLog> findByCondi(@Param("param1")String param, @Param("value1")String value);

    int updateTypeByPrimaryKey(@Param("countId") Integer countId, @Param("getType")Integer getType);

//    Map<String,String> findAllType();

    int insertSelective(UseAccessoriesLog record);

    UseAccessoriesLog selectByPrimaryKey(Integer countId);

    int updateByPrimaryKeySelective(UseAccessoriesLog record);

    int updateByPrimaryKey(UseAccessoriesLog record);
}