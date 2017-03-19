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
        staffDao.pageQuery(pageBean);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(String ids) {
        String[] staffIds = ids.split(",");
        for (String id : staffIds) {
            staffDao.executeUpdate("staff.delete", id);
        }
    }

    @Override
    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }

}
