package com.tk.controller.admin.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.BasDictionaryManage;
import com.tk.model.BasDictionary;
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
public class BasDictionaryController extends AdminBaseController {

	@Autowired
	private BasDictionaryManage basDictionaryManage;

	@RequestMapping(value = "/dictionary")
	public String index() {
		return "admin/sys/dictionary";
	}

	@RequestMapping(value = "dictionary/rootNode", method = RequestMethod.GET)
	@ResponseBody
	public List getRootNode(String dictionaryName) {
		JSONObject retObj = new JSONObject();
		List<BasDictionary> list = basDictionaryManage.getAll(dictionaryName);
		List resList = new LinkedList();
		for (BasDictionary basDictionary : list) {
			Map<String, String> map = new HashMap<String, String>();

			String parentIdStr = basDictionary.getPid() == 0 ? "#"
					: basDictionary.getPid().toString();

			map.put("id", basDictionary.getId().toString());
			map.put("parent", parentIdStr);
			map.put("text", basDictionary.getTitle());
			map.put("cid", basDictionary.getCid());
			resList.add(map);
		}

		retObj.put("data", resList);
		return resList;
	}

	@RequestMapping(value = "dictionary/get/{dictionaryId}", method = RequestMethod.POST)
	@ResponseBody
	public BasDictionary getNode(@PathVariable("dictionaryId") long dictionaryId) {
		return basDictionaryManage.getObjById(dictionaryId);
	}
	
	@RequestMapping(value = "dictionary/queryDictionaryByPId/{dictionaryId}", method = RequestMethod.POST)
	@ResponseBody
	public List<BasDictionary> queryDictionaryByPId(@PathVariable("dictionaryId") long dictionaryId) {
		return basDictionaryManage.queryDictionaryByPId(dictionaryId);
	}
	
	@RequestMapping(value = "dictionary/queryDictionaryById/{dictionaryId}", method = RequestMethod.POST)
	@ResponseBody
	public List<BasDictionary> queryDictionaryById(@PathVariable("dictionaryId") long dictionaryId) {
		return basDictionaryManage.queryDictionaryById(dictionaryId);
	}
	
	@RequestMapping(value = "dictionary/findListByTitle", method = RequestMethod.POST)
	@ResponseBody
	public List<BasDictionary> findListByTitle(String title) {
		return basDictionaryManage.findListByTitle(title);
	}

	@RequestMapping(value = "dictionary/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean create(BasDictionary basDictionary) {
		if(StringUtils.isEmpty(basDictionary.getIdx())){
			basDictionary.setIdx(PinyinHelper.convertToPinyinString(basDictionary.getTitle(), "", PinyinFormat.WITHOUT_TONE));
		}
		return basDictionaryManage.save(basDictionary);
	}

	@RequestMapping(value = "dictionary/delete/{dictionaryId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean delete(@PathVariable("dictionaryId") long dictionaryId) {
		basDictionaryManage.delete(dictionaryId);
		return true;
	}

	@RequestMapping(value = "dictionary/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(BasDictionary basDictionary) {
		if(StringUtils.isEmpty(basDictionary.getIdx())){
			basDictionary.setIdx(PinyinHelper.convertToPinyinString(basDictionary.getTitle(), "", PinyinFormat.WITHOUT_TONE));
		}
		return basDictionaryManage.update(basDictionary);
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
