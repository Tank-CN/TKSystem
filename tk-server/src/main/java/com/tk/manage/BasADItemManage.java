package com.tk.manage;

import com.tk.mapper.ex.BasAdItemExMapper;
import com.tk.model.BasAdItem;
import com.tk.model.BasAdItemExample;
import com.tk.vo.ADItemVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class BasADItemManage extends BaseManage {

    @Autowired
    BasAdItemExMapper basAdItemExMapper;

    @Autowired
    BasBusinessManage basBusinessManage;

    public BasAdItem getById(Long id) {
        return basAdItemExMapper.selectByPrimaryKey(id);
    }

    public int save(BasAdItem basRegion) {
        return basAdItemExMapper.insertSelective(basRegion);
    }

    public int update(BasAdItem basRegion) {
        return basAdItemExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return basAdItemExMapper.deleteByPrimaryKey(id);
    }


    public List<BasAdItem> list(Integer pageNumber,
                                Integer pageSize) {
        BasAdItemExample example = new BasAdItemExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return basAdItemExMapper.selectByExample(example);
    }

    public int count() {
        return basAdItemExMapper.countByExample(null);
    }

    public long insertBackId(BasAdItem basRegion) {
        if (basAdItemExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }

    //api
    public List<ADItemVo> listVo(Integer pageNumber,
                                 Integer pageSize) {
        List<BasAdItem> list= list(pageNumber,pageSize);
        if(null!=list&&list.size()>0){
            List<ADItemVo> ls=new ArrayList<>();
            for(BasAdItem ad:list){
                ADItemVo vo=new ADItemVo();
                try {
                    PropertyUtils.copyProperties(vo,ad);
                    if(null!=vo.getBid()&&vo.getBid()>0){
                        vo.setBussinessVo(basBusinessManage.getVoById(vo.getBid()));
                    }
                    ls.add(vo);
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
        return null;
    }
}
