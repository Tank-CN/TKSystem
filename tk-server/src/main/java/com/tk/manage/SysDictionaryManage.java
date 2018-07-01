package com.tk.manage;

import com.tk.mapper.ex.SysDictionaryExMapper;
import com.tk.model.SysDictionary;
import com.tk.model.SysDictionaryExample;
import com.tk.model.ex.SysDictionaryEx;
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
public class SysDictionaryManage {

	@Autowired
	SysDictionaryExMapper sysDictionaryExMapper;

	public List<SysDictionary> getAll(String dictionaryName) {
		SysDictionaryExample example = new SysDictionaryExample();
		SysDictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andIidIsNotNull();
		//根据字典名检索父字典
		if(CommonUtils.isNotEmpty(dictionaryName)){
			criteria.andPidEqualTo(new Long(0));
			criteria.andTitleLike("%"+dictionaryName+"%");
			List<SysDictionary> list = sysDictionaryExMapper.selectByExample(example);
			List<SysDictionary> sum = new ArrayList<>();
			if(null != list && list.size() > 0){
				for (SysDictionary sysDictionary : list) {
					SysDictionaryExample exampleChild = new SysDictionaryExample();
					SysDictionaryExample.Criteria criteriaChild = exampleChild.createCriteria();
					criteriaChild.andIidIsNotNull();
					criteriaChild.andCidEqualTo(sysDictionary.getCid());
					sum.addAll(sysDictionaryExMapper.selectByExample(exampleChild));
				}
			}
			return sum;
		}else{
			return sysDictionaryExMapper.selectByExample(example);
		}
	}

	public List<SysDictionary> querySysDictionaryByPId(Long id) {
		SysDictionaryExample example = new SysDictionaryExample();
		SysDictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(id);
		return sysDictionaryExMapper.selectByExample(example);
	}
	
	public List<SysDictionary> querySysDictionaryById(Long id) {
		SysDictionary sysDictionary = sysDictionaryExMapper.selectByPrimaryKey(id);
		
		SysDictionaryExample example = new SysDictionaryExample();
		SysDictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(sysDictionary.getPid());
		return sysDictionaryExMapper.selectByExample(example);
	}

	public SysDictionaryEx getObjById(Long id) {
		SysDictionary sysDictionary = sysDictionaryExMapper.selectByPrimaryKey(id);
		SysDictionaryEx sysDictionaryEx = new SysDictionaryEx();
		try {
			PropertyUtils.copyProperties(sysDictionaryEx, sysDictionary);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		if (sysDictionaryEx.getPid() != 0) {
			sysDictionaryEx.setPname(sysDictionaryExMapper.selectByPrimaryKey(sysDictionary.getPid()).getTitle());
		}
		return sysDictionaryEx;
	}

	/**
	 * 根节点：iid自增，cid类别名，pid(0)，
	 * 子节点：iid自增，cid父cid，pid父iid
	 * @author  jhui
	 * @created 2015年9月11日 下午3:11:30
	 * @param sysDictionary
	 * @return
	 * @return  Boolean
	 */
	public Boolean save(SysDictionary sysDictionary) {
		if(sysDictionary.getPid() != 0){
			SysDictionary parent = sysDictionaryExMapper.selectByPrimaryKey(sysDictionary.getPid());
			sysDictionary.setCid(parent.getCid());
		}
		sysDictionaryExMapper.insert(sysDictionary);
		return true;
	}

	public Boolean update(SysDictionary sysDictionary) {
		sysDictionaryExMapper.updateByPrimaryKey(sysDictionary);
		return true;
	}

	public Boolean delete(Long id) {
		SysDictionary dic = sysDictionaryExMapper.selectByPrimaryKey(id);
		deleteRecursive(dic);
		return true;
	}

	/**
	 * 递归删除下面有关联的数据
	 * @param dic
	 */
	public void deleteRecursive(SysDictionary dic) {
		List<SysDictionary> list = querySysDictionaryByPId(dic.getIid());
		if (list != null && list.size() > 0) {
			for (SysDictionary obj : list) {
				deleteRecursive(obj);
			}
		}
		/**
		 * 这中间还要删除涉及到字典的业务逻辑，字典项的id是dic.getIid()
		 */
		sysDictionaryExMapper.deleteByPrimaryKey(dic.getIid());
	}

	public Long insertAndGetId(SysDictionary sysDictionary) {
		sysDictionaryExMapper.insertAndGetId(sysDictionary);
		return sysDictionary.getIid();
	}


	public List<SysDictionary> findListByTitle(String title) {
		if (StringUtils.isEmpty(title)) {
			return new ArrayList<>();
		}
		SysDictionaryExample example = new SysDictionaryExample();
		SysDictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andTitleLike("%" + title + "%");
		criteria.andPidEqualTo((long) 0);
//		criteria.andSourceEqualTo("1");
		return sysDictionaryExMapper.selectByExample(example);
	}
}
