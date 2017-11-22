<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<!-- 词条审核页面 -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:property value="irptermofword.termname" />_百科词条</title>
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
	<link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
	<jsp:include page="../../view/include/client_skin.jsp" />
	<style type="text/css">
	.wtgy {
		height: 28px;
		line-height: 28px;
		border: 1px solid rgb(209, 209, 209);
		color: rgb(102, 102, 102);
		padding: 0px 5px;
		width: 300px;
		margin: 0 0 0 5px;
	}
	
	.btn_ccw {
		background: none repeat scroll 0 0 #63C7E6;
		color: #FFFFFF;
		display: block;
		width: 100px;
		float: left;
		line-height: 28px;
		font-size: 18px;
		padding: 0px 5px;
		font-style: normal;
		height: 30px;
		text-align: center;
	}
	</style>
</head>
<script type="text/javascript">
$(function(){
	//选择头部标签
	selected('wordterm');
	
});
/**
 * 查看历史版本
 */
function searchHisVer(_tid){
	
	window.open('<%=rootPath%>term/linkhistory.action?hostorytermid='+_tid);
	
}
/**
 * 链接到编辑
 */
function termEdit(_tid){
	window.open('<%=rootPath%>term/linkedittermmgr.action?edittermid='+_tid);
	
}
/**
 * 通过审核
 */
function passAudit(_tid){
	$.ajax({
		type:'post',
		url:'<%=rootPath%>term/passauditterm.action',
		data:{
			passid:_tid
		},
		async:false,
		cache:false,
		success:function(msg){
			if(msg=='1'){
			
				$.dialog.tips('操作成功,词条已通过!',1,'32X32/succ.png',function(){
				window.location.reload();
				});
				
				
			}else{
				$.dialog.alert('操作失败');
			}
		}
	});
}
/**
* 锁定词条
*/
function lockTerm(_termid){
	var locktext = $("#lockterm").text();
	if(locktext=='锁定'){
		$.dialog.confirm('您确定要锁定此词条吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>term/lockofterm.action',
				data:{
					lockid:_termid,
					lockstatus:1
				},
				async:false,
				cache:false,
				success:function(msg){
					if(msg=='1'){
						$.dialog.tips('操作成功,词条已锁定!',1,'32X32/succ.png',function(){
							window.location.reload();
						});

					}else{
						$.dialog.alert('操作失败');
					}
				}
			});
		},function(){}); 
	}else{
		//解除锁定
			$.ajax({
				type:'post',
				url:'<%=rootPath%>term/lockofterm.action',
				data:{
					lockid:_termid,
					lockstatus:2
				},
				async:false,
				cache:false,
				success:function(msg){
					if(msg=='1'){
						$.dialog.tips('操作成功,词条已接触锁定!',1,'32X32/succ.png',function(){
						window.location.reload();						
						});

					}else{
						$.dialog.alert('操作失败');
					}
				}
			});
	}	
}

/**
 * 置为非法
 */
