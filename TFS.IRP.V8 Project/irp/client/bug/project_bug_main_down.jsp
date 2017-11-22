<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<style type="text/css">
.div_one_tab{
	float: left;
	width: 25%;
	line-height: 40px;
	text-align: center; 
	background: url("<%=rootPath%>client/images/search_bug.gif"); 
	}
.div_one_tab_border{
	border: thin solid #F0F0F0;
}

.fyhb{margin:0px auto 10px 15px; clear:both;}
 .fyhb ul{width:100%;margin:10px auto 20px auto; display:block; text-align:right;}
 .fyhb ul span{color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
 .fyhb ul a {color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
 .fyhb ul a:hover{color:#333; padding:2px 8px; border:1px solid #c8c8c8;background:#ededed;}
</style>
</head>
  
  <body>
    <form id="pageFormBottom">
	<s:hidden name="pageNumBottom" id="pageNumBottom" />
	<s:hidden name="pageSizeBottom" id="pageSizeBottom"/>
	<s:hidden name="projectIdBottom" id="projectIdBottom"/>
  </form>
    <br/>
    <div id="inforight" style="float: left;margin-top: 5px;width: 100%;">
        <div style="line-height:237%; font-size: 15px;font-weight: bold;background: url(<%=rootPath%>client/images/common.gif) no-repeat left center;background-position: -474px -286px;font-size: 15px;font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最新Bug</div>
			<div style="float: left;width: 100%;background:#ddedfe;font-weight: bold;border-bottom: 2px solid #fff;">
				<div class="xm_01" style="width: 6%;text-align: center;">序号</div>
				<div class="xm_01" style="width: 61%;text-align: left;margin-left: 20px;">标题</div>
				<div class="xm_01" style="width: 10%;text-align: center;">优先级</div>
				<div class="xm_01" style="width: 20%;text-align: center;">分配人</div> 
			</div><br/>
			<s:iterator var="bug" value="newestBugs" status="status">
			<s:set var="pri" value="#bug.priority"></s:set>
			<div style="float: left;width: 100%;background:#f6f6f6;font-weight: bold;border-bottom: 2px solid #fff;">
				<div class="xm_01" style="width: 6%;text-align: center;"><s:property value="(pageNumBottom-1)*pageSizeBottom+#status.count"/></div>
				<div class="xm_01" style="width: 61%;text-align: left;color: #1077B5;margin-left: 20px;"><a title="<s:property value='#bug.title' />" onclick="bugInfo(<s:property value='#bug.serianum' />)" href="javascript:void(0)"><s:property value="#bug.title" /></a></div>
				<div class="xm_01"  <s:if test=" #pri==1 ">style="width: 10%;text-align: center;color: #1077B5;"</s:if><s:elseif test=" #pri==2 ">style="width: 10%;text-align: center;color: #1077B5;"</s:elseif><s:else>style="width: 10%;text-align: center;color: red;"</s:else></div><s:property value="priorityMap.get(#bug.priority)" /></div>
				<div class="xm_01" style="width: 20%;text-align: center;color: #1077B5;"><s:property value="founderNameMap.get(#bug.founderid)" /></div>
			</div>
 			</s:iterator>
 				<div class="fyhb" ><ul><s:property value="pageHTMLBottom" escapeHtml="false"/></ul></div>
	<script type="text/javascript">
	/**
	 * 分页
	 */
	function pageNavigainBottom(_nPageNum){
		//alert('进入分页方法');
		$('#pageNumBottom').val(_nPageNum);
		var queryString = $('#pageFormBottom').serialize();
		//alert(queryString);
		var urlStr="<%=rootPath %>bug/topagestatisticsdown.action?"+queryString+"&queryType=1";
		//alert(urlStr);
		$.ajax({
			type:"post",
			url:urlStr,
			async:false,
			cache:false,
			success:function(html){
				if(html!=null){
					//alert('分页完成');
					$('#buginfobottom').html(html);
				}
			}
		});	
	}
	
	$(function (){
		var size=<s:property  value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('BUG_SHOW_TOW_COUNT')" />;
		$('#pageSizeBottom').val(size);
	});
	</script>
    </div>
  </body>
</html>
