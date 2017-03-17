package com.liuhy.bos.service.staff.impl;

import com.liuhy.bos.dao.staff.StaffDao;
import com.liuhy.bos.model.Staff;
import com.liuhy.bos.service.staff.StaffService;
import com.liuhy.bos.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("staffService")
@Transactional
public class StaffServiceImpl implements StaffService {

    private StaffDao staffDao;

    @Resource
    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    @Override
    public void save(Staff staff) {
        staffDao.save(staff);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        //todo 实现dao之后调用
        staffDao.pageQuery(pageBean);
    }
}
