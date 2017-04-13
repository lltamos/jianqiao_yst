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
								"url" : "videoInfo!listAjax.action",
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
										"data" : "id",
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
										"data" : "str_tag_name",
										"render" : function(data, type, full,
												meta) {
											return data != null ? data : "";
										}
									},
									{
										"data" : "deleted",
										"render" : function(data, type, full,
												meta) {
											if(data==0){
												return "否";
											}
											if(data==1){
												return "是";
											}
											if(data==null){
												return "";
											}
										}
									},
									{
										"data" : "id",
										"render" : function(data, type, full,
												meta) {

											var deleteStr = "";
											deleteStr = "<button class='btn' type='button' title='删除' onclick='pageGo(\"videoInfo!deleteAjax?id="
													+ data
													+ "\")'><i class='icon-trash'></i></button>"+"<button class='btn' type='button' title='评论管理' onclick='pageGo(\"comment.action?comment_for_type=3&comment_for_id="
													+ data
													+ "\")'><i class='icon-comment'></i></button>";
											return "<button class='btn' type='button' title='编辑' onclick='pageGo(\"videoInfo!updatePage?id="
													+ data
													+ "\")'><i class='icon-pencil'></i></button>"
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
</script>
<body>
	<ul class="breadcrumb">
		<li><a href="main.jsp">主页</a> <span class="divider">/</span></li>
		<li class="active">视频列表</li>
	</ul>
	<div class="btn-toolbar">
		<button class="btn" onclick="pageGo('videoInfo!addPage')" type="button">添加视频
		</button>
	</div>
	<div class="container-fluid">
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>视频编号</th>
					<th>视频名称</th>
					<th>所属标签</th>
					<th>是否删除</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
	
			</tbody>
		</table>
	</div>
</body>
</html>
