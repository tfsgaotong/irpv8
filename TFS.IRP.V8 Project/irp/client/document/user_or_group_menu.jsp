<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
  <body>
  <script type="text/javascript">
  //全局变量，
	var m_checked = false; 
	function tabs(){
		$('#tab').tabs({
			onSelect:function(title,index){
				var panels = $('#tab').tabs('tabs');
				for(var i=0;i<panels.length;i++){
					var panel = panels[i];
					if(i==index){  
						var paramString1=$.trim($('#transdocument').find('#userIds').val());
						var paramString2=$.trim($('#transdocument').find('#groupIds').val());
						var paramNameStr1=$.trim($('#transdocument').find('#userNames').val());
						var paramNameStr2=$.trim($('#transdocument').find('#groupNames').val());
						var _data="?userIds="+paramString1+"&groupIds="+paramString2+"&groupNames="+paramNameStr2+"&userNames="+paramNameStr1+"&ismaxamount=false";
						var _href=panel.attr('link')+_data;
					   panel.panel('refresh',_href);    
					}else{
						panel.html('');
					}
				}
			}
		});
	}  
function findSelectUserContent(_postData){
	var result = $.ajax({
		type: 'POST',
		url: '<%=rootPath %>user/select_user.action',
	    data: _postData,
	    async: false,
	    cache: false
	}).responseText;
	return result;
}
function jump(_form){    
 	var queryString = _form.serialize();  
		$('#user').panel('refresh',"<%=rootPath %>site/select_user.action?"+queryString);  
}   
 </script> 
   <%--选择用户或者组织转发文档 --%>
   <div id="tab" fit="true" plain="true" style="width: 640px;height: 380px;"> 
		<div id="group" link="<%=rootPath %>site/person_allgroup.action" style="width: 500px; padding: 5px; overflow: auto;" title="个人组织" ></div>
 		<div id="user" link="<%=rootPath %>site/select_user.action" style="width: 500px; padding: 5px; overflow: auto;" title="系统用户"></div> 
     </div> 
  </body>
</html>
