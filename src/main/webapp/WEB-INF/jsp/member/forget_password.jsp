<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/js/jquery-1.3.2.min.js"></script>
<script>
$(document).ready(function(){
	$("#resetPassword").click(function(){
		var username = $("#loginname").val();
		var email = $("#email").val();
		if(username == "" || email == ""){
			alert("登陆名或邮箱不能为空！");
		}
		$.ajax({
			type: "get",
			url: "/password/find.shtml?loginname="+username+"&email="+email,
			dataType: "json",
			success: function(data) {
				if(data['status'] == '0')
					alert(data['msg']);
				else
					alert(data['msg']);
			}
		});
		
	});
});
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>晒库网-找回密码</title>
<style type="text/css">
body {
	_behavior: url(css/csshover.htc);
}
</style>
<link href="../member/css/base.css" rel="stylesheet" type="text/css">
<link href="../member/login/css/home_header.css" rel="stylesheet" type="text/css">
<link href="../member/login/css/home_login.css" rel="stylesheet" type="text/css">
<link href="../member/login/css/sk_login.css" rel="stylesheet" type="text/css">
<link href="../member/login/css/font-awesome.min.css" rel="stylesheet" />
<link href="../member/login/css/base.css" rel="stylesheet" />
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000;
	font-weight: bold;
	font-size: 24px;
}
-->
</style>
</head>
<body>
<!-- PublicHeadLayout Begin -->
<div class="header-wrap">
  <header class="public-head-layout wrapper">
    <h1 class="site-logo"><a href="/">
    	<img src="../member/login/images/login/logo.png" class="pngFix">
    </a></h1>
  </header>
</div>
<!-- PublicHeadLayout End -->

<div class="nch-breadcrumb-layout"> </div>
<style type="text/css"> 
	.public-top-layout, .head-search-bar, .head-user-menu, .public-nav-layout, .nch-breadcrumb-layout, #faq {
		display: none !important;
	}
	.public-head-layout {
		margin: 10px auto -10px auto;
	}
	.wrapper {
		width: 1000px;
	}
	#footer {
		border-top: none!important;
		padding-top: 30px;
	}
</style>
<div class="nc-login-layout">
  <div class="left-pic"><img src="../member/login/images/login/2.jpg"  border="0"></div>
  <div class="nc-login">
    <div class="nc-login-title">
          <h3>忘记密码</h3>
    </div>
    <div class="nc-login-content" id="demo-form-site">
      	<dl>
         <dt>登录账号</dt>
          <dd style="min-height:54px;">
          	<input type="text" value="" class="text" id="loginname"/>
            <label></label>
          </dd>
        </dl>
        <dl>
           <dt>电子邮箱 </dt>
          <dd style="min-height:54px;">
          	<input type="text" value="" class="text" id="email"/>
            <label></label>
          </dd>
        </dl>
        <!-- 
        <dl>
          <dt>验证码</dt>
          <dd style="min-height:54px;">
            <input type="text" name="captcha" class="text w50 fl" id="captcha" maxlength="4" size="10" />
            <img src="images/login/cord.jpg" name="codeimage" border="0" id="codeimage" class="fl ml5"> <a href="javascript:void(0)" class="ml5" onClick="javascript:document.getElementById('codeimage').src='images/login/cord.jpg=' + Math.random();">看不清，换一张</a>
            <label></label>
          </dd>
        </dl>
         -->
        <dl>
            <label><font color="red">${error}</font></label>
        </dl>
        <dl>
          <dt>&nbsp;</dt>
          <dd>
              <input type="button" class="submit" value="重置密码" id="resetPassword" onclick="reset();"/>
          </dd>
        </dl>
      <dl class="mt10 mb10">
      </dl>
    </div>
    <div class="nc-login-bottom"></div>
  </div>
</div>
<script> 
$(document).ready(function(){
    $('input[name="Submit"]').click(function(){
        if($("#login_form").valid()){
        	$("#login_form").submit();
        } else{
        	document.getElementById('codeimage').src='/index.php?act=seccode&op=makecode&nchash=a3bacc57&t=' + Math.random();
        }
    });
	$("#login_form").validate({
        errorPlacement: function(error, element){
            var error_td = element.parent('dd');
            error_td.find('label').hide();
            error_td.append(error);
        },
		rules: {
			user_name: "required",
			password: "required"
			            ,captcha : {
                required : true,
                minlength: 4,
                remote   : {
                    url : '#/shop/index.php?act=seccode&op=check&nchash=a3bacc57',
                    type: 'get',
                    data:{
                        captcha : function(){
                            return $('#captcha').val();
                        }
                    }
                }
            }
					},
		messages: {
			user_name: "用户名不能为空",
			password: "密码不能为空"
			            ,captcha : {
                required : '验证码不能为空',
                minlength: '验证码不能为空',
				remote	 : '验证码错误'
            }
					}
	});
});
</script>
<div id="footer" class="wrapper">
</div>
<div id="footer" class="wrapper">
</div>
<footer class="footer" style="padding-top:30px;">
  <nav class="ft_nav"> <a href="/about" target="_blank">关于晒库网</a> <a href="/helpcenter/index.html" target="_blank">晒库网帮助</a> <a href="/feedbask" target="_blank">意见反馈</a> <a href="/contactus" target="_blank">联系方式</a> <a href="/job" target="_blank">招聘信息</a> <a href="#">手机触屏版</a> </nav>
  <div class="copyright">
    <p class="info"> <span class="mr10">京ICP证<a href="http://www.miitbeian.gov.cn" target="_blank">1234567</a>号</span> <span class="mr10">Copyright &copy; 2014 </span> <span>晒库网 <a target="_blank" href="http://www.shareku.com/">Shareku.com</a></span> </p>
  </div>
</footer>

</body>
</html>
