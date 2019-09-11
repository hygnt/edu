<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="save.shtml" name="userForm" id="userForm" target="result" method="post" onsubmit="return checkInfo();">
	<input type="hidden" name="ccId" id="ccId" value="${courses_class.ccId }"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>类别名称<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" type="text" name="ccName" id="ccName" class="input_txt" value="${courses_class.ccName }"/></td>
		</tr>
		<tr class="info">
			<th>父类别<span style="color:red;">*</span>:</th>
			<td>
				<select name="ccParentId" id="ccParentId" class="input_txt" onchange="changeClass()">
				</select>
			</td>
		</tr>
		<tr class="info">
			<th>类别编号<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" style="background-color:#FCF1F1;" type="text" name="typeId" id="typeId" class="input_txt" value="${courses_class.typeId }"/></td>
		</tr>
		<tr class="info" style="display:none;">
			<th>类别全名<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" type="text" name="typeName" id="typeName" class="input_txt" value="${courses_class.typeName }"/></td>
		</tr>
		<tr class="info">
			<th>排序<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" type="text" name="ccSort" id="ccSort" class="input_txt" value="${courses_class.ccSort }"/></td>
		</tr>
		<tr class="info" style="display:none;">
			<th>类别标题<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" type="text" name="ccTitle" id="ccTitle" class="input_txt" value="${courses_class.ccTitle }"/></td>
		</tr>
		<tr class="info">
			<th>关键字:</th>
			<td><input autocomplete="off" type="text" name="ccKeywords" id="ccKeywords" class="input_txt" value="${courses_class.ccKeywords }"/></td>
		</tr>
		<tr class="info">
			<th>描述:</th>
			<td><input autocomplete="off" type="text" name="ccDescription" id="ccDescription" class="input_txt" value="${courses_class.ccDescription }"/></td>
		</tr>
		<tr class="info">
			<th>页面标题:</th>
			<td><input autocomplete="off" type="text" name="pagetitle" id="pagetitle" class="input_txt" value="${courses_class.pagetitle }"/></td>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	
	<script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript">
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				$("#userForm").submit();
			});
			
			//如果为修改页面，则默认为选择的值
			if ($("#ccId").val() != "") {
				var val = fillPreZero("${courses_class.ccParentId }", 6);
				getCCList(val);
			} else {
				getCCList();
				//初始化值
				changeClass("000000")
			}
		});
		
		function checkInfo(){
			if($("#ccName").val() == ""){
				alert("类别名称不能为空!");
				$("#ccName").focus();
				return false;
			}
			/* if($("#typeName").val() == ""){
				alert("类别全名不能为空!");
				$("#typeName").focus();
				return false;
			} */
			if($("#ccSort").val() == ""){
				alert("排序字段不能为空!");
				$("#ccSort").focus();
				return false;
			}
			if($("#typeId").val() == ""){
				alert("类别编号不能为空!");
				$("#typeId").focus();
				return false;
			}
			/* if($("#ccTitle").val() == ""){
				alert("类别标题不能为空!");
				$("#ccTitle").focus();
				return false;
			} */
			return true;
		}
		
		function success(){
			if(dg.curWin.document.forms[0]){
				dg.curWin.document.forms[0].action = dg.curWin.location+"";
				dg.curWin.document.forms[0].submit();
			}else{
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
		
		function failed(){
			alert("添加或修改失败！");
		}
		
	/**
	 * 获取课程分类数据
	 */
	function getCCList(val) {
		$.ajax({
			type: "get",
			url: "cc.shtml?t=" + Math.random(),
			dataType: "json",
			success: function(data) {
				var ccHtml = '<option value="000000">请选择</option>';
				for (var index in data) {
					var tid = index;
					var o = data[index];
					if (o.pId == "000000") {
						//一级分类
						ccHtml += '<option value="' + tid + '">' + o.cName + '</option>';
						for (var i in data) {
							var o1 = data[i];
							var t1 = o1.tId;
							if (tid == o1.pId) {
								//二级分类
								ccHtml += '<option value="' + t1 + '">--------' + o1.cName + '</option>';
							}
						}
					}
				}
				$("#ccParentId").html(ccHtml);
				//默认选中
				val && $("#ccParentId").val(val);
			}
		});
	}

	/**
	 * 填充前缀0，最多六位
	 */
	function fillPreZero(val, len) {
		var str = '000000';
		val += '';
		return (str.substring(0, len - val.length) + val);
	}
	
	function changeClass(initVal) {
		if ($("#ccId").val() == "") {
			var val = initVal || $("#ccParentId").val();
			$("#typeId").val(val);
			$.ajax({
				type: "get",
				url: "cc.shtml?pid=" + val + "&t=" + Math.random(),
				dataType: "json",
				success: function(data) {
					console && console.log(data);
					var levelNum = 0;
					if (val == "000000") {
						levelNum += 10000;
					} else if (val.substring(2, 6) == "0000") {
						levelNum += 100;
					} else if (val.substring(4, 6) == "00") {
						levelNum += 1;
					}
					for (var index in data) {
						levelNum += index - 1 + 1;
						break;
					}
					//alert(JSON.stringify(data));
					if (JSON.stringify(data) == "{}") {
						levelNum += val - 1 + 1;
					}
					$("#typeId").val(levelNum);
				}
			});
		}
	}
	</script>
</body>
</html>