package learn.caojw.eppsp.framework.web.service;

import learn.caojw.eppsp.common.constant.CacheConstants;
import learn.caojw.eppsp.common.constant.Constants;
import learn.caojw.eppsp.common.constant.UserConstants;
import learn.caojw.eppsp.common.core.domain.entity.SysUser;
import learn.caojw.eppsp.common.core.domain.model.RegisterBody;
import learn.caojw.eppsp.common.core.redis.RedisCache;
import learn.caojw.eppsp.common.exception.user.CaptchaException;
import learn.caojw.eppsp.common.exception.user.CaptchaExpireException;
import learn.caojw.eppsp.common.utils.MessageUtils;
import learn.caojw.eppsp.common.utils.SecurityUtils;
import learn.caojw.eppsp.common.utils.StringUtils;
import learn.caojw.eppsp.framework.manager.AsyncManager;
import learn.caojw.eppsp.framework.manager.factory.AsyncFactory;
import learn.caojw.eppsp.system.service.ISysConfigService;
import learn.caojw.eppsp.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 注册校验方法
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
@Service
public class SysRegisterService {
    private final ISysUserService userService;
    private final ISysConfigService configService;
    private final RedisCache redisCache;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody) {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);

        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled) {
            validateCaptcha(registerBody.getCode(), registerBody.getUuid());
        }

        if (StringUtils.isEmpty(username)) {
            msg = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (!userService.checkUserNameUnique(sysUser)) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        } else {
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            sysUser.setDeptId(3L);
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag) {
                msg = "注册失败,请联系系统管理人员";
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     *
     * @param code 验证码
     * @param uuid 唯一标识
     */
    public void validateCaptcha(String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }
    }
}
