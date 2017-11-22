<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">


var gochecknum = 1;
var backchecknum = 1;
var picfilenamearraylength = parseInt('<s:property value="picfilenamearray.length" />');
var checkid = '<s:property value="picfileids" />';
var checkiddis = "";
var originalimageswidth = 0; //宽
var originalimagesheight = 0; //高
var imgs;
var disposedivimg ='#loadimgsrcpicimg<s:property value="picfilenamelist.toString().substring(0,17)" />';
var peridss = "";
var loadimgsrcpicimgdiv ='#loadimgsrcpicimgdiv<s:property value="picfilenamelist.toString().substring(0,17)" />';
var backpiccsspicdiv ='#backpiccsspicdiv<s:property value="picfilenamelist.toString().substring(0,17)" />';
var goonpiccsspicdiv ='#goonpiccsspicdiv<s:property value="picfilenamelist.toString().substring(0,17)" />';
var bottomimgsdiv = '#bottomimgsdiv<s:property value="picfilenamelist.toString().substring(0,17)" />';

var isfanconut = "";
var dispselistone = "";
var dispselisttwo = "";

	
function Initpic(){
	 imgs = new Image();
	 var disimgsrc=$("#"+microblogidpage+"div").find(disposedivimg).attr("src");
	 if(disimgsrc.length>0){
		 var disimgsrcpose = disimgsrc.replace("_150X150","");
		 imgs.src= disimgsrcpose;
		 pretreatmentInit();
	 }

}
$(function(){
	Initpic();
	
	
	
});


function pretreatmentInit(){
	//imgDisplayView();
	
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
		initpicDispose();
		//$("#bottomimgsdiv").fadeIn("slow");
}

function findASId(_ids,_perids,_onlength){
	
	
	checkid = _ids;
	
	
	checkiddis = _ids.toString().substring(0,17);
	
	picfilenamearraylength = parseInt(_onlength);
	
	peridss = _perids;
	disposedivimg =  '#loadimgsrcpicimg'+_perids;
	
	loadimgsrcpicimgdiv = '#loadimgsrcpicimgdiv'+_perids;
	backpiccsspicdiv = '#backpiccsspicdiv'+_perids;
	goonpiccsspicdiv = '#goonpiccsspicdiv'+_perids;
	isfanconut = "ISL_Cont"+_perids;
	dispselistone = "List1"+_perids;
	dispselisttwo =	"List2"+_perids;
	bottomimgsdiv = '#bottomimgsdiv'+_perids;
					
}
/**
 * 前进
 */
function goonpic(){
	
	var inputidvs = "#inputpicids"+peridss;
	checkid = $("#"+microblogidpage+"div").find(inputidvs).val();
	checkid = checkiddis+checkid.toString().substring(17,checkid.length);
	var dispstr = checkid.toString().substring(17,checkid.length);

	

	if(dispstr<picfilenamearraylength-1){
		gochecknum = parseInt(dispstr)+1;	
	}else{
		$("#"+microblogidpage+"div").find(goonpiccsspicdiv).css("cursor","");
		
		return false;
	}
	

	
	var ckids = checkid.toString().substring(0,17)+gochecknum;
	var goonpic = "#"+checkid.toString().substring(0,17)+gochecknum;
	checkid = ckids;
	
	$("#"+microblogidpage+"div").find(inputidvs).val(checkid);
	
	backchecknum = gochecknum;
	var goonpicval =  $(goonpic).attr("src");
	if(goonpicval!=undefined){
		
		imgDisplayView();
		$("#"+microblogidpage+"div").find(backpiccsspicdiv).bind("onclick","backpic()");
		
		if(gochecknum==picfilenamearraylength){
			
			$("#"+microblogidpage+"div").find(goonpiccsspicdiv).bind("onclick","");
			
		}
		$("#"+microblogidpage+"div").find(disposedivimg).attr("src",goonpicval.replace("_150X150",""));
		
		
		Initpic();
	}
}
/**
 * 后退
 */
