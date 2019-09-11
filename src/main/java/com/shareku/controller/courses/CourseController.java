package com.shareku.controller.courses;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.shareku.entity.Code;
import com.shareku.entity.Page;
import com.shareku.entity.courses.Course;
import com.shareku.entity.courses.CoursesClass;
import com.shareku.service.courses.CourseService;
import com.shareku.service.courses.CoursesClassService;
import com.shareku.util.Config;
import com.shareku.util.Tools;

@Controller
@RequestMapping(value="/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private CoursesClassService coursesClassService;
	private static final String path; //课程文件存放父目录  
	private static final String imagepath; //课程文件存放父目录  
	private final String SPLIT = "/"; //
	
	public static Config config = null;
	
	static {
		config = Config.getInstance();
		//视频及图片存放地址
		path = config.getValue("video.path");
		imagepath = config.getValue("image.path");
	}
	
	@RequestMapping
	public ModelAndView list(Course c){
		List<Course> coursesList = courseService.listPageCourse(c);
		ModelAndView mv = new ModelAndView();
		mv.addObject("courseList", coursesList);
		mv.addObject("course", c);
		//获取代码列表
		Map<String, List<Code>> map = getCodeList();
		for (String key : map.keySet()) {
			mv.addObject(key, map.get(key));
		}
		mv.setViewName("courses/courses_list");
		return mv;
	}
	
	
	/**
	 * 出售的课程列表
	 * @param c
	 * @return
	 */
	@RequestMapping(value="/online")
	public ModelAndView onlineList(Course c){
		List<Course> coursesList = courseService.listPageCourse(c);
		ModelAndView mv = new ModelAndView();
		mv.addObject("courseList", coursesList);
		mv.addObject("course", c);
		mv.setViewName("school/online");
		return mv;
	}
	
	
	/**
	 * 仓库的课程列表
	 * @param c
	 * @return
	 */
	@RequestMapping(value="/outline")
	public ModelAndView outlineList(Course c){
		List<Course> coursesList = courseService.listPageCourse(c);
		ModelAndView mv = new ModelAndView();
		mv.addObject("courseList", coursesList);
		mv.addObject("course", c);
		mv.setViewName("school/outline");
		return mv;
	}
	
	
	
	/**
	 * 首页显示精品课程
	 * @param c
	 * @return
	 */
	@RequestMapping(value="/indexCourse")
	public void indexCourse(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		String typeId = request.getParameter("typeId");
		Course c = new Course();
		CoursesClass cc = new CoursesClass();
		cc.setTypeId(NumberUtils.toInt(typeId));
		List<CoursesClass> classList = coursesClassService.listAllCoursesClass(cc);
		cc = classList.get(0);
		c.setCourseOne(NumberUtils.toInt(typeId));
		List<Course> coursesList = courseService.listPageCourse(c);
		JSONArray jsonList = new JSONArray();
		if (coursesList.size() > 4) {
			coursesList = coursesList.subList(0, 3);
		} 
			for(Course cou : coursesList) {
				JSONObject json = new JSONObject();
				json.put("courseId", cou.getCourseId());
				json.put("courseOne", cc.getCcName());
				json.put("courseTwo", cou.getCourseTwo());
				json.put("courseThree", cou.getCourseThree());
				json.put("courseName", cou.getCourseName());
				json.put("keywords", cou.getKeywords());
				json.put("coursePrice", cou.getCoursePrice());
				json.put("valuations", cou.getValuations());
				json.put("discount", cou.getDiscount());
				json.put("imagePath", cou.getImagePath().replaceAll("/data/image/", ""));
				json.put("sales", cou.getSales());
				json.put("lecturer", cou.getLecturer());
				json.put("description", cou.getDescription());
				json.put("recommend", cou.getRecommend());
				json.put("status", cou.getStatus());
				json.put("date", Tools.date2Str(cou.getDate()));
				json.put("filePath", cou.getFilePath());
				jsonList.add(json);
			}
		
		try {
			response.getWriter().write(jsonList.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 详情页关键词
	 * @param resp
	 * @param tId
	 */
	@RequestMapping(value="/detail_keywords.shtml")
	public @ResponseBody HashMap<String,String> keywords(HttpServletRequest request, HttpServletResponse resp){
		Course c = new Course();
		String cid = request.getParameter("cid");
		int id = NumberUtils.toInt(cid);
		c.setCourseId(id);
		List<Course> list = courseService.listPageCourse(c);
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("keywords", list.get(0).getKeywords());
		map.put("description", list.get(0).getDescription());
		map.put("pagetitle", list.get(0).getPagetitle());
		return map;
	}
	
	@RequestMapping(value="/listRecommend")
	public void listRecommend(HttpServletResponse resp, @RequestParam String tId){
		Course c = new Course();
		int id = NumberUtils.toInt(tId);
		if(tId.matches("^[0-9][0-9]0000")){
			c.setCourseOne(id);
		} else if(tId.matches("^[0-9][0-9][0-9][1-9]00")) {
			c.setCourseTwo(id);
		} else {
			c.setCourseThree(id);
		}
		c.setRecommend(1);
		List<Course> coursesList = courseService.listPageCourse(c);
		if (coursesList.size() > 7) {
			coursesList = coursesList.subList(0, 6);
		}
		
	}
	
	@RequestMapping(value="/listByClass")
	public void listByClass(HttpServletResponse resp, @RequestParam String tId, @RequestParam int index, @RequestParam int size){
		Course c = new Course();
		int id = NumberUtils.toInt(tId);
		if(tId.matches("^[0-9][0-9]0000")){
			c.setCourseOne(id);
		} else if(tId.matches("^[0-9][0-9][0-9][1-9]00")) {
			c.setCourseTwo(id);
		} else {
			c.setCourseThree(id);
		}
		Page page = new Page();
		page.setShowCount(size);
		page.setCurrentPage(index);
		c.setPage(page);
		List<Course> coursesList = courseService.listPageCourse(c);
		JSONArray jsonList = new JSONArray();
		//添加总条数
		JSONObject j = new JSONObject();
		j.put("itemCount", c.getPage().getTotalResult());
		jsonList.add(j);
		for (Course cou : coursesList) {
			JSONObject json = new JSONObject();
			json.put("courseId", cou.getCourseId());
			json.put("courseOne", cou.getCourseOne());
			json.put("courseTwo", cou.getCourseTwo());
			json.put("courseThree", cou.getCourseThree());
			json.put("courseName", cou.getCourseName());
			json.put("keywords", cou.getKeywords());
			json.put("coursePrice", cou.getCoursePrice());
			json.put("valuations", cou.getValuations());
			json.put("discount", cou.getDiscount());
			json.put("imagePath", cou.getImagePath().replaceAll("/data/image/", ""));
			json.put("sales", cou.getSales());
			json.put("lecturer", cou.getLecturer());
			json.put("description", cou.getDescription());
			json.put("recommend", cou.getRecommend());
			json.put("status", cou.getStatus());
			json.put("date", Tools.date2Str(cou.getDate()));
			json.put("filePath", cou.getFilePath());
			jsonList.add(json);
		}
		resp.setContentType("text/html;charset=UTF-8");
		try {
			resp.getWriter().print(jsonList.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/listBySubClass")
	public void listAllByClass(HttpServletResponse resp, @RequestParam String tId){
		Course c = new Course();
		int id = NumberUtils.toInt(tId);
		int level = -1;
		if (tId.matches("000000")) {
			//c.setCourseOne(id);
			level = 0;
		} else if(tId.matches("^[0-9][0-9]0000")){
			c.setCourseOne(id);
			level = 1;
		} else if(tId.matches("^[0-9][0-9][0-9][1-9]00")) {
			c.setCourseTwo(id);
			level = 2;
		} else {
			c.setCourseThree(id);
		}
		List<Course> coursesList = courseService.listAllCourse(c);
		//获取相应的子分类
		CoursesClass cc = new CoursesClass();
		cc.setCcParentId(tId);
		List<CoursesClass> ccList = coursesClassService.listAllCoursesClass(cc);
		Iterator<CoursesClass> it = ccList.iterator();
		JSONObject json = new JSONObject();
		while (it.hasNext()) {
			CoursesClass cc1 = it.next();
			int typeId = cc1.getTypeId();
			String typeName = cc1.getTypeName();
			int counter = 0;
			//遍历数量
			for (Course cou : coursesList) {
				int ttid = 0;
				switch (level) {
					case 1:
						ttid = cou.getCourseTwo();
						break;
					case 2:
						ttid = cou.getCourseThree();
						break;
					case 0:
						ttid = cou.getCourseOne();
				}
				if (typeId == ttid) {
					counter++;
				}
			}
			//固定成六位
			json.put(Tools.formatFixedNumber(typeId), typeName + "|" + counter);
		}
		resp.setContentType("text/html;charset=UTF-8");
		try {
			resp.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 请求新增商品页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public ModelAndView toAdd(Model model){
		ModelAndView mv = new ModelAndView();
		//获取分类列表
		List<Code> classes = getClassList("000000");
		mv.addObject("one", classes);
		List<Code> classes2 = getClassList(classes.get(0).getCode());
		mv.addObject("two", classes2);
		List<Code> classes3 = getClassList(classes2.get(0).getCode());
		mv.addObject("three", classes3);
		//获取代码列表
		Map<String, List<Code>> map = getCodeList();
		for (String key : map.keySet()) {
			mv.addObject(key, map.get(key));
		}
		mv.setViewName("courses/courses_info");
		
		return mv;
	}
	
	/**
	 * 获取下级分类
	 * @param coursesId
	 * @param out
	 */
	@RequestMapping(value="/next")
	public void getNext(@RequestParam String typeId, HttpServletResponse response){
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
		response.setCharacterEncoding("UTF-8");  
		JSONObject type = new JSONObject();
		List<Code> types = getClassList(typeId);
		for(Code c : types) {
			type.put(c.getCode(), c.getView());
		}
		//System.out.println(type.toString());
		try {
			response.getWriter().write(type.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 保存商品信息
	 * @param courses
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveUser(Course c){
		ModelAndView mv = new ModelAndView();
		//如果存在id，则证明是修改操作，否则是删除操作
		if (c.getCourseId() != null && c.getCourseId().intValue() != 0) {
			//日期类型最好改成带时间的 datatime
//			c.setDate(new Date());
			courseService.updateCourse(c);
		} else {
			c.setDate(new Date());
			c.setFilePath("");
			courseService.insertCourse(c);
		}
		mv.addObject("couese",c);
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 请求编辑商品页面
	 * @param coursesId
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView toEdit(@RequestParam int coursesId){
		ModelAndView mv = new ModelAndView();
		//课程
		Course c = courseService.getCourseById(coursesId);
		mv.addObject("course", c);
		//获取分类列表
		List<Code> classes = getClassList("000000");
		mv.addObject("one", classes);
		List<Code> classes2 = getClassList(c.getCourseOne() + "");
		mv.addObject("two", classes2);
		List<Code> classes3 = getClassList(c.getCourseTwo() + "");
		mv.addObject("three", classes3);
		//获取代码列表
		Map<String, List<Code>> map = getCodeList();
		for (String key : map.keySet()) {
			mv.addObject(key, map.get(key));
		}
		mv.setViewName("courses/courses_info");
		return mv;
	}
	
	/**
	 * 请求商品详情页面
	 * @param coursesId
	 * @return
	 */
	@RequestMapping(value="/detail")
	public void toDetail(HttpServletResponse resp, @RequestParam int cId){
		//课程
		Course c = courseService.getCourseById(cId);
		//获取代码列表
		JSONObject course = new JSONObject();
		if (c != null) {
			course.put("courseId", c.getCourseId());
			course.put("courseOne", c.getCourseOne());
			course.put("courseTwo", c.getCourseTwo());
			course.put("courseThree", c.getCourseThree());
			course.put("courseName", c.getCourseName());
			course.put("keywords", c.getKeywords());
			course.put("coursePrice", c.getCoursePrice());
			course.put("valuations", c.getValuations());
			course.put("discount", c.getDiscount());
			course.put("imagePath", c.getImagePath().replaceAll("/data/image/", ""));
			course.put("sales", c.getSales());
			course.put("lecturer", c.getLecturer());
			course.put("description", c.getDescription());
			course.put("recommend", c.getRecommend());
			course.put("status", c.getStatus());
			course.put("date", Tools.date2Str(c.getDate()));
			course.put("filePath", c.getFilePath());
		}
		resp.setContentType("text/html;charset=UTF-8");
		try {
			resp.getWriter().print(course.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除某个商品
	 * @param coursesId
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteUser(HttpServletRequest request, PrintWriter out){
		Course c = new Course();
		String ids = request.getParameter("cIds");
		String[] cIds = ids.split("\\,");
		List<Integer> list = new ArrayList<Integer>();
		for (String id : cIds) {
			list.add(NumberUtils.toInt(id));
		}
		c.setIds(list);
		courseService.deleteCourse(c);
		out.write("success");
		out.close();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}

	/**
	 * 获取code列表
	 * @return
	 */
	private Map<String, List<Code>> getCodeList() {
		Map<String, List<Code>> map = new HashMap<String, List<Code>>();
		//课程状态
		List<Code> coursesStateList = new ArrayList<Code>();
		coursesStateList.add(new Code("0", "下架"));
		coursesStateList.add(new Code("1", "正常"));
		coursesStateList.add(new Code("10", "违规"));
		map.put("coursesStateList", coursesStateList);
		//课程审核状态
		List<Code> coursesVerifyList = new ArrayList<Code>();
		coursesVerifyList.add(new Code("0", "未通过"));
		coursesVerifyList.add(new Code("1", "通过"));
		coursesVerifyList.add(new Code("10", "审核中"));
		map.put("coursesVerifyList", coursesVerifyList);
		//课程推荐
		List<Code> coursesCommendList = new ArrayList<Code>();
		coursesCommendList.add(new Code("0", "否"));
		coursesCommendList.add(new Code("1", "是"));
		map.put("coursesCommendList", coursesCommendList);
		return map;
	}
	//获取分类列表
	private List<Code> getClassList(String id) {
		List<Code> li = new ArrayList<Code>();
		CoursesClass cl = new CoursesClass();
		cl.setCcParentId(id);
		List<CoursesClass> ccList = coursesClassService.listPageCoursesClass(cl);
		for(CoursesClass cc : ccList) {
			System.out.println(cc.getTypeId() + ":" + cc.getTypeName());
			Code code =  new Code();
			code.setCode(cc.getTypeId() + "");
			code.setView(cc.getTypeName());
			if (li.contains(code)) {
				continue;
			} else {
				li.add(code);
			}
		}
		return li;
	}
	
	/**
	 * 请求商品详情页面
	 * @param coursesId
	 * @return
	 */
	@RequestMapping(value="/add_video")
	public ModelAndView add_video(@RequestParam int coursesId, @RequestParam String type){
		ModelAndView mv = new ModelAndView();
		//课程
		mv.addObject("type", type);
		mv.addObject("coursesId", coursesId);
		mv.setViewName("courses/video_info");
		return mv;
	}
	
	/**
	 * 课程上传视频文件
	 * 
	 */
	@RequestMapping(value="/upload")
	public ModelAndView upload(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
//		String coursesId =  request.getParameter("coursesId");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		String videoName = file.getOriginalFilename();
		String localPath = path + SPLIT + videoName;
		if (storageFile(file, localPath)) {
//			Course c = courseService.getCourseById(NumberUtils.toInt(coursesId));
//			String filePath = c.getFilePath();
//			if(filePath == null || "".equals(filePath)) {
//				c.setFilePath(videoName);
//			} else {
//				c.setFilePath(videoName);
//			}
//			courseService.updateCourse(c);
			System.out.println("上传成功");
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 课程图片文件
	 * 
	 */
	@RequestMapping(value="/uploadimage")
	public ModelAndView uploadimage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
//		String coursesId =  request.getParameter("coursesId");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		String imgName = file.getOriginalFilename();
		String localPath = imagepath + SPLIT + imgName;
		if (storageFile(file, localPath)) {
//			Course c = courseService.getCourseById(NumberUtils.toInt(coursesId));
//			//只存图片名就可以了
//			c.setImagePath(imgName);
//			courseService.updateCourse(c);
			System.out.println("上传成功");
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

	private boolean storageFile(MultipartFile file, String localPath) {
		System.out.println(localPath);
		try {
			File localfile = new File(localPath);
			if(!localfile.exists()) {
				localfile.createNewFile();
			}
			FileUtils.copyInputStreamToFile(file.getInputStream(), localfile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
