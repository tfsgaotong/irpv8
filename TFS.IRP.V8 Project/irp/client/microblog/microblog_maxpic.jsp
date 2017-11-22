<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
var gochecknum = 1;
var backchecknum = 1;
var picfilenamearraylength = parseInt('<s:property value="picfilenamearray.length" />');
var checkid = parseInt('<s:property value="picfileids" />')+1;
var originalimageswidth = 0; //宽
var originalimagesheight = 0; //高
var imgs;
function Initpic(){
	 imgs = new Image();
	 var disimgsrc =$('#loadimgsrcpicimg').attr("src");
	 var disimgsrcpose = disimgsrc.replace("_150X150","");
	 imgs.src= disimgsrcpose;
	  pretreatmentInit();
}
$(function(){
	Initpic();
	initpicDispose();
	
});
function initpicDispose(){
	originalimageswidth = imgs.width;
	originalimagesheight = imgs.height;
	//竖图
	var proportion;
	if(originalimagesheight>400 && originalimageswidth>580){
		if(originalimagesheight>originalimageswidth){
			var originalimagesheightdispose = originalimagesheight/400;
			$('#loadimgsrcpicimg').css({"height":originalimagesheight/originalimagesheightdispose+"px"});
			$('#loadimgsrcpicimg').css({"width":originalimageswidth/originalimagesheightdispose+"px"});
			
		}else if(originalimagesheight<originalimageswidth){
			var originalimageswidthdispose = originalimageswidth/400;
			$('#loadimgsrcpicimg').css({"height":originalimagesheight/originalimageswidthdispose+"px"});
			$('#loadimgsrcpicimg').css({"width":originalimageswidth/originalimageswidthdispose+"px"});
		}else{
			proportion = originalimagesheight/originalimageswidth;
			$('#loadimgsrcpicimg').css({"height":400/proportion+"px"});
			$('#loadimgsrcpicimg').css({"width":580/proportion+"px"});
		}
	}else if(originalimagesheight>400 && originalimageswidth<580){
		var originalimagesheightdispose = originalimagesheight/400;
		$('#loadimgsrcpicimg').css({"height":originalimagesheight/originalimagesheightdispose+"px"});
		$('#loadimgsrcpicimg').css({"width":originalimageswidth/originalimagesheightdispose+"px"});
	}else if(originalimagesheight<400 && originalimageswidth>580){
		var originalimageswidthdispose = originalimageswidth/580;
		$('#loadimgsrcpicimg').css({"height":originalimagesheight/originalimageswidthdispose+"px"});
		$('#loadimgsrcpicimg').css({"width":originalimageswidth/originalimageswidthdispose+"px"});
	}else if(originalimagesheight==400 && originalimageswidth>580){
		var originalimageswidthdispose = originalimageswidth/580;
		$('#loadimgsrcpicimg').css({"height":originalimagesheight/originalimageswidthdispose+"px"});
		$('#loadimgsrcpicimg').css({"width":originalimageswidth/originalimageswidthdispose+"px"});
	}else if(originalimagesheight>400 && originalimageswidth==580){
		var originalimageswidthdispose = originalimagesheight/400;
		$('#loadimgsrcpicimg').css({"height":originalimagesheight/originalimageswidthdispose+"px"});
		$('#loadimgsrcpicimg').css({"width":originalimageswidth/originalimageswidthdispose+"px"});
	}else{
		$('#loadimgsrcpicimg').css({"width":originalimageswidth+"px"});
		$('#loadimgsrcpicimg').css({"height":originalimagesheight+"px"});
	}
}

function pretreatmentInit(){
	if(window.ActiveXObject) {
		initpicDispose();
	imgs.onreadystatechange = function() {
  		 if(imgs.readyState == "loaded" || imgs.readyState == "complete") {
	    	  imgs.onreadystatechange = null;
	    	  initpicDispose();
				}
			}		
		}else{
			imgs.onload = function() {
				imgs.onload  = null;
				initpicDispose();
		   }
			
		}
}
/**
 * 前进
 */
