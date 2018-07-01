package com.tk.manage;

import com.tk.mapper.BasAppAdviseMapper;
import com.tk.model.BasAppAdvise;
import com.tk.model.BasAppAdviseExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component
@Transactional(readOnly = true)
public class BasAdviseManage extends BaseManage {

    @Autowired
    private BasAppAdviseMapper appAdviseMapper;

    public int insert(BasAppAdvise advise) {
        advise.setCreatedate(new Date());
        return appAdviseMapper.insert(advise);
    }

    public List<BasAppAdvise> queryAdvices(Date startDate, Date endDate, Integer pageNo, Integer pageSize) {
        BasAppAdviseExample example = new BasAppAdviseExample();
        BasAppAdviseExample.Criteria criteria = example.createCriteria();
        if(null!=startDate&&null!=endDate) {
            criteria.andCreatedateBetween(startDate, endDate);
        }
        if (null != pageNo && null != pageSize) {
            example.setOrderByClause(getPage(pageNo, pageSize));
        }
        return appAdviseMapper.selectByExample(example);
    }

    public int countAdvices(Date startDate, Date endDate) {
        BasAppAdviseExample example = new BasAppAdviseExample();
        BasAppAdviseExample.Criteria criteria = example.createCriteria();
        if(null!=startDate&&null!=endDate) {
            criteria.andCreatedateBetween(startDate, endDate);
        }
        return appAdviseMapper.countByExample(example);
    }

    public BasAppAdvise getAdviceById(long id) {
        return appAdviseMapper.selectByPrimaryKey(id);
    }
}
