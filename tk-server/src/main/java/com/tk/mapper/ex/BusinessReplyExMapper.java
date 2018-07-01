package com.tk.mapper.ex;

import com.tk.mapper.BusinessReplyMapper;
import com.tk.model.BusinessReply;
import com.tk.util.annotation.BatisRepository;

/**
 * Created by Administrator on 2016/6/12.
 */
@BatisRepository
public interface BusinessReplyExMapper extends BusinessReplyMapper {

    int insertBackId(BusinessReply record);
}
