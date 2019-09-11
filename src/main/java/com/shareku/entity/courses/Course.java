package com.shareku.entity.courses;


import java.util.Date;
import java.util.List;

import lombok.Data;

import com.shareku.entity.Page;

/**
 * 课程类
 * @author guodongbin
 * @date 2014-09-28 17:12
 *
 */
@Data
public class Course {
	private Integer courseId; // 课程ID
	private Integer courseOne; //一级分类
	private Integer courseTwo; //二级分类
	private Integer courseThree; //三级分类
	private String courseName; //课程名称
	private String keywords; //关键词
	private double coursePrice; //课程出售价格
	private double valuations; //市场价
	private double discount; //折扣
	private String imagePath; //图片存放路径
	private Integer sales; //销售量
	private String lecturer; //讲师
	private String description; //课程描述
	private String pagetitle;
	private String editor1;
	private Integer recommend; //是否推荐
	private Integer status = 1; //课程状态1-仓库 2-发布
	private Date date; //发布日期
	private String filePath; //视频存放路径
	private Page page;
	private List<Integer> ids;
	
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getCourseOne() {
		return courseOne;
	}
	public void setCourseOne(Integer courseOne) {
		this.courseOne = courseOne;
	}
	public Integer getCourseTwo() {
		return courseTwo;
	}
	public void setCourseTwo(Integer courseTwo) {
		this.courseTwo = courseTwo;
	}
	public Integer getCourseThree() {
		return courseThree;
	}
	public void setCourseThree(Integer courseThree) {
		this.courseThree = courseThree;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public double getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(double coursePrice) {
		this.coursePrice = coursePrice;
	}
	public double getValuations() {
		return valuations;
	}
	public void setValuations(double valuations) {
		this.valuations = valuations;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public String getLecturer() {
		return lecturer;
	}
	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getPagetitle() {
		return pagetitle;
	}
	public void setPagetitle(String pagetitle) {
		this.pagetitle = pagetitle;
	}
	public String getEditor1() {
		return editor1;
	}
	public void setEditor1(String editor1) {
		this.editor1 = editor1;
	}
	
	
}
