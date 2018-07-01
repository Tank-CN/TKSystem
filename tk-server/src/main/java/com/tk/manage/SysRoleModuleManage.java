package com.tk.manage;

import com.tk.mapper.SysRoleModuleMapper;
import com.tk.model.SysRoleModule;
import com.tk.model.SysRoleModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional(readOnly = true)
public class SysRoleModuleManage {

	@Autowired
	SysRoleModuleMapper sysRoleModuleMapper;

	public int save(SysRoleModule roleModule) {
		return sysRoleModuleMapper.insert(roleModule);
	}

	public List<SysRoleModule> getByRole(Integer roleId) {
		SysRoleModuleExample example = new SysRoleModuleExample();
		SysRoleModuleExample.Criteria criteria = example.createCriteria();
		criteria.andRidEqualTo(roleId);
		return sysRoleModuleMapper.selectByExample(example);
	}

	public int deleteByRoleAndModule(Integer roleId, Integer moduleId) {
		SysRoleModuleExample example = new SysRoleModuleExample();
		SysRoleModuleExample.Criteria criteria = example.createCriteria();
		criteria.andMidEqualTo(moduleId);
		criteria.andRidEqualTo(roleId);
		return sysRoleModuleMapper.deleteByExample(example);
	}

	public SysRoleModule getById(Integer id) {
		return sysRoleModuleMapper.selectByPrimaryKey(id);
	}

}
