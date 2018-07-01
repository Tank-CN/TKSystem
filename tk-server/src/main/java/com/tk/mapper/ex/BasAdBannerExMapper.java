package com.tk.mapper.ex;

import com.tk.mapper.BasAdBannerMapper;
import com.tk.model.BasAdBanner;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BasAdBannerExMapper extends BasAdBannerMapper{

    int insertBackId(BasAdBanner record);
}
