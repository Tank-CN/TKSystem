package com.tk.mapper.ex;

import com.tk.mapper.BasUserDeviceMapper;
import com.tk.model.BasUserDevice;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BasUserDeviceExMapper extends BasUserDeviceMapper {

    int insertBackId(BasUserDevice record);
}
