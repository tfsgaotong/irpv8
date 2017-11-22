<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>意见反馈</title>
<script type="text/javascript">
var tranmicronumberwrod="300";
$(function(){
	$("#complaindesclength").text(tranmicronumberwrod);
	var mictxt='<s:property value="title"/>'+'<s:property value="option"/>';
	$("#complaindesc").text(mictxt);
})

//控制反馈内容的长度
function complainInfoControl(_microblogcontent){
	if(tranmicronumberwrod-$.trim(_microblogcontent).length>=0){
		$('#moreli').css({display:'none'});
		$('#goodli').css({display:'block'});
		$('#complaindesclength').html(tranmicronumberwrod-$.trim(_microblogcontent).length);
	}else{
		$('#goodli').css({display:'none'});
		$('#moreli').css({display:'block'});
		$('#complaindescmorelength').html(Math.abs(tranmicronumberwrod-$.trim(_microblogcontent).length));
	}
}
</script>
</head>
<body>

     
  <div style="padding: 10px;">
     <s:form action="" id="addComform" theme="simple">	
         <ul> 
               <li>
                	<div style="font-size: 16px;"> 
              	 	<s:if test="myagree>0">
	                                                   成功支持了 <s:if test="myagree==1"><font color="red">红方</font></s:if><s:else><font color="blue">蓝方</font></s:else> 观点  
             	  	</s:if>
	                 <s:else>
	                                                    你已经给<s:if test="myagree.substring(1,2)==1"><font color="red">红方</font></s:if><s:if test="myagree.substring(1,2)==2"><font color="blue">蓝方</font></s:if>阵营了
	                 </s:else>
               	 </div>
               </li>
               <li id="goodli">
                 <div>
	                 <s:if test="myagree>0">
	                                                     发表给  <s:if test="myagree==1"><font color="red">红方</font></s:if><s:elseif test="myagree==2"> <font color="blue">蓝方</font></s:elseif>阵营加吧劲吧
	                 </s:if>
	                 <s:else>
	                                                    继续为 <s:if test="myagree.substring(1,2)==1"><font color="red">红方</font></s:if><s:if test="myagree.substring(1,2)==2"><font color="blue">蓝方</font></s:if>阵营加吧劲吧
	                 </s:else>
                 </div>
              </li>
              <li id="moreli" style="text-align: right;display: none;">超出了<label id="complaindescmorelength" style="font-family:Tahoma,Arial,sans-serif; font-size: 12px;color:red;" ></label>个字</li>
	          <li>
	             <textarea id="complaindesc" onkeyup="complainInfoControl(this.value)"  rows="6"  style="font-size: 14px; width: 480px;background: rgb(250, 250, 250);height: 120px;" ></textarea>
	          </li>
	    </ul>    
    </s:form>	
  </div>
</body>
</html>