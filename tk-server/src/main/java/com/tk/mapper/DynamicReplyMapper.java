package com.tk.mapper;

import com.tk.model.DynamicReply;
import com.tk.model.DynamicReplyExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface DynamicReplyMapper {
    int countByExample(DynamicReplyExample example);

    int deleteByExample(DynamicReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DynamicReply record);

    int insertSelective(DynamicReply record);

    List<DynamicReply> selectByExample(DynamicReplyExample example);

    DynamicReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DynamicReply record, @Param("example") DynamicReplyExample example);

    int updateByExample(@Param("record") DynamicReply record, @Param("example") DynamicReplyExample example);

    int updateByPrimaryKeySelective(DynamicReply record);

    int updateByPrimaryKey(DynamicReply record);
}