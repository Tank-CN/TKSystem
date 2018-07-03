package com.tk.manage;

import com.tk.mapper.ex.BasDictionaryExMapper;
import com.tk.model.BasDictionary;
import com.tk.model.BasDictionaryExample;
import com.tk.model.ex.BasDictionaryEx;
import com.tk.util.CommonUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


@Component
@Transactional(readOnly = true)
public class BasDictionaryManage {

	@Autowired
	BasDictionaryExMapper basDictionaryExMapper;

	public List<BasDictionary> getAll(String dictionaryName) {
		BasDictionaryExample example = new BasDictionaryExample();
		BasDictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		//根据字典名检索父字典
		if(CommonUtils.isNotEmpty(dictionaryName)){
			criteria.andPidEqualTo(new Long(0));
			criteria.andTitleLike("%"+dictionaryName+"%");
			List<BasDictionary> list = basDictionaryExMapper.selectByExample(example);
			List<BasDictionary> sum = new ArrayList<>();
			if(null != list && list.size() > 0){
				for (BasDictionary basDictionary : list) {
					BasDictionaryExample exampleChild = new BasDictionaryExample();
					BasDictionaryExample.Criteria criteriaChild = exampleChild.createCriteria();
					criteriaChild.andIdIsNotNull();
					criteriaChild.andCidEqualTo(basDictionary.getCid());
					sum.addAll(basDictionaryExMapper.selectByExample(exampleChild));
				}
			}
			return sum;
		}else{
			return basDictionaryExMapper.selectByExample(example);
		}
	}

	public List<BasDictionary> queryDictionaryByPId(Long id) {
		BasDictionaryExample example = new BasDictionaryExample();
		BasDictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(id);
		return basDictionaryExMapper.selectByExample(example);
	}
	
	public List<BasDictionary> queryDictionaryById(Long id) {
		BasDictionary basDictionary = basDictionaryExMapper.selectByPrimaryKey(id);

		BasDictionaryExample example = new BasDictionaryExample();
		BasDictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(basDictionary.getPid());
		return basDictionaryExMapper.selectByExample(example);
	}

	public BasDictionaryEx getObjById(Long id) {
		BasDictionary sysDictionary = basDictionaryExMapper.selectByPrimaryKey(id);
		BasDictionaryEx basDictionaryEx = new BasDictionaryEx();
		try {
			PropertyUtils.copyProperties(basDictionaryEx, sysDictionary);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		if (basDictionaryEx.getPid() != 0) {
			basDictionaryEx.setPname(basDictionaryExMapper.selectByPrimaryKey(sysDictionary.getPid()).getTitle());
		}
		return basDictionaryEx;
	}

	/**
	 * 根节点：iid自增，cid类别名，pid(0)，
	 * 子节点：iid自增，cid父cid，pid父iid
	 * @author  jhui
	 * @created 2015年9月11日 下午3:11:30
	 * @param basDictionaryEx
	 * @return
	 * @return  Boolean
	 */
	public Boolean save(BasDictionary basDictionaryEx) {
		if(basDictionaryEx.getPid() != 0){
			BasDictionary parent = basDictionaryExMapper.selectByPrimaryKey(basDictionaryEx.getPid());
			basDictionaryEx.setCid(parent.getCid());
		}
		basDictionaryExMapper.insert(basDictionaryEx);
		return true;
	}

	public Boolean update(BasDictionary basDictionary) {
		basDictionaryExMapper.updateByPrimaryKey(basDictionary);
		return true;
	}

	public Boolean delete(Long id) {
		BasDictionary dic = basDictionaryExMapper.selectByPrimaryKey(id);
		deleteRecursive(dic);
		return true;
	}

	/**
	 * 递归删除下面有关联的数据
	 * @param dic
	 */
	public void deleteRecursive(BasDictionary dic) {
		List<BasDictionary> list = queryDictionaryByPId(dic.getId());
		if (list != null && list.size() > 0) {
			for (BasDictionary obj : list) {
				deleteRecursive(obj);
			}
		}
		/**
		 * 这中间还要删除涉及到字典的业务逻辑，字典项的id是dic.getIid()
		 */
		basDictionaryExMapper.deleteByPrimaryKey(dic.getId());
	}

	public Long insertAndGetId(BasDictionary basDictionary) {
		basDictionaryExMapper.insertBackId(basDictionary);
		return basDictionary.getId();
	}


	public List<BasDictionary> findListByTitle(String title) {
		if (StringUtils.isEmpty(title)) {
			return new ArrayList<>();
		}
		BasDictionaryExample example = new BasDictionaryExample();
		BasDictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andTitleLike("%" + title + "%");
		criteria.andPidEqualTo((long) 0);
//		criteria.andSourceEqualTo("1");
		return basDictionaryExMapper.selectByExample(example);
	}
}
