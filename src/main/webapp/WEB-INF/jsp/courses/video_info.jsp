<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="upload.shtml?coursesId=${coursesId }" name="userForm" id="userForm" target="result" method="post" enctype="multipart/form-data" onsubmit="return checkInfo();">
	<input type="hidden" name="courseId" id="courseId" value="${coursesId }"/>
	<table border="0" cellpadding="10" cellspacing="10">
		<%-- <tr class="info">
			<th>所属课程ID:</th>
			<td><input type="text" name="courseId" id="courseId" class="input_txt" readonly="readonly" value="${coursesId }"/></td>
		</tr> --%>
		<tr>
			<th id="typeName">视频文件</th>
			<td><input type="file" name="file" id="file" class="input_txt"/></td>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			var type = "${type}";
			if(type=="image"){
				document.forms[0].action = "uploadimage.shtml?coursesId="+"${coursesId }";
				$("#typeName").html("图片文件");
			}
			dg.addBtn('ok','保存',function(){
				if (type == "image") {
					//文件名获取
					//console.log(parent.frames[2].$("#imagePath"));
					var filename = $("#file").val();
					//console.log(filename);
					filename = filename.substr(filename.lastIndexOf("\\") + 1);
					parent.frames[2].$("#imagePath").val(filename);
				} else {
					var filename = $("#file").val();
					filename = filename.substr(filename.lastIndexOf("\\") + 1);
					parent.frames[2].$("#filePath").val(filename);
				}
				$("#userForm").submit();
			});
		});
		
		function checkInfo(){
			if($("#file").val() == ""){
				alert("还未选择文件!");
				$("#file").focus();
				return false;
			}
			return true;
		}
		
		function success(){
			dg.cancel();
			return;
			alert('上传成功');
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
	</script>
</body>
</html>