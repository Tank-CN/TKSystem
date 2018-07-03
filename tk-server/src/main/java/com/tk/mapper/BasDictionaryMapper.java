package com.tk.mapper;

import com.tk.model.BasDictionary;
import com.tk.model.BasDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasDictionaryMapper {
    int countByExample(BasDictionaryExample example);

    int deleteByExample(BasDictionaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasDictionary record);

    int insertSelective(BasDictionary record);

    List<BasDictionary> selectByExample(BasDictionaryExample example);

    BasDictionary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasDictionary record, @Param("example") BasDictionaryExample example);

    int updateByExample(@Param("record") BasDictionary record, @Param("example") BasDictionaryExample example);

    int updateByPrimaryKeySelective(BasDictionary record);

    int updateByPrimaryKey(BasDictionary record);
}