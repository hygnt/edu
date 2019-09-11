/**
 * 首页JS交互 
 */
var tag = true;
var theHideMenu = function(obj, tag){
	if(tag){
		obj.removeClass("hover"); //删除获取焦点时的样式
		obj.next(".i_c_tips").hide(); //隐藏提示框
	}
}

$(function(){
	//分类列表
	$("#categoryList > dl.item").mouseenter(function() {//设置当前所选项的鼠标滑过事件
		$("#categoryList > dl.item").removeClass('hover').next(".i_c_tips").hide();
		var objL = $(this); //获取当前对象
		var curY = objL.position().top;
		var curW = objL.outerWidth();
		
		objL.addClass("hover"); //增加获取焦点时的样式
		objL.next(".i_c_tips").show().css({"top":curY, "left":curW}); //显示并设置提示框的坐标
	}).mouseleave(function() {//设置当前所选项的鼠标移出事件
		objL = $(this);
		objL.next(".i_c_tips").mouseenter(function(){
			tag = false;
			objL.addClass("hover");
		}).mouseleave(function(){
			tag = true;
			objL.removeClass("hover");
			$(this).hide();
		});
		setTimeout('theHideMenu(objL, tag)', 1);
	});
	
	//热门学校列表切换
	$("#toplistTab").find("li").click(function(){
		var cobj = $(this);
		$("#toplistTab").find("li").removeClass('curr');
		cobj.addClass('curr');
		var tid = cobj.attr('tid');
		$(".cons_sub").hide();
		$("#toplist_"+tid).show();
	});
});