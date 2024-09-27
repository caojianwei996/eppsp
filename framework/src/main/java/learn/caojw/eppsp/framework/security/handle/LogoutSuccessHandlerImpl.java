package learn.caojw.eppsp.framework.security.handle;

import com.alibaba.fastjson2.JSON;
import learn.caojw.eppsp.common.constant.Constants;
import learn.caojw.eppsp.common.core.domain.AjaxResult;
import learn.caojw.eppsp.common.core.domain.model.LoginUser;
import learn.caojw.eppsp.common.utils.MessageUtils;
import learn.caojw.eppsp.common.utils.ServletUtils;
import learn.caojw.eppsp.common.utils.StringUtils;
import learn.caojw.eppsp.framework.manager.AsyncManager;
import learn.caojw.eppsp.framework.manager.factory.AsyncFactory;
import learn.caojw.eppsp.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success(MessageUtils.message("user.logout.success"))));
    }
}
