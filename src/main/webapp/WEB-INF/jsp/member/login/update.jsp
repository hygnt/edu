<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>晒库网–个人中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="晒库网，中国最大的网络课程在线分享平台。您可在线传授技能，亦可在线学习你所需的一技之长。内容涉及英语学习、职场培训、生活技巧等任何技能。"/>
<meta name="keywords" content="晒库网,KK,英语,在线课堂,远程教育,在线学习,视频课程,公开课,电子商务,信息分享,职场培训,直播课堂,免费听课,名师网络课程,名师网课,B2B" />
<link rel="shortcut icon" type="/image/x-icon" href="/favicon.ico" />
<link rel="stylesheet" href="/member/css/sk_base.css" media="screen">
<link rel="stylesheet" href="/member/css/sk_set.css" media="screen">
<script src="/js/jquery-1.5.1.min.js"></script>
<script src="/js/jquery.common.js"></script>
<script src="/js/area.js"></script>
</head>
<body class="pageSet">
<div class="topR" id="topBar">
  <div class="wrapper"> <a href="#/" class="topR_logo"></a>
    <ul class="topR_dev">
      <li class="liClient"> <a href="javascript:;"><i class="icoR icoR_0"></i></a> </li>
      <li class="cart"><a href="#//?mod=cart&act=show"><i class="icoR icoR_4"></i>购物车(<i id="cartCount">0</i>)</a></li>
    </ul>
    <!-- =E topR_dev -->
    <div class="topR_search">
      <div class="inner"> <span class="tp_label"> <a href="javascript:;" id="searchType" stype="course">课程<i class="icoR icoR_5"></i></a> <span class="tp_label_sub" id="searchTypeList" style="display:none;"> <a href="javascript:;" id="courseType" stype="course">课程</a> <a href="javascript:;" id="schoolType" stype="school">学校</a> </span> </span> <span class="tp_txt">
        <!-- 鼠标点击的时候，添加tp_txt_focus -->
        <input type="text" prompt="请输入关键词、学校名等..." class="ipt" id="head_searchKeywords" style="width:173px;">
        <!--<span class="tips">请输入关键字</span>-->
        <a class="tp_btn" href="javascript:;" title="搜索" id="head_searchSubmit"><i class="icoR icoR_6"></i></a> </span> </div>
    </div>
    <!-- =E topR_search -->
    <ul class="topR_nav">
      <li class="myCourse"> <a href="#//?mod=student&act=course">我的课程</a>
        <div class="hasChild">
          <ul class="ulNav">
            <li><a href="#//?mod=student">学习首页</a></li>
            <li><a href="#//?mod=student&act=course&do=timelist">直播日历</a></li>
            <li><a href="#//?mod=student&act=course">我的课程</a></li>
            <li><a href="#//?mod=student&act=collect">收藏的课程</a></li>
            <li><a href="#//?mod=student&act=school">收藏的学校</a></li>
          </ul>
        </div>
      </li>
      <li class="seller curr"> <a href="#//?mod=seller">我是校长</a>
        <div class="hasChild">
          <ul class="ulNav">
            <li><a href="#//?mod=seller">我是校长</a></li>
            <li><a href="#//?mod=seller&act=course">课程管理</a></li>
            <li><a href="#//?mod=seller&act=order">销售订单</a></li>
            <li><a href="#//?mod=school&act=show&sid=2553134">我的学校</a></li>
            <li><a href="#//?mod=course&act=create&do=cate">发布课程</a></li>
          </ul>
        </div>
      </li>
      <li class="teacher"> <a href="#//?mod=teacher">我是老师</a>
        <div class="hasChild">
          <ul class="ulNav">
            <li><a href="#//?mod=teacher">我是老师</a></li>
            <li><a href="#//?mod=teacher&act=course&do=appointment">课程任命</a></li>
            <li><a href="#//?mod=teacher&act=school">签约学校</a></li>
          </ul>
        </div>
      </li>
    </ul>
    <ul class="topR_user">
      <li class="msg"> <a href="#//?mod=message&act=index&do=list"><i class="icoR icoR_7"></i></a>
        <div class="hasChild">
          <ul class="ulUser">
            <li><a href="#//?mod=message&act=index&do=list&type=1">课程通知<i class="num msg_course">0</i></a></li>
            <li><a href="#//?mod=message&act=index&do=list&type=2">交易通知<i class="num msg_trade">0</i></a></li>
            <li><a href="#//?mod=message&act=index&do=list&type=3">评论<i class="num msg_comment">0</i></a></li>
            <li><a href="#//?mod=message&act=index&do=list&type=4">系统消息<i class="num msg_system">0</i></a></li>
          </ul>
        </div>
      </li>
      <li class="photo"> <a href="#"><img src="/member/login/images/info/0-0.jpg" width="40" height="40" alt=""></a>
        <div class="hasChild">
          <ul class="ulUser">
            <li><a href="#//?mod=student&act=course">我的课程</a></li>
            <li><a href="/info/detail">设置中心</a></li>
            <li><a href="https://pay.shareku.com/st_f/PreAccount?uid=2553134&username=816211447%40autobaidu.com&email=816211447%40autobaidu.com&sig=33d51e41c21918bb178b8786bf67cfbb">支付中心</a></li>
            <li class="line"><a href="/remind">提醒设置</a></li>
            <li><a href="https://pay.shareku.com/st_f/PreRecharge?uid=2553134&username=816211447%40autobaidu.com&email=816211447%40autobaidu.com&sig=33d51e41c21918bb178b8786bf67cfbb">充值</a></li>
            <li class="line"><a href="/login/logout">退出</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
