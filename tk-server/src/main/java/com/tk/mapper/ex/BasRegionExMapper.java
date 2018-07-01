package com.tk.mapper.ex;

import com.tk.mapper.BasRegionMapper;
import com.tk.model.BasRegion;
import com.tk.util.annotation.BatisRepository;


@BatisRepository
public interface BasRegionExMapper extends BasRegionMapper{

    long insertBackId(BasRegion basRegion);
}
