package cn.dk.shiro.realm.dao;

import cn.dk.shiro.domain.AdminInfo;

public interface IUserDAO {
    /**
     * 通过用户名查找用户对象
     * @param ad_id
     * @return
     */
    AdminInfo getUserByAdId(int ad_id);
}
