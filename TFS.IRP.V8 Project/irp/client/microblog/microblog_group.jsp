<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
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
					   panel.panel('refresh',panel.attr('link'));    
					}else{
						panel.html('');
					}
				}
			}
		});
	}  
	function jump(paramString){  
	    var pannel=$('#tab').tabs('getSelected');  
	  	var link=pannel.attr('link'); 
	  	var _cUrl=link+"&"+paramString; 
	  	 pannel.attr('link',_cUrl);     
	    jump(_cUrl);
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
 
 </script> 
   <%--选择用户或者组织转发文档 --%>
   <div id="tab" fit="true" plain="true" style="width: 640px;height: 380px;"> 
		<div id="group" link="<%=rootPath %>site/person_allgroup.action" style="width: 500px; padding: 5px; overflow: auto;" title="个人组织" ></div> 
     </div> 