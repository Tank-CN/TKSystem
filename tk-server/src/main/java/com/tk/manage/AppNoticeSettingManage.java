package com.tk.manage;

import com.tk.mapper.AppNoticeSettingMapper;
import com.tk.model.AppNoticeSetting;
import com.tk.model.AppNoticeSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional(readOnly = true)
public class AppNoticeSettingManage {

    @Autowired
    AppNoticeSettingMapper appNoticeSettingMapper;

    public int save(AppNoticeSetting record) {
        return appNoticeSettingMapper.insertSelective(record);
    }

    public int update(AppNoticeSetting record) {
        AppNoticeSettingExample example = new AppNoticeSettingExample();
        AppNoticeSettingExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(record.getUid());
        return appNoticeSettingMapper.updateByExampleSelective(record, example);
    }

    public AppNoticeSetting findByUid(Long uid) {
        AppNoticeSettingExample example = new AppNoticeSettingExample();
        AppNoticeSettingExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<AppNoticeSetting> list = appNoticeSettingMapper
                .selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
