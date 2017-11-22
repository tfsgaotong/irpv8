<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>  
<style type="text/css">
.page { text-align: right; padding: 10px 20px; clear:both }
.page label{padding: 2px 5px;}
.page strong,.page span,.page a { border: 1px solid #cad9ea; padding: 2px 5px; font-family: "宋体"; }
.page strong,.page span { margin-right: 3px; font-weight: bold; color: #FFFFFF; background: #cad9ea; }

</style>  
<script type="text/javascript">
//全局变量 
var strquestion ="";
  $(function(){
     $('#checkchannelul').tree({
     		url:'<%=rootPath%>site/to_load_channeltoshow.action?channelid=<s:property value="channelid"/>',  
     		lines:true,
     		onClick:function(node){
     			$('#selecQuestionQuotecontent').panel('refresh','<%=rootPath %>site/siteshowchanneldoc_document.action?channelid=<s:property value="channelid"/>&id='+node.id);
     		},
    		onLoadSuccess:function(node, data){
    			if(!node){
    				var root = $('#checkchannelul').tree('getRoot');
    				$('#checkchannelul').tree('select',root.target);
    				$('#selecQuestionQuotecontent').panel('refresh','<%=rootPath %>site/siteshowchanneldoc_document.action?channelid=<s:property value="channelid"/>&id='+root.id);
    			}
    		}
     });
     
  }); 
 
 </script>  
 <input type="text" style="display: none;" id="alreadyselectedknow" />
<div id="cc" class="easyui-layout" data-options="fit:true" style="width:775;height:500px;">  
    <div data-options="region:'west',title:'知识类型',split:true" style="width:150px;"><ul id="checkchannelul"></ul> </div>
    <div id="selecQuestionQuotecontent" data-options="region:'center',title:'内容'" style="padding:5px;background:#eee;"></div> 
</div>  
