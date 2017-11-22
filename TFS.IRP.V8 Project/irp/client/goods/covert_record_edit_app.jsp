<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<style>
.ellipsis {
    table-layout: fixed;
}
.ellipsis td{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.pros{height:40px;padding-left:100px;padding-top:30px}
.pros span{height:38px;line-height:38px;color:#286095;margin:0 25px 0 25px;display:inline-block;}
.pros .line{border-bottom:solid 3px #4090d3}
.section-item{width:990px;margin:40px auto 0 auto;}
/* .section-item a:hover{text-decoration:none} */
.section-item .prize{float:left;width:200px;margin-bottom:40px}
.section-item .prize-card-list .prize-card{float:left;margin-right:50px;transition:.2s}
.section-item .prize-card:hover{transform:scale(1.1)}
.prize-card{position:relative;display:block;width:210px;height:226px;padding-top:10px;background:url(prize-card-bj_17bb4d4.png);text-align:center;}
.prize-card .cover{position:relative;display:block;width:170px;height:170px;margin:0 auto;background-size:cover}
@font-face{
		font-family: jianzhi;
		src:url(<%=rootPath %>client/images/jianzhi.TTF),
			url(<%=rootPath %>client/images/jianzhi.TTF); /* IE9 */
	 }
.prize-card .cover p{font-size:36px;color:white;font-family:"jianzhi";padding-top:64px}
.prize-card .prize-title{position:absolute;width:100%;bottom:5px;left:0;line-height:24px;font-size:14px;color:#fff}
.prize-card .need-score{display:block;padding-top:18px;text-align:center}
.prize-card .need-score strong{display:inline-block;vertical-align:text-bottom;height:28px;color:#425666;margin-right:4px;padding-left:20px;margin-bottom:10px}

.prize-card .need-score span{display:inline-block;vertical-align:text-bottom;padding-bottom:3px;font-size:12px;color:#425766}
.prize-card .need-score-decline{position:relative;display:block;padding-top:8px;}
.prize-card .need-score-decline .need-score-before{display:block;height:14px;line-height:14px;color:#b4b4b4;text-decoration:line-through}

.prize-card .need-score-decline .need-score-before img{vertical-align:text-top;margin-right:1px}
.prize-card .need-score-decline .need-score{padding-top:0;padding-left:20px}
.prize-card .need-score-decline .need-score strong{padding:0;font-size:30px;font-family:"Bebas"}

.prize-card .need-score-decline i{display:inline-block;width:13px;height:17px;margin-right:5px;background:url(<%=rootPath %>client/images/jt.png) no-repeat center center}
.prize-card .icon-hot{position:absolute;top:-8px;right:-8px;width:62px;height:62px;background:url(//vipllq.baidu.com/statics/user_point/theme-v2/images/icon-hot_4f1e91a.png)}
.prize-card.is-lucky-bag .prize-title{color:#766450}
.download-dialog{height:250px}
.download-dialog .dialog-content{height:109px;padding:55px 0 0 136px;position:relative}
.download-dialog .dialog-content h3{font-weight:700;font-size:16px;line-height:22px;color:#425766}
.download-dialog .dialog-content p{padding-top:5px;font-size:12px;line-height:1.5em;color:#a0aab1}
.download-dialog .dialog-buttonpane{height:58px;padding-top:15px;text-align:center;background:#fff}
.download-dialog .icon{position:absolute;width:78px;height:111px;top:43px;left:28px;background-repeat: no-repeat;}
.download-dialog .button{display:block;width:216px;height:70px;margin:auto;line-height:40px;text-align:center;color:#fff;font-size:14px;font-weight:700;background:url(//vipllq.baidu.com/statics/user_point/theme-v2/images/button-blue_ead0db1.png);cursor:pointer}
.download-dialog .button.dark{background:url(//vipllq.baidu.com/statics/user_point/theme-v2/images/button-dark_4bf766d.png);cursor:default}
.prize-detail{position:relative;height:350px}
.prize-detail-side{position:absolute;top:0;left:0;width:210px;padding:46px 0 0 70px}
.prize-detail-side .prize-card{margin:auto}
.setUp .myDiscuss {
    width: 940px;
}
.prize form,.prize .prod{padding-left:20px;}
.prize input[type="number"]{width:40px;display:inline-block;border:solid 1px #0e6ec0;border-radius:2px;line-height:28px;}
.prize input[type="submit"]{width:42px;display:inline-block;background: #0e6ec0;border-radius:2px;border:none;line-height:30px;color:#fff}
.prod h1{font-size:16px;font-weight:bold;line-height:24px;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;}
.prod p{width:178px;display:block;font-size:12px;padding-bottom:14px;color:#323232;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;line-height: 12px}
</style>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript">

///////删除
function deletecovert(_usercovertid){
		var messageiddiv = "#"+_usercovertid+"div";  
		$.messager.confirm('提示信息','是否要删除这条数据？',function(r){ 
		   // if(r){
		    		$.ajax({
	    				url: "<%=rootPath %>covert/deleteCovert.action",
	    			   	type: "POST",
	    			   	data: {
	    			   		covertid: _usercovertid,
	    			   	},
	    			   	success: function(msg){
	    			   		/* $.messager.alert("提示信息","成功删除了这条数据","info"); */
	    			   		if(msg>=0){
		   					$(messageiddiv).fadeOut();
		   					}
	    			   	}
		    		});
		    //}
		});
}
//分页
function page(_nPageNum){
	alert(_nPageNum);
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	$('#covertrecord').empty();
	var result=$.ajax({
		type:'post',
		url:"<%=rootPath%>covert/listCovertRecordByUserid.action?"+queryString,
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#covertrecord").html(result);
}
	</script>
<form id="pageForm">
        <s:hidden name="goodsname" id="goodsdesc"/>
        <s:hidden name="goodsdesc" id="goodsdesc"/>
        <s:hidden name="searchWord" id="kd"/>
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSizeclient" id="pageSizeclientform"/>
		<s:hidden name="coverstate" id="coverstate" />
</form>
<form id="addcovert" method="post">
<s:if test="listcovertrecord.size!=0">
<%--<div style="height: 30px;width: 950px;">
	<span style="float: right;padding-right: 32px;">搜索 &nbsp;&nbsp;&nbsp;<input value='<s:property value="searchWord"/>' class="searchbox-text searchbox-prompt" type="text" name="searchWord" id="searchWord" style="width: 126px; height: 20px; line-height: 20px;">
		<span>
		   <span class="searchbox-button" style="height: 20px;" onclick="searchUser()"></span>
		</span>
	</span>
</div>
    --%><%--<ul class="cardList" style="width: 950px;">
		<s:iterator value="listcovertrecord">
		<li style="width: 940px;">
		<div style="border: 1px solid #000;height: 30px;padding-top: 10px;">
		<span style="padding-left: 20px;"><s:property value="covertgoods" /></span>
		<span><s:property value="covertnum" /></span>
		<span style="padding-left: 20px;"><s:property value="coverttime" /></span>
		 <a href="javascript:void(0)" onclick="deletecovert(<s:property value="usergoodsid" />)">删除</a>
		</div>
		 </li>
	    </s:iterator>
     </ul>
      --%>
			<s:iterator value="listcovertrecord" >
			    <ul class="myDiscuss list9" id="<s:property value='usergoodsid' />div" >
			   		<li><div style="padding-left: 40px"><s:date name="coverttime" format="yyyy-MM-dd HH:mm" /><div class="userIcon" >
			   			
						         <s:if test="getIrpUserByUserid(userid).userpic!=null">
						          <img style="border-radius: 20px;" width="60" height="60" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='getIrpUserByUserid(userid).userpic' />&token=<s:property value="tokens" />" >
						         </s:if>
						         <s:elseif test="getIrpUserByUserid(userid).userpic==null"> 
						          <img style="border-radius: 20px;" width="60" height="60" <s:if test="getIrpUserByUserid(userid).sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户图片"   onmouseover="personalCard(<s:property value='userid' />,<s:property value='#resverobj.messageid' />)" onmouseout="personalCardOut(<s:property value='#resverobj.messageid' />)"  />
						         </s:elseif>
			   			</div><section><aside></aside>
			   		<div>
			   			 <div style="float: left; width: 250px;"><s:property value="covertgoods" escapeHtml="false" /></div>
			   			 <div style="float: left;">数量：<s:property value="covertnum" />件&nbsp;&nbsp;商品单价：<s:property value="singleScore" />积分&nbsp;&nbsp;&nbsp;共消费：<s:property value="totalScore" />个
						    <s:if test="convertType==20">
						    	积分
						    </s:if>
						    <s:else>
						   	 勋章
						    </s:else></div>
			    		<a style="line-height: 44px;padding-left: 30px;" href="javascript:void(0)" onclick="deletecovert(<s:property value="usergoodsid" />)">删除</a>
			        </div>
			        </section></div>
			        </li> 
			    </ul>
			</s:iterator>
      <div style="clear:both" ></div>
     <div class="fyh" style="text-align: center;"><ul> <s:property value="pageHTML" escapeHtml="false" /></ul></div>
</s:if>
<s:else>
    <div  class="emptyDiv"> 暂无兑换记录</div>
</s:else>		    
</form>   