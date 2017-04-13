<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<script type="text/javascript">
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
							"searching" : true,
							"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
							"paginationType" : "full_numbers",
							"ajax" : {
								"url" : "doctor!listAjax.action",
								"type" : "POST",
								"beforeSend" : function() {
									var data = this.data;
									this.data = data.substring(0, data
											.indexOf("&columns"))
											+ data.substring(data
													.indexOf("start") - 1, data
													.indexOf("&search")+7)+ data
													.substring(
															data
																	.indexOf("&search%5Bvalue%5D") + 18,
															data
																	.indexOf("&search%5Bregex%5D"));
								}
							},
							"columns" : [
									{
										"data" : "doctor_id",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "str_office",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "hospital",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "verify",
										"render" : function(data, type, full,
												meta) {
												if(data == null){
													return "";	
												}
												if(data == 0){
													return "等待验证";	
												}
												if(data == 1){
													return "认证";
												}
												if(data == 2){
													return "拒绝";
												}
										}
									},
									{
										"data" : "str_last_login_time",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "doctor_id",
										"render" : function(data, type, full,
												meta) {

											var deleteStr = "";
											(full.verify == 0) ? deleteStr = "<button class='btn' type='button' title='审核' onclick='pageGo(\"doctor!approval?doctor_id="
													+ data
													+ "&deleted=1\")'><i class='icon-check'></i></button>"
													: deleteStr = "<button class='btn' type='button' title='重新审核' onclick='pageGo(\"doctor!approval?doctor_id="
															+ data
															+ "&verify=0\")'><i class='icon-share'></i></button>";
											return "<button class='btn' type='button' title='查看' onclick='pageGo(\"doctor!showPage?doctor_id="
													+ data
													+ "\")'><i class='icon-eye-open'></i></button>"+"<button class='btn' type='button' title='编辑' onclick='pageGo(\"doctor!updatePage?doctor_id="
													+ data
													+ "\")'><i class='icon-pencil'></i></button>"
													+ "<button class='btn' type='button' title='图文介绍' onclick='pageGo(\"doctorImage!listPage?doctor_id="
													+ data
													+ "\")'><i class='icon-picture'></i></button>" + deleteStr;
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
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="main.jsp">主页</a> <span class="divider">/</span></li>
		<li class="active">医生列表</li>
	</ul>
	<div class="btn-toolbar">
		<button class="btn" onclick="pageGo('doctor!addPage')" type="button">医生申请
		</button>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>医生编号</th>
					<th>医生姓名</th>
					<th>科室</th>
					<th>医院</th>
					<th>是否审核</th>
					<th>上次登录时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
	
			</tbody>
		</table>
	</div>
</body>
</html>
