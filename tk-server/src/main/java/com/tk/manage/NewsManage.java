package com.tk.manage;

import com.tk.mapper.ex.NewsExMapper;
import com.tk.model.News;
import com.tk.model.NewsExample;
import com.tk.vo.NewsVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class NewsManage extends BaseManage {

    @Autowired
    NewsExMapper newsExMapper;


    public NewsVo getHotestNews() {
        NewsExample example = new NewsExample();
        example.setOrderByClause(getPage(1, 1));
        List<News> list = newsExMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            News news = list.get(0);
            NewsVo newsVo = new NewsVo();
            try {
                PropertyUtils.copyProperties(newsVo, news);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return newsVo;
        }
        return null;
    }


    public List<NewsVo> listNews(Integer pageNumber,
                                      Integer pageSize) {
        List<News> list = list(pageNumber,pageSize);
        if (null != list && list.size() > 0) {
            List<NewsVo> ls=new ArrayList<>();
            for(News vo:list){
                NewsVo newsVo = new NewsVo();
                try {
                    PropertyUtils.copyProperties(newsVo, vo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                ls.add(newsVo);
            }
            return ls;
        }
        return null;
    }

    public News getById(Long id) {
        return newsExMapper.selectByPrimaryKey(id);
    }

    public int save(News basRegion) {
        return newsExMapper.insertSelective(basRegion);
    }

    public int update(News basRegion) {
        return newsExMapper.updateByPrimaryKeySelective(basRegion);
    }

    public int delete(Long id) {
        return newsExMapper.deleteByPrimaryKey(id);
    }


    public List<News> list(Integer pageNumber,
                           Integer pageSize) {
        NewsExample example = new NewsExample();
        example.setOrderByClause(getPage(pageNumber, pageSize));
        return newsExMapper.selectByExample(example);
    }

    public int count() {
        return newsExMapper.countByExample(null);
    }


    public long insertBackId(News basRegion) {
        if (newsExMapper.insertBackId(basRegion) > 0) {
            return basRegion.getId();
        }
        return 0;
    }


}
