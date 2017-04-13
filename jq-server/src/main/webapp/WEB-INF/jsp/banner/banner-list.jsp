<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	$(function() {

		$('#table')
				.dataTable(
						{
							ajax : {
								type : "POST",
								url : "${cxt}/banner/get-bannerlist",
								dataSrc : "data",
							},
							ordering : false,
							jQueryUI : true,
							paging : true,
							searching : false,
							lengthMenu : [ 5, 10, 25, 50, 75, 100 ],
							columns : [
									{
										data : 'id'
									},
									{
										data : 'descs'
									},
									{
										data : 'address'
									},
									{
										data : 'id',

										render : function(data, type, full,
												meta) {

											var deleteStr = "";
											deleteStr = "<button class='btn' type='button' title='删除' onclick='operate("
													+ data
													+ ")'><i class='icon-trash'></i></button>";
											return "<button class='btn' type='button' title='编辑' onclick='pageGo(\"${ctx }/banner/to-banneradd?id="
													+ data
													+ "\")'><i class='icon-pencil'></i></button>"
													+ deleteStr;
										}
									} ],

							columnDefs : [

							{
								"targets" : [ 2 ],

								"render" : function(data, type, full) { // 返回自定义内容

									return "<img style='height: 120px;width: 220px' src=${img_service }/"+data+">";
								}
							} ],

							language : {
								"sProcessing" : "处理中...",
								"sLengthMenu" : "显示 _MENU_ 项结果",
								"sZeroRecords" : "没有匹配结果",
								"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
								"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
								"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
								"sInfoPostFix" : "",
								"sSearch" : "搜索:",
								"sUrl" : "",
								"sEmptyTable" : "表中数据为空",
								"sLoadingRecords" : "载入中...",
								"sInfoThousands" : ",",
								"oPaginate" : {
									"sFirst" : "首页",
									"sPrevious" : "上页",
									"sNext" : "下页",
									"sLast" : "末页"
								},
								"oAria" : {
									"sSortAscending" : ": 以升序排列此列",
									"sSortDescending" : ": 以降序排列此列"
								}
							}

						});

	});
	function operate(datas) {
		$("#index_div").load("${ctx }/banner/delete?id=" + datas);
	}
</script>

<body>


	<ul class="breadcrumb">
		<li><a href="${ctx }/user/user-index">主页</a> <span
			class="divider">/</span></li>
		<li class="active">首页宣传图列表</li>
	</ul>
	<div class="btn-toolbar">
		<button class="btn" onclick="pageGo('${ctx }/banner/to-banneradd')"
			type="button">添加宣传图</button>
	</div>
	<div class="container-fluid">
		<table id="table" class="display">
			<thead>
				<tr>
					<th>序号</th>
					<th>文字描述</th>
					<th>图片</th>
					<th>操作</th>
				</tr>

			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</body>
