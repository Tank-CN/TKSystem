package com.tk.manage;

import com.tk.mapper.ex.ActivityExMapper;
import com.tk.mapper.ex.ActivitySignupExMapper;
import com.tk.model.ActivitySignup;
import com.tk.model.ActivitySignupExample;
import com.tk.vo.admin.SignCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ActivitySignupManage extends BaseManage {

    @Autowired
    ActivityExMapper activityExMapper;

    @Autowired
    ActivitySignupExMapper activitySignupExMapper;


    public ActivitySignup getById(Long id) {
        return activitySignupExMapper.selectByPrimaryKey(id);
    }

    public int save(ActivitySignup basRegion) {
        return activitySignupExMapper.insertSelective(basRegion);
    }

    public int update(ActivitySignup basRegion) {
        return activitySignupExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return activitySignupExMapper.deleteByPrimaryKey(id);
    }


    public  List<SignCountVo> getSignCounts(Integer pageNumber,
                                            Integer pageSize){
        return activitySignupExMapper.getSign( (pageNumber - 1) * pageSize,pageSize);
    }

    public  int countSignCounts(){
        return activitySignupExMapper.countSign();
    }

    public List<ActivitySignup> listByAid(Long aid,Integer pageNumber,
                                     Integer pageSize) {
        ActivitySignupExample example = new ActivitySignupExample();
        ActivitySignupExample.Criteria criteria=example.createCriteria();
        criteria.andAidEqualTo(aid);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return activitySignupExMapper.selectByExample(example);
    }

    public int countByAid(Long aid) {
        ActivitySignupExample example = new ActivitySignupExample();
        ActivitySignupExample.Criteria criteria=example.createCriteria();
        criteria.andAidEqualTo(aid);
        return activitySignupExMapper.countByExample(example);
    }

    public List<ActivitySignup> list(Integer pageNumber,
                                  Integer pageSize) {
        ActivitySignupExample example = new ActivitySignupExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return activitySignupExMapper.selectByExample(example);
    }

    public int count() {
        return activitySignupExMapper.countByExample(null);
    }


    public long insertBackId(ActivitySignup basRegion) {
        if (activitySignupExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }



}
