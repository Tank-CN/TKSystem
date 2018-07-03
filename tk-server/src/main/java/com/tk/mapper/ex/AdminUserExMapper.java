package com.tk.mapper.ex;

import com.tk.mapper.AdminUserMapper;
import com.tk.model.AdminUser;
import com.tk.util.annotation.BatisRepository;

/**
 *
 */
@BatisRepository
public interface AdminUserExMapper extends AdminUserMapper{

    int insertBackId(AdminUser record);
}
