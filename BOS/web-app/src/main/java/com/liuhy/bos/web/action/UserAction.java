package com.liuhy.bos.web.action;

import com.liuhy.bos.model.User;
import com.liuhy.bos.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

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
                this.putToSession("loginUser", user);
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

    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return "login";
    }

    /*
    * 修改密码
    * */
    public String editPassword() throws IOException {
        User user = (User) session.get("loginUser");
        user.setPassword(MD5Utils.md5(this.getModel().getPassword()));

        String flag = "1";
        try {
            userService.editPassword(user);
        }
        catch (Exception e) {
            flag = "0";
            e.printStackTrace();
        }

        ServletActionContext.getResponse().setContentType("text/html");
        ServletActionContext.getResponse().getWriter().print(flag);
        return NONE;
    }
}
