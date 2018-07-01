package com.tk.mapper;

import com.tk.model.BasAttention;
import com.tk.model.BasAttentionExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasAttentionMapper {
    int countByExample(BasAttentionExample example);

    int deleteByExample(BasAttentionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasAttention record);

    int insertSelective(BasAttention record);

    List<BasAttention> selectByExample(BasAttentionExample example);

    BasAttention selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasAttention record, @Param("example") BasAttentionExample example);

    int updateByExample(@Param("record") BasAttention record, @Param("example") BasAttentionExample example);

    int updateByPrimaryKeySelective(BasAttention record);

    int updateByPrimaryKey(BasAttention record);
}