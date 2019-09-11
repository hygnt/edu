package com.shareku.controller.courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shareku.entity.courses.CoursesClass;
import com.shareku.service.courses.CoursesClassService;
import com.shareku.util.Tools;

@Controller
@RequestMapping(value="/courses_class")
public class CoursesClassController {
	
	@Autowired
	private CoursesClassService coursesClassService;
	
	@RequestMapping
	public ModelAndView list(CoursesClass cc){
//		List<CoursesClass> ccList = coursesClassService.listAllCoursesClass();
		List<CoursesClass> ccList = coursesClassService.listPageCoursesClass(cc);
		ModelAndView mv = new ModelAndView();
		mv.addObject("ccList", ccList);
		mv.addObject("courses_class", cc);
		mv.setViewName("courses/courses_class_list");
		return mv;
	}
	
	/**
	 * 请求新增课程分类页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public String toAdd(Model model){
		return "courses/courses_class_info";
	}
	
	/**
	 * 保存课程分类信息
	 * @param CoursesClass
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveUser(CoursesClass cc){
		ModelAndView mv = new ModelAndView();
		//如果存在id，则证明是修改操作，否则是删除操作
		if (cc.getCcId() != null && cc.getCcId().intValue() != 0) {
			coursesClassService.updateCoursesClass(cc);
		} else {
			coursesClassService.insertCoursesClass(cc);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 请求编辑课程分类页面
	 * @param ccId
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView toEdit(@RequestParam int ccId){
		ModelAndView mv = new ModelAndView();
		CoursesClass cc = coursesClassService.getCoursesClassById(ccId);
		mv.addObject("courses_class", cc);
		mv.setViewName("courses/courses_class_info");
		return mv;
	}
	
	/**
	 * 删除某个课程分类
	 * @param ccId
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteUser(HttpServletRequest request, PrintWriter out){
		CoursesClass cc = new CoursesClass();
		String ids = request.getParameter("ccIds");
		String[] ccIds = ids.split("\\,");
		List<Integer> list = new ArrayList<Integer>();
		for (String id : ccIds) {
			list.add(NumberUtils.toInt(id));
		}
		cc.setCcIds(list);
		coursesClassService.deleteCoursesClass(cc);
		out.write("success");
		out.close();
	}
	
	
	@RequestMapping(value="/cc")
	public void listJson(HttpServletRequest request, HttpServletResponse resp){
		resp.setContentType("text/html;charset=UTF-8");
//		//如果内存中有数据，则不用读取数据库了
//		if (this.result == null) {
//			this.result = getJsonStr();
//		}
		String pId = request.getParameter("pid");
		String result = getJsonStr(pId);
		try {
			resp.getWriter().print(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 动态获取关键词和描述
	 * @param request
	 * @param resp
	 */
	@RequestMapping(value="/detail.shtml")
	public @ResponseBody HashMap<String,String> detail(HttpServletRequest request, HttpServletResponse resp){
		resp.setContentType("text/html;charset=UTF-8");
		String tId = request.getParameter("tid");
		String cName = request.getParameter("cName");
		CoursesClass cc = new CoursesClass();
		cc.setTypeId(NumberUtils.toInt(tId));
		cc.setCcName(cName);
		List<CoursesClass> list = coursesClassService.listAllCoursesClass(cc);
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("keywords", list.get(0).getCcKeywords());
		map.put("description", list.get(0).getCcDescription());
		map.put("pagetitle", list.get(0).getPagetitle());
		return map;
	}
	
	private String getJsonStr(String pid) {
		CoursesClass cc1 = new CoursesClass();
		if (pid != null) {
			cc1.setCcParentId(pid);
		}
		List<CoursesClass> ccList = coursesClassService.listAllCoursesClass(cc1);
		Iterator<CoursesClass> it = ccList.iterator();
		//一、二、三级类别及总类别
		JSONObject one = new JSONObject();
		while (it.hasNext()) {
			CoursesClass cc = it.next();
			String tId = Tools.formatFixedNumber(cc.getTypeId());
			String pId = Tools.formatFixedNumber(cc.getCcParentId());
			JSONObject two = new JSONObject();
			two.put("id", cc.getCcId());
			two.put("cName", cc.getCcName());
			two.put("tId", tId);
//			two.put("tName", cc.getTypeName());
			two.put("pId", pId);
			two.put("sort", cc.getCcSort());
//			two.put("title", cc.getCcTitle());
			one.put(tId, two);
		}
		return one.toString();
	}
	
}