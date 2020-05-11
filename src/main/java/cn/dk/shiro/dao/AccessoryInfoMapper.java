package cn.dk.shiro.dao;

import cn.dk.shiro.domain.AccessoryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccessoryInfoMapper {
    int deleteByPrimaryKey(Integer aId);

    List<AccessoryInfo> findAll();

    List<AccessoryInfo> findByCondi(@Param("param1")String param, @Param("value1")String value);

    /**
     * 通过主键更改配件的数量
     * @param aNum
     * @param aId
     * @return
     */
    int  updateNumByPrimaryKey(@Param("aNum") Integer aNum, @Param("aId")Integer aId);

    int insert(AccessoryInfo record);

    int insertSelective(AccessoryInfo record);

    AccessoryInfo selectByPrimaryKey(Integer aId);

    int updateByPrimaryKeySelective(AccessoryInfo record);

    int updateByPrimaryKey(AccessoryInfo record);

    AccessoryInfo selectByName(String accessoryName);
}