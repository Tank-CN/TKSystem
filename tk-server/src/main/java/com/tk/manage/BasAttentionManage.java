package com.tk.manage;

import com.tk.mapper.BasAttentionMapper;
import com.tk.model.BasAttention;
import com.tk.model.BasAttentionExample;
import com.tk.model.User;
import com.tk.vo.AttentionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class BasAttentionManage extends BaseManage {

    @Autowired
    BasAttentionMapper basAttentionMapper;

    @Autowired
    UserManage userManage;


    public boolean save(BasAttention basAttention) {
        if (basAttentionMapper.insertSelective(basAttention) > 0) {
            return true;
        }
        return false;
    }


    public boolean delete(Long id) {
        if (basAttentionMapper.deleteByPrimaryKey(id) > 0) {
            return true;
        }
        return false;
    }

    public boolean delete(Long uid, Long auid) {
        BasAttentionExample example = new BasAttentionExample();
        BasAttentionExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andAuidEqualTo(auid);
        if (basAttentionMapper.deleteByExample(example) > 0) {
            return true;
        }
        return false;
    }



    public boolean isAttention(Long uid, Long auid) {
        BasAttentionExample example = new BasAttentionExample();
        BasAttentionExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andAuidEqualTo(auid);
        List list = basAttentionMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * UID所关注的人
     *
     * @param uid
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<AttentionVo> listSelf(Long uid, Integer pageNumber,
                                      Integer pageSize) {
        BasAttentionExample example = new BasAttentionExample();
        BasAttentionExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<BasAttention> list = basAttentionMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            List<AttentionVo> lists = new ArrayList<>();
            User user = null;
            for (BasAttention vo : list) {
                AttentionVo av = new AttentionVo();
                av.setCreatedate(vo.getCreatedate());
                av.setUserVo(getUserVo(vo.getAuid()));
                lists.add(av);
            }
            return lists;
        }
        return null;
    }


    /**
     * UID被关注的人，粉丝
     *
     * @param uid
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<AttentionVo> listAttention(Long uid, Integer pageNumber,
                                           Integer pageSize) {
        BasAttentionExample example = new BasAttentionExample();
        BasAttentionExample.Criteria criteria = example.createCriteria();
        criteria.andAuidEqualTo(uid);
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<BasAttention> list = basAttentionMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            List<AttentionVo> lists = new ArrayList<>();
            User user = null;
            for (BasAttention vo : list) {
                AttentionVo av = new AttentionVo();
                av.setCreatedate(vo.getCreatedate());
                av.setUserVo(getUserVo(vo.getUid()));
                lists.add(av);
            }
            return lists;
        }
        return null;
    }

}