</div>
<!-- =E topR -->
<script>
	$(function(){
		$("li.liClient").mouseover(function(){
			$("li.liClient > a").css('background','#135dbc');
			$("#clientList").show();
		}).mouseleave(function(){
			$("li.liClient > a").css('background','');
			$("#clientList").hide();
		});
		$("#head_searchSubmit").mouseover(function(){
			$(this).parent().addClass('tp_txt_focus');
		}).mouseleave(function(){
			$(this).parent().removeClass('tp_txt_focus');
		});
		$(".topR_search").inputTips();
		$("#head_searchKeywords").prev("span.iptTips").css({"left":"1px", "top":"0px"});
 
		$("#searchType").click(function(){
			var obj = $(this);
			var pobj = $("#searchTypeList");
			var course = $("#courseType").clone();
			var school = $("#schoolType").clone();
			obj.showMenu('#searchTypeList');
			pobj.mouseout(function(){
				cobj.hideMenu('#searchTypeList');
			});
			
			pobj.find("a").click(function(){
				var cobj = $(this);
				obj.attr('stype', cobj.attr('stype')).html(cobj.text()+'<i class="icoR icoR_5"></i>');
				if(cobj.attr('stype') == 'school'){
					$("#courseType").remove();
					$("#schoolType").after(course);
				} else {
					$("#schoolType").remove();
					$("#courseType").after(school);
				}
				pobj.hide();
			});
		});
		
		$("#head_searchKeywords").keydown(function(event){
			if(event.keyCode == 13){
				$("#head_searchSubmit").trigger('click');
			}
		});
		var searchUrl = '#/';
		$("#head_searchSubmit").click(function(){
			var keywords = $.trim($("#head_searchKeywords").val()),
			searchType = $("#searchType").attr('stype');
			if(searchType == "course"){
				if(keywords == ''){
					searchUrl += "/course/index.html";	
				}else{
					searchUrl += "/course/_"+encodeURIComponent(keywords)+"_____.html";
				}
			}else{
				searchUrl += "/?mod=search&act=school&keyword="+encodeURIComponent(keywords);
			}
			document.location.href = searchUrl;
			return false;
		});
	});
	</script>
