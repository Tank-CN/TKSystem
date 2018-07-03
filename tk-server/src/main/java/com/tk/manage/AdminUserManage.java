package com.tk.manage;

import com.tk.mapper.ex.AdminUserExMapper;
import com.tk.model.AdminUser;
import com.tk.model.AdminUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class AdminUserManage extends BaseManage{

    @Autowired
	AdminUserExMapper adminUserExMapper;


	public List<AdminUser> getList(Integer pageNumber, Integer pageSize){
		AdminUserExample example=new AdminUserExample();
		example.setOrderByClause(getPage(pageNumber, pageSize));
		return adminUserExMapper.selectByExample(example);
	}

	public Integer count() {
		return adminUserExMapper.countByExample(null);
	}

    	/**
	 * 根据用户名得到Account
	 *
	 * @param username
	 * @return
	 */
	public AdminUser getAdminUserByUsername(String username) {
        AdminUserExample example = new AdminUserExample();
        AdminUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<AdminUser> AdminUsers = adminUserExMapper.selectByExample(example);
		if (AdminUsers.size() > 0) {
			return AdminUsers.get(0);
		} else {
			return null;
		}
	}


	public AdminUser getAdminUserById(Long id) {
		return adminUserExMapper.selectByPrimaryKey(id);
	}


	public Integer updateAdminUser(AdminUser Account) {
		return adminUserExMapper.updateByPrimaryKeySelective(Account);
	}


	public int delete(Long id) {
		return adminUserExMapper.deleteByPrimaryKey(id);
	}


	/**
	 * 是否有相同的用户名 有返回false
	 *
	 * @param userName
	 * @return
	 */
	public Boolean haveSameUsername(String userName) {
		AdminUserExample example = new AdminUserExample();
		AdminUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		criteria.andUtypeEqualTo((byte) 0);
		List<AdminUser> accounts = adminUserExMapper.selectByExample(example);
		if (accounts.size() == 0) {
			return true;
		}
		return false;
	}

	public AdminUser save(AdminUser account) {
		int i = adminUserExMapper.insertBackId(account);
		if (i > 0 ) {
			return account;
		}
		return null;
	}

}
