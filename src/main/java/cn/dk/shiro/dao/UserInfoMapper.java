package cn.dk.shiro.dao;

import cn.dk.shiro.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    int deleteByPrimaryKey(@Param("uId") Integer uId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer uId);

    List<Map> findAllType();

    List<UserInfo> selectByCondition(@Param("param1") String param1, @Param("value1") String value1);

    List<UserInfo> selectAll();

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}