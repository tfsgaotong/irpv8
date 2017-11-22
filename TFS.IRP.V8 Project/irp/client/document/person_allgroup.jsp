<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
 
  <body> 
   <script type="text/javascript">
   	//全选
	function checkAll(){
		m_checked = !m_checked;
		var checks = $('#groupTable').find('input[name="groupids"]');
		for(var i=0;i<checks.length;i++){
			if(checks[i] && checks[i].checked!=m_checked){
				checks[i].checked = m_checked;
				checkClick(checks[i]);
			}
		}
	}
   //记录选择用户
	function checkClick(checkBox){ 
		var sUserIds = $('#groupForm').find('input[name="groupIds"]').val();
		var sUserNames = $('#groupForm').find('input[name="groupNames"]').val();
		var nVal = $(checkBox).val();
		var sName = $(checkBox).attr('_groupname'); 
		var arrIds;
		var arrNames;
		if(sUserIds){
			arrIds = sUserIds.split(',');
		}else{
			arrIds = new Array();
		}
		if(sUserNames){
			arrNames = sUserNames.split(',');
		}else{
			arrNames = new Array();
		}
				
		if(checkBox.checked){ 
			if(${maxAddUserNum}>0 && arrIds.length>=${maxAddUserNum}){
				checkBox.checked = false;
				return false;
			}
			arrIds.push(nVal);
			arrNames.push(sName);
		}else{
			arrIds.splice($.inArray(nVal, arrIds), 1);
			arrNames.splice($.inArray(sName, arrNames), 1);
		}
		$('#groupForm').find('input[name="groupIds"]').val(arrIds.join(','));
		$('#groupForm').find('input[name="groupNames"]').val(arrNames.join(',')); 
		$('#transdocument').find('#groupIds').val(arrIds.join(',')); 
		$('#transdocument').find('#groupNames').val(arrNames.join(',')); 
	}
   </script>
<form id="groupForm">
	<s:hidden name="groupIds" id="groupIds" />   
	<s:hidden name="groupNames" id="groupNames" /> 
</form>

<s:if test="irpGroups.size()==0">
无分组,<a	href="<%=rootPath %>user/user_group.action" class="linkbh14">创建</a>分组？
</s:if><s:else>
<a href="javascript:void(0);" onclick="checkAll()">全选</a>
</s:else>
  <s:set var="arr" value='groupIds.split(",")' />
     <table id="groupTable"  width="100%" border="0" cellspacing="0" cellpadding="0" class="olbg11">
                  <tr class="main_qbut">   
  		 			 <s:iterator  value="irpGroups" status="listStat">
		  				 <td width="20%" style="padding-top: 5px;" align="left">  
				  				 <input name="groupids"  type="checkbox" value="<s:property value='groupid'/>" id="<s:property value='groupid'/>"
				  				  <s:if test='@org.apache.commons.lang.ArrayUtils@contains(#arr,""+groupid)'>checked="true"</s:if> onchange="checkClick(this)" 
				  				  _groupname="<s:property value="groupname" />" 
				  				  >  <s:property value="groupname"/>
		  				 </td>
		  			     <s:set var="amount" value="#amount-1"/> 
	  					 <s:if test="#listStat.count%4==0"> 
	  						<s:set var="amount" value="4"/>
	  		 				</tr><tr class="main_qbut">
	  					</s:if> 
	  			 </s:iterator>   
                </tr>
              </table> 
  
  </body>
</html>