function goonpic(){
	if(gochecknum==1){
		gochecknum = checkid+1;	
	}else if(gochecknum<picfilenamearraylength){
		gochecknum = gochecknum+1;
	}
	var goonpic = "#"+gochecknum;
	checkid = gochecknum;
	backchecknum = gochecknum;
	var goonpicval =  $(goonpic).attr("src"); 
	if(goonpicval!=undefined){
		$('#loadimgsrcpicimg').attr("src",goonpicval.replace("_150X150",""));
		
		$('#backpiccsspicdiv').bind("onclick","backpic()");
		
		if(gochecknum==picfilenamearraylength){
			
			$('#goonpiccsspicdiv').bind("onclick","");
		}
		
			Initpic();	
	}
	
}
/**
 * 后退
 */
function backpic(){
	if(backchecknum==1){
		if(checkid>1){
			backchecknum = checkid -1;	
		}
	}else if(backchecknum>=2){
		backchecknum = backchecknum - 1;
	}
	var goonpic = "#"+backchecknum;
	checkid = backchecknum;
	gochecknum = backchecknum;
	var goonpicval =  $(goonpic).attr("src");
	if(goonpicval!=undefined){
		$('#loadimgsrcpicimg').attr("src",goonpicval.replace("_150X150",""));
		$('#goonpiccsspicdiv').bind("onclick","goonpic()");
		if(backchecknum<1){
			$('#backpiccsspicdiv').bind("onclick","");
		}
		Initpic();
	}
}
/**
 * 下方点击图片
 */
function searchLoadMicroPics(_src,_id){
	gochecknum = parseInt(_id);
	backchecknum = parseInt(_id);
	if(_id==1){
		checkid = 1;
		$('#backpiccsspicdiv').bind("onclick","");
		$('#goonpiccsspicdiv').bind("onclick","goonpic()");
	}else if(_id==picfilenamearraylength){
		checkid = picfilenamearraylength;
		$('#goonpiccsspicdiv').bind("onclick","");
		$('#backpiccsspicdiv').bind("onclick","backpic()");
	}else{
		$('#goonpiccsspicdiv').bind("onclick","goonpic()");
		$('#backpiccsspicdiv').bind("onclick","backpic()");
	}
	$('#loadimgsrcpicimg').attr("src",_src.replace("_150X150",""));
	Initpic();
} 
/**
 * 查看原图
 */
function checkMaxPicBlank(){
	var _src = $('#loadimgsrcpicimg').attr("src");
	window.open("<%=rootPath%>microblog/findmicroblogoriginalpic.action?originalpic="+_src);
}

</script>
<body style="background-color: black;text-align: center;" onload="Initpic()" >
<div id="loadimgsrcpicimgdiv" style="height: 400;width:600;margin:0 auto;text-align: center;">

<img id="loadimgsrcpicimg"  src="<s:property value="picfile" />"  />
</div>
<div  id="backpiccsspicdiv"  onclick="backpic()" style="height: 400;width:200;margin:0 auto;float: left;margin-top: -400px;position: relative;cursor:url(<%=rootPath%>client/images/micropicicons/cur_next.cur),auto;">
</div>

<div  id="goonpiccsspicdiv" onclick="goonpic()"  style="height: 400;width:200;margin:0 auto;float:right;margin-top: -400px;position: relative;cursor:url(<%=rootPath%>client/images/micropicicons/cur_pre.cur),auto;">
</div>

<div id="bottomimgsdiv">
	<div style="width:600;margin:0 auto;height: 20;text-align: center;"><a style="color:white;" href="javascript:void(0)"  onclick="checkMaxPicBlank()">查看原图</a></div>
	<div style="width:600;margin:0 auto;height: 50;position: relative;text-align: center;">
				<s:iterator status="strpic" value="picfilenamearray">
				 	<span>
				 		<a href="javascript:void(0)"><img id="<s:property value="#strpic.count" />" style="width: 50;height: 50; " alt="" src="<%=rootPath%>file/readfile.action?fileName=<s:property value="picfilenamearray[#strpic.index]" />" onclick="searchLoadMicroPics(this.src,this.id)" ></a>
				 	</span>
				 </s:iterator>
	</div>
</div>
</body>