<div class="set_wrap clearfix">
  <aside class="set_sidebar">
    <div class="nav_hd">设置中心</div>
    <ul class="sidetab">
      <li class="curr"><a href="http://passport.shareku.com/info/detail" class="icoR_11">个人资料</a></li>
      <li ><a href="http://passport.shareku.com/avatar" class="icoR_12">设置头像</a></li>
      <li ><a href="http://passport.shareku.com/accountbind" class="icoR_13">帐户绑定</a></li>
      <li ><a href="http://passport.shareku.com/remind" class="icoR_14">提醒设置</a></li>
      <li ><a href="http://passport.shareku.com/security/password" class="icoR_15">帐号安全</a></li>
    </ul>
  </aside>
  <div class="set_content personal_info">
    <div class="pi_bd clearfix">
    	<sf:form class="formH grzlForm clearfix" method="POST" action="/setting/update.shtml" modelAttribute="user">
        <div class="formItem mb10">
        	<label class="form_label">登陆名：</label>
			<div class="form_field">
				<sf:input class="form_text" path="loginname"  maxlength="20" />
			</div>
        </div>
        <div class="formItem mb20">
        	<label class="form_label">昵　称：</label>
          	<div class="form_field"> 
          		<sf:input class="form_text" path="username"  maxlength="20"/>	
          	</div>
        </div>
        <div class="formItem mb20">
        	<label class="form_label">性　别：</label>
          	<div class="form_field"> 		
          		<sf:radiobutton path="gender" value="1"/>男　
				<sf:radiobutton path="gender" value="0"/>女
			</div>
        </div>
        <div class="formItem mb20">
          <label class="form_label">生　日：</label>
          <div class="form_field">
            <sf:input path="birthday" maxlength="20" class="form_text"/>
          </div>
        </div>
        <div class="formItem mb20">
          <label class="form_label">　QQ：</label>
          <div class="form_field">
            <sf:input path="qqcode" maxlength="20" class="form_text"/>
          </div>
        </div>
        <div class="formItem mb20">
          <label class="form_label">行　业：</label>
          <div class="form_field"> <span class="mr10">
            <sf:select path="occupation" id="trade">
              <option value="0" >--请选择--</option>
              <sf:option value="1" >销售业务</sf:option>
              <sf:option value="2" >销售管理</sf:option>
              <sf:option value="3" >销售支持</sf:option>
              <sf:option value="4" >市场/市场拓展/公关</sf:option>
              <sf:option value="5" >商务/采购/贸易</sf:option>
              <sf:option value="6" >计算机软、硬件/互联网/IT</sf:option>
              <sf:option value="7" >建筑/房地产/装修/物业</sf:option>
              <sf:option value="8" >金融/银行/证券/投资</sf:option>
              <sf:option value="9" >汽车</sf:option>
              <sf:option value="10" >生产/加工/制造</sf:option>
              <sf:option value="11" >交通/仓储/物流</sf:option>
              <sf:option value="12" >零售业</sf:option>
              <sf:option value="13" >医疗/护理/保健/美容</sf:option>
              <sf:option value="14" >生物/制药/医疗器械</sf:option>
              <sf:option value="15" >人力资源</sf:option>
              <sf:option value="16" >客户服务/技术支持</sf:option>
              <sf:option value="17" >行政/后勤</sf:option>
              <sf:option value="18" >通信技术</sf:option>
              <sf:option value="19" >教育/培训</sf:option>
              <sf:option value="20" >化工</sf:option>
              <sf:option value="21" >广告</sf:option>
              <sf:option value="22" >服装/纺织/皮革</sf:option>
              <sf:option value="23" >财会/审计/统计</sf:option>
              <sf:option value="24" >编辑/文案/传媒/影视/新闻</sf:option>
              <sf:option value="25" >电子/半导体/仪表仪器</sf:option>
              <sf:option value="26" >法律</sf:option>
              <sf:option value="27" >翻译</sf:option>
              <sf:option value="28" >高级管理</sf:option>
              <sf:option value="29" >工程机械</sf:option>
              <sf:option value="30" >环境科学/环保</sf:option>
              <sf:option value="31" >咨询/顾问</sf:option>
              <sf:option value="32" >质控/安检</sf:option>
              <sf:option value="33" >酒店/餐饮/旅游/娱乐</sf:option>
              <sf:option value="34" >美术/设计/创意</sf:option>
              <sf:option value="35" >能源/矿产/地质勘查</sf:option>
              <sf:option value="36" >学术/科研</sf:option>
              <sf:option value="37" >保险</sf:option>
              <sf:option value="38" >公务员</sf:option>
              <sf:option value="39" >技工</sf:option>
              <sf:option value="40" >在校学生</sf:option>
              <sf:option value="41" >农/林/牧/渔业</sf:option>
              <sf:option value="42" >普通劳动力/家政服务</sf:option>
              <sf:option value="43" >兼职/临时/培训生/储备干部</sf:option>
              <sf:option value="44" >其他</sf:option>
            </sf:select>
            </span>
          </div>
        </div>
        <div class="formItem mb20">
          <label for="" class="form_label">电　话：</label>
          <div class="form_field">
            <sf:input path="phone" maxlength="20" class="form_text" />
          </div>
        </div>
        <div class="formItem mb20">
          <label for="" class="form_label">手　机：</label>
          <div class="form_field">
            <sf:input path="mobile" maxlength="20" class="form_text" />
            <span style="margin-left:10px;">接收免费公开课通知</span> 
          </div>
        </div>
        <div class="formItem mb20">
          <label for="" class="form_label">所在城市：</label>
          <div class="form_field"> 
            <!-- =E 省份 -->
          	<span class="mr10">
            	<select id="s_province" name="province_tmp"> </select>        
            </span>
            <!-- =E 市 -->
            <span class="mr25">
            	<select id="s_city" name="city_tmp"></select>
            </span>
            <!-- 县 -->
            <span class="mr25">
		    	<select id="s_county" name="county_tmp"></select>
		    </span>
            <script type="text/javascript">_init_area();</script>
            <div id="show"></div>
           </div>
        </div>
        <div class="formItem mb20">
          <label for="" class="form_label">详细地址：</label>
          <div class="form_field">
            <sf:input path="address" maxlength="50" class="form_text" />
          </div>
        </div>
        <div class="formItem mb20">
          <label for="" class="form_label">简　介：</label>
          <div class="form_field">
            <sf:textarea path="user_desc" rows="5" cols="40" id="desc" />
          </div>
        </div>
        <div class="formItem mb20">
          <label for="" class="form_label">&nbsp;</label>
          <div class="form_field">
            <p class="mb10"> 
				<input id="commit" type="submit" value="保存" />	
				<input id="cancel" type="button" value="取消" />	
			</p>
          </div>
        </div>
      </sf:form>
    </div>
  </div>
  <script type="text/javascript"> 
