package com.tk.mapper;

import com.tk.model.SysRoleModule;
import com.tk.model.SysRoleModuleExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface SysRoleModuleMapper {
    int countByExample(SysRoleModuleExample example);

    int deleteByExample(SysRoleModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleModule record);

    int insertSelective(SysRoleModule record);

    List<SysRoleModule> selectByExample(SysRoleModuleExample example);

    SysRoleModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRoleModule record, @Param("example") SysRoleModuleExample example);

    int updateByExample(@Param("record") SysRoleModule record, @Param("example") SysRoleModuleExample example);

    int updateByPrimaryKeySelective(SysRoleModule record);

    int updateByPrimaryKey(SysRoleModule record);
}