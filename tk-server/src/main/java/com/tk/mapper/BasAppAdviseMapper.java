package com.tk.mapper;

import com.tk.model.BasAppAdvise;
import com.tk.model.BasAppAdviseExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasAppAdviseMapper {
    int countByExample(BasAppAdviseExample example);

    int deleteByExample(BasAppAdviseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasAppAdvise record);

    int insertSelective(BasAppAdvise record);

    List<BasAppAdvise> selectByExample(BasAppAdviseExample example);

    BasAppAdvise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasAppAdvise record, @Param("example") BasAppAdviseExample example);

    int updateByExample(@Param("record") BasAppAdvise record, @Param("example") BasAppAdviseExample example);

    int updateByPrimaryKeySelective(BasAppAdvise record);

    int updateByPrimaryKey(BasAppAdvise record);
}