package com.tk.mapper;

import com.tk.model.BasAdBanner;
import com.tk.model.BasAdBannerExample;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@BatisRepository
public interface BasAdBannerMapper {
    int countByExample(BasAdBannerExample example);

    int deleteByExample(BasAdBannerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasAdBanner record);

    int insertSelective(BasAdBanner record);

    List<BasAdBanner> selectByExampleWithBLOBs(BasAdBannerExample example);

    List<BasAdBanner> selectByExample(BasAdBannerExample example);

    BasAdBanner selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasAdBanner record, @Param("example") BasAdBannerExample example);

    int updateByExampleWithBLOBs(@Param("record") BasAdBanner record, @Param("example") BasAdBannerExample example);

    int updateByExample(@Param("record") BasAdBanner record, @Param("example") BasAdBannerExample example);

    int updateByPrimaryKeySelective(BasAdBanner record);

    int updateByPrimaryKeyWithBLOBs(BasAdBanner record);

    int updateByPrimaryKey(BasAdBanner record);
}