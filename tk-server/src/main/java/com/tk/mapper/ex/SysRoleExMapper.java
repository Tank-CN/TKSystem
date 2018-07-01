package com.tk.mapper.ex;


import com.tk.mapper.SysRoleMapper;
import com.tk.model.SysRole;
import com.tk.util.annotation.BatisRepository;

@BatisRepository
public interface SysRoleExMapper extends SysRoleMapper {

    int insertBackId(SysRole record);
}