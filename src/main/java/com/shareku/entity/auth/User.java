package com.shareku.entity.auth;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Pattern;

import com.shareku.entity.Page;
import com.shareku.util.Tools;

public class User {
	private Integer userId;
	
	//@Size(min=6,max=20,message="用户名为6~20位字母、数字、下划线组合！")
	//@NotEmpty(message="用户名不能为空！")
	//@List(value={@Pattern(regexp="\\d{2,3}"),@Pattern(regexp="^[a-zA-Z0-9_]{6,20}$")})
	@Pattern(regexp="^[a-zA-Z0-9_]{6,20}$",message="用户名为6~20位字母、数字、下划线组合！")
	private String loginname;
	//@Email(message="邮箱格式不正确！")
	@Pattern(regexp="^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$",message="邮箱格式不正确！")
	private String email;	//用户注册使用
	private String username;
	@Pattern(regexp="^[a-zA-Z0-9_]{6,20}$",message="密码为6~20位字母、数字、下划线组合！")
	private String password;
	private String rights;
	private Integer status;
	private Integer roleId;
	private Date lastLogin;
	private Role role;
	private Page page;
	private Date lastLoginStart;
	private Date lastLoginEnd;
	
	//BEGIN Ray
	private Integer gender;
	private String birthday;
	private String qqcode;
	private Integer occupation;
	private String phone;
	private String mobile;
	private String address;
	private String header_pic; //header_pic
	private String user_desc; //user_desc
	private Integer province;
	private Integer city;
	
	//找回密码使用
	private String encrypt_key;
	private Timestamp timeout;
	
	
	
	//END Ray
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getOccupation() {
		return occupation;
	}
	public void setOccupation(Integer occupation) {
		this.occupation = occupation;
	}
	public String getQqcode() {
		return qqcode;
	}
	public void setQqcode(String qqcode) {
		this.qqcode = qqcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return Tools.getMD5(this.password);
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Date getLastLoginStart() {
		return lastLoginStart;
	}
	public void setLastLoginStart(Date lastLoginStart) {
		this.lastLoginStart = lastLoginStart;
	}
	public Date getLastLoginEnd() {
		return lastLoginEnd;
	}
	public void setLastLoginEnd(Date lastLoginEnd) {
		this.lastLoginEnd = lastLoginEnd;
	}
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		if(gender == null){	this.gender = 1;}
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEncrypt_key() {
		return encrypt_key;
	}
	public void setEncrypt_key(String encrypt_key) {
		this.encrypt_key = encrypt_key;
	}
	public Timestamp getTimeout() {
		return timeout;
	}
	public void setTimeout(Timestamp timeout) {
		this.timeout = timeout;
	}
	public String getHeader_pic() {
		return header_pic;
	}
	public void setHeader_pic(String header_pic) {
		this.header_pic = header_pic;
	}
	public String getUser_desc() {
		return user_desc;
	}
	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}
}
