package com.tk.mapper.ex;

import com.tk.mapper.UserMapper;
import com.tk.model.User;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface UserExMapper extends UserMapper {

    int insertBackId(User record);
}
