<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<div class="fenye">
           <span>共 <i><s:property value="#appResult.page_model.rowCount" /></i> 件商品</span>
            <div class="fenye_2">
               <a href="javascript:;" <s:if test="#appResult.page_model.pageNo!=0">onclick="pageGo('<s:property value="#appResult.uri" />?page_no=<fmt:formatNumber value="${appResult.page_model.pageNo-1}" pattern="0" />&page_size=<s:property value="#appResult.page_model.pageSize" />')"</s:if> class="shang"> &lt;上一页</a>
               <div class="fendd_1">
               		<s:iterator begin="1" end="#appResult.page_model.pageCount" step="1"  var="No"  >
               			<a href="javascript:;"  <s:if test="#appResult.page_model.pageNo+1==#No">class="lanse"</s:if> <s:if test="#appResult.page_model.pageNo+1!=#No">onclick="pageGo('<s:property value="#appResult.uri" />?page_no=<fmt:formatNumber value="${No-1}" pattern="0" />&page_size=<s:property value="#appResult.page_model.pageSize" />')"</s:if>><s:property value="#No" /></a>
               			
               		</s:iterator>
               </div>
               <a href="javascript:;" <s:if test="(#appResult.page_model.pageNo+1)!=#appResult.page_model.pageCount">onclick="pageGo('<s:property value="#appResult.uri" />?page_no=<fmt:formatNumber value="${appResult.page_model.pageNo+1}" pattern="0" />&page_size=<s:property value="#appResult.page_model.pageSize" />')"</s:if> class="xia">下一页&gt; </a>
               <span> 共<b><s:property value="#appResult.page_model.pageCount" /></b>页 </span>
               <span class="all_1"> 到第 <input type="text" id="pageTo" value="1"> 页 </span>
               <a href="javascript:;" onclick="pageTo()" class="qding">确定</a>
           </div>
           <script type="text/javascript">
           		function pageTo(){
           			var pageCount =<s:property value="#appResult.page_model.pageCount" />;
           			var r = /^[0-9]*[1-9][0-9]*$/;
           			var pageTo=$("#pageTo").val();
           			if(!r.test(pageTo)){
           				alert('请输入数字');
           				return;
           			}
           			if(pageTo>pageCount){
           				alert('第'+pageTo+'页不存在');
           				return;
           			}
           			pageGo('<s:property value="#appResult.uri" />?page_no='+(pageTo-1)+'&page_size=<s:property value="#appResult.page_model.pageSize" />');	
           		}
           </script>
       </div>