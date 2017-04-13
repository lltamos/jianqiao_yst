<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<script type="text/javascript">
	$(function() {
		//将menuContainer变成一棵树
		$("#jstree_main").jstree({
			"core" : {
				"themes" : {
					"name" : "default-dark"
				},
				"data" : {
					"url" : "resource!showTree",
					"dataType" : "json",
					"type":"POST"
				},
			},
			"strings" : {
				"Loading ..." : "Please wait ..."
			},
			"plugins" : [ "wholerow", "themes", "html_data","dnd" ]
		}).bind("select_node.jstree", function(event, data) {//节点单击事件  
			var url = data.node.a_attr.url;
			var index_div = $("#index_div");
			if (url == null || url == "") {
				return;
			} else {
				//resourceMain的div加载页面
				index_div.load(url, function() {
					//alert("The last 25 entries in the feed have been loaded");
				});
			}
		});
		//给所有连接target设置成rightFrame
	});
</script>
<div class="sidebar-nav">
	<div id="jstree_main" style="height: 100%">
		<!-- 树开始 -->

		<!-- 树结束-->
	</div>
</div>