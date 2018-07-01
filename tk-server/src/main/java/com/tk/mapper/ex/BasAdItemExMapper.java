package com.tk.mapper.ex;

import com.tk.mapper.BasAdItemMapper;
import com.tk.model.BasAdItem;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BasAdItemExMapper extends BasAdItemMapper{

    int insertBackId(BasAdItem record);
}
