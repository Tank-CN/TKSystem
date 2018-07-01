package com.tk.manage;

import com.tk.mapper.ex.AdminExMapper;
import com.tk.model.Admin;
import com.tk.model.AdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class AdminManage extends BaseManage{

    @Autowired
	AdminExMapper adminExMapper;


	public List<Admin> getList(Integer pageNumber, Integer pageSize){
		AdminExample example=new AdminExample();
		example.setOrderByClause(getPage(pageNumber, pageSize));
		return adminExMapper.selectByExample(example);
	}

	public Integer count() {
		return adminExMapper.countByExample(null);
	}

    	/**
	 * 根据用户名得到Account
	 *
	 * @param username
	 * @return
	 */
	public Admin getAdminByUsername(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<Admin> admins = adminExMapper.selectByExample(example);
		if (admins.size() > 0) {
			return admins.get(0);
		} else {
			return null;
		}
	}


	public Admin getAdminById(Long id) {
		return adminExMapper.selectByPrimaryKey(id);
	}


	public Integer updateAdmin(Admin Account) {
		return adminExMapper.updateByPrimaryKeySelective(Account);
	}


	public int delete(Long id) {
		return adminExMapper.deleteByPrimaryKey(id);
	}


	/**
	 * 是否有相同的用户名 有返回false
	 *
	 * @param userName
	 * @return
	 */
	public Boolean haveSameUsername(String userName) {
		AdminExample example = new AdminExample();
		AdminExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		criteria.andUtypeEqualTo((byte) 0);
		List<Admin> accounts = adminExMapper.selectByExample(example);
		if (accounts.size() == 0) {
			return true;
		}
		return false;
	}

	public Admin save(Admin account) {
		int i = adminExMapper.insertBackId(account);
		if (i > 0 ) {
			return account;
		}
		return null;
	}

}
