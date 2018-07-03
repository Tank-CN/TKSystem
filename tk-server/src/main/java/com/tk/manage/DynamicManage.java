package com.tk.manage;

import com.tk.mapper.ex.DynamicExMapper;
import com.tk.model.Dynamic;
import com.tk.model.DynamicExample;
import com.tk.model.User;
import com.tk.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class DynamicManage extends BaseManage {

    @Autowired
    DynamicExMapper dynamicExMapper;


    @Autowired
    UserManage userManage;

    public Dynamic insertBackId(Dynamic dynamic) {
        if (dynamicExMapper.insertBackId(dynamic) > 0) {
            return dynamic;
        }
        return null;
    }

    public Dynamic getById(Long id) {
        return dynamicExMapper.selectByPrimaryKey(id);
    }


    public int update(Dynamic dynamic) {
        return dynamicExMapper.updateByPrimaryKeySelective(dynamic);
    }

    public boolean delete(Long id) {
        if (dynamicExMapper.deleteByPrimaryKey(id) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Dynamic> list(String nickname,Long uid,Integer pageNumber,
                              Integer pageSize) {
        DynamicExample example = new DynamicExample();
        DynamicExample.Criteria criteria=example.createCriteria();
//        if(!CommonUtils.isNull(uid)){
//            criteria.andUidEqualTo(uid);
//        }
        if(!CommonUtils.isNull(nickname)){
            List<User> list=userManage.search(nickname,1,1000);
            if(null!=list&&list.size()>0){
                List<Long> ids=new ArrayList<>();
                for(User u:list){
                    ids.add(u.getId());
                }
                criteria.andUidIn(ids);
            }else{
                return null;
            }
        }
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return dynamicExMapper.selectByExample(example);
    }

    public int count(String nickname,Long uid) {
        DynamicExample example = new DynamicExample();
        DynamicExample.Criteria criteria=example.createCriteria();
//        if(!CommonUtils.isNull(uid)){
//            criteria.andUidEqualTo(uid);
//        }
        if(!CommonUtils.isNull(nickname)){
            List<User> list=userManage.search(nickname,1,1000);
            if(null!=list&&list.size()>0){
                List<Long> ids=new ArrayList<>();
                for(User u:list){
                    ids.add(u.getId());
                }
                criteria.andUidIn(ids);
            }else{
                return 0;
            }
        }
        return dynamicExMapper.countByExample(example);
    }


    public List<Dynamic> list(Integer pageNumber,
                              Integer pageSize) {
        DynamicExample example = new DynamicExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return dynamicExMapper.selectByExample(example);
    }



    /**
     * 个人动态数
     *
     * @param uid
     * @return
     */
    public int countByUid(Long uid) {
        DynamicExample example = new DynamicExample();
        DynamicExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        return dynamicExMapper.countByExample(example);
    }




}
