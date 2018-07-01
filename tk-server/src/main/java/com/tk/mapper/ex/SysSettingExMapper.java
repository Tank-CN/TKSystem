package com.tk.mapper.ex;

import com.tk.mapper.SysSettingMapper;
import com.tk.util.annotation.BatisRepository;

import java.util.Map;

@BatisRepository
public interface SysSettingExMapper extends SysSettingMapper {
	
	void updateBatch(Map<String, Object> map);
	
}