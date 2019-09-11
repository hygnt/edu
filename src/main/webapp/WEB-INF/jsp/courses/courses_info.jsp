<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<script type="text/javascript" src="../js/ckeditor/ckeditor.js"></script>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:100px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="save.shtml" name="userForm" id="userForm" target="result" method="post" onSubmit="return checkInfo();">
	<input type="hidden" name="courseId" id="courseId" value="${course.courseId }"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>一级分类:</th>
			<td>
			<select name="courseOne" id="courseOne" class="input_txt" onChange="changeclass(this.value,2)">
					<c:forEach items="${one }" var="code">
						<c:choose>
							<c:when test="${code.code == course.courseOne }">
								<option value="${code.code }" selected="selected">${code.view }</option>
							</c:when>
							<c:otherwise>
								<option value="${code.code }">${code.view }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select>
			</td>
		</tr>
		<tr class="info">
			<th>二级分类:</th>
			<td>
				<select name="courseTwo" id="courseTwo" class="input_txt" onChange="changeclass(this.value,3)">
					<c:forEach items="${two }" var="code">
						<c:choose>
							<c:when test="${code.code == course.courseTwo }">
								<option value="${code.code }" selected="selected">${code.view }</option>
							</c:when>
							<c:otherwise>
								<option value="${code.code }">${code.view }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr class="info">
			<th>三级分类:</th>
			<td>
				<select name="courseThree" id="courseThree" class="input_txt">
					<c:forEach items="${three }" var="code">
						<c:choose>
							<c:when test="${code.code == course.courseThree }">
								<option value="${code.code }" selected="selected">${code.view }</option>
							</c:when>
							<c:otherwise>
								<option value="${code.code }">${code.view }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr class="info">
			<th>课程名称<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" type="text" name="courseName" id="courseName" class="input_txt" value="${course.courseName }"/></td>
		</tr>
		<tr class="info">
			<th>关键词:</th>
			<td><input autocomplete="off" type="text" name="keywords" id="keywords" class="input_txt" value="${course.keywords }"/></td>
		</tr>
		<tr class="info">
			<th>课程价格<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" type="text" name="coursePrice" id="coursePrice" class="input_txt" value="${course.coursePrice }"/></td>
		</tr>
		<tr class="info">
			<th>市场价<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" type="text" name="valuations" id="valuations" class="input_txt" value="${course.valuations }"/></td>
		</tr>
		<tr class="info">
			<th>折扣<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" type="text" name="discount" id="discount" class="input_txt" value="${course.discount }"/></td>
		</tr>
		<tr class="info">
			<th>图片<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" readonly="readonly" onFocus="parent.frames[0].addImage(${course.courseId })" type="text" name="imagePath" id="imagePath" class="input_txt" value="${course.imagePath }"/></td>
		</tr>
		<tr class="info">
			<th>视频<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" readonly="readonly" onFocus="parent.frames[0].addVideo(${course.courseId })" type="text" name="filePath" id="filePath" class="input_txt" value="${course.filePath }"/></td>
		</tr>
		<tr class="info">
			<th>销售数量<span style="color:red;">*</span>:</th>
			<td><input autocomplete="off" type="text" name="sales" id="sales" class="input_txt" value="${course.sales }"/></td>
		</tr>
		<tr class="info">
			<th>讲师:</th>
			<td><input autocomplete="off" type="text" name="lecturer" id="lecturer" class="input_txt" value="${course.lecturer }"/></td>
		</tr>
		<tr class="info">
			<th>课程描述:</th>
			<td><input autocomplete="off" type="text" name="description" id="description" class="input_txt" value="${course.description }"/></td>
		</tr>
		<tr class="info">
			<th>页面标题:</th>
			<td><input autocomplete="off" type="text" name="pagetitle" id="pagetitle" class="input_txt" value="${course.pagetitle }"/></td>
		</tr>
        <tr class="info">
			<th>课程介绍:</th>
			<td><textarea cols="80" id="editor1" name="editor1" rows="10">${course.description }</textarea>

			<script type="text/javascript">

				CKEDITOR.replace( 'editor1');

</script>
</td>
		</tr>
		<tr class="info">
			<th>课程状态:</th>
			<td>
				<select name="status" id="status" class="input_txt">
					<c:forEach items="${coursesStateList }" var="code">
						<c:choose>
							<c:when test="${code.code == course.status }">
								<option value="${code.code }" selected="selected">${code.view }</option>
							</c:when>
							<c:otherwise>
								<option value="${code.code }">${code.view }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr class="info">
			<th>课程发布时间:</th>
			<td><input autocomplete="off" type="text" name="date" id="date" class="input_txt Wdate" value="<fmt:formatDate value="${course.date }" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-M-d H:mm:ss'})" readonly="readonly"/></td>
		</tr>
		<tr class="info">
			<th>课程推荐:</th>
			<td>
				<select name="recommend" id="recommend" class="input_txt">
					<c:forEach items="${coursesCommendList }" var="code">
						<c:choose>
							<c:when test="${code.code == course.recommend }">
								<option value="${code.code }" selected="selected">${code.view }</option>
							</c:when>
							<c:otherwise>
								<option value="${code.code }">${code.view }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=top&s=areo_blue"></script>
	<script type="text/javascript">
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				$("#userForm").submit();
			});
			
			//模拟鼠标单击事件
			//$("#courseOne").trigger("change");
		});
		
		function checkInfo(){
			if($("#courseName").val() == ""){
				alert("课程名称不能为空!");
				$("#courseName").focus();
				return false;
			}
			if($("#coursePrice").val() == ""){
				alert("课程价格不能为空!");
				$("#coursePrice").focus();
				return false;
			}
			if($("#valuations").val() == ""){
				alert("课程市场价不能为空!");
				$("#valuations").focus();
				return false;
			}
			if($("#discount").val() == ""){
				alert("折扣不能为空!");
				$("#discount").focus();
				return false;
			}
			if($("#imagePath").val() == ""){
				alert("图片不能为空!");
				parent.frames['mainFrame'].addImage(${course.courseId });
				return false;
			}
			if($("#filePath").val() == ""){
				alert("视频不能为空!");
				parent.frames['mainFrame'].addVideo(${course.courseId });
				return false;
			}
			if($("#sales").val() == ""){
				alert("销售数量不能为空!");
				$("#sales").focus();
				return false;
			}
			return true;
		}
		
		function changeclass(value,level){
			if(level==2) {
				document.getElementById("courseTwo").options.length=0; 
				document.getElementById("courseThree").options.length=0;
			} else {
				document.getElementById("courseThree").options.length=0;
			}
			if (value) {
				$.ajax({
					type: "get",
					url: "./next.shtml?typeId=" + value,
					dataType: "json",
					success: function(data) {
						if (level==2) {
							for(var index in data) {
								var tid = index;
								var o = data[index];
								if(tid == "${course.courseTwo}") {
									document.getElementById("courseTwo").options.add(new Option(o,tid)).selected;
								} else {
									document.getElementById("courseTwo").options.add(new Option(o,tid));
								}
							}
							//如果是一级联动，直接带动第二级也联动
							changeclass(document.getElementById("courseTwo").value,3);
						} else {
							for(var index in data) {
								var tid = index;
								var o = data[index];
								if(tid == "${course.courseThree}") {
									document.getElementById("courseThree").options.add(new Option(o,tid)).selected;
								} else {
									document.getElementById("courseThree").options.add(new Option(o,tid));
								}
							}
						}
					}
				});
			}
			
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
		
	</script>
</body>
</html>