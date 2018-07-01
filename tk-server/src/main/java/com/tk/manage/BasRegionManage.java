package com.tk.manage;

import com.tk.mapper.ex.BasRegionExMapper;
import com.tk.model.BasRegion;
import com.tk.model.BasRegionExample;
import com.tk.model.ex.BasRegionEx;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class BasRegionManage {

	@Autowired
	BasRegionExMapper basRegionMapper;

	public BasRegion getById(Integer id) {
		return basRegionMapper.selectByPrimaryKey(id);
	}

	public int save(BasRegion basRegion) {
		return basRegionMapper.insertSelective(basRegion);
	}

	public int update(BasRegion basRegion) {
		return basRegionMapper.updateByPrimaryKeySelective(basRegion);
	}

	public int delete(Integer id) {
		return basRegionMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 得到所有的省份（pid=1）
	 * 
	 * 
	 * @return
	 */
	public List<BasRegion> getAllProvice() {
		BasRegionExample example = new BasRegionExample();
		BasRegionExample.Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(1);
		return basRegionMapper.selectByExample(example);
	}

	public BasRegionEx getObjById(Integer id) {
		BasRegion basRegion = basRegionMapper.selectByPrimaryKey(id);
		BasRegionEx basRegionEx = new BasRegionEx();
		try {
			PropertyUtils.copyProperties(basRegionEx, basRegion);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		if (basRegionEx.getPid() != 0) {
			basRegionEx.setPname(basRegionMapper.selectByPrimaryKey(basRegionEx.getPid()).getTitle());
		}
		return basRegionEx;
	}

	/**
	 * 根据父节点得到子节点
	 * 
	 * @param pid
	 * @return
	 */
	public List<BasRegion> getByPid(Integer pid) {
		BasRegionExample example = new BasRegionExample();
		BasRegionExample.Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(pid);
		return basRegionMapper.selectByExample(example);
	}

	/**
	 * 根据父节点得到子节点
	 * 
	 * @param pid
	 * @return
	 */
	public int getCountByPid(Integer pid) {
		BasRegionExample example = new BasRegionExample();
		BasRegionExample.Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(pid);
		return basRegionMapper.countByExample(example);
	}

//	/**
//	 * 通过经纬度获取城市
//	 *
//	 * @param location
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 */
//	public String getCity(String location) {
//		if (location != null && location.indexOf(",") > 0) {
//			String[] loctArry = location.split(",");
//			location = loctArry[1] + "," + loctArry[0];
//		}
//		JSONObject jon = null;
//		try {
//			jon = JSONObject.parseObject(BaiduOpenSDKUtil.geocoder(location));
//		} catch (UnsupportedEncodingException e) {
//			return null;
//		}
//		if (jon.getInteger("status") == 0) {
//			return jon.getJSONObject("result").getJSONObject("addressComponent").getString("city");
//		}
//		return null;
//	}


	/**
	 * 根据城市名获取平台系统的城市ID
	 * 
	 * @param currentCity
	 * @return
	 */
	public String selectCityIdByName(String currentCity) {
		List<Integer> levs = new ArrayList<Integer>();
		levs.add(2);
		levs.add(3);
		BasRegionExample example = new BasRegionExample();
		BasRegionExample.Criteria criteria = example.createCriteria();
		criteria.andTitleLike(currentCity);
		criteria.andLevelIn(levs);
		List<BasRegion> regions = basRegionMapper.selectByExample(example);
		if (regions.size() > 0) {
			return regions.get(0).getId().toString();
		}
		return null;
	}

//	public List<BasRegion> getRegionsByIdAndLevel(Integer regionId, Integer level) {
//		List<BasRegion> list = new ArrayList<BasRegion>();
//		if (level.intValue() == 2) {
//			list = basRegionMapper.getRegionsByProvinceId(regionId);
//		} else if (level.intValue() == 3) {
//			list = basRegionMapper.getRegionsByCityId(regionId);
//		}
//		return list;
//	}
}
