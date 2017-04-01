package com.liuhy.bos.service.region;

import com.liuhy.bos.model.Region;

import java.util.List;

public interface RegionService {
    public void saveBatch(List<Region> list);
}
