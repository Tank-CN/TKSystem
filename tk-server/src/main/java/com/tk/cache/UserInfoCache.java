package com.tk.cache;

import com.tk.manage.UserManage;
import com.tk.util.cache.BaseCache;
import com.tk.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/9/13.
 */
@Component
@Transactional(readOnly = true)
public class UserInfoCache {

    @Autowired
    UserManage userManage;

    BaseCache<Long, UserVo> userInfoCache;

    public UserInfoCache() {
        this.userInfoCache = new BaseCache<Long, UserVo>(10000, 24 * 60 * 60);
    }



}