function backpic(){
	var inputidvs = "#inputpicids"+peridss;
	checkid = $("#"+microblogidpage+"div").find(inputidvs).val();

	checkid = checkiddis+checkid.toString().substring(17,checkid.length);
	var dispstr = checkid.toString().substring(17,checkid.length);
	

	if(dispstr>0){
		backchecknum = parseInt(dispstr) -1;
	}else{
		return false;
	}
	var ckids = checkid.toString().substring(0,17)+backchecknum;
	var goonpic = "#"+checkid.toString().substring(0,17)+backchecknum;
	
	checkid = ckids;
	gochecknum = backchecknum;
	
	$("#"+microblogidpage+"div").find(inputidvs).val(checkid);
	var goonpicval =  $(goonpic).attr("src");
	if(goonpicval!=undefined){
		imgDisplayView();
		$("#"+microblogidpage+"div").find(disposedivimg).attr("src",goonpicval.replace("_150X150",""));
		
		$("#"+microblogidpage+"div").find(goonpiccsspicdiv).bind("onclick","goonpic()");
		if(backchecknum<1){
			$("#"+microblogidpage+"div").find(backpiccsspicdiv).bind("onclick","");
		}
		Initpic();
	}
}
/**
 * 下方点击图片
 */
function searchLoadMicroPics(_src,_id){
	var inputidvs = "#inputpicids"+peridss;
	var disck = _id.toString().substring(17,_id.length);
	
	disck = parseInt(disck)-1;
	gochecknum = parseInt(disck);
	backchecknum = parseInt(disck);
	
	
	checkid = _id;
	$(inputidvs).val(checkid);
	
	var trueid = parseInt(disck+1);
	
	
	if(trueid==0){
		$("#"+microblogidpage+"div").find(backpiccsspicdiv).bind("onclick","");
		$("#"+microblogidpage+"div").find(goonpiccsspicdiv).bind("onclick","goonpic()");

	}else if(trueid==picfilenamearraylength-1){
		$("#"+microblogidpage+"div").find(goonpiccsspicdiv).bind("onclick","");
		$("#"+microblogidpage+"div").find(backpiccsspicdiv).bind("onclick","backpic()");

	}else{
		$("#"+microblogidpage+"div").find(goonpiccsspicdiv).bind("onclick","goonpic()");
		$("#"+microblogidpage+"div").find(backpiccsspicdiv).bind("onclick","backpic()");

	}
	imgDisplayView();
	$("#"+microblogidpage+"div").find(disposedivimg).attr("src",_src.replace("_150X150",""));
	Initpic();
} 
//效果 显示 
function imgDisView(){
    var trueid = parseInt(checkid.toString().substring(17,checkid.length));
	if(trueid==0){
		$("#"+microblogidpage+"div").find(backpiccsspicdiv).css("cursor","");
		$("#"+microblogidpage+"div").find(goonpiccsspicdiv).css("cursor","url(<%=rootPath%>client/images/micropicicons/cur_pre.cur),auto");
	}else if(trueid==picfilenamearraylength-1){
		$("#"+microblogidpage+"div").find(goonpiccsspicdiv).css("cursor","");
		$("#"+microblogidpage+"div").find(backpiccsspicdiv).css("cursor","url(<%=rootPath%>client/images/micropicicons/cur_next.cur),auto");
	}else{
		$("#"+microblogidpage+"div").find(goonpiccsspicdiv).css("cursor","url(<%=rootPath%>client/images/micropicicons/cur_pre.cur),auto");
		$("#"+microblogidpage+"div").find(backpiccsspicdiv).css("cursor","url(<%=rootPath%>client/images/micropicicons/cur_next.cur),auto");
	}
         
		$("#"+microblogidpage+"div").find(loadimgsrcpicimgdiv).css("min-height","");
		$("#"+microblogidpage+"div").find(loadimgsrcpicimgdiv).fadeIn();
		$("#"+microblogidpage+"div").find(disposedivimg).fadeIn();
}
/**
 * 查看原图
 */