var $$ = function (id) {  
    return "string" == typeof id ? document.getElementById(id) : id;  
};
 
function addEventHandler(oTarget, sEventType, fnHandler) {  
    if (oTarget.addEventListener) {  
        oTarget.addEventListener(sEventType, fnHandler, false);  
    } else if (oTarget.attachEvent) {  
        oTarget.attachEvent("on" + sEventType, fnHandler);  
    } else {  
        oTarget["on" + sEventType] = fnHandler;  
    }  
};
 
var Class = {  
  	create: function() {  
    	return function() {  
    		this.initialize.apply(this, arguments);  
    	}  
  	}  
};
 
var Extend = function(destination, source) {  
    for (var property in source) {  
        destination[property] = source[property];  
    }  
    return destination;  
};
 
var DateSelector = Class.create();
DateSelector.prototype = {  
	initialize: function(oYear, oMonth, oDay, options) {  
    	this.SelYear = $$(oYear);//年选择对象  
    	this.SelMonth = $$(oMonth);//月选择对象  
    	this.SelDay = $$(oDay);//日选择对象  
    	this.SetOptions(options); 
    	var dt = new Date(), iMonth = parseInt(this.options.Month), iDay = parseInt(this.options.Day), iMinYear = parseInt(this.options.MinYear), iMaxYear = parseInt(this.options.MaxYear);  
    	this.Year = parseInt(this.options.Year) || dt.getFullYear();  
    	this.Month = 1 <= iMonth && iMonth <= 12 ? iMonth : dt.getMonth() + 1;  
    	this.Day = iDay > 0 ? iDay : dt.getDate();  
    	this.MinYear = iMinYear && iMinYear < this.Year ? iMinYear : this.Year;  
    	this.MaxYear = iMaxYear && iMaxYear > this.Year ? iMaxYear : this.Year;  
    	this.onChange = this.options.onChange;  
      
    	//年设置  
    	this.SetSelect(this.SelYear, this.MinYear, this.MaxYear - this.MinYear + 1, this.Year - this.MinYear);  
    	//月设置  
    	this.SetSelect(this.SelMonth, 1, 12, this.Month - 1);  
    	//日设置  
    	this.SetDay();  
      
    	var oThis = this;  
    	//日期改变事件  
    	addEventHandler(this.SelYear, "change", function(){  
        	oThis.Year = oThis.SelYear.value; oThis.SetDay(); oThis.onChange();  
    	});  
    	addEventHandler(this.SelMonth, "change", function(){  
        	oThis.Month = oThis.SelMonth.value; oThis.SetDay(); oThis.onChange();  
    	});  
    	addEventHandler(this.SelDay, "change", function(){ oThis.Day = oThis.SelDay.value; oThis.onChange(); });  
  	},  
  	//设置默认属性  
  	SetOptions: function(options) {  
    	this.options = {//默认值  
        	Year:       0,//年  
        	Month:      0,//月  
        	Day:        0,//日  
        	MinYear:    0,//最小年份  
        	MaxYear:    0,//最大年份  
        	onChange:   function(){}//日期改变时执行  
    	};  
    	Extend(this.options, options || {});  
  	},  
  	//日设置  
  	SetDay: function() {  
    	//取得月份天数  
    	var daysInMonth = new Date(this.Year, this.Month, 0).getDate();  
    	if (this.Day > daysInMonth) { this.Day = daysInMonth; };  
    	this.SetSelect(this.SelDay, 1, daysInMonth, this.Day - 1);  
  	},  
  	//select设置  
  	SetSelect: function(oSelect, iStart, iLength, iIndex) {  
    	//添加option  
    	oSelect.options.length = iLength;  
    	for (var i = 0; i < iLength; i++) { 
        	oSelect.options[i].text = oSelect.options[i].value = iStart + i; 
        }  
    	//设置选中项  
    	oSelect.selectedIndex = iIndex;  
  	}  
};
 
 
//初始化日期
var ds = new DateSelector("idYear", "idMonth", "idDay", {  
	MinYear: 1900,
    MaxYear: new Date().getFullYear(),
    Year: '1980',
   	Month: '1',
   	Day: '1'
});
 
