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

<title>转发微知</title>

</head>
<body>

<script type="text/javascript">
//转发字数限制
var tranmicronumberwrod = 0;
//转发传递
$(function(){
	
	//配置转发字数限制
	$.ajax({
		type:"post",
		cache:false,
		async:false,
		url:"<%=rootPath%>microblog/initTranMicroblogNumberWord.action",
		success:function(msg){
			tranmicronumberwrod=parseInt(msg);
			$('#microblogContentCount_01_font').text(tranmicronumberwrod);
		}
		
	});
	 
	var show_name = decodeURI('<s:property value='showname'/>');
	 
	$('#tronspondUser').text("@"+show_name);
	$('#trancommentuser').text(show_name);
    if(${micrtranspondid}==0){
    	$('#transpondInfo').val("");
		 
    }else{
    	//var contenttranspond= document.getElementById("tranmicrcontent").childNodes[0].innerHTML;
    	var contenttranspond = $("#tranmicrcontent").html(); 
    	var contranarray = contenttranspond.split("<"); 
    	var contstr_2 = "";  
    	for(var i in contranarray){
    		var disstr_1 = contranarray[i].substring(contranarray[i].indexOf("public/image"),contranarray[i].indexOf(".gif\">")).replace("public/image/","");
    		if(disstr_1!=""){ 
    			var zdystr = zDybath(disstr_1); 
    			contstr_2 += "["+zdystr+"]";
    		}else{ 
    			var constr_3 = contranarray[i]; 
    			constr_3 = constr_3.substring(constr_3.indexOf("@"));
    			constr_3 = constr_3.replace("/a>",""); 
    			contstr_2 += constr_3; 
    		}
    	}  
    	//alert(contstr_2);
    	$('#transpondInfo').val(" //@"+show_name+":"+contstr_2); 
    } 
});
//控制微知内容的长度
function microblogInfoControltran(_microblogcontent){
	if(tranmicronumberwrod-$.trim(_microblogcontent).length>=0){
		$('#microblogContentprompt_02').css({display:'none'});
		$('#microblogContentprompt_01').css({display:'block'});
		$('#microblogContentCount_01_font').text(tranmicronumberwrod-$.trim(_microblogcontent).length);
	}else{
		$('#microblogContentprompt_01').css({display:'none'});
		$('#microblogContentprompt_02').css({display:'block'});
		$('#microblogContentCount_02').text(Math.abs(tranmicronumberwrod-$.trim(_microblogcontent).length));
	}
} 
</script>
	<s:set var="microobj" value="transpondIrpMicroblog(micrtranspondid)" ></s:set>
	
	<s:set var="microcontobj" value="transpondIrpMicroblog(microblogid)" ></s:set>
 
	
   <span id="tranmicrcontent" style="display: none;"><s:property value='#microcontobj.microblogcontent' escapeHtml="false" /></span>
   
   
  <div>
 
 
      <s:if test="micrtranspondid==0">
       <span style="margin-left: 10px;">
        <p> <a target="_blank" class="linkb14" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#microcontobj.userid' /> " id="tronspondUser"></a>:
         	<s:property value='#microcontobj.microblogcontent' escapeHtml="false"  /></p>
         	<p><s:property value='getMicroblogContent(#microcontobj.microblogcontentimg)' escapeHtml="false"  /></p>
       </span>   
       </s:if>
       <s:if test="micrtranspondid!=0">
       <span style="margin-left: 10px;">
          <p><a target="_blank" class="linkb14" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#microobj.userid' /> ">@<s:property value="showPageName(#microobj.userid)" /></a>:
          	<s:property value='#microobj.microblogcontent' escapeHtml="false" /></p>
          	<p>
          	<s:property value='getMicroblogContent(#microobj.microblogcontentimg)' escapeHtml="false"  />
          	
          	</p>
        </span> 
       </s:if>
       <div>
        
      <form method="post" id="transpondcontent1">
      	<input type="hidden" name="microblogid" value="<s:property value='microblogid' />" />
          <ul>   
           <li id="microblogContentprompt_01" style="text-align: right;">
           <s:if test="micrtranspondid==0">
            <input style="display: none;" type="text"  name="irpMicroblog.transpondid" value="<s:property value='microblogid' />" >
           </s:if>
           <s:if test="micrtranspondid!=0">
            <input style="display: none;" type="text"  name="irpMicroblog.transpondid" value="<s:property value='micrtranspondid' />" >
           </s:if>
           <em style="float: right;">还可以输入<label id="microblogContentCount_01_font" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</em></li>
           <li id="microblogContentprompt_02" style="text-align: right;display: none;">超出了<label id="microblogContentCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;" ></label>个字</li>
           <li>
           	<div class="publishercss" id="div_07" >
           		<textarea     style="width:480px;font-size: 14px;" id="transpondInfo" onkeyup="microblogInfoControltran(this.value)"  name="irpMicroblog.microblogcontent"  ></textarea>	
           	</div>
           
           </li>
           <li style="margin-top: 5px;">
           	 
           		<a class="trigger"   comid="transpondInfo"  href="javascript:;" style="font-size: 30px;float: left;">☺</a>
           		 
           		
           		<div style="float: right;">&nbsp;同时作为给<a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#microcontobj.userid' />" id="trancommentuser" style="color:rgb(32, 108, 124); "></a>的评论发布</div>&nbsp;
           		<input style="float: right;margin-top: 3px;" type="checkbox" name="" id="microblogTranComment">
           </li>
           </ul>    
     </form>
      
     </div>
  </div>
</body>
</html>