function checkMaxPicBlank(){
	var _src = $(disposedivimg).attr("src");
	window.open("<%=rootPath%>microblog/findmicroblogoriginalpic.action?originalpic="+_src);
}
function initpicDispose(){
	
	originalimageswidth = imgs.width;
	originalimagesheight = imgs.height;
	//竖图
	
	if(originalimageswidth<600){
		//宽度小于600 显示原图
		$("#"+microblogidpage+"div").find(disposedivimg).css({"height":originalimagesheight+"px"});
		$("#"+microblogidpage+"div").find(disposedivimg).css({"width":originalimageswidth+"px"});
		var disposeheght = parseInt(originalimagesheight);
		$("#"+microblogidpage+"div").find(backpiccsspicdiv).css({
			"height":disposeheght+"px",
			"margin-top":parseInt(-disposeheght)+"px"
		});
		$("#"+microblogidpage+"div").find(goonpiccsspicdiv).css({
			"height":disposeheght+"px",
			"margin-top":parseInt(-disposeheght)+"px"
		});
	}else{
		//宽度大于600 显示处理过的图片
		if(originalimageswidth==originalimagesheight){
			//正方形
			$("#"+microblogidpage+"div").find(disposedivimg).css({"height":600+"px"});
			$("#"+microblogidpage+"div").find(disposedivimg).css({"width":600+"px"});

			var disposeheght =600;
			$("#"+microblogidpage+"div").find(backpiccsspicdiv).css({
				"height":disposeheght+"px",
				"margin-top":parseInt(-disposeheght)+"px"
			});
			$("#"+microblogidpage+"div").find(goonpiccsspicdiv).css({
				"height":disposeheght+"px",
				"margin-top":parseInt(-disposeheght)+"px"
			});
		}else{
			//高大于宽 宽大于高
			
			$("#"+microblogidpage+"div").find(disposedivimg).css({"width":"600px"});
			var heights = 600*parseInt(originalimagesheight)/parseInt(originalimageswidth);
			$("#"+microblogidpage+"div").find(disposedivimg).css({"height":heights+"px"});
			
			var disposeheght = parseInt(heights);
			$("#"+microblogidpage+"div").find(backpiccsspicdiv).css({
				"height":disposeheght+"px",
				"margin-top":parseInt(-disposeheght)+"px"
			});
			$("#"+microblogidpage+"div").find(goonpiccsspicdiv).css({
				"height":disposeheght+"px",
				"margin-top":parseInt(-disposeheght)+"px"
			});
		}

	}
	imgDisView();
	$("#"+microblogidpage+"div").find(bottomimgsdiv).fadeIn("slow");
}

//效果 隐藏
function imgDisplayView(){
		$("#"+microblogidpage+"div").find(loadimgsrcpicimgdiv).css("display","block");
		
		var yuanpicheight = $("#"+microblogidpage+"div").find(loadimgsrcpicimgdiv).css("height");
	
		$("#"+microblogidpage+"div").find(loadimgsrcpicimgdiv).css("min-height",yuanpicheight);
		
		$("#"+microblogidpage+"div").find(disposedivimg).css("display","none");
}

//图片滚动列表
var Speed = 0.01;//速度(毫秒)
var Space = 2;//每次移动(px)
var PageWidth = 60;//翻页宽度
var fill = 0;//整体移位
var MoveLock = false;
var MoveTimeObj;
var Comp = 0;
var AutoPlayObj = null;

	dispselistone = 'List1<s:property value="picfilenamelist.toString().substring(0,17)" />';
	dispselisttwo =	'List2<s:property value="picfilenamelist.toString().substring(0,17)" />';
	isfanconut = 'ISL_Cont<s:property value="picfilenamelist.toString().substring(0,17)" />';
	
	$("#"+microblogidpage+"div").find("#"+dispselistone).attr("id",microblogidpage+dispselistone);
	$("#"+microblogidpage+"div").find("#"+dispselisttwo).attr("id",microblogidpage+dispselisttwo);
	$("#"+microblogidpage+"div").find("#"+isfanconut).attr("id",microblogidpage+isfanconut);

	
	if(parseInt('<s:property value="picfilenamearray.length" />')>4){
	
		GetObj(dispselisttwo).innerHTML = GetObj(dispselistone).innerHTML;
	}else{
		
		var toppages = '#toppages<s:property value="picfilenamelist.toString().substring(0,17)" />';
		var bottompages = '#bottompages<s:property value="picfilenamelist.toString().substring(0,17)" />';
		$(toppages).attr({
			"onmouseout":"",
			"onmouseup":"",
			"onmousedown":""
		});
		$(bottompages).attr({
			"onmouseout":"",
			"onmouseup":"",
			"onmousedown":""
		});
	}
	
	GetObj(isfanconut).scrollLeft = fill;
	GetObj(isfanconut).onmouseover = function(){
		clearInterval(AutoPlayObj);
	}
	GetObj(isfanconut).onmouseout = function(){
		AutoPlay();
	}
	

	
function GetObj(objName){
	objName = microblogidpage+objName;
	if(document.getElementById){
		return eval('document.getElementById("'+objName+'")');
	}else{
		return eval('document.all.'+objName);
	}
}

function AutoPlay(){ //自动滚动
	//clearInterval(AutoPlayObj);
	//AutoPlayObj = setInterval('ISL_GoDown();ISL_StopDown();',2000);//间隔时间
}

function ISL_GoUp(){ //上翻开始

	if(MoveLock) return;
	clearInterval(AutoPlayObj);
	MoveLock = true;
	MoveTimeObj = setInterval('ISL_ScrUp();',Speed);
}

