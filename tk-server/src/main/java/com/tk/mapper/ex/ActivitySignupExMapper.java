package com.tk.mapper.ex;

import com.tk.mapper.ActivitySignupMapper;
import com.tk.model.ActivitySignup;
import com.tk.util.annotation.BatisRepository;
import com.tk.vo.admin.SignCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface ActivitySignupExMapper extends ActivitySignupMapper {

    int insertBackId(ActivitySignup record);

    List<SignCountVo> getSign(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int countSign();
}
