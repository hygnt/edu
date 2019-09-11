<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>晒库网 - 用户注册</title>
<meta name="keywords" content="晒库网" />
<meta name="description" content="晒库网" />
<meta name="author" content="">
<meta name="copyright" content="">
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
<!--<script src="/member/login/js/jquery.js"></script>
<script src="/member/login/js/common.js" charset="utf-8"></script>
<script src="/member/login/js/jquery.ui.js"></script>
<script src="/member/login/js/jquery.validation.min.js"></script>
<script src="/member/login/js/jquery.masonry.js"></script>
<script src="/member/login/js/dialog.js" id="dialog_js" charset="utf-8"></script>-->
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
</p>
<div class="header-wrap">
  <header class="public-head-layout wrapper">
   <h1 class="site-logo"><a href="/"><img src="../member/login/images/login/logo.png" class="pngFix"></a></h1>
   
  </header>
</div>
<!-- PublicHeadLayout End -->

<div class="nch-breadcrumb-layout">
  </div>
<style type="text/css">
.public-top-layout, .head-app, .head-search-bar, .head-user-menu, .public-nav-layout, .nch-breadcrumb-layout, #faq {
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
  <div class="nc-login">
    <div class="nc-login-title">
      <h3>用户注册</h3>
    </div>
    <div class="nc-login-content">
      <sf:form id="register_form" method="post" action="" modelAttribute="user">
       <dl>
          <dt>用户名</dt>
          <dd style="min-height:54px;">
          	<sf:input id="username" path="loginname" size="15" class="text tip" title="6-20位字符，可由中文、英文、数字及“_”、“-”组成"/><br/>
			<sf:errors path="loginname" cssClass="error" />
            <label></label>
          </dd>
        </dl>
        <dl>
          <dt>设置密码</dt>
          <dd style="min-height:54px;">
          	<sf:password id="password" path="password" size="30" showPassword="true" class="text tip" title="6-16位字符，可由英文、数字及标点符号组成"/><br/>
			<sf:errors path="password" cssClass="error" />
            <label></label>
          </dd>
        </dl>
        <dl>
          <dt>确认密码</dt>
          <dd style="min-height:54px;">
          	<input id="password_confirm" type="password" size="30" class="text tip" title="请再次输入您的密码"/><br/>
            <label></label>
          </dd>
        </dl>
        <dl>
          <dt>邮箱</dt>
          <dd style="min-height:54px;">
            <sf:input path="email" class="text tip" title="请输入常用的邮箱，将用来找回密码、接受订单通知等" /><br/>
            <sf:errors path="email" cssClass="error" />
            <label></label>
          </dd>
        </dl>
        <!-- 
        <dl>
          <dt>验证码</dt>
          <dd style="min-height:54px;">
            <input type="text" id="captcha" name="captcha" class="text w50 fl tip" maxlength="4" size="10" title="请输入验证码，不区分大小写" />
            <img border="0" class="fl ml5" id="codeimage" name="codeimage" src="images/login/cord.jpg"> <a href="javascript:void(0)" class="ml5" onClick="javascript:document.getElementById('codeimage').src='index.php?act=seccode&op=makecode&nchash=5f93ec3d&t=' + Math.random();">看不清，换一张</a>
            <label></label>
          </dd>
        </dl>
         -->
        <dl>
          <dt>&nbsp;</dt>
          <dd>
            <input type="submit" id="Submit" value="立即注册" class="submit" title="立即注册" />
            <!-- 
            <input name="agree" type="checkbox" class="vm ml10" id="clause" value="1" checked="checked" />
            <span for="clause" class="ml5">阅读并同意<a href="../index.php?act=document&op=index&code=agreement" target="_blank" class="agreement" title="阅读并同意">服务协议</a></span>
             -->
            <label></label>
          </dd>
        </dl>
      </sf:form>
      <div class="clear"></div>
    </div>
    <div class="nc-login-bottom"></div>
  </div>
  <div class="nc-login-left">
    <h3>注册之后您可以</h3>
    <ol>
      <li class="ico05"><i></i>购买课程支付订单</li>
      <li class="ico01"><i></i>申请开店销售课程</li>
      <li class="ico03"><i></i>空间好友推送分享</li>
      <li class="ico02"><i></i>收藏课程关注店铺</li>
      <li class="ico06"><i></i>课程咨询服务评价</li>
      <li class="ico04"><i></i>安全交易诚信无忧</li>
      <div class="clear"></div>
    </ol>
    <h3 class="mt20">如果您是本站用户</h3>
    <div class="nc-login-now mt10"><span class="ml20">我已经注册过帐号，立即<a href="../member/login.shtml" title="" class="register">登录</a></span><span>或是<a class="forget" href="#">找回密码？</a></span></div>
  </div>
