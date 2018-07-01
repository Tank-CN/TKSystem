package com.tk.manage;

import com.tk.mapper.ex.SysRoleExMapper;
import com.tk.model.SysRole;
import com.tk.model.SysRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional(readOnly = true)
public class SysRoleManage extends BaseManage {

	@Autowired
	SysRoleExMapper sysRoleExMapper;


	public List<SysRole> selectRolesByIds(List<Integer> rids) {
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		criteria.andFlagEqualTo(new Byte("0"));
		criteria.andIdIn(rids);
		return sysRoleExMapper.selectByExample(example);
	}


	public List<SysRole> list(Integer pageNumber, Integer pageSize) {
		SysRoleExample example=new SysRoleExample();
		example.setOrderByClause(getPage(pageNumber, pageSize));
		return sysRoleExMapper.selectByExample(example);
	}

	public Integer count() {
		return sysRoleExMapper.countByExample(null);
	}

	public SysRole getSysRole(int aid) {
		return (SysRole) sysRoleExMapper.selectByPrimaryKey(aid);
	}

	public Integer saveSysRole(SysRole sysRole) {
		sysRoleExMapper.insertBackId(sysRole);
		return sysRole.getId();
	}

	public Integer updateSysRole(SysRole sysRole) {
		return sysRoleExMapper.updateByPrimaryKeySelective(sysRole);
	}

	public int disable(int id) {
		SysRole role = new SysRole();
		role.setId(id);
		role.setFlag(Byte.valueOf("0"));
		return sysRoleExMapper.updateByPrimaryKeySelective(role);
	}
	
	
	public int enable(int id) {
		SysRole role = new SysRole();
		role.setId(id);
		role.setFlag(Byte.valueOf("1"));
		return sysRoleExMapper.updateByPrimaryKeySelective(role);
	}

}
