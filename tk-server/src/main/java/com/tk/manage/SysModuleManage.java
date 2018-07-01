package com.tk.manage;

import com.tk.mapper.ex.SysModuleExMapper;
import com.tk.model.SysModule;
import com.tk.model.SysModuleExample;
import com.tk.model.SysRoleModule;
import com.tk.vo.ModuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Component
@Transactional(readOnly = true)
public class SysModuleManage extends BaseManage {

	@Autowired
	SysModuleExMapper sysModuleExMapper;

	public List<SysModule> getByUser(Long userId) {
		return sysModuleExMapper.selectByUser(userId);
	}

	/**
	 * 超级管理员
	 * 
	 * 
	 * @return
	 */
	public List<ModuleVo> getByAdmin() {
		SysModuleExample example = new SysModuleExample();
		SysModuleExample.Criteria criter = example.createCriteria();
		criter.andFlagEqualTo((byte) 1);
		List<SysModule> modules = sysModuleExMapper.selectByExample(example);
		if (null != modules && modules.size() > 0) {
			List<ModuleVo> moduleList = new ArrayList<ModuleVo>();
			Map<String, List<SysModule>> modelMap = new HashMap<String, List<SysModule>>();
			for (SysModule module : modules) {
				if (modelMap.get(module.getPcode()) == null) {
					List<SysModule> list = new LinkedList<>();
					list.add(module);
					modelMap.put(module.getPcode(), list);
				} else {
					List<SysModule> list = (List<SysModule>) modelMap.get(module.getPcode());
					list.add(module);
					modelMap.put(module.getPcode(), list);
				}
			}
			List<SysModule> pl = modelMap.get("0");
			if (null != pl && pl.size() > 0) {
				for (SysModule sm : pl) {
					ModuleVo vo = new ModuleVo();
					vo.setCode(sm.getCode());
					vo.setId(sm.getId());
					vo.setIntro(sm.getIntro());
					vo.setLevel(sm.getLevel());
					vo.setPcode(sm.getPcode());
					vo.setTitle(sm.getTitle());
					vo.setUrl(sm.getUrl());
					vo.setList(modelMap.get(vo.getCode()));
					moduleList.add(vo);
				}
				return moduleList;
			}
		}
		return null;
	}

	/**
	 * 得到用户所对应角色下的模块权限
	 * 
	 * 
	 * @param userId
	 * @return
	 */
	public List<ModuleVo> getByUid(Long userId) {
		List<SysModule> modules = sysModuleExMapper.selectByUser(userId);
		if (null != modules && modules.size() > 0) {
			List<ModuleVo> moduleList = new ArrayList<ModuleVo>();
			Map<String, List<SysModule>> modelMap = new HashMap<String, List<SysModule>>();
			for (SysModule module : modules) {
				if (modelMap.get(module.getPcode()) == null) {
					List<SysModule> list = new LinkedList<>();
					list.add(module);
					modelMap.put(module.getPcode(), list);
				} else {
					List<SysModule> list = (List<SysModule>) modelMap.get(module.getPcode());
					list.add(module);
					modelMap.put(module.getPcode(), list);
				}
			}
			List<SysModule> pl = modelMap.get("0");
			if (null != pl && pl.size() > 0) {
				for (SysModule sm : pl) {
					ModuleVo vo = new ModuleVo();
					vo.setCode(sm.getCode());
					vo.setId(sm.getId());
					vo.setIntro(sm.getIntro());
					vo.setLevel(sm.getLevel());
					vo.setPcode(sm.getPcode());
					vo.setTitle(sm.getTitle());
					vo.setUrl(sm.getUrl());
					vo.setList(modelMap.get(vo.getCode()));
					moduleList.add(vo);
				}
				return moduleList;
			}
		}
		return null;
	}

	/**
	 * 得到角色下的模块
	 * 
	 * 
	 * @param rid
	 * @return
	 */
	public List<ModuleVo> getByRole(Long rid) {
		List<SysModule> modules = sysModuleExMapper.selectByRole(rid);
		if (null != modules && modules.size() > 0) {
			List<ModuleVo> moduleList = new ArrayList<ModuleVo>();
			Map<String, List<SysModule>> modelMap = new HashMap<String, List<SysModule>>();
			for (SysModule module : modules) {
				if (modelMap.get(module.getPcode()) == null) {
					List<SysModule> list = new LinkedList<>();
					list.add(module);
					modelMap.put(module.getPcode(), list);
				} else {
					List<SysModule> list = (List<SysModule>) modelMap.get(module.getPcode());
					list.add(module);
					modelMap.put(module.getPcode(), list);
				}
			}
			List<SysModule> pl = modelMap.get("0");
			if (null != pl && pl.size() > 0) {
				for (SysModule sm : pl) {
					ModuleVo vo = new ModuleVo();
					vo.setCode(sm.getCode());
					vo.setId(sm.getId());
					vo.setIntro(sm.getIntro());
					vo.setLevel(sm.getLevel());
					vo.setPcode(sm.getPcode());
					vo.setTitle(sm.getTitle());
					vo.setUrl(sm.getUrl());
					vo.setList(modelMap.get(vo.getCode()));
					moduleList.add(vo);
				}
				return moduleList;
			}
		}
		return null;
	}

