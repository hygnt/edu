/**
 * js函数库
 */

/**
 * 获取字符长度，中文按2个字符计算
 */
function getStrActualLen(sChars) {
    sChars = $.trim(sChars);
    var len = 0;
    for(i=0; i<sChars.length; i++){
        iCode = sChars.charCodeAt(i);
        if((iCode >= 0 && iCode <= 255)||(iCode >= 0xff61 && iCode <= 0xff9f)){
            len += 1;
        }else{
            len += 2;
        }
    }
    return len;
}
/**
 * 手机号码检测
 * @param mobile
 * @returns {Boolean}
 */
function CheskMobile(mobile){
	if(/^1[3|4|5|8][0-9]\d{8}$/.test(mobile)){
		return true;
	}else{
		return false;
	}
}

/**
 * 中文算两个字符
 * @param $Str
 */
function CutString(Str, Length){
	Str = $.trim(Str);
	var Count = 0,
		RetStr = '',
		StrArr = Str.split("");
	for(var i=0; i<Str.length; i++){
		 iCode = Str.charCodeAt(i);
		 if((iCode >= 0 && iCode <= 255)||(iCode >= 0xff61 && iCode <= 0xff9f)){
			 Count += 1;
		 }else{
			 Count += 2;
		 }
		 if(Count <= Length){
			 RetStr += StrArr[i];
		 }
	}
	return RetStr;
}

/**
 * 文本框输入提示
 * elementID input元素ID
 * msg 提示消息
 * type 提示类型：notice-提示；error-错误；sucess-成功
 */
function showMessageTip(elementID, msg, type) {
	var obj = $("#form_tips_"+elementID);
	if (type == 'error') {
		$("input[name='"+elementID+"']").addClass('form_text_error');
		obj.attr("class", "fl form_error").text(msg);
	} else if (type == 'notice') {
		$("input[name='"+elementID+"']").removeClass('form_text_error');
		obj.attr("class", "fl form_notice").text(msg);
	} else if (type == 'success') {
		$("input[name='"+elementID+"']").removeClass('form_text_error');
		obj.attr("class", "fl form_success").text('');
	}
}

function registerHotKey(e){
	var evt = e ? e : (window.event ? window.event : null);
	var element = evt.srcElement || evt.target;
	if (element.nodeName != 'INPUT' && element.nodeName != 'OBJECT' && element.nodeName != 'TEXTAREA') {
		//var myKeyCode = $.browser.msie ? evt.which : evt.keyCode;
		var myKeyCode = evt.keyCode;
		if (myKeyCode >= 65 && myKeyCode <= 90 && myKeyCode != 67 && evt.shiftKey == false && evt.altKey == false && evt.ctrlKey == false) {
			window.scrollTo(0, 0);
			$("#head_searchKeywords").focus();
		}
	}
}

function getrsp() {
	var reg = new RegExp("http:\/\/www.chuanke.com\/(.*)-(.*).html");
	var r = reg.exec(window.location);
	var info = "";
	if(r != null && r != ''){
		info = '&sid='+r[1]+'&courseid='+r[2];
	}
	$.get(
		"/?mod=access&act=getrsp"+info,
		"&rand="+Math.random(),
		function(data){
			if (data.code == 0){
				//$("#rspnum").html(data.data.RspNum);
			}
		},
		"json"
		);
}

function buyCourseTip(tip) {
	var w = (parseInt($(window).width()) - 1000)/2;
	var str = '<div class="sk_tips_close" style="z-index:99;top:16px;position:absolute;right:'+w+'px;" id="buy_course_tip">';
	str += '<div class="ti_tri" style="left:70px;"></div>';
	str += '<div class="ti_con clearfix">';
	str += '<p class="fl">';
	str += '购买成功，你可以到<a href="'+KK._kkurl+'/?mod=student&act=course" style="color:#0065CB;">我的课程</a>中查看';
	str += '</p>';
	str += '<a href="javascript:;" onclick="$(\'#buy_course_tip\').remove();" class="ti_close"></a>';
	if(tip == 'nomobile'){		
		str += '<p class="cb pt10">';
		str += '<img class="vm fl mr5" src="http://res.skimg.com/sites/www/v2/images/public/ico_warning_14x14.png" alt="">';
		str += '<span class="fl lh_16">课程免费短信提醒功能 <a id="message_remind" href="javascript:;">马上开通</a></span>';
		str += '</p>';
	}
	str += '</div></div>';
	
	$("body").append(str);
}