function ISL_StopUp(){ //上翻停止
	clearInterval(MoveTimeObj);
	if(GetObj(isfanconut).scrollLeft % PageWidth - fill != 0){
		Comp = fill - (GetObj(isfanconut).scrollLeft % PageWidth);
		CompScr();
	}else{
		MoveLock = false;
	}
	AutoPlay();
}

function ISL_ScrUp(){ //上翻动作
	if(GetObj(isfanconut).scrollLeft <= 0){
		GetObj(isfanconut).scrollLeft = GetObj(isfanconut).scrollLeft + GetObj(dispselistone).offsetWidth
	}
		GetObj(isfanconut).scrollLeft -= Space ;
}

function ISL_GoDown(){ //下翻
	clearInterval(MoveTimeObj);
	if(MoveLock) return;
	clearInterval(AutoPlayObj);
	MoveLock = true;
	ISL_ScrDown();
	MoveTimeObj = setInterval('ISL_ScrDown()',Speed);
}
function ISL_StopDown(){ //下翻停止
	clearInterval(MoveTimeObj);
	if(GetObj(isfanconut).scrollLeft % PageWidth - fill != 0 ){
		Comp = PageWidth - GetObj(isfanconut).scrollLeft % PageWidth + fill;
		CompScr();
	}else{
		MoveLock = false;
	}
	AutoPlay();
}

function ISL_ScrDown(){ //下翻动作
	if(GetObj(isfanconut).scrollLeft >= GetObj(dispselistone).scrollWidth){
		GetObj(isfanconut).scrollLeft = GetObj(isfanconut).scrollLeft - GetObj(dispselistone).scrollWidth;
	}
	GetObj(isfanconut).scrollLeft += Space ;
}

function CompScr(){
	var num;
	if(Comp == 0){
		MoveLock = false;return;
	}
	if(Comp < 0){ //上翻
		if(Comp < -Space){
			Comp += Space;
			num = Space;
		}else{
			num = -Comp;
			Comp = 0;
		}
		GetObj(isfanconut).scrollLeft -= num;
		setTimeout('CompScr()',Speed);
	}else{ //下翻
		if(Comp > Space){
			Comp -= Space;
			num = Space;
		}else{
			num = Comp;
			Comp = 0;
		}
		GetObj(isfanconut).scrollLeft += num;
		setTimeout('CompScr()',Speed);
	}
}
	/**
	* 收起图片显示div
	*/	
	function showDivByViewMicro(_picnames,_bid){
	
		var shownobiaoshis = "#shownobiaoshis"+microblogcontentxfid;
		$("#"+microblogidpage+"div").find(shownobiaoshis).remove();
		$("#"+microblogidpage+"div").find("img[name='"+$.trim(_picnames)+"']").fadeIn();
		
		
	
		if(micropictranids==1){
		var imagesdiv =	"#microcontents"+microblogcontentxfid;
		}else if(micropictranids==2){
		var imagesdiv =	"#microcontentstran"+microblogcontentxfid;
		}
		
		
		
		$("#"+microblogidpage+"div").find(imagesdiv).attr("onclick","checkMaxPic('m_pic','m_pic',"+microblogcontentxfid+","+micropictranids+")");
		
		microimagescontent = "";
		microblogimagesnamesv = "";
		
		//初始化所有属性
		fill = 0;//整体移位
		MoveLock = false;
		MoveTimeObj;
		Comp = 0;
		AutoPlayObj = null;
		 gochecknum = 1;
		 backchecknum = 1;
		 picfilenamearraylength = parseInt('<s:property value="picfilenamearray.length" />');
		 checkid = '<s:property value="picfileids" />';
		 checkiddis = "";
		 originalimageswidth = 0; //宽
		 originalimagesheight = 0; //高
		 imgs;
		 disposedivimg ="";
		 peridss = "";
		 loadimgsrcpicimgdiv ="";
		 backpiccsspicdiv ="";
		 goonpiccsspicdiv ="";
		
		 isfanconut = "";
		 dispselistone = "";
		 dispselisttwo = "";
	}
</script>
<body id="" style="text-align: center;" onload="Initpic()" >


<div id="microcontentimsges" style="margin-top: 10px;"></div>

