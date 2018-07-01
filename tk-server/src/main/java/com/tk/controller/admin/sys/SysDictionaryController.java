package com.tk.controller.admin.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.SysDictionaryManage;
import com.tk.model.SysDictionary;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/sys")
public class SysDictionaryController extends AdminBaseController {

	@Autowired
	private SysDictionaryManage sysDictionaryManage;

	@RequestMapping(value = "/dictionary")
	public String index() {
		return "admin/sys/dictionary";
	}

	@RequestMapping(value = "dictionary/rootNode", method = RequestMethod.GET)
	@ResponseBody
	public List getRootNode(String dictionaryName) {
		JSONObject retObj = new JSONObject();
		List<SysDictionary> list = sysDictionaryManage.getAll(dictionaryName);
		List resList = new LinkedList();
		for (SysDictionary sysDictionary : list) {
			Map<String, String> map = new HashMap<String, String>();

			String parentIdStr = sysDictionary.getPid() == 0 ? "#"
					: sysDictionary.getPid().toString();

			map.put("id", sysDictionary.getIid().toString());
			map.put("parent", parentIdStr);
			map.put("text", sysDictionary.getTitle());
			map.put("cid", sysDictionary.getCid());
			map.put("gbcode", sysDictionary.getGbcode());
			map.put("isocode", sysDictionary.getIsocode());
			resList.add(map);
		}

		retObj.put("data", resList);
		return resList;
	}

	@RequestMapping(value = "dictionary/get/{dictionaryId}", method = RequestMethod.POST)
	@ResponseBody
	public SysDictionary getNode(@PathVariable("dictionaryId") long dictionaryId) {
		return sysDictionaryManage.getObjById(dictionaryId);
	}
	
	@RequestMapping(value = "dictionary/querySysDictionaryByPId/{dictionaryId}", method = RequestMethod.POST)
	@ResponseBody
	public List<SysDictionary> querySysDictionaryByPId(@PathVariable("dictionaryId") long dictionaryId) {
		return sysDictionaryManage.querySysDictionaryByPId(dictionaryId);
	}
	
	@RequestMapping(value = "dictionary/querySysDictionaryById/{dictionaryId}", method = RequestMethod.POST)
	@ResponseBody
	public List<SysDictionary> querySysDictionaryById(@PathVariable("dictionaryId") long dictionaryId) {
		return sysDictionaryManage.querySysDictionaryById(dictionaryId);
	}
	
	@RequestMapping(value = "dictionary/findListByTitle", method = RequestMethod.POST)
	@ResponseBody
	public List<SysDictionary> findListByTitle(String title) {
		return sysDictionaryManage.findListByTitle(title);
	}

	@RequestMapping(value = "dictionary/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean create(SysDictionary sysDictionary) {
		if(StringUtils.isEmpty(sysDictionary.getGbcode())){
			sysDictionary.setGbcode(PinyinHelper.convertToPinyinString(sysDictionary.getTitle(), "", PinyinFormat.WITHOUT_TONE));
		}
		if(StringUtils.isEmpty(sysDictionary.getIdx())){
			sysDictionary.setIdx(PinyinHelper.convertToPinyinString(sysDictionary.getTitle(), "", PinyinFormat.WITHOUT_TONE));
		}
		return sysDictionaryManage.save(sysDictionary);
	}

	@RequestMapping(value = "dictionary/delete/{dictionaryId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean delete(@PathVariable("dictionaryId") long dictionaryId) {
		sysDictionaryManage.delete(dictionaryId);
		return true;
	}

	@RequestMapping(value = "dictionary/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(SysDictionary sysDictionary) {
		if(StringUtils.isEmpty(sysDictionary.getGbcode())){
			sysDictionary.setGbcode(PinyinHelper.convertToPinyinString(sysDictionary.getTitle(), "", PinyinFormat.WITHOUT_TONE));
		}
		if(StringUtils.isEmpty(sysDictionary.getIdx())){
			sysDictionary.setIdx(PinyinHelper.convertToPinyinString(sysDictionary.getTitle(), "", PinyinFormat.WITHOUT_TONE));
		}
		return sysDictionaryManage.update(sysDictionary);
	}

	/**
	 * 树形结构不是很熟悉暂时没什么用 节点在初始化加载中全部加载完成[getRootNode]
	 * 
	 * @return
	 */
	@RequestMapping(value = "dictionary/childNode")
	@ResponseBody
	public List getChildNode() {
		List list = new LinkedList();
		Map map = new HashMap();
		map.put("id", "4");
		map.put("parent", "1");
		map.put("text", "node4");
		list.add(map);
		return list;
	}
}