function todayLiveCourseTip(num) {
	var w = (parseInt($(window).width()) - 1000)/2;
	var str = '<div class="sk_tips_close" style="z-index:99;top:16px;position:absolute;right:'+w+'px;" id="live_course_tip">';
	str += '<div class="ti_tri" style="left:70px;"></div>';
	str += '<div class="ti_con clearfix">';
	str += '<p class="fl">';
	str += '你今日有<i style="color:red;font-weight:bold;">'+num+'</i>节直播课，请到<a href="'+KK._kkurl+'/?mod=student&act=course&do=timelist" style="color:#0065CB;">直播日历</a>中查看';
	str += '</p>';
	str += '<a href="javascript:;" onclick="$(\'#live_course_tip\').remove();" class="ti_close"></a>';
	str += '</div></div>';
	$("body").append(str);
}

/**
 * 时间选择控件
 * @param obj
 * @returns
 * @author herenet
 */
var timepisker = function(obj){
	var setValue = $(obj).val(),
		timeDivSize = $("[id^='timeDiv_']").length+1;
	if(setValue != '' && /^\d{2}:\d{2}$/.test(setValue)){
		var currTime = setValue.split(":"),
			currHours = currTime[0],
			currMinute = currTime[1];
	}else{
		var currTime = new Date(),
			currHours= currTime.getHours().toString().replace(/^\d*$/, function(v){if(v.length == 1){return '0'+v;}else{return v;}}),
			currMinute = '00';
	};
	var offset = $(obj).offset(),
		left = parseInt(offset.left),
		top = parseInt(offset.top)+parseInt($(obj).height()),
		html = [
			'<div class="timeDiv" id="timeDiv_'+timeDivSize+'" style="top:'+parseInt(top+10)+'px;left:'+left+'px">',
			'<div class="oLeft">',
			'<div class="tit">',
			'<p><span class="f20 c_555 hour">'+currHours+'</span>',
			'<span class="f14 c_999">点</span></p>',
			'</div><div class="con">',
			'<ul class="oUl">',
			'<li><a href="javascript:;">00</a>',
			'<a href="javascript:;">01</a>',
			'<a href="javascript:;">02</a>',
			'<a href="javascript:;">03</a>',
			'<a href="javascript:;">04</a>',
			'<a href="javascript:;">05</a>',
			'<a href="javascript:;">06</a>',
			'<a href="javascript:;">07</a>',
			'</li><li><a href="javascript:;">08</a>',
			'<a href="javascript:;">09</a>',
			'<a href="javascript:;">10</a>',
			'<a href="javascript:;">11</a>',
			'<a href="javascript:;">12</a>',
			'<a href="javascript:;">13</a>',
			'<a href="javascript:;">14</a>',
			'<a href="javascript:;">15</a>',
			'</li><li><a href="javascript:;">16</a>',
			'<a href="javascript:;">17</a>',
			'<a href="javascript:;">18</a>',
			'<a href="javascript:;">19</a>',
			'<a href="javascript:;">20</a>',
			'<a href="javascript:;">21</a>',
			'<a href="javascript:;">22</a>',
			'<a href="javascript:;">23</a>',
			'</li></ul></div></div>',
			'<div class="oRight">',
			'<div class="tit">',
			'<p><span class="f20 c_555 minute">'+currMinute+'</span>',
			'<span class="f14 c_999">分</span></p>',
			'</div><div class="con">',
			'<ul class="oUl"><li>',
			'<a href="javascript:;">00</a>',
			'<a href="javascript:;">10</a>',
			'<a href="javascript:;">15</a>',
			'<a href="javascript:;">20</a>',
			'<a href="javascript:;">30</a>',
			'<a href="javascript:;">40</a>',
			'<a href="javascript:;">45</a>',
			'<a href="javascript:;">50</a>',
			'</li></ul></div></div>',
			'<div class="oBottom">',
			'<a href="javascript:;" class="timeSubmit">确 定</a>',
			'</div></div>',
		].join('');
	if($("[id^='timeDiv_']").length > 0){
		return $.each($("[id^='timeDiv_']"), function(i, item){
			tempOffset = $(item).offset();
			if(tempOffset.left == left && tempOffset.top == top+10){
				//$(item).remove();
				return false;
			}
		});
	}
	$("body").append(html);
	var timeBox = $('#timeDiv_'+timeDivSize),
		isHover = false;

	$.each(timeBox.find("div.oLeft a"), function(n, item){
		if($(item).text() == currHours){
			$(this).addClass("curr");
		}
	});

	$.each(timeBox.find("div.oRight a"), function(n, item){
		if($(item).text() == currMinute){
			$(this).addClass("curr");
		}
	});

	$(obj).blur(function(){
		if(isHover == false){
			timeBox.remove();
		}
		return false;
	});

	timeBox.hover(
		function(){
			isHover = true;
			$(this).find("div.oLeft a").click(function(){
				timeBox.find("div.oLeft a").removeClass("curr");
				$(this).addClass("curr");
				timeBox.find("span.hour").text($(this).text());
				return false;
			});
			$(this).find("div.oRight a").click(function(){
				timeBox.find("div.oRight a").removeClass("curr");
				$(this).addClass("curr");
				timeBox.find("span.minute").text($(this).text());
				return false;
			});
			$(this).find("a.timeSubmit").click(function(){
				isHover = false;
				var hour = timeBox.find(".hour").text(),
					minute = timeBox.find(".minute").text();
				$(obj).val(hour+":"+minute);
				timeBox.remove();
				return false;
			});
			return false;
		},
		function(){
			$(obj).focus();
			isHover = false;
			return false;
		}
	);
}

