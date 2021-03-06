package com.liuhy.bos.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {

        Object loginUser = ActionContext.getContext().getSession().get("loginUser");
        if (null == loginUser) {
            return "login";
        }

        return invocation.invoke();
    }
}
