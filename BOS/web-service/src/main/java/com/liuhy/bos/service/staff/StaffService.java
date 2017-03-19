package com.liuhy.bos.service.staff;

import com.liuhy.bos.model.Staff;
import com.liuhy.bos.utils.PageBean;

public interface StaffService {
    void save(Staff staff);

    void pageQuery(PageBean pageBean);

    void deleteBatch(String ids);

    Staff findById(String id);

    void update(Staff staff);
}
