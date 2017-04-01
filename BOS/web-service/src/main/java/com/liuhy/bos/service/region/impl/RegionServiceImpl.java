package com.liuhy.bos.service.region.impl;

import com.liuhy.bos.dao.region.RegionDao;
import com.liuhy.bos.model.Region;
import com.liuhy.bos.service.region.RegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {
    private RegionDao regionDao;

    @Resource
    public void setRegionDao(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    @Override
    public void saveBatch(List<com.liuhy.bos.model.Region> list) {
        for (Region region : list) {
            regionDao.saveOrUpdate(region);
        }
    }
}
