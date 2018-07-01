package com.tk.manage;

import com.tk.mapper.ex.SysSettingExMapper;
import com.tk.model.SysSetting;
import com.tk.model.SysSettingExample;
import com.tk.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Transactional(readOnly = true)
public class SysSettingManage {

	@Autowired
	SysSettingExMapper settingMapper;

	public SysSetting findById(Long id) {
		return settingMapper.selectByPrimaryKey(id);
	}

	public int save(SysSetting vo) {
		return settingMapper.insert(vo);
	}

	public int update(SysSetting vo) {
		return settingMapper.updateByPrimaryKeySelective(vo);
	}

	public int delete(Long id) {
		return settingMapper.deleteByPrimaryKey(id);
	}

	public SysSetting getSettingtByCode(String code) {
		SysSettingExample example = new SysSettingExample();
		SysSettingExample.Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		List<SysSetting> list = settingMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<SysSetting> getByCodes(List<String> codes) {
		SysSettingExample example = new SysSettingExample();
		SysSettingExample.Criteria criteria = example.createCriteria();
		criteria.andCodeIn(codes);
		return settingMapper.selectByExample(example);
	}

	/**
	 * 返回所有系统setting,并按module分组
	 * @return
	 */
	public Map<String, List<SysSetting>> groupByModule() {
		SysSettingExample example = new SysSettingExample();
		SysSettingExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo((byte) 1);
		Map<String, List<SysSetting>> map = new HashMap<String, List<SysSetting>>();
		List<SysSetting> list = settingMapper.selectByExample(example);
		for (SysSetting sysSetting : list) {
			List<SysSetting> moduleList = map.get(sysSetting.getModule());
			if (null != moduleList) {
				moduleList.add(sysSetting);
				map.put(sysSetting.getModule(), moduleList);
			} else {
				moduleList = new ArrayList<>();
				moduleList.add(sysSetting);
				map.put(sysSetting.getModule(), moduleList);
			}
		}
		return map;
	}

	public Map<String, Object> getSettings() {
		SysSettingExample example = new SysSettingExample();
		SysSettingExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo((byte) 1);
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysSetting> list = settingMapper.selectByExample(example);
		for (SysSetting sysSetting : list) {
			if (sysSetting.getModule().equals("region")) {
				map.put(sysSetting.getCode() + "id", sysSetting.getText());
				map.put(sysSetting.getCode(), sysSetting.getMemo());
			} else {
				map.put(sysSetting.getCode(), sysSetting.getText());
			}
		}
		return map;
	}

	public void updateBatch(List<SysSetting> list) {
		if (CommonUtils.isNotEmpty(list)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("settings", list);
			settingMapper.updateBatch(map);
		}
	}

	public int updateByCode(SysSetting record) {
		SysSettingExample example = new SysSettingExample();
		SysSettingExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo((byte) 1).andCodeEqualTo(record.getCode());
		return settingMapper.updateByExampleSelective(record, example);
	}
	
}