function illegalityTerm(_tid){
	var illaudittext = $('#illaudit').text();
	if(illaudittext=='置为非法'){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>term/illegalityauditterm.action',
			data:{
				illegid:_tid,
				illstatus:1
			},
			async:false,
			cache:false,
			success:function(msg){
				if(msg=='1'){
					$.dialog.tips('操作成功,词条已置为非法!',1,'32X32/succ.png',function(){
							window.location.reload();
						});
				}else{
					$.dialog.alert('操作失败');
				}
			}
		});
	}else{
		$.ajax({
			type:'post',
			url:'<%=rootPath%>term/illegalityauditterm.action',
			data:{
				illegid:_tid,
				illstatus:2
			},
			async:false,
			cache:false,
			success:function(msg){
				if(msg=='1'){
					$.dialog.tips('操作成功,词条已恢复!',1,'32X32/succ.png',function(){
							window.location.reload();
						});
				}else{
					$.dialog.alert('操作失败');
				}
			}
		});
	}
	
}
</script>
<body style="background: url();">
	<jsp:include page="../../view/include/client_head.jsp" />
	<section class="mainBox">
        <nav class="privateNav"> </nav>
    </section>
	<div class="main" style="width: 1200px; margin-top: 20px; background: url();">
		<div class="left" style="width: 930px;">
			<div>
				<div style="font-size: 24px;line-height: 34px;border-bottom: 1px solid #BDBDBD;padding: 0 0 10px 0;">
					<s:property value="irptermofword.termname" />
					<s:if test="irptermofword.termstatus==@com.tfs.irp.term.entity.IrpTerm@TERMSTATUSWORDS && irptermofword.termisdel!=@com.tfs.irp.term.entity.IrpTerm@TERMLOCK">
						<a href="javascript:void(0)" style="border: 1px solid #DBDBDB;padding: 2px 8px;font-size: 14px;margin: 0 0 0 10px;" onclick="termEdit(<s:property value="irptermofexpl.termid" />)">编辑</a>
					</s:if>
					<%
					    if (LoginUtil.getLoginUser().isTermswordManager()) {
					%>
					<s:if test="irptermofexpl.termstatus==@com.tfs.irp.term.entity.IrpTerm@TERMSTATUSWORDSNO">
						<a href="javascript:void(0)" style="border: 1px solid #DBDBDB;padding: 2px 8px;font-size: 14px;margin: 0 0 0 10px;" onclick="passAudit(<s:property value="irptermofexpl.termid" />)"
							id="passaudit">通过审核</a>
					</s:if>
					<s:if test="irptermofexpl.termstatus==@com.tfs.irp.term.entity.IrpTerm@TERMSTATUSWORDSNO">
						<a href="javascript:void(0)" style="border: 1px solid #DBDBDB;padding: 2px 8px;font-size: 14px;margin: 0 0 0 10px;" onclick="illegalityTerm(<s:property value="irptermofexpl.termid" />)"
							id="illaudit">置为非法</a>
					</s:if>
					<s:if test="irptermofexpl.termstatus==@com.tfs.irp.term.entity.IrpTerm@TERMSTATUSILLEGALITY">
						<a href="javascript:void(0)" style="border: 1px solid #DBDBDB;padding: 2px 8px;font-size: 14px;margin: 0 0 0 10px;" onclick="illegalityTerm(<s:property value="irptermofexpl.termid" />)"
							id="illaudit">恢复</a>
					</s:if>
					<s:if test="irptermofword.termisdel==@com.tfs.irp.term.entity.IrpTerm@TERMISDELNORMAL && irptermofexpl.termstatus==@com.tfs.irp.term.entity.IrpTerm@TERMSTATUSWORDS">
						<a href="javascript:void(0)" style="border: 1px solid #DBDBDB;padding: 2px 8px;font-size: 14px;margin: 0 0 0 10px;" onclick="lockTerm(<s:property value="irptermofexpl.termid" />)" id="lockterm">锁定</a>
					</s:if>
					<s:if test="irptermofword.termisdel==@com.tfs.irp.term.entity.IrpTerm@TERMLOCK">
						<a href="javascript:void(0)" style="border: 1px solid #DBDBDB;padding: 2px 8px;font-size: 14px;margin: 0 0 0 10px;" onclick="lockTerm(<s:property value="irptermofexpl.termid" />)" id="lockterm">解除锁定</a>
					</s:if>
					<%
					    }
					%>
				</div>
				<div style="min-height: 43vh;margin: 20px 2px 0 1px;line-height: 24px;padding: 1% 1% 1% 1%;font-size: 14px;">

					<s:property value="irptermofexpl.termexplain" escapeHtml="false" />

				</div>
				<s:if test="irptermofexpl.eidtcause != '创建词条'">
					<div style="margin: 20px 2px 0 1px;line-height: 24px;padding: 1% 1% 1% 1%;font-size: 14px;border-top: 1px solid #BDBDBD;">
						编辑原因：<s:property value="irptermofexpl.eidtcause" escapeHtml="false" />
					</div>
				</s:if>
			</div>
		</div>
		<div class="right" style="width: 245px;">
			<div class="duo">
				<dl style="border-bottom: none;">
					<dt>
						<a href="javascript:void(0);" class="linkbh14">词条统计</a>
					</dt>
					<dd>
						所属类别:&nbsp;
						<s:property value="irpCategoryById(irptermofword.quoteclassify).cname" />
					</dd>
					<dd>
						当前版本:&nbsp;
						<s:property value="irptermofexpl.termversion" />
						版&nbsp;
						<a href="javascript:void(0)" class="linkb12" onclick="searchHisVer(<s:property value="irptermofword.termid" />)">历史版本</a>
					</dd>
					<dd>
						浏览次数:&nbsp;
						<s:property value="irptermofword.termbcount" />
						次
					</dd>
					<dd>
						创建者:&nbsp;
						<s:property value="getShowPageViewNameByUserId(irptermofexpl.cruserid)" />
					</dd>
					<dd>
						创建时间:&nbsp;
						<s:date format="yyyy年MM月dd日 HH:mm" name="irptermofexpl.crtime" />
					</dd>
				</dl>
			</div>
			
			<!-- 附件处理 -->
            <s:iterator value="attachedList">
                <div style="display: inline-block;width: 245px;padding-left: 15px;">
                                     附件：
                    <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
                        <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -11px -10px; vertical-align: middle;"></span>
                    </s:if>
                    <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
                        <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -60px -10px; vertical-align: middle;"></span>
                    </s:elseif>
                    <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MENU_FIELD_NAME==typeid">
                        <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -110px -10px; vertical-align: middle;"></span>
                    </s:elseif>
                    <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MICUS_FIELD_NAME==typeid">
                        <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -160px -10px; vertical-align: middle;"></span>
                    </s:elseif>
                    <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@ID_FIELD_NAMEOTHER==typeid">
                        <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -210px -10px; vertical-align: middle;"></span>
                    </s:elseif>
                    <a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"  style="cursor: pointer; text-decoration: none;">
                        <s:property value="attdesc" />
                    </a>
                    <a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"  style="cursor: pointer; text-decoration: none;">下载</a>
                    <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
                        | <a target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>"  style="cursor: pointer; text-decoration: none;">在线浏览</a>
                    </s:if>
                    <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
                        |  <a target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>"  style="cursor: pointer; text-decoration: none;">在线浏览</a>
                    </s:elseif>
                </div>
            </s:iterator>
		</div>
		<jsp:include page="../../view/include/client_foot.jsp" />
	</div>
</body>
</html>