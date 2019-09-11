package com.shareku.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shareku.entity.auth.Menu;
import com.shareku.entity.auth.User;
import com.shareku.service.auth.MenuService;
import com.shareku.util.Const;
import com.shareku.util.RightsHelper;
import com.shareku.util.Tools;

public class RightsHandlerInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private MenuService menuService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if(path.matches(Const.NO_INTERCEPTOR_PATH))
			return true;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		Integer menuId = null;
		List<Menu> subList = menuService.listAllSubMenu();
		for(Menu m : subList){
			String menuUrl = m.getMenuUrl();
			if(Tools.notEmpty(menuUrl)){
				if(path.contains(menuUrl)){
					menuId = m.getMenuId();
					break;
				}else{
					String[] arr = menuUrl.split("\\.");
					String regex = "";
					if(arr.length==2){
						regex = "/?"+arr[0]+"(/.*)?."+arr[1];
						
					}else{
						regex = "/?"+menuUrl+"(/.*)?.shtml";
					}
					if(path.matches(regex)){
						menuId = m.getMenuId();
						break;
					}
				}
			}
		}
		//System.out.println(path+"===="+menuId);
		if(menuId!=null){
			//user = ServiceHelper.getUserService().getUserAndRoleById(user.getUserId());
			String userRights = (String) session.getAttribute(Const.SESSION_USER_RIGHTS);
			String roleRights = (String) session.getAttribute(Const.SESSION_ROLE_RIGHTS);
			if(RightsHelper.testRights(userRights, menuId) || RightsHelper.testRights(roleRights, menuId)){
				return true;
			}else{
				System.out.println("用户："+user.getLoginname()+"试图访问"+path+"被阻止！");
				ModelAndView mv = new ModelAndView();
				mv.setViewName("no_rights");
				throw new  ModelAndViewDefiningException(mv);
			}
		}
		return super.preHandle(request, response, handler);
	}
	
}
