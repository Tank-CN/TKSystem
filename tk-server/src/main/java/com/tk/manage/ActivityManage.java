package com.tk.manage;

import com.tk.mapper.ex.ActivityExMapper;
import com.tk.mapper.ex.ActivitySignupExMapper;
import com.tk.model.*;
import com.tk.util.CommonUtils;
import com.tk.vo.ActivitySignUserVo;
import com.tk.vo.ActivityVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class ActivityManage extends BaseManage {

    @Autowired
    ActivityExMapper activityExMapper;

    @Autowired
    ActivitySignupExMapper activitySignupExMapper;

    @Autowired
    UserManage userManage;


    public Activity getById(Long id) {
        return activityExMapper.selectByPrimaryKey(id);
    }

    public int save(Activity basRegion) {
        return activityExMapper.insertSelective(basRegion);
    }

    public int update(Activity basRegion) {
        return activityExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return activityExMapper.deleteByPrimaryKey(id);
    }


    public List<Activity> list(String title,Integer pageNumber,
                               Integer pageSize) {
        ActivityExample example = new ActivityExample();
        if(!CommonUtils.isNull(title)){
            ActivityExample.Criteria criteria=example.createCriteria();
            criteria.andTitleLike("%" + title + "%");
        }
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return activityExMapper.selectByExample(example);
    }

    public List<ActivitySignup> listSigns(Long uid) {
        ActivitySignupExample example = new ActivitySignupExample();
        ActivitySignupExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        return activitySignupExMapper.selectByExample(example);
    }


    public int count(String title) {
        ActivityExample example = new ActivityExample();
        if(!CommonUtils.isNull(title)){
            ActivityExample.Criteria criteria=example.createCriteria();
            criteria.andTitleLike("%" + title + "%");
        }
        return activityExMapper.countByExample(example);
    }


    public long insertBackId(Activity basRegion) {
        if (activityExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }


    /**
     * 活动参加人数
     *
     * @param aid
     * @return
     */
    public int signCountByAId(Long aid) {
        ActivitySignupExample example = new ActivitySignupExample();
        ActivitySignupExample.Criteria criteria = example.createCriteria();
        criteria.andAidEqualTo(aid);
        return activitySignupExMapper.countByExample(example);
    }


    /**
     * 所有活动，加入报名数
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<ActivityVo> listAll(Long uid, Integer pageNumber,
                                    Integer pageSize) {
        ActivityExample example = new ActivityExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<Activity> list = activityExMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return parser(list, uid);
        }
        return null;
    }

    /**
     * 用户参加的活动
     *
     * @param uid
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<ActivityVo> listByUid(Long uid, Integer pageNumber,
                                      Integer pageSize) {
        List<ActivitySignup> signups=listSigns(uid);
        if (null != signups && signups.size() > 0) {
            List<Long> ids=new ArrayList<>();
            for(ActivitySignup signup:signups){
                ids.add(signup.getAid());
            }
            ActivityExample example = new ActivityExample();
            ActivityExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            example.setOrderByClause(getPage(pageNumber, pageSize));
            List<Activity> list = activityExMapper.selectByExample(example);
            if (null != list && list.size() > 0) {
                return parser(list, uid);
            }
        }
        return null;
    }


    public List<ActivityVo> parser(List<Activity> list, Long uid) {
        List<ActivityVo> ls = new ArrayList<>();
        List<ActivitySignup> signups = null;
        if (null != uid) {
            signups = listSigns(uid);
        }
        for (Activity vo : list) {
            ActivityVo av = new ActivityVo();
            try {
                PropertyUtils.copyProperties(av, vo);
                av.setSignCount(signCountByAId(vo.getId()));
                if (null != signups && signups.size() > 0) {
                    for (ActivitySignup signup : signups) {
                        if (signup.getAid().equals(av.getId())&&signup.getUid().longValue() == uid.longValue()) {
                            av.setIsSign(1);
                            break;
                        }
                    }
                }
                ls.add(av);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }


    /**
     * 活动报名列表
     *
     * @param aid
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<ActivitySignUserVo> listSignUserByAid(Long aid, Integer pageNumber,
                                                      Integer pageSize) {
        ActivitySignupExample example = new ActivitySignupExample();
        ActivitySignupExample.Criteria criteria = example.createCriteria();
        criteria.andAidEqualTo(aid);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<ActivitySignup> list = activitySignupExMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            List<ActivitySignUserVo> ls = new ArrayList<>();
            User user = null;
            for (ActivitySignup vo : list) {
                ActivitySignUserVo av = new ActivitySignUserVo();
                user = userManage.getUserById(vo.getUid());
                if (null != user) {
                    av.setHeader(user.getHeader());
                    av.setUname(user.getNickname());
                }
                av.setUid(vo.getUid());
                av.setAid(vo.getAid());
                av.setCreatedate(vo.getCreatedate());
                av.setId(vo.getId());
                ls.add(av);
            }
            return ls;
        }
        return null;
    }


    public boolean sign(ActivitySignup activitySignup) {
        activitySignup.setCreatedate(new Date());
        if (activitySignupExMapper.insertSelective(activitySignup) > 0) {
            return true;
        }
        return false;
    }

}