<div style="width:80px; font-size: 15px;cursor: pointer;" title="收起" onclick="showDivByViewMicro('<s:property value="picfilenamelist.replace(\"_150X150\",\"\")" />',<s:property value="boolcid" />)"><img style="width: 14px;" src="<%=rootPath%>view/images/microblogshouqi.jpg">&nbsp;<a style="color: #3C3C3C;">收起</a></div>



<input style="display: none;" id="inputpicids<s:property value="picfilenamelist.toString().substring(0,17)" />" value="<s:property value="picfileids" />" />
<div style="margin-top: 10px;" onmouseover="findASId('<s:property value="picfileids" />','<s:property value="picfilenamelist.toString().substring(0,17)" />','<s:property value="picfilenamearray.length" />')">
<div  id="loadimgsrcpicimgdiv<s:property value="picfilenamelist.toString().substring(0,17)" />" style="width:600;margin:0 auto;text-align: center;display:none;">

<img id="loadimgsrcpicimg<s:property value="picfilenamelist.toString().substring(0,17)" />"  src="<s:property value="picfile" />" style="display: none;" />
</div>
<div  id="backpiccsspicdiv<s:property value="picfilenamelist.toString().substring(0,17)" />"  onclick="backpic()" style="height: 400;width:200;margin:0 auto;float: left;margin-top: -400px;position: relative;cursor:url(<%=rootPath%>view/images/cur_next.cur),auto;">
</div>

<div  id="goonpiccsspicdiv<s:property value="picfilenamelist.toString().substring(0,17)" />" onclick="goonpic()"  style="height: 400;width:200;margin:0 auto;float:right;margin-top: -400px;position: relative;cursor:url(<%=rootPath%>view/images/cur_pre.cur),auto;">
</div>

<div id="bottomimgsdiv<s:property value="picfilenamelist.toString().substring(0,17)" />" style="display: none;">
	<div style="width:600;margin:0 auto;height: 20;text-align: center;"><a style="color:#6C6C6C;" href="javascript:void(0)"  onclick="checkMaxPicBlank()">查看原图</a></div>
	<div style="width:600;margin:0 auto;height: 50;position: relative;text-align: center;">
				<div class="rollBox">
					
					<div class="scrollcon">
						<div id="toppages<s:property value="picfilenamelist.toString().substring(0,17)" />" class="LeftBotton" onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()" onmouseout="ISL_StopUp()"></div>
						<div class="Cont" id="ISL_Cont<s:property value="picfilenamelist.toString().substring(0,17)" />" >
							<div class="ScrCont">
								<div style="float: left;" id="List1<s:property value="picfilenamelist.toString().substring(0,17)" />">
									<!-- 图片列表 begin -->
									<s:iterator status="strpic" value="picfilenamearray">
									<div class="pic">
										<a href="javascript:void(0)"><img id="<s:property value="picfilenamelist.toString().substring(0,17)" /><s:property value="#strpic.index" />" style="width: 83;height: 68; " alt="" src="<%=rootPath%>file/readfile.action?fileName=<s:property value="picfilenamearray[#strpic.index]" />" onclick="searchLoadMicroPics(this.src,this.id)" ></a>
									</div> 
									</s:iterator>
									<!-- 图片列表 end -->
								</div>
								<div style="float: left;" id="List2<s:property value="picfilenamelist.toString().substring(0,17)" />"></div>
							</div>
						</div>
						<div id="bottompages<s:property value="picfilenamelist.toString().substring(0,17)" />" class="RightBotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()" onmouseout="ISL_StopDown()"></div>
					</div><!--content end-->
				</div><!--rollBox end-->
	</div>
</div><br/>
</div>
<style type="text/css">
.rollBox{background-color:#F5F5F5;height:68px;margin:0;float:left;overflow:hidden}
.rollBox .jslogo{height:68px;width:120px;border:0px;padding:1px}
.rollBox .scrollcon{width:598px;height:68px;position:absolute;top:1px;left:120px;}
.rollBox .LeftBotton{height:68px;width:22px;background:#ff0000 url(<%=rootPath%>view/images/microblogpicview/1294825702857.jpg) no-repeat 0px 0;overflow:hidden;float:left;display:inline;margin:0px;cursor:pointer;}
.rollBox .RightBotton{height:68px;width:22px;background:#ff0000 url(<%=rootPath%>view/images/microblogpicview/1294825623597.jpg);overflow:hidden;float:left;display:inline;margin:0px;cursor:pointer;}
.rollBox .Cont{width:344px;overflow:hidden;float:left;height:70px;}
.rollBox .ScrCont{width:10000000px;}
.rollBox .Cont .pic{width:86px;height:68px;overflow:hidden;float:left;}
.rollBox .Cont .pic img{background:#fff;display:block;}
.rollBox #List1, .rollBox #List2{float:left;}
</style>
<script type="text/javascript">

</script>
</body>
