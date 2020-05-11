package cn.dk.shiro.realm;

import cn.dk.shiro.realm.dao.IRoleDAO;
import cn.dk.shiro.realm.dao.IPermissionDAO;
import cn.dk.shiro.realm.dao.IUserDAO;
import cn.dk.shiro.domain.AdminInfo;


import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Setter
    private IUserDAO userDAO;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setRoleDAO(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void setPermissionDAO(IPermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

    @Setter
    private IRoleDAO roleDAO;

    @Setter
    private IPermissionDAO permissionDAO;

    //认证操作
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token中获取登录的用户名， 查询数据库返回用户信息

        Integer ad_id = Integer.parseInt(token.getPrincipal().toString()) ;
        AdminInfo user = userDAO.getUserByAdId(ad_id);

        if(user == null){
            return null;
        }
        System.out.println("盐:"+user.getAdId().toString()+"密码"+user.getAdPassword());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getAdPassword(),
                null,
                getName());
        return info;
    }



    @Override
    public String getName() {
        return "UserRealm";
    }

    //授权操作
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        AdminInfo user = (AdminInfo) principals.getPrimaryPrincipal();

        List<String> permissions = new ArrayList<String>();
        List<String> roles = new ArrayList<>();
        System.out.println("用户的权限为："+user.getAdType());
        if("1".equals(user.getAdType().toString())){
            //拥有所有权限
            permissions.add("*:*");
            //查询所有角色
            roles = roleDAO.getAllRoleSn();
        }else{
            //根据用户id查询该用户所具有的角色
            roles = roleDAO.getRoleSnByUserId(user.getAdType());
            //根据用户id查询该用户所具有的权限
            permissions = permissionDAO.getPermissionResourceByUserId(user.getAdType());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }

    //清除缓存
    public void clearCached() {
        //获取当前等的用户凭证，然后清除
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
