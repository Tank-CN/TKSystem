package com.tk.mapper.ex;

import com.tk.mapper.SysDictionaryMapper;
import com.tk.model.SysDictionary;
import com.tk.util.annotation.BatisRepository;


@BatisRepository
public interface SysDictionaryExMapper extends SysDictionaryMapper {

    int selectMaxIID(Integer parentId);

    int selectMaxCID();
    
    int insertAndGetId(SysDictionary sysDictionary);
}
