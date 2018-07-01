package com.tk.cache;

import com.tk.manage.BasAttentionManage;
import com.tk.manage.UserManage;
import com.tk.model.User;
import com.tk.util.CommonUtils;
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

    @Autowired
    BasAttentionManage basAttentionManage;

    BaseCache<Long, UserVo> userInfoCache;

    public UserInfoCache() {
        this.userInfoCache = new BaseCache<Long, UserVo>(10000, 24 * 60 * 60);
    }

    /**
     * @param id  当前登陆者的id
     * @param uid 查看的用户id
     * @return
     */
    public UserVo get(Long id, Long uid) {
        UserVo userVo = userInfoCache.get(uid);
        if (null == userVo) {
            User user = userManage.getUserById(uid);
            userVo = getUserVo(id, user);
            userInfoCache.put(uid, userVo);
        }
        return userVo;
    }


    public void resetCache(Long uid){
        UserVo userVo = userInfoCache.get(uid);
        if (null == userVo) {
            return;
        }
        User user = userManage.getUserById(uid);
        if(null!=user){
            userVo = getUserVo(null, user);
            userInfoCache.put(uid, userVo);
        }
    }


    public UserVo get(Long uid) {
        UserVo userVo = userInfoCache.get(uid);
        if (null == userVo) {
            User user = userManage.getUserById(uid);
            if (null != user) {
                userVo = getUserVo(null, user);
                userInfoCache.put(uid, userVo);
            }
        }
        return userVo;
    }


    public void update(Long uid) {
        UserVo userVo = userInfoCache.get(uid);
        if (null != userVo) {
            userInfoCache.remove(uid);
            User user = userManage.getUserById(uid);
            if (null != user) {
                userVo = getUserVo(null, user);
                userInfoCache.put(uid, userVo);
            }
        }
    }


    public void delete(Long uid) {
        UserVo userVo = userInfoCache.get(uid);
        if (null != userVo) {
            userInfoCache.remove(uid);
        }
    }


    public UserVo getUserVo(Long id, User user) {
        UserVo userVo = new UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setHeader(user.getHeader());
        userVo.setBirthdate(user.getBirthdate());
        userVo.setId(user.getId());
        userVo.setInfo(user.getInfo());
        userVo.setSexcode(user.getSexcode());
        userVo.setVip(user.getVip());
        userVo.setMobile(user.getMobile());
        if (!CommonUtils.isNull(id)) {
            userVo.setAttention(basAttentionManage.isAttention(id, user.getId()));
        }
        return userVo;
    }
}
