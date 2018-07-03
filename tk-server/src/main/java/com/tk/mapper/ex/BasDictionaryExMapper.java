package com.tk.mapper.ex;

import com.tk.mapper.BasDictionaryMapper;
import com.tk.model.BasDictionary;
import com.tk.util.annotation.BatisRepository;

/**
 *
 */
@BatisRepository
public interface BasDictionaryExMapper extends BasDictionaryMapper {

    int insertBackId(BasDictionary record);
}
