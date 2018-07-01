package com.tk.mapper;

import com.tk.model.BusinessCollect;
import com.tk.model.BusinessCollectExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BusinessCollectMapper {
    int countByExample(BusinessCollectExample example);

    int deleteByExample(BusinessCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessCollect record);

    int insertSelective(BusinessCollect record);

    List<BusinessCollect> selectByExample(BusinessCollectExample example);

    BusinessCollect selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusinessCollect record, @Param("example") BusinessCollectExample example);

    int updateByExample(@Param("record") BusinessCollect record, @Param("example") BusinessCollectExample example);

    int updateByPrimaryKeySelective(BusinessCollect record);

    int updateByPrimaryKey(BusinessCollect record);
}