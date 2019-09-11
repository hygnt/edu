package com.shareku.controller.member;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shareku.entity.auth.User;
import com.shareku.service.auth.UserService;
import com.shareku.util.Const;
@Controller
public class MemberController {
	@Autowired
	private UserService us;
	
	@RequestMapping(value="/member/logout.shtml",method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_USER_RIGHTS);
		return "redirect:/index.html";
	}
	
	@RequestMapping(value="/member/register.shtml",method=RequestMethod.GET)
	public String addUser(Model model){
		model.addAttribute("user",new User());
		return "member/register";
	}
	@RequestMapping(value="/member/register.shtml",method=RequestMethod.POST)
	public String addUser(@Validated User user,BindingResult br,HttpSession session){
		if(br.hasErrors()){
			return "member/register";
		}
		if(!us.insertUser(user)){	//存在该用户返回false
			br.addError(new ObjectError("loginname", "该用户名已经被注册！"));
			return "member/register";
		}
		session.setAttribute(Const.SESSION_USER,user);
		us.updateLastLogin(user);
		return "redirect:/index.html";
	}
	
	@RequestMapping(value="/member/login.shtml",method=RequestMethod.GET)
	public String login(){
		//model.addAttribute("user", new User());
		return "member/login";
	}
	@RequestMapping(value="/member/login.shtml",method=RequestMethod.POST)
	public String login(Model model,String loginname,String password,HttpSession session){
		String errInfo = "";
		User loginUser = us.getUserByNameAndPwd(loginname,password);
		if(loginUser!=null){
			loginUser.setLastLogin(new Date());
			us.updateLastLogin(loginUser);
			session.setAttribute(Const.SESSION_USER, loginUser);
			errInfo = "";
		}else{
			errInfo = "用户名、密码不一致！";
		}
		if("".equals(errInfo)){
			return "redirect:/index.html";
		}else{
			model.addAttribute("error", errInfo);
			return "member/login";
		}
	}
//	获取登录用户信息 headers={"Accept=application/json"}
	@RequestMapping(value="/member/userinfo.shtml",method=RequestMethod.GET)
	public @ResponseBody  User getLoginUserInfo(HttpSession session){
		User sessionUser = (User) session.getAttribute(Const.SESSION_USER);
		return sessionUser;
	}
	//修改用户 【个人信息】
	@RequestMapping(value="/setting/update.shtml",method=RequestMethod.GET,params={"modify"})
	public String updateUserInfo(Model model,HttpSession session){
		User user = (User) session.getAttribute(Const.SESSION_USER);
		model.addAttribute("user", user);
		return "member/login/update";
	}
	@RequestMapping(value="/setting/update.shtml",method=RequestMethod.POST)
	public String updateUserInfo(User user,HttpSession session){
		this.us.updateUserBaseInfoByLoginname(user);
		session.setAttribute(Const.SESSION_USER, user);
		return "redirect:/setting/update.shtml?modify";
	}
	
}
