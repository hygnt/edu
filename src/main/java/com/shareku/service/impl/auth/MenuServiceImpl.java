package com.shareku.service.impl.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shareku.entity.auth.Menu;
import com.shareku.mapper.auth.MenuMapper;
import com.shareku.service.auth.MenuService;

public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	
	public void deleteMenuById(Integer menuId) {
		menuMapper.deleteMenuById(menuId);
	}

	public Menu getMenuById(Integer menuId) {
		return menuMapper.getMenuById(menuId);
	}

	public List<Menu> listAllParentMenu() {
		return menuMapper.listAllParentMenu();
	}

	public void saveMenu(Menu menu) {
		if(menu.getMenuId()!=null && menu.getMenuId().intValue()>0){
			menuMapper.updateMenu(menu);
		}else{
			menuMapper.insertMenu(menu);
		}
	}

	public List<Menu> listSubMenuByParentId(Integer parentId) {
		return menuMapper.listSubMenuByParentId(parentId);
	}
	
	public List<Menu> listAllMenu() {
		List<Menu> rl = this.listAllParentMenu();
		for(Menu menu : rl){
			List<Menu> subList = this.listSubMenuByParentId(menu.getMenuId());
			menu.setSubMenu(subList);
		}
		return rl;
	}

	public List<Menu> listAllSubMenu(){
		return menuMapper.listAllSubMenu();
	}
	
}
