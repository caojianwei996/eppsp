package learn.caojw.eppsp.controller.system;

import learn.caojw.eppsp.common.core.controller.BaseController;
import learn.caojw.eppsp.common.core.domain.AjaxResult;
import learn.caojw.eppsp.common.core.domain.model.RegisterBody;
import learn.caojw.eppsp.common.utils.StringUtils;
import learn.caojw.eppsp.framework.web.service.SysRegisterService;
import learn.caojw.eppsp.system.service.ISysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册验证
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
@RestController
public class SysRegisterController extends BaseController {
    private final SysRegisterService registerService;
    private final ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
