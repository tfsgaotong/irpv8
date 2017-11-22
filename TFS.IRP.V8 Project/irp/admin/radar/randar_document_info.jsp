<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title><s:property value="irps.doctitle"/> </title>  


<style type="text/css">
/*文章分页*/
#page_break {

}
#page_break .collapse {
display: none;
}
#page_break .num {
	padding: 10px 0;
	text-align: center;
}
#page_break .num li{
	display: inline;
	margin: 0 2px;
	padding: 3px 5px;
	border: 1px solid #72BBE6;
	background-color: #fff;
	
	color: #72BBE6;
	text-align: center;
	cursor: pointer;
	font-family: Arial;
	font-size: 12px;
	overflow: hidden;
}
#page_break .num li.on{
	background-color: #72BBE6;
	color: #fff;
	font-weight: bold;
} 
 
</style> 
<link href="<%=rootPath%>/client/css/css.css" rel="stylesheet" type="text/css" /> 
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" /> 
<link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
</head> 
 <body>
 <script type="text/javascript">
function showAllData(){
	var _show=$.trim($('#show').html());
	if(_show=="查看全文"){
		$('#allDoc').show();
		$('#partOfDoc').hide();
		$('#show').html('取消查看全文');
	} 
}
$(document).ready(function(){
	$('#page_break .num li:first').addClass('on');
	$('#page_break .num a').click(function(){
		var _text= $.trim($(this).text()) ;
		var _num=parseInt(0);
		 if(_text=="上一页"){
			 //获取当前选中的li ,然后将它小于1的点击一下，如果当前选中的是1就不执行
			 $('#page_break').find('li').each(function(){ 
					if(this.className=="on"){
						var _id=this.id;
						if(_id==1)return;
						_num=parseInt(_id)-1;
					}	 
		      });  
		 }else if(_text=="下一页"){
			 var len=$('#page_break').find('li').length;
		    $('#page_break').find('li').each(function(){ 
		    	if(this.className=="on"){
					var _id=this.id;
					if(_id==len)return;
					 _num=parseInt(_id)+1;
				}	 
	        });  
		 }
		 $("#page_break").find('#'+_num).click();return ;
	});
	$('#page_break .num li').click(function(){
		//隐藏所有页内容
		$("#page_break div[id='page_1']").hide(); 
		//显示当前页内容。 
		  var li=$('#page_break').find('li');
	      var len= li.length ;
		if ($(this).hasClass('on')) { 
			$('#page_break #page_' + $(this).text()).show();
		} else {
			$('#page_break .num li').removeClass('on');
			$(this).addClass('on');
			$('#page_break #page_' + $(this).text()).fadeIn('normal');
			for(i=1;i<=len;i++){
				if(parseInt($(this).text())==i){}else{
					$("#page_break div[id='page_"+i+"']").hide(); 
				}
			}
		}
		});
	});
</script> 
  <s:include value="/client/include/enterprise_head.jsp"></s:include>
    <div class="area1"></div>
    <div class="zj_wBox">
        <div class="zj_xl_txt"> 
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="olbg7" height="300">
      <tr>
        <td valign="top" class="olbg8">
      <div id="doprint">
        <table width="874" border="0" cellspacing="0" cellpadding="0" class="box doctitle boxcenter dttitle ">
            <tr>
              <td width="874" align="center" class="box doctitle boxcenter dttitle "> 
              <s:property value="irps.doctitle "/>
               </td>
            </tr>
          </table> 
          <div class="date" style="border-bottom: 1px solid;">
            <center>采集时间：<s:property value="irps.systime"/></center>
          </div>
			<div class="documenttxt">
          	<%--锚点 --%><a id="documenttop" name="documenttop"></a> 

		       <div  id="allDoc"  class=" documenttxt">
	   			  	 <s:property value="irps.doccontent" escapeHtml="false" />
	   			</div> 
   		</div>
      </div>
   		<!-- 结束打印 -->
          <div class="dtgjc">来源站点：  
  				<s:property value="irps.sitename"/>
          </div>
          </td>
      </tr>
    </table>      
        </div> 
    </div>
    <s:include value="/client/include/enterprise_foot.jsp"></s:include>
</body>
</html>
 