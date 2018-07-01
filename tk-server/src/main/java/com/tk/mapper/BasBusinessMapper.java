package com.tk.mapper;

import com.tk.model.BasBusiness;
import com.tk.model.BasBusinessExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@BatisRepository
public interface BasBusinessMapper {
    int countByExample(BasBusinessExample example);

    int deleteByExample(BasBusinessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasBusiness record);

    int insertSelective(BasBusiness record);

    List<BasBusiness> selectByExampleWithBLOBs(BasBusinessExample example);

    List<BasBusiness> selectByExample(BasBusinessExample example);

    BasBusiness selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasBusiness record, @Param("example") BasBusinessExample example);

    int updateByExampleWithBLOBs(@Param("record") BasBusiness record, @Param("example") BasBusinessExample example);

    int updateByExample(@Param("record") BasBusiness record, @Param("example") BasBusinessExample example);

    int updateByPrimaryKeySelective(BasBusiness record);

    int updateByPrimaryKeyWithBLOBs(BasBusiness record);

    int updateByPrimaryKey(BasBusiness record);
}