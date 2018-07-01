package com.tk.mapper.ex;

import com.tk.mapper.BasBusinessMapper;
import com.tk.model.BasBusiness;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BasBusinessExMapper extends BasBusinessMapper {

    int insertBackId(BasBusiness record);
}
