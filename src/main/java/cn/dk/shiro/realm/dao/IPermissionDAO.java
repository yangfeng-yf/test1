package cn.dk.shiro.realm.dao;

import cn.dk.shiro.domain.Permission;

import java.util.List;

public interface IPermissionDAO {

    /**
     * 保存权限对象
     * @param permission
     */
    void save(Permission permission);

    /**
     * 获取员工的权限表达式
     * @param adType
     * @return
     */
    List<String> getPermissionResourceByUserId(long adType);


    List<String> getAllResources();
}
