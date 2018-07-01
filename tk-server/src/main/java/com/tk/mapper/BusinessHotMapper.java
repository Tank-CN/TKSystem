package com.tk.mapper;

import com.tk.model.BusinessHot;
import com.tk.model.BusinessHotExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BusinessHotMapper {
    int countByExample(BusinessHotExample example);

    int deleteByExample(BusinessHotExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessHot record);

    int insertSelective(BusinessHot record);

    List<BusinessHot> selectByExample(BusinessHotExample example);

    BusinessHot selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusinessHot record, @Param("example") BusinessHotExample example);

    int updateByExample(@Param("record") BusinessHot record, @Param("example") BusinessHotExample example);

    int updateByPrimaryKeySelective(BusinessHot record);

    int updateByPrimaryKey(BusinessHot record);
}