/**
 * 课程详情页JS调用
 */
function remainTime() {
	var iDay, iHour, iMinute, iSecond;
	var sDay="", sHour="", sMinute="", sSecond="", sTime="";
	if (iTime >= 0) {
		iDay = parseInt(iTime/24/3600);
		if (iDay > 0) {
			sDay = '<i class="c_f60 f14 fb">' + iDay + '</i>&nbsp;天';
		}
		iHour = parseInt((iTime/3600)%24);
		if (iHour > 0){
			sHour = '&nbsp;<i class="c_f60 f14 fb">' + iHour + '</i>&nbsp;小时';
		}
		iMinute = parseInt((iTime/60)%60);
		if (iMinute > 0){
			sMinute = '&nbsp;<i class="c_f60 f14 fb">' + iMinute + '</i>&nbsp;分';
		}
		iSecond = parseInt(iTime%60);
		if (iSecond >= 0){
			sSecond = '&nbsp;<i class="c_f60 f14 fb">' + iSecond + '</i>&nbsp;秒';
		}
		sTime='倒计时 '+sDay+sHour+sMinute+sSecond;
		if(iTime==0){
			clearTimeout(Account);
			sTime='<i style="color:red;font-weight:bold">有课程正在直播中...</i>';
		} else {
			Account = setTimeout("remainTime()",1000);
		}
		iTime=iTime-1;
	} else {
		sTime='<i style="color:red;font-weight:bold">有课程正在直播中...</i>';
	}
	$("#remain_time").html(sTime);
}

function getBoxContent(tab) {
	$.ajax({
		url:"/?mod=course&act=show&do="+tab+"&sid="+Course.SID+"&courseid="+Course.CourseID+"&r="+Math.random(),
		type:"get",
		beforeSend:function(){
			$("#box_content").html('<div class="tc pt30"><img border="0" src="http://res.ckimg.com/sites/www/v2/images/public/loading.gif" /></div>');
		},
		success:function(data) {
			$("#box_content").html(data);
		}
	});
}

function getCommentVote() {
	$.ajax({
		url:"/?mod=course&act=show&do=comment&sid="+Course.SID+"&courseid="+Course.CourseID+"&comment_flag=1&r="+Math.random(),
		type:"get",
		beforeSend:function(){
			$("#box_content").html('<div class="tc pt30"><img border="0" src="http://res.ckimg.com/sites/www/v2/images/public/loading.gif" /></div>');
		},
		success:function(data) {
			$("#box_content").html(data);
		}
	});
}

function addtoCart(){
	if(KK.checkLogin() === false){
		$.ckLoginBoxy();
		return false;
	}		
	$.ajax({
		url:'/?mod=cart&act=operate&do=add&sid='+Course.SID+'&courseid='+Course.CourseID+'&r='+Math.random(),
		type:'get',
		dataType:'json',
		beforeSend:function(){
			loading = $.ckTipsBoxy({type:"loading", mask:true, message:"提交数据中..."});
		},
		success:function(ret) {
			loading.cancel();
			if(ret.code == 0){
				var msg = '<div class="d_d_gx lh25 mt25 mb30 f14 c_777">';
				msg += '<img class="img" src="http://res.ckimg.com/sites/www/v2/images/public/ico_ok_25x25.png">';
				msg += '<p>已加入购物车！</p>';
				msg += '<p class="f12"><a href="/?mod=cart&act=show" target="_blank">查看我的购物车>></a></p>';
				msg += '</div>';
				msg += '<p class="tc">';
				msg += '<a href="javascript:;" id="try_success_confirm" class="c_btn37a"><span>确 定</span></a>';
				msg += '</p>';
				var ajaxBox = $.ckBoxy({
					content: msg,
					title : "提示",
					width : 338,
					height : 236,
					callback : function(){
						$(".close").click(function(){
							ajaxBox.cancel();
						});
						$("#try_success_confirm").live('click', function(){
							ajaxBox.cancel();
						});
					}
				});
				KK.updateCartCount();
			}
		}
	});	
}