function setLocationCity(code) {
	var obj = KK.province[code].Nodes;
	var str = '';
	$.each(obj, function(i, item){
		str += '<option value="'+item.Code+'">'+item.CodeName+'</option>';
	});
	$("#idCity").html(str);
}
 
function initLocation(code) {
	$.each(KK.province, function(pi, pv){
		$.each(pv.Nodes, function(ci, cv){
			if (ci == code) {
				setLocationCity(pv.Code);
				$("#idProvince").find("option[value='"+pi+"']").attr("selected", true);
				$("#idCity").find("option[value='"+ci+"']").attr("selected", true);
			}
		});
	});
}
 
//初始化所在地
$.ajax({
	url:"js/location.json",
	type:"get",
	dataType:"json",
	async:false,
	success:function(ret){
		KK.province = ret;
		var options = '';
		$.each(ret, function(i, item){
			options += '<option value="'+item.Code+'">'+item.CodeName+'</option>';
		});
		jQuery("#idProvince").html(options).change(function(){
			setLocationCity($(this).val());
		});
	}
});
 
initLocation('10101010000');
 
$("#saveSubmit").click(function(){
	var nickname = $("#nickname").val();
	var sign = $("#sign").val();
	var realname = $("#realname").val();
	var sex = $(":radio[name='sex'][checked=true]").val();
	var year = $("#idYear").val();
	var month = $("#idMonth").val();
	var day = $("#idDay").val();
	var location = $("#idCity").val();
	var params = "nickname="+encodeURIComponent(nickname)+"&sign="+encodeURIComponent(sign)+"&realname="+encodeURIComponent(realname)+"&sex="+sex+"&year="+year+"&month="+month+"&day="+day+"&location="+location;
 
	$.ajax({
		url:"/info/saveBasic",
		type:"post",
		data:params,
		dataType:"json",
		success:function(ret) {
			if (ret.code == 0) {
				$.ckAlert('ok', "资料修改成功", "http://passport.shareku.com/info/basic");
			} else {
				$.ckAlert('error', ret.result, "http://passport.shareku.com/info/basic");
			}
		}
	});
});
</script>
  <script type="text/javascript"> 
