<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>选择栏目</title>
  </head> 
  <body> 
  <script type="text/javascript"> 
  function findALlGroupName(_sAllName, _node){
		var parent = $('#checkchannelul').tree('getParent', _node.target);
		if(parent){
			_sAllName = parent.text+"\\"+_sAllName;
			return findALlGroupName(_sAllName, parent);
		}else{
			return _sAllName;
		}
	}

   $(function(){

      var _siteid=<s:property value='siteid'/>;
      $('#checkchannelul').tree({
    	url:'<%=rootPath%>site/to_stat_site_to_check_channel.action?siteid='+_siteid,
  		animate:false,
  		lines:true,
  		checkbox:true,
  		cascadeCheck:false,
  		onLoadSuccess:function(node, data){
  			if(!node){
  				var root = $('#checkchannelul').tree('getRoot');
  				$(root.target).find(".tree-checkbox").removeClass("tree-checkbox tree-checkbox0");
  				$('#checkchannelul').tree('expand',root.target);
  			}
  		},onCheck:function(node, checked){  			
  			if(checked){
				var sid=$("#parentId").val();
				var allName = findALlGroupName(node.text, node);
				var pare="";
				if(sid=="a"){
					pare=node.id;
				}else if(sid!=node.id){
					if(sid==""){
						pare=node.id;	
					}else{
						pare=sid+","+node.id;
					}					
				}
				$("#parentId").val(pare); 
			}else{
				var sid=$("#parentId").val();
				var strs= new Array();
				strs=sid.split(",");
				var pare="";
				for(var p in strs){
					
					if(strs[p]!=node.id){
						if(pare==""){
							pare=strs[p]
						}else{
						pare=strs[p]+","+pare;
						}
					}
				}
				$("#parentId").val(pare); 
			}
  		}

  	});
  }); 
  </script> 
  <form id="addUserForm" method="post"> 
	   <input type="hidden" id="parentId"  name="channelid" value="a"/>
       <ul id="checkchannelul"></ul> 
  </form>
        
  </body>
</html>
