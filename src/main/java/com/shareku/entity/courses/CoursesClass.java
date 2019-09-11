package com.shareku.entity.courses;

import java.util.List;

import com.shareku.entity.Page;


public class CoursesClass {
	private Integer ccId;
	private String ccName;
	private Integer typeId;
	private String typeName;
	private String ccParentId;
	private Integer ccSort;
	private String ccTitle;
	private String ccKeywords;
	private String ccDescription;
	private List<Integer> ccIds;
	private String pagetitle;
	
	public String getPagetitle() {
		return pagetitle;
	}
	public void setPagetitle(String pagetitle) {
		this.pagetitle = pagetitle;
	}
	private Page page;

	
	public List<Integer> getCcIds() {
		return ccIds;
	}
	public void setCcIds(List<Integer> ccIds) {
		this.ccIds = ccIds;
	}
	public Integer getCcId() {
		return ccId;
	}
	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}
	public String getCcName() {
		return ccName;
	}
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCcParentId() {
		return ccParentId;
	}
	public void setCcParentId(String ccParentId) {
		this.ccParentId = ccParentId;
	}
	public Integer getCcSort() {
		return ccSort;
	}
	public void setCcSort(Integer ccSort) {
		this.ccSort = ccSort;
	}
	public String getCcTitle() {
		return ccTitle;
	}
	public void setCcTitle(String ccTitle) {
		this.ccTitle = ccTitle;
	}
	public String getCcKeywords() {
		return ccKeywords;
	}
	public void setCcKeywords(String ccKeywords) {
		this.ccKeywords = ccKeywords;
	}
	public String getCcDescription() {
		return ccDescription;
	}
	public void setCcDescription(String ccDescription) {
		this.ccDescription = ccDescription;
	}
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
