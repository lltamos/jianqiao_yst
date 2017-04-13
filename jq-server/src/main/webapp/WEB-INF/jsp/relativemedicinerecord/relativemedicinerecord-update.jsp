<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function check() {
		return true;
	}
	
	function updateRelativeMedicineRecord() {
		if (check()) {
			var form = $("#doctorInfoForm");
			var options = {
				url : '${ctx}/relativemedicinerecord/relativemedicinerecord-update', //提交给哪个执行
				type : 'POST',
				success : function(result) {
					var appResult = eval("(" + result + ")");
					var message = appResult.msg;
					var success = appResult.code;
					if (success == 0 ) {
						$("#save").tips({
							side : 2,
							msg : message,
							bg : '#68B500',
							time : 10
						});
					} else {
						pageGo('${ctx }/relativemedicinerecord/to-relativemedicinerecord-list');
					}
				} 
			};
			form.ajaxSubmit(options);
		}
	}
	
$(function() {
	var start = {
			elem : '#start',//目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			//event: 'focus', //响应事件。如果没有传入event，则按照默认的click
			//festival: true, //显示节日
			format : 'YYYY-MM-DD hh:mm:ss',
			min : laydate.now(), //设定最小日期为当前日期
			max : '2099-06-16 23:59:59', //最大日期
			istime : true,
			istoday : false
		};
		laydate(start);
	});
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="user!index">主页</a> <span class="divider">/</span></li>
		<li>病例列表 <span class="divider">/</span>
		</li>
		<li class="active">修改病例记录信息：</li>
	</ul>
	<form method="post" id="doctorInfoForm">
		<table border="3" bordercolor="blue" width="60%" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>所属相关人员：</td>
				<td>
					<input type="hidden" name="id" value="${relativemedicinerecord.id }"/>
					<select name="relativeName">
						<c:forEach items="${customerRelativedList }" var="cr">
							<option value="${cr.id }_${cr.name}" <c:if test="${cr.id == relativemedicinerecord.id }">selected</c:if>>${cr.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>症状及诊断：</td>
				<td><input type="text" name="des" value="${relativemedicinerecord.des }" /></td>
			</tr>
			<tr>
				<td>医生名称：</td>
				<td><input type="text" name="doctorName" value="${relativemedicinerecord.doctorName }" /></td>
			</tr>
			<tr>
				<td>科室：</td>
				<td><input type="text" name="officeName" value="${relativemedicinerecord.officeName }" /></td>
			</tr>
			<tr>
				<td>用药时长：</td>
				<td><input type="text" name="during" value="${relativemedicinerecord.during }" /></td>
			</tr>
			<tr>
				<td>服用时间：</td>
				<td><input type="text" class="laydate-icon" name="start_date" id="start" value="${relativemedicinerecord.startDate }"  style="width:200px; margin-right:10px;"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button class="btn"
						type="button" onclick="updateRelativeMedicineRecord()" id="save">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn" type="button"
						onclick="pageGo('${ctx }/relativemedicinerecord/to-relativemedicinerecord-list')">返回</button></td>
			</tr>
		</table>

	</form>
</body>
