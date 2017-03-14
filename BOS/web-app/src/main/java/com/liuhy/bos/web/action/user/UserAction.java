package com.liuhy.bos.web.action.user;

import com.liuhy.bos.model.User;
import com.liuhy.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String login() {
        // 如果验证码正确
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals((String) session.get("key"))) {
            User user = userService.login(this.getModel());
            if (null != user) {
                // 如果要登录的用户存在
                return "home";
            } else {
                // 如果要登录的用户不存在
                this.addActionError(this.getText("loginError"));
            }
        } else {    // 如果验证码不正确
            this.addActionError(this.getText("validateCodeError"));
        }

        return "login";
    }
}
