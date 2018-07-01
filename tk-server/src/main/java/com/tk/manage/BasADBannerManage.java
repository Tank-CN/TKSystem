package com.tk.manage;

import com.tk.mapper.ex.BasAdBannerExMapper;
import com.tk.model.BasAdBanner;
import com.tk.model.BasAdBannerExample;
import com.tk.vo.ADBannerVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class BasADBannerManage extends BaseManage {

    @Autowired
    BasAdBannerExMapper basAdBannerExMapper;

    @Autowired
    BasBusinessManage basBusinessManage;

    public BasAdBanner getById(Long id) {
        return basAdBannerExMapper.selectByPrimaryKey(id);
    }

    public int save(BasAdBanner basRegion) {
        return basAdBannerExMapper.insertSelective(basRegion);
    }

    public int update(BasAdBanner basRegion) {
        return basAdBannerExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return basAdBannerExMapper.deleteByPrimaryKey(id);
    }


    public List<BasAdBanner> list(Integer pageNumber,
                                  Integer pageSize) {
        BasAdBannerExample example = new BasAdBannerExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));

        return basAdBannerExMapper.selectByExample(example);
    }

    public int count() {
        return basAdBannerExMapper.countByExample(null);
    }


    public long insertBackId(BasAdBanner basRegion) {
        if (basAdBannerExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }


    //api
    public List<ADBannerVo> listVo(Integer pageNumber,
                                 Integer pageSize) {
        List<BasAdBanner> list=  list(pageNumber,pageSize);
        if(null!=list&&list.size()>0){
            List<ADBannerVo> ls=new ArrayList<>();
            for(BasAdBanner ad:list){
                ADBannerVo vo=new ADBannerVo();
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
