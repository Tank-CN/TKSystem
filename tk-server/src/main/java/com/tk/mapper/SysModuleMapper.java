package com.tk.mapper;

import com.tk.model.SysModule;
import com.tk.model.SysModuleExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface SysModuleMapper {
    int countByExample(SysModuleExample example);

    int deleteByExample(SysModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysModule record);

    int insertSelective(SysModule record);
    
    List<SysModule> selectByExample(SysModuleExample example);

    SysModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysModule record, @Param("example") SysModuleExample example);

    int updateByExample(@Param("record") SysModule record, @Param("example") SysModuleExample example);

    int updateByPrimaryKeySelective(SysModule record);

    int updateByPrimaryKey(SysModule record);
}