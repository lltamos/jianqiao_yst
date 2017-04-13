<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
String doctor_id=request.getParameter("doctor_id");
%>
<html>
<script type="text/javascript">
var urls='${urls}';
	$(function() {
		oTable = $("#table")
				.DataTable(
						{
							"processing" : true,
							"serverSide" : true,
							"scrollX": true,
							"scrollCollapse": true,
        					"jQueryUI": true,
							"paging" : true,
							"ordering" : false,
							"searching" : false,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "${ctx}/doctorimage/doctorImage-list?doctorid=${doctorid}",
								"type" : "POST",
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data
											.indexOf("&columns"))
											+ data.substring(data
													.indexOf("start") - 1, data
													.indexOf("&search"));
								}
							},
							"columns" : [
									{
										"data" : "doctorName",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "des",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "image",
										"render" : function(data, type, full,
												meta) {
											return data != null ? "<img alt='image' src='"+data+"' width='200' height='200'>" : "";
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {

													var	deleteStr = "<button class='btn' type='button' title='删除' onclick='removeDoctorImage(\""
													+ data
													+ "\")'><i class='icon-trash'></i></button>";
											return "<button class='btn' type='button' title='编辑' onclick='pageGo(\"${ctx}/doctorimage/to-doctorimage-modify?diid="
													+ data
													+ "&doctorid=${doctorid}\")'><i class='icon-pencil'></i></button>"
													+ deleteStr;
									}
									} ],
							"language" : {
								"emptyTable" : "没有数据",
								"info" : "显示 第 _START_ 到 _END_ 条数据  总共 _TOTAL_ 条数据 ",
								"infoEmpty" : "没有数据",
								"infoFiltered" : "(总共  _MAX_ 条数据)",
								"infoPostFix" : "",
								"thousands" : ",",
								"lengthMenu" : "每页显示条数 _MENU_ ",
								"loadingRecords" : "加载中...",
								"processing" : "处理中...",
								"search" : "搜索:",
								"zeroRecords" : "找不到匹配的数据",
								"paginate" : {
									"first" : "首页",
									"last" : "尾页",
									"next" : "下一页",
									"previous" : "上一页"
								}
							}
						});

	});
	function pageGo(href) {
		$("#index_div").load(href);
	}
	
	function removeDoctorImage(id){
		var url = "${ctx}/doctorimage/doctorImage-list?doctorid=${doctorid}";
		$.ajax({
			type : 'POST',
			url : "${ctx}/doctorimage/doctorimage-remove?doctorimageid="+ id,
			dateType : "JSON",
			success : function(result) {

				//从返回的json数据中获取结果信息
				//结果提示信息
				var appResult = eval("(" + result + ")");
				var message = appResult.msg;
				var success = appResult.code;
				if (success == 0) {
					$("#regBox").tips({
						side : 2,
						msg : message,
						bg : '#68B500',
						time : 2
					});
				}else{
					oTable.ajax.url(url).load();
				}
			}
		});
	}
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="main.jsp">主页</a> <span class="divider">/</span></li>
		<li class="active" ><a href="javascript:;" onclick="pageGo('${ctx }/doctor/to-doctor-list')">医生列表</a><span class="divider">/</span></li>
		<li class="active">图文详情列表</li>
	</ul>
	<div class="btn-toolbar">
		<button class="btn" onclick="pageGo('${ctx }/doctorimage/to-doctorimage-add?doctorid=${doctorid }')" type="button">添加图文介绍
		</button>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>所属医生</th>
					<th>图片描述</th>
					<th>图片</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
	
			</tbody>
			
		</table>
	</div>
</body>
</html>
