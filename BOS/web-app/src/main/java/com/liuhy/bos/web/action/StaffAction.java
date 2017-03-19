package com.liuhy.bos.web.action;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    //接收ids参数
    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
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

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(pageBean);

        //将PageBean对象转为json返回
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    /*
     * 批量删除功能（逻辑删除）
     */
    public String delete() {
        staffService.deleteBatch(ids);
        return "list";
    }

    /*
     * 修改取派员信息
     */
    public String edit() {
        //显查询数据库中原始数据
        Staff staff = staffService.findById(this.getModel().getId());
        //再按照页面提交的参数进行覆盖
        staff.setName(this.getModel().getName());
        staff.setTelephone(this.getModel().getTelephone());
        staff.setStation(this.getModel().getStation());
        staff.setHaspda(this.getModel().getHaspda());
        staff.setStandard(this.getModel().getStandard());
        staffService.update(staff);
        return "list";
    }


}