$(function(){
	$("#selected_class").val('');
	var hash = location.hash;
	//装载课程简介
	if(hash == '#comment'){
		$("#tab_switch li").removeClass('curr');
		$('li[tab=comment]').addClass('curr');
		getBoxContent('comment');
	} else {
		getBoxContent('brief');
	}
	$("#btnList").show();
	var offset = $(".bdTabs").offset();
	$(window).scroll(function(){
		var scrollTop = $(this).scrollTop();
		var height = $(window).height();
		var height1 = $('div.detailContent').height();
		if((scrollTop >= offset.top) && (height1 >= height)){
			$(".bdTabs").addClass('fixed');
		}else{
			$(".bdTabs").removeClass('fixed');
		}
	});
	
	//标签切换
	$("#tab_switch li[float!=float]").click(function(){
		var tab = $(this).attr('tab');
		$("#tab_switch li").removeClass('curr');
		$(this).addClass('curr');
		if($(window).scrollTop() > offset.top){
			$(window).scrollTop(offset.top);
		}
		
		if (tab == 'comment' && Course.CommentVote == 1) {
			getCommentVote();
		} else {
			getBoxContent(tab);
		}
		
		return false;
	});
	
	//学生模块“更多”操作
	$("#student_tab_switch").click(function(){
		$("#tab_switch li").removeClass('curr');
		$("#tab_switch li[tab='student']").addClass('curr');
		getBoxContent('student');
		return false;
	});
	
	//学生模块“更多”操作
	$("#comment_tab_switch").click(function(){
		$("#tab_switch li").removeClass('curr');
		$("#tab_switch li[tab='comment']").addClass('curr');
		location.href = "#comment";
		if (Course.CommentVote == 1) {
			getCommentVote();
		} else {
			getBoxContent('comment');
		}
		return false;
	});
	
	//自定义分类
	$(".type_switch").toggle(
		function(){
			$(this).parent().parent().addClass("unfold");
			$(this).parent().next().slideUp("fast");
		},
		function(){
			$(this).parent().parent().removeClass("unfold");
			$(this).parent().next().slideDown("fast");
		}
	);
	
	//收藏课程
	$("#collect_course").live('click',function(){
		if(KK.checkLogin() === false){
			$.ckLoginBoxy();
			return false;
		}else{
			var loading = null;
			$.ajax({
				url:'/?mod=course&act=show&do=collect&sid='+Course.SID+'&courseid='+Course.CourseID+'&r='+Math.random(),
				type:'get',
				dataType:'json',
				success:function(ret) {
					if (ret.code == 0) {
						$.ckTipsBoxy({type:"success", message:"收藏成功", mask:true});
						var collect_course_num = parseInt($("#collect_course_num").text());
						collect_course_num = collect_course_num + 1;
						$("#collect_course").attr('id', 'uncollect_course').html('取消收藏（<span id="collect_course_num">'+collect_course_num+'</span>）');
					} else {
						$.ckAlert({message:ret.data});
					}
				}
			});
			return false;
		}
	});
	
	$("#uncollect_course").live('click',function(){
		if(KK.checkLogin() === false){
			$.ckLoginBoxy();
			return false;
		}else{
			var loading = null;
			$.ajax({
				url:'/?mod=course&act=show&do=uncollect&sid='+Course.SID+'&courseid='+Course.CourseID+'&r='+Math.random(),
				type:'get',
				dataType:'json',
				success:function(ret) {
					if (ret.code == 0) {
						$.ckTipsBoxy({type:"success", message:"取消收藏成功", mask:true});
						var collect_course_num = parseInt($("#collect_course_num").text());
						if(collect_course_num > 0){
							collect_course_num = collect_course_num - 1;
						}
						$("#uncollect_course").attr('id', 'collect_course').html('课程收藏（<span id="collect_course_num">'+collect_course_num+'</span>）');
					} else {
						$.ckAlert({message:ret.data});
					}
				}
			});
			return false;
		}
	});
	
	//收藏学校
	$("#collect_school").live('click',function(){
		if(KK.checkLogin() === false){
			$.ckLoginBoxy();
			return false;
		}else{
			$.ajax({
				url:'/?mod=school&act=edit&do=collect&sid='+Course.SID+'&r='+Math.random(),
				type:'get',
				dataType:'json',
				success:function(ret) {
					if (ret.code == 0) {
						$.ckTipsBoxy({type:"success", message:"收藏成功", mask:true});
						var collect_school_num = parseInt($("#collect_school_num").text());
						collect_school_num = collect_school_num + 1;
						$("#collect_school_num").text(collect_school_num);
						$("#collect_school").attr('id', 'uncollect_school').text('取消收藏');
					} else {
						$.ckAlert({message:ret.data});
					}
				}
			});
			return false;
		}
	});
	
	//取消收藏学校
	$("#uncollect_school").live('click',function(){
		if(KK.checkLogin() === false){
			$.ckLoginBoxy();
			return false;
		}else{
			$.ajax({
				url:'/?mod=school&act=edit&do=unCollect&sid='+Course.SID+'&r='+Math.random(),
				type:'get',
				dataType:'json',
				success:function(ret) {
					if (ret.code == 0) {
						$.ckTipsBoxy({type:"success", message:"取消收藏成功", mask:true});
						var collect_school_num = parseInt($("#collect_school_num").text());
						if(collect_school_num > 0){
							collect_school_num = collect_school_num - 1;
						}
						$("#collect_school_num").text(collect_school_num);
						$("#uncollect_school").attr('id', 'collect_school').text('收藏学校');
					} else {
						$.ckAlert({message:ret.data});
					}
				}
			});
			return false;
		}
	});
	
	//学校课程排行
	$("#collect_list_switch").click(function(){
		$("#buy_list_switch").removeClass('curr');
		$(this).addClass('curr');
		$("#buy_top_list").hide();
		$("#collect_top_list").show();
	});
	$("#buy_list_switch").click(function(){
		$("#collect_list_switch").removeClass('curr');
		$(this).addClass('curr');
		$("#collect_top_list").hide();
		$("#buy_top_list").show();
	});
	
	//开通短信通知
	$("#message_remind").click(
		function(){
			var Box = $.ckAjaxBoxy({
				url : '/?mod=student&act=course&do=remind',
				title : "短信提醒",
				width : 354,
				height : 194,
				callback:function(){
					$("#fe_dialogBox").find("a.cancel").unbind().bind('click', function(){
						Box.cancel();
					});
				}
			});
		}
	);	
	
	//立刻购买
	$("a.buyBtn").click(function(){
		if(KK.checkLogin() === false){
			$.ckLoginBoxy();
			return false;
		}else{
			var buy_type = $("#buy_course_type").text();
			if (Course.Cost == 0) {
				//如果是免费课程，则调用免费购买
				$.ajax({
					url:'/?mod=order&act=create&do=freebuy&sid='+Course.SID+'&courseid='+Course.CourseID+'&r='+Math.random(),
					type:'get',
					dataType:'json',
					success:function(ret) {
						if (ret.code == 0) {
							$.get('/?mod=index&act=index&do=redirect&type=freebuy&returl='+encodeURIComponent('/?mod=student&act=study&courseid='+Course.CourseID)+'&rand='+Math.random(), function(rethtml){
								var Box = $.ckRedirectBoxy({
									content : rethtml,
									width : 421,
									height : 97
								});
							});
						} else {
							$.ckAlert({message:ret.data});
						}
					}
				});
				return false;
			} else {
				//判断单课购买				
				if (buy_type == 2) {
					var cids = $("#selected_class").val();
					if (!cids) {
						$(".extra_gmlx_txt").show();
						return false;
					}
				} else {
					cids = 0;
				}
				
				$.ajax({
					url:'/?mod=order&act=show&do=checkCourseCost&sid='+Course.SID+'&courseid='+Course.CourseID+'&cids='+cids+'&rand='+Math.random(),
					type:'get',
					async:false,
					dataType:'json',
					success:function(ret){
						if (ret.code == '1') {
							//整课免费购买
							$.ajax({
								url:'/?mod=order&act=create&do=freebuy&sid='+Course.SID+'&courseid='+Course.CourseID+'&r='+Math.random(),
								type:'get',
								async:false,
								dataType:'json',
								success:function(rs) {
									if (rs.code == 0) {
										$.get('/?mod=index&act=index&do=redirect&type=freebuy&returl='+encodeURIComponent('/?mod=student&act=study&courseid='+Course.CourseID)+'&rand='+Math.random(), function(rethtml){
											var Box = $.ckRedirectBoxy({
												content : rethtml,
												width : 421,
												height : 97
											});
										});
									} else {
										$.ckAlert({message:rs.data});
									}
									return false;
								}
							});
						} else if (ret.code == '2') {
							//单课免费购买
							$.ajax({
								url:'/?mod=order&act=create&do=freebuy&sid='+Course.SID+'&courseid='+Course.CourseID+'&cids='+cids+'&r='+Math.random(),
								type:'get',
								async:false,
								dataType:'json',
								success:function(rs) {
									if (rs.code == 0) {
										$.get('/?mod=index&act=index&do=redirect&type=freebuy&returl='+encodeURIComponent('/?mod=student&act=study&courseid='+Course.CourseID)+'&rand='+Math.random(), function(rethtml){
											var Box = $.ckRedirectBoxy({
												content : rethtml,
												width : 421,
												height : 97
											});
										});
									} else {
										$.ckAlert({message:rs.data});
									}
									return false;
								}
							});
						} else if (ret.code == '0') {
							//付费购买
							$.ajax({
								url:'/?mod=cart&act=operate&do=add&sid='+Course.SID+'&courseid='+Course.CourseID+'&cids='+cids+'&r='+Math.random(),
								type:'get',
								dataType:'json',
								beforeSend:function(){
									loading = $.ckTipsBoxy({type:"loading", mask:true, message:"提交数据中..."});
								},
								success:function(ret) {
									loading.cancel();
									window.location.href = "/?mod=cart&act=show";
								}
							});
						}
					}
				});
			}
			
			return false;
		}
	});
	
	//报名试听
	$("a.a_lkst").click(function(){
		if(KK.checkLogin() === false){
			$.ckLoginBoxy();
			return false;
		}else{
			$.ajax({
				url:'/?mod=order&act=create&do=trial&sid='+Course.SID+'&courseid='+Course.CourseID+'&r='+Math.random(),
				type:'get',
				dataType:'json',
				success:function(ret) {
					if (ret.code == 0) {
						$.get('/?mod=index&act=index&do=redirect&type=trial&returl='+encodeURIComponent('/?mod=student&act=study&courseid='+Course.CourseID)+'&rand='+Math.random(), function(rethtml){
							var Box = $.ckRedirectBoxy({
								content : rethtml,
								width : 421,
								height : 97
							});
						});
					} else {
						$.ckAlert({message:ret.data});
					}
				}
			});
			return false;
		}
	});
	
	//加入购物车
	$("#addToCart").click(function(){
		addtoCart();
	});
	
	//单课购买
	$(".gmlx_zk").click(function(){
		$(".gmlx_bf").removeClass('gmlx_here');
		$(this).addClass('gmlx_here');
		$(".gmlx_bf_tips").hide();
		$(".extra_gmlx_txt").hide();
		$("#buy_course_type").text('1');
		var $sHeight = $('.priInfo').outerHeight(true);
		$('.priSchool').css({'min-height': $sHeight});
		var $Height = $('header.hd').height();
		var height1 =  $('div.bd li').outerHeight();
		var height2 =  $('footer.ft').height();
		var height = ($sHeight - $Height - height2)/7;
		$('#schoolInfo > li').css({'margin-top': (height - height1)*3/4});
	});
	
	$(".gmlx_bf").click(function(){
		$(".gmlx_zk").removeClass('gmlx_here');
		$(this).addClass('gmlx_here');
		$(".gmlx_bf_tips").show();
		$(".extra_gmlx_txt").show();
		$("#buy_course_type").text('2');
		$("#select_class").trigger('click');
		var $sHeight = $('.priInfo').outerHeight(true);
		$('.priSchool').css({'min-height': $sHeight});
		var $Height = $('header.hd').height();
		var height1 =  $('div.bd li').outerHeight();
		var height2 =  $('footer.ft').height();
		var height = ($sHeight - $Height - height2)/7;
		$('#schoolInfo > li').css({'margin-top': (height - height1)*3/4});
	});
	
	$("#select_class").click(function(){
		var Box = $.ckAjaxBoxy({
			url : '/?mod=course&act=show&do=content&sid='+Course.SID+'&courseid='+Course.CourseID+'&rand='+Math.random(),
			title : '请选择你要购买的章节<span class="c_333">（<em class="c_f60">'+Course.ClassCost/100+'</em>元/节）</span>',
			width : 712,
			height : 536,
			callback:function(){
				$("#fe_dialogBox").find("a.cancel").unbind().bind('click', function(){
					Box.cancel();
				});
			}
		});
		return false;
	});
});
