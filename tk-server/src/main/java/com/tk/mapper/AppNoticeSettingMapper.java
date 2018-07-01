package com.tk.mapper;

import com.tk.model.AppNoticeSetting;
import com.tk.model.AppNoticeSettingExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface AppNoticeSettingMapper {
    int countByExample(AppNoticeSettingExample example);

    int deleteByExample(AppNoticeSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppNoticeSetting record);

    int insertSelective(AppNoticeSetting record);

    List<AppNoticeSetting> selectByExampleWithBLOBs(AppNoticeSettingExample example);

    List<AppNoticeSetting> selectByExample(AppNoticeSettingExample example);

    AppNoticeSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppNoticeSetting record, @Param("example") AppNoticeSettingExample example);

    int updateByExampleWithBLOBs(@Param("record") AppNoticeSetting record, @Param("example") AppNoticeSettingExample example);

    int updateByExample(@Param("record") AppNoticeSetting record, @Param("example") AppNoticeSettingExample example);

    int updateByPrimaryKeySelective(AppNoticeSetting record);

    int updateByPrimaryKeyWithBLOBs(AppNoticeSetting record);

    int updateByPrimaryKey(AppNoticeSetting record);
}