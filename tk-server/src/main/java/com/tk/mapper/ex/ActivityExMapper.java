package com.tk.mapper.ex;

import com.tk.mapper.ActivityMapper;
import com.tk.model.Activity;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface ActivityExMapper extends ActivityMapper{

    int insertBackId(Activity record);
}