</div>
<script>
//注册表单提示
$('.tip').poshytip({
	className: 'tip-yellowsimple',
	showOn: 'focus',
	alignTo: 'target',
	alignX: 'center',
	alignY: 'top',
	offsetX: 0,
	offsetY: 5,
	allowTipHover: false
});

//注册表单验证
$(function(){
    $('#Submit').click(function(){
        if($("#register_form").valid()){
        	ajaxpost('register_form', '', '', 'onerror');
        } else{
        	document.getElementById('codeimage').src='../index.php?act=seccode&op=makecode&nchash=5f93ec3d&t=' + Math.random();
        }
    });
		jQuery.validator.addMethod("lettersonly", function(value, element) {
			return this.optional(element) || /^[^:%,'\*\"\s\<\>\&]+$/i.test(value);
		}, "Letters only please"); 
		jQuery.validator.addMethod("lettersmin", function(value, element) {
			return this.optional(element) || ($.trim(value.replace(/[^\u0000-\u00ff]/g,"aa")).length>=3);
		}, "Letters min please"); 
		jQuery.validator.addMethod("lettersmax", function(value, element) {
			return this.optional(element) || ($.trim(value.replace(/[^\u0000-\u00ff]/g,"aa")).length<=15);
		}, "Letters max please");
    $("#register_form").validate({
        errorPlacement: function(error, element){
            var error_td = element.parent('dd');
            error_td.find('label').hide();
            error_td.append(error);
        },
        submitHandler:function(form){
            ajaxpost('register_form', '', '', 'onerror') 
        },
        rules : {
            user_name : {
                required : true,
                lettersmin : true,
                lettersmax : true,
                lettersonly : true,
                remote   : {
                    url :'index.php?act=login&op=check_member&column=ok',
                    type:'get',
                    data:{
                        user_name : function(){
                            return $('#user_name').val();
                        }
                    }
                }
            },
            password : {
                required : true,
                minlength: 6,
				maxlength: 20
            },
            password_confirm : {
                required : true,
                equalTo  : '#password'
            },
            email : {
                required : true,
                email    : true,
                remote   : {
                    url : 'index.php?act=login&op=check_email',
                    type: 'get',
                    data:{
                        email : function(){
                            return $('#email').val();
                        }
                    }
                }
            },
			            captcha : {
                required : true,
                minlength: 4,
                remote   : {
                    url : 'index.php?act=seccode&op=check&nchash=5f93ec3d',
                    type: 'get',
                    data:{
                        captcha : function(){
                            return $('#captcha').val();
                        }
                    }
                }
            },
			            agree : {
                required : true
            }
        },
        messages : {
            user_name : {
                required : '用户名不能为空',
                lettersmin : '用户名必须在3-15个字符之间',
                lettersmax : '用户名必须在3-15个字符之间',
				lettersonly: '用户名不能包含敏感字符',
				remote	 : '该用户名已经存在'
            },
            password  : {
                required : '密码不能为空',
                minlength: '密码长度应在6-20个字符之间',
				maxlength: '密码长度应在6-20个字符之间'
            },
            password_confirm : {
                required : '请再次输入您的密码',
                equalTo  : '两次输入的密码不一致'
            },
            email : {
                required : '电子邮箱不能为空',
                email    : '这不是一个有效的电子邮箱',
				remote	 : '该电子邮箱已经存在'
            },
			            captcha : {
                required : '请输入验证码',
                minlength: '验证码不正确',
				remote	 : '验证码不正确'
            },
			            agree : {
                required : '请阅读并同意该协议'
            }
        }
    });
});
</script><div id="footer" class="wrapper">
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
