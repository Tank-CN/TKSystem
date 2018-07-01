package com.tk.mapper.ex;

import com.tk.mapper.DynamicMapper;
import com.tk.model.Dynamic;
import com.tk.util.annotation.BatisRepository;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface DynamicExMapper extends DynamicMapper {

    int insertBackId(Dynamic record);

    int replyCountadd1( @Param("id") long id);

    int likeCountadd1( @Param("id") long id);

    int replyCountsub1( @Param("id") long id);

    int likeCountsub1( @Param("id") long id);

}
