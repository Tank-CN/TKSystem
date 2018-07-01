package com.tk.controller.admin.bas;

import com.tk.manage.BasRegionManage;
import com.tk.model.BasRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



/**
 * 地区管理
 * 
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/admin/bas")
public class BasRegionController {

	@Autowired
	private BasRegionManage basRegionManage;

	@RequestMapping(value = "region")
	public String index() {
		return "admin/bas/region";
	}

	@RequestMapping(value = "region/list", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getListByPid(@RequestParam(value = "pid", defaultValue = "0") Integer pid, @RequestParam(value = "children", defaultValue = "true") Boolean children) {
		List<BasRegion> list = basRegionManage.getByPid(pid);
		List<Map<String, Object>> resList = new LinkedList<Map<String, Object>>();
		for (BasRegion basRegion : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentIdStr = basRegion.getPid() == 1 ? "#" : basRegion.getPid().toString();
			map.put("id", basRegion.getId());
			map.put("level", basRegion.getLevel());
			map.put("parent", parentIdStr);
			map.put("text", basRegion.getTitle());
			map.put("pid", basRegion.getPid().toString());
			map.put("children", children);
			resList.add(map);
		}
		return resList;
	}

	@RequestMapping(value = "region/detail/{regionId}", method = RequestMethod.POST)
	@ResponseBody
	public BasRegion detail(@PathVariable("regionId") int regionId) {
		return basRegionManage.getById(regionId);
	}

	@RequestMapping(value = "region/save", method = RequestMethod.POST)
	@ResponseBody
	public Boolean save(BasRegion basRegion) {
		return basRegionManage.save(basRegion) == 1;
	}

	@RequestMapping(value = "region/delete/{dictionaryId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean delete(@PathVariable("dictionaryId") int dictionaryId) {
		return basRegionManage.delete(dictionaryId) == 1;
	}

	@RequestMapping(value = "region/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(BasRegion basRegion) {
		return basRegionManage.update(basRegion) == 1;
	}

}
