package com.tk.mapper.ex;

import com.tk.mapper.SysModuleMapper;
import com.tk.model.SysModule;
import com.tk.util.annotation.BatisRepository;

import java.util.List;


@BatisRepository
public interface SysModuleExMapper extends SysModuleMapper {

    List<SysModule> selectByUser(Long userId);
    
    List<SysModule> selectByRole(Long rid);
}
