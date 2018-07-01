package com.tk.manage;

import com.tk.mapper.SysUserRoleMapper;
import com.tk.model.SysUserRole;
import com.tk.model.SysUserRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional(readOnly = true)
public class SysUserRoleManage extends BaseManage {

	@Autowired
	SysUserRoleMapper sysUserRoleMapper;

	public List<SysUserRole> getByUserId(Integer userId) {
		SysUserRoleExample example = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(userId);
		return sysUserRoleMapper.selectByExample(example);
	}

	public int save(SysUserRole userRole) {
		return sysUserRoleMapper.insertSelective(userRole);
	}

	public int removeRoleByUserAndRole(Integer userId, Integer roleId) {
		SysUserRoleExample example = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRidEqualTo(roleId);
		criteria.andUidEqualTo(userId);
		return sysUserRoleMapper.deleteByExample(example);
	}
}
