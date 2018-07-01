package com.tk.mapper.ex;

import com.tk.mapper.AppNoticeSettingMapper;
import com.tk.model.AppNoticeSetting;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface AppNoticeSettingExMapper extends AppNoticeSettingMapper {

    int insertBackId(AppNoticeSetting record);
}
