package cn.dk.shiro.dao;

import cn.dk.shiro.domain.SupplierInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierInfoMapper {
    int deleteByPrimaryKey(Integer sId);

    List<SupplierInfo> findAll();

    List<SupplierInfo> findSupplierInfoByCondi(@Param("param1") String param, @Param("value1") String value1);
    int insert(SupplierInfo record);

    int insertSelective(SupplierInfo record);

    SupplierInfo selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(SupplierInfo record);

    int updateByPrimaryKey(SupplierInfo record);
}