package com.tk.mapper;

import com.tk.model.ActivitySignup;
import com.tk.model.ActivitySignupExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface ActivitySignupMapper {
    int countByExample(ActivitySignupExample example);

    int deleteByExample(ActivitySignupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivitySignup record);

    int insertSelective(ActivitySignup record);

    List<ActivitySignup> selectByExample(ActivitySignupExample example);

    ActivitySignup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivitySignup record, @Param("example") ActivitySignupExample example);

    int updateByExample(@Param("record") ActivitySignup record, @Param("example") ActivitySignupExample example);

    int updateByPrimaryKeySelective(ActivitySignup record);

    int updateByPrimaryKey(ActivitySignup record);
}