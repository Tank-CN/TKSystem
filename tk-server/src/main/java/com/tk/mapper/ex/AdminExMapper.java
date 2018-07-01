package com.tk.mapper.ex;

import com.tk.mapper.AdminMapper;
import com.tk.model.Admin;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface AdminExMapper extends AdminMapper{

    int insertBackId(Admin record);
}
