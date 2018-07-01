package com.tk.manage;

import com.tk.mapper.BusinessApplyMapper;
import com.tk.model.BusinessApply;
import com.tk.model.BusinessApplyExample;
import com.tk.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class BusinessApplyManage extends BaseManage {

    @Autowired
    BusinessApplyMapper businessApplyMapper;

    @Autowired
    UserManage userManage;


    public boolean save(BusinessApply businessApply) {
        businessApply.setCreatedate(new Date());
        if (businessApplyMapper.insertSelective(businessApply) > 0) {
            return true;
        }
        return false;
    }


    public boolean delete(Long id) {
        if (businessApplyMapper.deleteByPrimaryKey(id) > 0) {
            return true;
        }
        return false;
    }


    //0 申请 1 审核 2处理完
    public List<BusinessApply> list(String title, String phone, Byte flag, Integer pageNumber,
                                    Integer pageSize) {
        BusinessApplyExample example = new BusinessApplyExample();
        BusinessApplyExample.Criteria criteria = example.createCriteria();
        if (!CommonUtils.isNull(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (!CommonUtils.isNull(phone)) {
            criteria.andTelephoneLike("%" + phone + "%");
        }
        if (!CommonUtils.isNull(flag) && flag.intValue() != -1) {
            criteria.andFlagEqualTo(flag);
        }
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return businessApplyMapper.selectByExample(example);
    }


    public int count(String title, String phone, Byte flag) {
        BusinessApplyExample example = new BusinessApplyExample();
        BusinessApplyExample.Criteria criteria = example.createCriteria();
        if (!CommonUtils.isNull(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (!CommonUtils.isNull(phone)) {
            criteria.andTelephoneLike("%" + phone + "%");
        }
        if (!CommonUtils.isNull(flag) && flag.intValue() != -1) {
            criteria.andFlagEqualTo(flag);
        }
        return businessApplyMapper.countByExample(example);
    }


    public int isApplay(Long uid) {
        BusinessApplyExample example = new BusinessApplyExample();
        BusinessApplyExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<BusinessApply> list = businessApplyMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            BusinessApply apply = list.get(0);
            return apply.getFlag();
        } else {
            return -1;
        }
    }


    //0 申请 1 审核 2处理完
    public void changeStatue(Long id, Byte flag) {
        if (null != id && null != flag) {
            BusinessApply apply = new BusinessApply();
            apply.setId(id);
            if (flag.intValue() == 0) {
                apply.setFlag(Byte.valueOf("1"));
            } else if (flag.intValue() == 1) {
                apply.setFlag(Byte.valueOf("2"));
            }
            businessApplyMapper.updateByPrimaryKeySelective(apply);

        }


    }

}
