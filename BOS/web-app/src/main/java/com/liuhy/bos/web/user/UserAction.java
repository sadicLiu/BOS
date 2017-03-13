package com.liuhy.bos.web.user;

import com.liuhy.bos.model.User;
import com.liuhy.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {


}
