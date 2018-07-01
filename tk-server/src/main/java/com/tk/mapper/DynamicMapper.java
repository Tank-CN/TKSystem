package com.tk.mapper;

import com.tk.model.Dynamic;
import com.tk.model.DynamicExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface DynamicMapper {
    int countByExample(DynamicExample example);

    int deleteByExample(DynamicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Dynamic record);

    int insertSelective(Dynamic record);

    List<Dynamic> selectByExample(DynamicExample example);

    Dynamic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Dynamic record, @Param("example") DynamicExample example);

    int updateByExample(@Param("record") Dynamic record, @Param("example") DynamicExample example);

    int updateByPrimaryKeySelective(Dynamic record);

    int updateByPrimaryKey(Dynamic record);
}