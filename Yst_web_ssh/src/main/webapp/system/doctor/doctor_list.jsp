<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<html>
<!-- 医生列表 -->
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
										"data" : "doctor_id",
										"render" : function(data, type, full,
												meta) {

											return "<button class='btn' type='button' title='查看' onclick='pageGo(\"doctor!showPage?doctor_id="
													+ data
													+ "\")'><i class='icon-eye-open'></i></button>"+"<button class='btn' type='button' title='禁用' onclick='pageGo(\"doctor!updatePage?doctor_id="
													+ data
													+ "\")'><i class='icon-pencil'></i></button>";
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
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>医生账号</th>
					<th>工作单位</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
	
			</tbody>
		</table>
	</div>
</body>
</html>