function str_repeat(i, m) {
    for (var o = []; m > 0; o[--m] = i);
    return o.join('');
}

function sprintf() {
    var i = 0, a, f = arguments[i++], o = [], m, p, c, x, s = '';
    while (f) {
        if (m = /^[^\x25]+/.exec(f)) {
            o.push(m[0]);
        }
        else if (m = /^\x25{2}/.exec(f)) {
            o.push('%');
        }
        else if (m = /^\x25(?:(\d+)\$)?(\+)?(0|'[^$])?(-)?(\d+)?(?:\.(\d+))?([b-fosuxX])/.exec(f)) {
            if (((a = arguments[m[1] || i++]) == null) || (a == undefined)) {
                throw('Too few arguments.');
            }
            if (/[^s]/.test(m[7]) && (typeof(a) != 'number')) {
                throw('Expecting number but found ' + typeof(a));
            }
            switch (m[7]) {
                case 'b': a = a.toString(2); break;
                case 'c': a = String.fromCharCode(a); break;
                case 'd': a = parseInt(a); break;
                case 'e': a = m[6] ? a.toExponential(m[6]) : a.toExponential(); break;
                case 'f': a = m[6] ? parseFloat(a).toFixed(m[6]) : parseFloat(a); break;
                case 'o': a = a.toString(8); break;
                case 's': a = ((a = String(a)) && m[6] ? a.substring(0, m[6]) : a); break;
                case 'u': a = Math.abs(a); break;
                case 'x': a = a.toString(16); break;
                case 'X': a = a.toString(16).toUpperCase(); break;
            }
            a = (/[def]/.test(m[7]) && m[2] && a >= 0 ? '+'+ a : a);
            c = m[3] ? m[3] == '0' ? '0' : m[3].charAt(1) : ' ';
            x = m[5] - String(a).length - s.length;
            p = m[5] ? str_repeat(c, x) : '';
            o.push(s + (m[4] ? a + p : p + a));
        }
        else {
            throw('Huh ?!');
        }
        f = f.substring(m[0].length);
    }
    return o.join('');
}

//头部html
var header = function () {
	/*<div class="topR" id="topBar">
	  <div class="wrapper"> <a href="#" class="topR_logo"></a>
	    <ul class="topR_dev">
	      <li class="liClient"><a href="javascript:;"><i class="icoR icoR_01"></i>
	        <!--下载晒库-->
	        </a>
	        <!-- <div id="clientList" class="hasChild" style="display:none">
	          <div class="subC"> <a href="#" target="_blank">晒库网KK</a> <a href=#" target="_blank">晒库网iPhone版</a> <a href="#" target="_blank">晒库网iPad版</a> <a href="#" target="_blank">晒库网Android版</a> </div>
	        </div>-->
	      </li>
	      <li class="cart"><a href="#/?mod=cart&amp;act=show"><i class="icoR icoR_4"></i>购物车(<i id="cartCount">0</i>)</a></li>
	    </ul>
	    <!-- =E topR_dev -->
	    <div class="topR_search">
	      <div class="inner"> <span class="tp_label"> <a href="javascript:;" id="searchType" stype="course">课程<i class="icoR icoR_5"></i></a> <span class="tp_label_sub" id="searchTypeList" style="display:none;"> <a href="javascript:;" id="courseType" stype="course">课程</a> <a href="javascript:;" id="schoolType" stype="school">学校</a> </span> </span> <span class="tp_txt">
	        <!-- 鼠标点击的时候，添加tp_txt_focus -->
	        <span class="iptTips" id="sk_prompt_1" style="left: 1px; top: 0px;">请输入关键词、学校名等...</span><input type="text" prompt="请输入关键词、学校名等..." class="ipt" id="head_searchKeywords" style="width:350px;">
	        <!--<span class="tips">请输入关键字</span>-->
	        <a class="tp_btn" href="javascript:;" title="搜索" id="head_searchSubmit"><i class="icoR icoR_6"></i></a> </span> </div>
	    </div>
	    <!-- =E topR_search -->
	    <div style="position: fixed;_position:absolute;top: 66%; z-index: 599; right: 0px;" class="rTop">
	      <ul>
	        <li class="r_weixin">
	          <div class="codeImgWrap" id="weixinCodeImg" style="background-color:#FFFFFF;display: none;"> <a href="javascript:;" id="weixinClose" class="close"></a>
	            <p class="tc mt20">轻松一扫，精彩不停</p>
	            <div class="codeImg"></div>
	          </div>
	          <a href="javascript:;" style="display: block; left: 0px;" id="btnWeixin" class="wx item">微信关注</a> </li>
	        <li class="r_msg"> <a href="javascript:;" id="btnFeedbask" class="item" style="left: 0px;">意见反馈</a> </li>
	        <li class="r_gotop" style="display: none;"> <a href="#" id="btnGotop" class="item">返回顶部</a> </li>
	      </ul>
	    </div>
	    <div class="topR_login"> <a href="member/login.shtml" target="_blank">登录</a> <a href="member/register.shtml" target="_blank">注册</a> </div>
	    <!-- =E topR_login -->
	  </div>
	</div>
	<div class="mainNav mb20 bc">
	  <div class="wrapper">
	    <div class="inner">
	      <div class="mn_sort">
	        <div class="mn_mt"><a href="/course/index.html">所有课程分类</a></div>
	        <div id="ccList" class="mn_mc" style="display:none;"><div class="item"><div class="i_hd"><span class="i_hd_tit"><a href="list_product.html?tid=010000&amp;cName=IT%2F%E4%BA%92%E8%81%94%E7%BD%91%E3%80%81%E8%AE%A1%E7%AE%97%E6%9C%BA" target="_blank">IT/互联网、计算机</a></span><s class="i_hd_arr"></s></div><div class="i_bd"><div class="categ_m"><h3><a href="javascript:void(0);" target="_blank">IT/互联网、计算机</a></h3><dl><dt><a class="c_1ab177" href="list_product.html?tid=010100&amp;cName=%E7%BC%96%E7%A8%8B%E8%AF%AD%E8%A8%80" target="_blank">编程语言</a></dt><dd><a href="list_product.html?tid=010101&amp;cName=C%2FC%2B%2B" target="_blank">C/C++</a><a href="list_product.html?tid=010102&amp;cName=VC%2FMFC" target="_blank">VC/MFC</a><a href="list_product.html?tid=010103&amp;cName=JAVA" target="_blank">JAVA</a><a href="list_product.html?tid=010104&amp;cName=Python" target="_blank">Python</a><a href="list_product.html?tid=010105&amp;cName=PHP" target="_blank">PHP</a><a href="list_product.html?tid=010106&amp;cName=%E8%84%9A%E6%9C%AC%E8%AF%AD%E8%A8%80" target="_blank">脚本语言</a><a href="list_product.html?tid=010107&amp;cName=Objective-C" target="_blank">Objective-C</a></dd></dl><dl><dt><a class="c_1ab177" href="list_product.html?tid=010200&amp;cName=%E5%B8%B8%E7%94%A8%E8%BD%AF%E4%BB%B6" target="_blank">常用软件</a></dt><dd><a href="list_product.html?tid=010201&amp;cName=Photoshop" target="_blank">Photoshop</a><a href="list_product.html?tid=010202&amp;cName=3Dmax" target="_blank">3Dmax</a><a href="list_product.html?tid=010203&amp;cName=Illustrator" target="_blank">Illustrator</a><a href="list_product.html?tid=010204&amp;cName=Flash" target="_blank">Flash</a><a href="list_product.html?tid=010205&amp;cName=Dreamwaver" target="_blank">Dreamwaver</a><a href="list_product.html?tid=010206&amp;cName=Maya" target="_blank">Maya</a><a href="list_product.html?tid=010207&amp;cName=Axure" target="_blank">Axure</a></dd></dl><dl><dt><a class="c_1ab177" href="list_product.html?tid=010300&amp;cName=%E8%AE%BE%E8%AE%A1%E5%88%B6%E4%BD%9C" target="_blank">设计制作</a></dt><dd><a href="list_product.html?tid=010301&amp;cName=%E5%B9%B3%E9%9D%A2%E8%AE%BE%E8%AE%A1" target="_blank">平面设计</a><a href="list_product.html?tid=010302&amp;cName=%E7%BD%91%E7%AB%99%E5%88%B6%E4%BD%9C" target="_blank">网站制作</a><a href="list_product.html?tid=010303&amp;cName=%E9%A1%B5%E9%9D%A2%E8%AE%BE%E8%AE%A1" target="_blank">页面设计</a><a href="list_product.html?tid=010304&amp;cName=%E6%B8%B8%E6%88%8F%E8%AE%BE%E8%AE%A1" target="_blank">游戏设计</a><a href="list_product.html?tid=010305&amp;cName=%E4%B8%89%E7%BB%B4%E8%AE%BE%E8%AE%A1" target="_blank">三维设计</a><a href="list_product.html?tid=010306&amp;cName=CG%E5%8A%A8%E7%94%BB" target="_blank">CG动画</a></dd></dl><dl><dt><a class="c_1ab177" href="list_product.html?tid=010400&amp;cName=%E6%95%B0%20%E6%8D%AE%20%E5%BA%93" target="_blank">数 据 库</a></dt><dd><a href="list_product.html?tid=010401&amp;cName=Oracle" target="_blank">Oracle</a><a href="list_product.html?tid=010402&amp;cName=SQL%20Server" target="_blank">SQL Server</a><a href="list_product.html?tid=010403&amp;cName=MySQL" target="_blank">MySQL</a></dd></dl><dl><dt><a class="c_1ab177" href="list_product.html?tid=010500&amp;cName=%E7%B3%BB%E7%BB%9F%E8%BF%90%E7%BB%B4" target="_blank">系统运维</a></dt><dd><a href="list_product.html?tid=010501&amp;cName=Linux" target="_blank">Linux</a><a href="list_product.html?tid=010502&amp;cName=Vmware" target="_blank">Vmware</a><a href="list_product.html?tid=010503&amp;cName=Windows" target="_blank">Windows</a><a href="list_product.html?tid=010504&amp;cName=%E7%BD%91%E7%BB%9C%E7%AE%A1%E7%90%86" target="_blank">网络管理</a><a href="list_product.html?tid=010505&amp;cName=Exchange" target="_blank">Exchange</a></dd></dl><dl><dt><a class="c_1ab177" href="list_product.html?tid=010600&amp;cName=%E7%A7%BB%E5%8A%A8%E4%BA%92%E8%81%94%E7%BD%91" target="_blank">移动互联网</a></dt><dd><a href="list_product.html?tid=010601&amp;cName=Android" target="_blank">Android</a><a href="list_product.html?tid=010602&amp;cName=IOS" target="_blank">IOS</a><a href="list_product.html?tid=010603&amp;cName=Webapp" target="_blank">Webapp</a></dd></dl><dl><dt><a class="c_1ab177" href="list_product.html?tid=010700&amp;cName=%E4%BA%A7%E5%93%81%E8%BF%90%E8%90%A5" target="_blank">产品运营</a></dt><dd><a href="list_product.html?tid=010701&amp;cName=%E4%BA%A7%E5%93%81%E8%AE%BE%E8%AE%A1" target="_blank">产品设计</a><a href="list_product.html?tid=010702&amp;cName=%E7%BD%91%E7%AB%99%E7%BC%96%E8%BE%91" target="_blank">网站编辑</a><a href="list_product.html?tid=010703&amp;cName=%E6%95%B0%E6%8D%AE%E5%88%86%E6%9E%90" target="_blank">数据分析</a><a href="list_product.html?tid=010704&amp;cName=%E7%AD%96%E5%88%92" target="_blank">策划</a></dd></dl><dl><dt><a class="c_1ab177" href="list_product.html?tid=010800&amp;cName=%E5%85%B6%E3%80%80%E3%80%80%E4%BB%96" target="_blank">其　　他</a></dt><dd><a href="list_product.html?tid=010801&amp;cName=%E7%BD%91%E7%BB%9C%E5%AE%89%E5%85%A8" target="_blank">网络安全</a><a href="list_product.html?tid=010802&amp;cName=%E5%B5%8C%E5%85%A5%E5%BC%8F%E5%9F%B9%E8%AE%AD" target="_blank">嵌入式培训</a><a href="list_product.html?tid=010803&amp;cName=%E7%A7%BB%E5%8A%A8%E9%80%9A%E4%BF%A1" target="_blank">移动通信</a><a href="list_product.html?tid=010804&amp;cName=%E4%BA%91%E8%AE%A1%E7%AE%97" target="_blank">云计算</a><a href="list_product.html?tid=010805&amp;cName=%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84" target="_blank">系统架构</a></dd></dl></div></div></div><div class="item"><div class="i_hd"><span class="i_hd_tit"><a href="list_product.html?tid=020000&amp;cName=%E8%AF%AD%E8%A8%80%E5%AD%A6%E4%B9%A0" target="_blank">语言学习</a></span><s class="i_hd_arr"></s></div><div class="i_bd"><div class="categ_m"><h3><a href="javascript:void(0);" target="_blank">语言学习</a></h3></div></div></div><div class="item"><div class="i_hd"><span class="i_hd_tit"><a href="list_product.html?tid=030000&amp;cName=%E4%B8%AD%E5%B0%8F%E5%AD%A6" target="_blank">中小学</a></span><s class="i_hd_arr"></s></div><div class="i_bd"><div class="categ_m"><h3><a href="javascript:void(0);" target="_blank">中小学</a></h3><dl><dt><a class="c_1ab177" href="list_product.html?tid=030100&amp;cName=%E9%AB%98%E4%B8%AD" target="_blank">高中</a></dt><dd><a href="list_product.html?tid=030101&amp;cName=%E9%AB%98%E4%B8%80" target="_blank">高一</a><a href="list_product.html?tid=030102&amp;cName=%E9%AB%98%E4%BA%8C" target="_blank">高二</a><a href="list_product.html?tid=030103&amp;cName=%E9%AB%98%E4%B8%89" target="_blank">高三</a><a href="list_product.html?tid=030104&amp;cName=%E9%AB%98%E8%80%83" target="_blank">高考</a></dd></dl><dl><dt><a class="c_1ab177" href="list_product.html?tid=030300&amp;cName=%E5%88%9D%E4%B8%AD" target="_blank">初中</a></dt><dd><a href="list_product.html?tid=030302&amp;cName=%E5%88%9D%E4%B8%80" target="_blank">初一</a><a href="list_product.html?tid=030301&amp;cName=%E5%88%9D%E4%BA%8C" target="_blank">初二</a><a href="list_product.html?tid=030303&amp;cName=%E5%88%9D%E4%B8%89" target="_blank">初三</a></dd></dl><dl><dt><a class="c_1ab177" href="list_product.html?tid=030400&amp;cName=%E5%B0%8F%E5%AD%A6" target="_blank">小学</a></dt><dd></dd></dl></div></div></div></div>
	      </div>
	      <ul class="mn_menu fl clearfix">
	        <li><a href="/" class="here">首页</a></li>
	        <li><a href="/jineng" target="_blank" class="">技能专区</a></li>
	        <li><a href="/kaoshi" target="_blank" class="">考试考级</a></li>
	        <li><a href="/k12" target="_blank" class="">中小学</a></li>
	        <li><a href="/xingqu" target="_blank" class="">生活文艺</a></li>
	        <li> <a href="#/course/__prelectstarttime_asc___2.html">今日直播</a> <i class="hot"></i> </li>
	        <li><a href="#/" target="_blank">精品课</a></li>
	        <li><a href="#" target="_blank">万学专区</a></li>
	      </ul>
	      <ul class="mn_extd">
	        <li><a class="a2" target="_blank" href="school/stip01.html">创建学校</a></li>
	      </ul>
	    </div>
	  </div>
	</div>*/
};

//底部html
var footer = function () {
	/*<footer class="footer">
	  <nav class="ft_nav"> <a href="/about" target="_blank">关于晒库网</a> <a href="/helpcenter/index.html" target="_blank">晒库网帮助</a> <a href="/feedbask" target="_blank">意见反馈</a> <a href="/contactus" target="_blank">联系方式</a> <a href="/job" target="_blank">招聘信息</a> <a href="#">手机触屏版</a> </nav>
	  <div class="copyright">
	    <p class="info"> <span class="mr10">京ICP证<a href="http://www.miitbeian.gov.cn" target="_blank">1234567</a>号</span> <span class="mr10">Copyright &copy; 2014 </span> <span>晒库网 <a target="_blank" href="http://www.shareku.com/">Shareku.com</a></span> </p>
	  </div>
	</footer>*/
};

Function.prototype.toHtml = function() {
	return this.toString().replace(/\/\*|\*\/|function \(\) {|}|\n/g, '').replace(/(^\s*)|(\s*$)/g, '');
};

/**
 * 获取课程分类数据
 */
function getCCdata() {
	$.ajax({
		type: "get",
		url: "courses_class/cc.shtml?t=" + Math.random(),
		dataType: "json",
		success: function(data) {
			var ccHtml = '';
			for (var index in data) {
				var tid = index;
				var o = data[index];
				if (o.pId == "000000") {
					//大标题
					var tHtml = '<a href="list_product.html?tid=' + tid + '&cName=' + encodeURIComponent(o.cName) + '" target="_blank">' + o.cName + '</a>'
					ccHtml += '<div class="item">'
							+ '<div class="i_hd">'
							+ '<span class="i_hd_tit">'
							+ tHtml + '</span><s class="i_hd_arr"></s></div>';
					//二、三级标题
					ccHtml += '<div class="i_bd">'
							+ '<div class="categ_m">'
							+ '<h3><a href="javascript:void(0);" target="_blank">' + o.cName + '</a></h3>';
					for (var i in data) {
						var o1 = data[i];
						var t1 = o1.tId;
						if (tid == o1.pId) {
							//二级小标题
							ccHtml += '<dl>'
									+ '<dt>'
									+ '<a class="c_1ab177" href="list_product.html?tid=' + t1 + '&cName=' + encodeURIComponent(o1.cName) + '" target="_blank">' + o1.cName + '</a>'
									+ '</dt>'
									+ '<dd>';
							for (var j in data) {
								var o2 = data[j];
								var t2 = o2.tId;
								if (t1 == o2.pId) {
									//三级小标题
									ccHtml += '<a href="list_product.html?tid=' + t2 + '&cName=' + encodeURIComponent(o2.cName) + '" target="_blank">' + o2.cName+ '</a>';
								}
							}
							ccHtml += '</dd></dl>';
						}
					}
					ccHtml += '</div></div></div>';
				}
			}
			$("#ccList").html(ccHtml);
			
			//事件注册
			$('.mn_sort > .mn_mc > .item').hover(function(){
				$(this).addClass('item_hover');
				$(this).children('.i_bd').show();
			},function(){
				$(this).removeClass('item_hover');
				$(this).children('.i_bd').hide();
			});
		}
	});
}

/**
 * 根据key获取url中对应的值
 */
function getParameter(param) {
	var query = [];
	var array = [];
	var params = location.search.substring(1);
	array = params.split('&');
	if (array.length == 0) return "";
	var length = array.length;
	for (var i = 0; i < length; i++)
	{
		var index = array[i].indexOf('=');
		var key   = array[i].substring(0, index);
		var value = array[i].substring(index + 1);
		query[key] = value;
	}
	return query[param];
}

/**
 *
 * Base64 encode / decode
 *
 * @author haitao.tu
 * @date 2010-04-26
 * @email tuhaitao@foxmail.com
 *
 */
function Base64() {
	
	// private property
	_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	
	// public method for encoding
	this.encode = function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = _utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output +
			_keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
			_keyStr.charAt(enc3) + _keyStr.charAt(enc4);
		}
		return output;
	}
	
	// public method for decoding
	this.decode = function (input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		while (i < input.length) {
			enc1 = _keyStr.indexOf(input.charAt(i++));
			enc2 = _keyStr.indexOf(input.charAt(i++));
			enc3 = _keyStr.indexOf(input.charAt(i++));
			enc4 = _keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = _utf8_decode(output);
		return output;
	}
	
	// private method for UTF-8 encoding
	_utf8_encode = function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";
		for (var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
	
		}
		return utftext;
	}
	
	// private method for UTF-8 decoding
	_utf8_decode = function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while ( i < utftext.length ) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
}


function getuser(){
	$.ajax({
		type: "get",
		url: "member/userinfo.shtml",
		dataType: "json",
		success: function(data) {
			if (data) {
				$(".topR_login").html('<a href="javascript:void(0);" target="_blank">您好，' + data['loginname'] + '</a>'+
						'<a href="member/logout.shtml">注销</a>');
				return;
				for(var index in data) {
					var tid = index;
					var o = data[index];
					alert(tid);
					alert(o);
				}
			}
		}
	});
}
//加载用户信息
window.onload = getuser;

var tool = {
	domain : 'http://123.57.11.203/image/',
	flv : 'http://123.57.11.203/flv/'
};

