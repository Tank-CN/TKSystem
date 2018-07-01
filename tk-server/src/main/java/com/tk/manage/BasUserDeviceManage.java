package com.tk.manage;

import com.tk.mapper.ex.BasUserDeviceExMapper;
import com.tk.model.BasUserDevice;
import com.tk.model.BasUserDeviceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class BasUserDeviceManage {

    @Autowired
    BasUserDeviceExMapper basUserDeviceExMapper;

    public BasUserDevice getByUid(Long uid) {
        BasUserDeviceExample example = new BasUserDeviceExample();
        BasUserDeviceExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<BasUserDevice> list = basUserDeviceExMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public int save(BasUserDevice device) {
        return basUserDeviceExMapper.insertSelective(device);
    }

    public int update(BasUserDevice device) {
        BasUserDeviceExample example = new BasUserDeviceExample();
        BasUserDeviceExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(device.getUid());
        return basUserDeviceExMapper.updateByExampleSelective(device, example);
    }

    public int deleteByUid(Long uid) {
        BasUserDeviceExample example = new BasUserDeviceExample();
        BasUserDeviceExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        return basUserDeviceExMapper.deleteByExample(example);
    }
}
