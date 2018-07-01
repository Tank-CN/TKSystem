package com.tk.mapper.ex;

import com.tk.mapper.DynamicReplyMapper;
import com.tk.model.DynamicReply;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface DynamicReplyExMapper extends DynamicReplyMapper {

    int insertBackId(DynamicReply record);
}
