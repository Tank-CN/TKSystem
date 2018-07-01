package com.tk.mapper.ex;

import com.tk.mapper.NewsMapper;
import com.tk.model.News;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface NewsExMapper extends NewsMapper {

    int insertBackId(News record);
}