	/**
	 * 分组，并判断权限是否选择
	 * 
	 * @param sysRoles
	 * @return
	 */
	public List<ModuleVo> getAllByGroup(List<SysRoleModule> sysRoles) {
		List<SysModule> modules = getAll();
		if (null != modules && modules.size() > 0) {
			List<ModuleVo> moduleList = new ArrayList<ModuleVo>();
			Map<String, List<SysModule>> modelMap = new HashMap<String, List<SysModule>>();
			for (SysModule module : modules) {
				module.setFlag((byte) 0);
				if (null != sysRoles && sysRoles.size() > 0) {
					for (SysRoleModule srm : sysRoles) {
						if (srm.getMid() == module.getId()) {
							module.setFlag(Byte.valueOf("1"));
							break;
						}
					}
				}
				if (modelMap.get(module.getPcode()) == null) {
					List<SysModule> list = new LinkedList<>();
					list.add(module);
					modelMap.put(module.getPcode(), list);
				} else {
					List<SysModule> list = (List<SysModule>) modelMap.get(module.getPcode());
					list.add(module);
					modelMap.put(module.getPcode(), list);
				}
			}
			List<SysModule> pl = modelMap.get("0");
			if (null != pl && pl.size() > 0) {
				for (SysModule sm : pl) {
					ModuleVo vo = new ModuleVo();
					vo.setCode(sm.getCode());
					vo.setId(sm.getId());
					vo.setIntro(sm.getIntro());
					vo.setLevel(sm.getLevel());
					vo.setPcode(sm.getPcode());
					vo.setTitle(sm.getTitle());
					vo.setUrl(sm.getUrl());
					vo.setList(modelMap.get(vo.getCode()));
					vo.setFlag(null == sm.getFlag() ? Byte.valueOf("0") : sm.getFlag());
					moduleList.add(vo);
				}
				return moduleList;
			}
		}
		return null;
	}

	public List<SysModule> getAll() {
		SysModuleExample example = new SysModuleExample();
		SysModuleExample.Criteria criteria = example.createCriteria();
		criteria.andFlagEqualTo(Byte.valueOf("1"));
		return sysModuleExMapper.selectByExample(example);
	}

	public SysModule getSysModule(int aid) {
		return sysModuleExMapper.selectByPrimaryKey(aid);
	}

	public Integer saveSysModule(SysModule sysModule) {
		// 判断重复
		SysModuleExample example = new SysModuleExample();
		example.or(example.createCriteria().andTitleEqualTo(sysModule.getTitle()));
		example.or(example.createCriteria().andUrlEqualTo(sysModule.getUrl()));
		example.or(example.createCriteria().andCodeEqualTo(sysModule.getCode()));
		List<SysModule> list = sysModuleExMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			return 0;
		}

		return sysModuleExMapper.insert(sysModule);
	}

	public void updateSysModule(SysModule newModule) {
		SysModule oldModule = sysModuleExMapper.selectByPrimaryKey(newModule.getId());
		SysModuleExample example = new SysModuleExample();
		example.createCriteria().andPcodeEqualTo(oldModule.getCode());
		if (oldModule.getPcode().equals("0")) {
			List<SysModule> list = sysModuleExMapper.selectByExample(example);
			for (SysModule sysModule : list) {
				sysModule.setPcode(newModule.getCode());
				sysModuleExMapper.updateByPrimaryKeySelective(sysModule);
			}
		}
		sysModuleExMapper.updateByPrimaryKeySelective(newModule);
	}

	public Boolean deleteSysModule(int aid) {
		SysModule sysModule = sysModuleExMapper.selectByPrimaryKey(aid);
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(aid);
		if (sysModule.getPcode().equals("0")) {
			SysModuleExample example = new SysModuleExample();
			example.createCriteria().andPcodeEqualTo(sysModule.getCode());
			List<SysModule> list = sysModuleExMapper.selectByExample(example);
			for (SysModule sysModule2 : list) {
				ids.add(sysModule2.getId());
			}
		}
		SysModuleExample example = new SysModuleExample();
		example.createCriteria().andIdIn(ids);
		//		 module.setFlag(Byte.valueOf("0"));
		//		 sysModuleExMapper.updateByPrimaryKey(module);
		return sysModuleExMapper.deleteByExample(example) > 0;
	}

	public SysModule get(Integer id) {
		return sysModuleExMapper.selectByPrimaryKey(id);
	}

}
