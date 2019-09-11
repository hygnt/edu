<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>晒库网商家后台管理系统</title>
<link href="../school/css/global.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<script type="text/javascript" src="../school/js/ckeditor/ckeditor.js"></script>
</head>
<body>
<!--top start-->
<div id="head">
  <div class="head1200">
    <div class="logo"><a href="#" ><img src="../school/images/logo.png" width="126" height="60"/></a>
      <h1>学校管理中心</h1>
    </div>
    <!--nav start-->
    <div class="nav">
      <ul>
        <!--  <li><a href="#" target="_blank">首页</a></li>-->
        <li><a href="#" target="_blank">课程</a></li>
        <!--  <li><a href="#" target="_blank">测试</a></li>
        <li><a href="#" target="_blank">测试</a></li>
        <li><a href="#" target="_blank">测试</a></li>-->
      </ul>
    </div>
    <!--nav end-->
  </div>
</div>
<!--top end-->
<!--main start-->
<div id="main" >
  <div class="main1200">
    <div class="left">
      <div class="sidebar">
        <div class="column-title"><span class="ico-goods"></span>
          <h2>课程</h2>
        </div>
        <div class="column-menu">
          <ul id="seller_center_left_menu">
            <li><a href="../school/stip01.html">我要发布课程</a></li>
            <li class="current"><a href="javascript:void(0);">出售中的课程</a></li>
            <li><a href="../course/outline.shtml">仓库中的课程</a> </li>
            <li><a href="../school/category.html">本校课程分类</a> </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="right">
      <div class="ncsc-path"><i class="icon-desktop"></i>学校管理中心<i class="icon-angle-right">></i>课程<i class="icon-angle-right">></i>出售中的课程</div>
      <!--online start-->
      <div class="search">
        <input class="keyword" type="text" value="关键字搜索" onfocus="if(this.value=='关键字搜索'){this.value='';}" onblur="if(this.value==''){this.value='关键字搜索';} "/>
        <input type="submit" class="sub" value="搜索"/>
        <input type="submit" class="cx" value="批量撤销"/>
        <input type="submit" class="del" value="批量删除"/>
      </div>
      <div class="blank"></div>
      <div class="list">
        <ul class="ulist">
          <li class="clist">
            <dl class="qx">
              <dd style="background-color:#73bfb8;" class="xh"><input type="checkbox" />全选</dd>
              <dd style="background-color:#73bfb8;" class="sqh">编号</dd>
              <dd style="background-color:#73bfb8;" class="fmczmc">分类</dd>
              <dd style="background-color:#73bfb8;" class="dysqrxx">课程名称</dd>
              <dd style="background-color:#73bfb8;" class="zllx">主讲人</dd>
              <dd style="background-color:#73bfb8;" class="zjzt">价格</dd>
              <dd style="background-color:#73bfb8;" class="zjzt">操作</dd>
            </dl>
          </li>

			<c:choose>
			<c:when test="${not empty courseList}">
				<c:forEach items="${courseList}" var="c" varStatus="vs">
					<li class="clist">
						<dl class="qx">
							<dd class="xh">
								<input type="checkbox" name="cIds" id="cIds${c.courseId }" value="${c.courseId }"/>
							</dd>
							<dd class="sqh">KC11292834</dd>
							<dd class="fmczmc">${c.courseOne }>${c.courseTwo }>${c.courseThree }</dd>
							<dd class="dysqrxx">${c.courseName }</dd>
							<dd class="zllx">${c.lecturer }</dd>
							<dd class="zjzt">${c.coursePrice }</dd>
							<dd class="zjzt">
								<input type="submit" value="删除" onclick="del(${c.courseId });" />
								<input type="submit" value="修改" onclick="edit(${c.courseId });" />
								<input type="submit" value="撤销" onclick="putaway(${c.courseId });" />
							</dd>
						</dl>
					</li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<dd class="xh" style="float: none;width: auto;">
					没有相关数据
				</dd>
			</c:otherwise>
		</c:choose>

          <!-- li class="clist">
            <dl class="qx">
              <dd class="xh">
                <input type="checkbox" />
              </dd>
              <dd class="sqh">KC11292834</dd>
              <dd class="fmczmc">IT计算机>编程语言>JAVA</dd>
              <dd class="dysqrxx">JAVA从入门到精通</dd>
              <dd class="zllx">Ray</dd>
              <dd class="zjzt">200元</dd>
              <dd class="zjzt">
                <input type="submit" value="删除"/>
                <input type="submit" value="修改"/>
                <input type="submit" value="上架"/>
              </dd>
            </dl>
          </li-->


        </ul>

		<div class="page_and_btn">
			${course.page.pageStr }
		</div>

        <!--div class="fenye"><a href="#">首页</a><a href="#">第1页</a><a href="#">下一页</a><a href="#">末页</a></div-->

      </div>
      <!--online end-->
    </div>
  </div>
</div>
<!--main end -->
<!--footer start-->
<div id="footer">
  <div class="f1200">
    <p><a href="#">首页</a> | <a href="#">招聘英才</a> | <a href="#">合作及洽谈</a> | <a href="#">联系我们</a> | <a href="#">关于晒库网</a> </p>
    Copyright 2013-2015 Shareku Inc.,All rights reserved.<br>
    Powered by <span class="vol"><font class="b">share</font><font class="o">ku</font></span> <br>
  </div>
</div>
<!--footer end -->
</body>
</html>
