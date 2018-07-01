package com.tk.manage;

import com.tk.cache.UserInfoCache;
import com.tk.mapper.BasTipMapper;
import com.tk.model.*;
import com.tk.vo.TipVo;
import com.tk.vo.UserVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class BasTipManage extends BaseManage {

    @Autowired
    BasTipMapper basTipMapper;

//    @Autowired
//    UserManage userManage;

    @Autowired
    BasBusinessManage basBusinessManage;

    @Autowired
    DynamicManage dynamicManage;

    @Autowired
    UserInfoCache userInfoCache;


    public boolean save(BasTip basTip) {
        if (basTipMapper.insertSelective(basTip) > 0) {
            return true;
        }
        return false;
    }


    public boolean delete(Long id) {
        if (basTipMapper.deleteByPrimaryKey(id) > 0) {
            return true;
        }
        return false;
    }


    public List<TipVo> list(Byte status, Integer pageNumber,
                            Integer pageSize) {
        BasTipExample example = new BasTipExample();
        BasTipExample.Criteria criteria = example.createCriteria();
        if (null != status) {
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause(getPage(pageNumber, pageSize));
        List<BasTip> list = basTipMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            List<TipVo> ls = new ArrayList<>();
            Dynamic dynamic = null;
            UserVo user = null;
            BasBusiness basBusiness = null;
            for (BasTip tip : list) {
                TipVo vo = new TipVo();
                try {
                    PropertyUtils.copyProperties(vo, tip);
                    //举报类型 1 用户 2 动态 3 商家
                    switch (tip.getType().intValue()) {
                        case 1:
                            user = userInfoCache.get(tip.getTid());
                            if (null != user) {
                                vo.setName(user.getNickname());
                                vo.setImage(user.getHeader());
                            }
                            vo.setTypetext("用户");
                            break;
                        case 2:
                            dynamic = dynamicManage.getById(tip.getTid());
                            if (null != dynamic) {
                                vo.setName(dynamic.getContent());
                                vo.setImage(dynamic.getImgurl());
                            }
                            vo.setTypetext("动态");
                            break;
                        case 3:
                            basBusiness = basBusinessManage.getById(tip.getId());
                            vo.setName(basBusiness.getTitle());
                            vo.setImage(basBusiness.getPicurl());
                            vo.setTypetext("商家");
                            break;
                        default:
                            break;
                    }
                    //举报者
                    user = userInfoCache.get(tip.getUid());
                    if (null != user) {
                        vo.setNickname(user.getNickname());
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


    public int count(Byte status) {
        BasTipExample example = new BasTipExample();
        BasTipExample.Criteria criteria = example.createCriteria();
        if (null != status) {
            criteria.andStatusEqualTo(status);
        }
        return basTipMapper.countByExample(example);
    }


    public boolean statue(Long id) {
        BasTip basTip = basTipMapper.selectByPrimaryKey(id);
        if (null != basTip) {
            if (basTip.getType().intValue() == 2) {
                if (dynamicManage.delete(basTip.getTid())) {
                    basTip.setStatus(Byte.valueOf("1"));
                    basTipMapper.updateByPrimaryKeySelective(basTip);
                    return true;
                }
            }
        }
        return false;
    }


}
