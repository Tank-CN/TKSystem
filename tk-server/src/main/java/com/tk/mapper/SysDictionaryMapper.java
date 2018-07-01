package com.tk.mapper;

import com.tk.model.SysDictionary;
import com.tk.model.SysDictionaryExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface SysDictionaryMapper {
    int countByExample(SysDictionaryExample example);

    int deleteByExample(SysDictionaryExample example);

    int deleteByPrimaryKey(Long iid);

    int insert(SysDictionary record);

    int insertSelective(SysDictionary record);

    List<SysDictionary> selectByExample(SysDictionaryExample example);

    SysDictionary selectByPrimaryKey(Long iid);

    int updateByExampleSelective(@Param("record") SysDictionary record, @Param("example") SysDictionaryExample example);

    int updateByExample(@Param("record") SysDictionary record, @Param("example") SysDictionaryExample example);

    int updateByPrimaryKeySelective(SysDictionary record);

    int updateByPrimaryKey(SysDictionary record);
}