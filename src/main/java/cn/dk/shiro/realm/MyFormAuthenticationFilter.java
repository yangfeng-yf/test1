package cn.dk.shiro.realm;

import cn.dk.shiro.domain.AdminInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author:Create by dk
 * @Date:Create in 15:38:2019/3/10/010
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);
        WebUtils.redirectToSavedRequest(request,response,"/main");
        return false;
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
    {
        if (isLoginRequest(request, response))
        {
            if (isLoginSubmission(request, response))
            {
                //本次用户登陆账号
                String account = this.getUsername(request);

                Subject subject = this.getSubject(request, response);
                //之前登陆的用户
                AdminInfo user = (AdminInfo) subject.getPrincipal();
                //如果两次登陆的用户不一样，则先退出之前登陆的用户
                if (account != null && user != null && !account.equals(user.getAdId()))
                {
                    subject.logout();
                }
            }
        }

        return super.isAccessAllowed(request, response, mappedValue);
    }

}
