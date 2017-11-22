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
    	var contenttranspond = $('#tranmicrcontent').text();
    	$('#transpondInfo').val(" //@"+show_name+":"+contenttranspond);

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
   <span id="tranmicrcontent" style="display: none;"><s:property value='transpondIrpMicroblog(microblogid).microblogcontent' escapeHtml="false" /></span>
   
   
  <div>
   <div class="jz4">
     转发到：
     <span style="margin-right: 320px;"><a  href="javascript:void(0)" >我的微知</a></span>
      </div>
 
      <s:if test="micrtranspondid==0">
       <span style="margin-left: 10px;">
         <a target="_bank" class="linkb14" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(microblogid).userid' /> " id="tronspondUser"></a>:
         <s:property value='getMicroblogContent(transpondIrpMicroblog(microblogid).microblogcontent)' escapeHtml="false"  />
       </span>   
       </s:if>
       <s:if test="micrtranspondid!=0">
       <span style="margin-left: 10px;">
          <a target="_bank" class="linkb14" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(micrtranspondid).userid' /> ">@<s:property value="showPageName(transpondIrpMicroblog(micrtranspondid).userid)" /></a>:
          <s:property value='getMicroblogContent(transpondIrpMicroblog(micrtranspondid).microblogcontent)' escapeHtml="false" />
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
           <li><textarea  id="transpondInfo" onkeyup="microblogInfoControltran(this.value)"  name="irpMicroblog.microblogcontent" rows="4"  style="width: 480px;background: rgb(250, 250, 250);height: 60px;" ></textarea></li>
           <li><input type="checkbox" name="" id="microblogTranComment">&nbsp;同时作为给<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(microblogid).userid' />" id="trancommentuser" style="color:rgb(32, 108, 124); "></a>的评论发布</li>
           </ul>    
     </form>
      
     </div>
  </div>
</body>
</html>