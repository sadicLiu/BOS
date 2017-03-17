package com.liuhy.bos.web.action;


import com.liuhy.bos.model.Staff;
import com.liuhy.bos.utils.PageBean;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * 取派员管理
 *
 * @author zhaoqx
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    private int page;    // 当前页
    private int rows;    // 一共多少条记录

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * 添加取派员
     */
    public String add() {
        staffService.save(this.getModel());
        return "list";
    }

    public String pageQuery() throws IOException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        staffService.pageQuery(pageBean);

        //todo 用Google的json把对象转换成字符串
        //将PageBean对象转为json返回
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"currentPage", "detachedCriteria", "pageSize"});
        JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
        String json = jsonObject.toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

}
