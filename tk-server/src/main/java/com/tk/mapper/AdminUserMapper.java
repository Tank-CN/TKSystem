package com.tk.mapper;

import com.tk.model.AdminUser;
import com.tk.model.AdminUserExample;
import java.util.List;

import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

@BatisRepository
public interface AdminUserMapper {
    int countByExample(AdminUserExample example);

    int deleteByExample(AdminUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    List<AdminUser> selectByExample(AdminUserExample example);

    AdminUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByExample(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}