$(function(){	
	$("#saveBtn").click(function(){
		var trade = $("#trade").val();
		var degree = $("#degree").val();
		var school = $("#school").val();
		var mobile = $("#mobile").val();
		var email = $("#email").val();
		var desc = $("#desc").val();
 
		var nickname = $("#nickname").val();
		var sign = $("#sign").val();
		var realname = $("#realname").val();
		var sex = $(":radio[name='sex'][checked=true]").val();
		var year = $("#idYear").val();
		var month = $("#idMonth").val();
		var day = $("#idDay").val();
		var location = $("#idCity").val();
 
		if(mobile && !/^[(86)|0]?(13\d{9})|(15\d{9})|(18\d{9})$/g.test(mobile)) {
			alert('请填写正确的手机号码！');
			return false;
		}
		
		var params = "nickname="+encodeURIComponent(nickname)+"&sign="+encodeURIComponent(sign)+"&realname="+encodeURIComponent(realname)+"&sex="+sex+"&year="+year+"&month="+month+"&day="+day+"&location="+location;
		
		params += "&trade="+trade+"&degree="+degree+"&school="+encodeURIComponent(school)+"&mobile="+mobile+"&email="+encodeURIComponent(email)+"&desc="+encodeURIComponent(desc);
		var tipboxy = false;
		$.ajax({
			url:"/info/saveDetail",
			type:"post",
			data:params,
			dataType:"json",
			async : false,
			beforeSend:function(){
				tipboxy = $.ckTipsBoxy({type:"loading", message : "数据提交中...", mask : true});
			},
			success:function(ret) {
				tipboxy.cancel();
				if (ret.code == 0) {
					$.ckTipsBoxy({message : "资料修改成功", mask : true});
				} else {
					$.ckAlert({message : ret.result});
				}
			}
		});
	});
});
</script>
</div>
<footer class="footer">
  <nav class="ft_nav"> <a href="#/about" target="_blank">关于晒库网</a> <a href="#/helpcenter/index.html" target="_blank">晒库网帮助</a> <a href="#/feedback" target="_blank">意见反馈</a> <a href="#/contactus" target="_blank">联系方式</a> <a href="#/job" target="_blank">招聘信息</a> <a href="http://m.shareku.com">手机触屏版</a> </nav>
  <div class="copyright">
    <p class="info"> <span class="mr10">京ICP证<a target="_blank" href="http://www.miitbeian.gov.cn">211115</a>号</span> <span class="mr10">Copyright &copy; 2014 </span> <span>晒库网 <a href="#/" target="_blank">shareku.com</a></span> </p>
  </div>
</footer>
</body>
</html>
