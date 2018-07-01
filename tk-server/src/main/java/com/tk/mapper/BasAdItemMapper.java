package com.tk.mapper;

import com.tk.model.BasAdItem;
import com.tk.model.BasAdItemExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasAdItemMapper {
    int countByExample(BasAdItemExample example);

    int deleteByExample(BasAdItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasAdItem record);

    int insertSelective(BasAdItem record);

    List<BasAdItem> selectByExampleWithBLOBs(BasAdItemExample example);

    List<BasAdItem> selectByExample(BasAdItemExample example);

    BasAdItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasAdItem record, @Param("example") BasAdItemExample example);

    int updateByExampleWithBLOBs(@Param("record") BasAdItem record, @Param("example") BasAdItemExample example);

    int updateByExample(@Param("record") BasAdItem record, @Param("example") BasAdItemExample example);

    int updateByPrimaryKeySelective(BasAdItem record);

    int updateByPrimaryKeyWithBLOBs(BasAdItem record);

    int updateByPrimaryKey(BasAdItem record);
}