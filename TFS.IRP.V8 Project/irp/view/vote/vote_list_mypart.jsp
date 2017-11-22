<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser,com.tfs.irp.util.LoginUtil"%>
<%
    String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    IrpUser loginUser = LoginUtil.getLoginUser();
%>
<style type="text/css">
.bar {
	background: none repeat scroll 0 0 #F0F0F0;
	float: left;
	height: 11px;
	margin-top: 2px;
	width: 100px;
}

.resultbar {
	float: right;
}

.colors {
	float: left;
	height: 11px;
	margin-top: 2px;
	margin-left: -100px;
	width: 100px;
}

.sgn_0,.sgn_10 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left 0;
}

.sgn_1,.sgn_11 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left
		-13px;
}

.sgn_2,.sgn_12 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left
		-26px;
}

.sgn_3,.sgn_13 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left
		-39px;
}

.sgn_4,.sgn_14 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left
		-52px;
}

.sgn_5,.sgn_15 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left
		-65px;
}

.sgn_6,.sgn_16 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left
		-78px;
}

.sgn_7,.sgn_17 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left
		-91px;
}

.sgn_8,.sgn_18 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left
		-104px;
}

.sgn_9,.sgn_19 {
	background: url("../view/images/bg_bar.gif") repeat-x scroll left
		-117px;
}

.num {
	
}
</style>
<script type="text/javascript">
	function showresult() {
		var leni = '<s:property value='
		maplist.size()
		' />';
		for (var i = 0; i < leni; i++) {
			var lenj = $("#votelist").children("div:eq(" + i + ")").find(
					"span[class='bar']").length;
			var ncount = 0;
			for (var j = 0; j < lenj; j++) {
				var tcount = $("body #votelist").children("div:eq(" + i + ")")
						.find("span[class='bar']:eq(" + j + ")").attr("title");
				ncount = eval(ncount + eval(tcount));
			}
			for (var x = 0; x < lenj; x++) {
				var count = $("body #votelist").children("div:eq(" + i + ")")
						.find("span[class='bar']:eq(" + x + ")").attr("title");
				var withres = eval(count / ncount) * 100;
				withres += "%";
				if (withres == "NaN%") {
					withres = "0%";
				}
				$("body #votelist").children("div:eq(" + i + ")").find(
						"span[title='colorbar']:eq(" + x + ")").attr("style",
						"width:" + withres);
				if (withres.length > 5) {
					$("#votelist").children("div:eq(" + i + ")").find(
							"span[class='num']:eq(" + x + ")").html(
							withres.substring(0, 5) + "%");
				} else {
					$("#votelist").children("div:eq(" + i + ")").find(
							"span[class='num']:eq(" + x + ")").html(withres);
				}
			}
		}
	}
	$(function() {
		showresult();
	});
</script>
<s:if test="maplist.size()>0">
	<div id="topictabmenu" style="width: 100%">
		<dl class="linkbh14">
			<dt style="width: 5%;">排名</dt>
			<dt style="width: 40%">标题</dt>
			<s:if test="typeindex==1">
				<dt style="width: 15%">发起人</dt>
			</s:if>
			<dt style="width: 10%">热度</dt>
			<dt style="width: 18%">开始时间</dt>
			<s:if test="typeindex==3">
				<dt style="width: 15%">操作</dt>
			</s:if>
		</dl>
		<br />
		<div id="votelist">
			<s:iterator value="maplist" status="irptopicliststatus">
				<!-- 跌代第一个map -->
				<s:set var="votekey" value="key" />
				<div>
					<dl>
						<dt
							style="width: 5%; <s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==1">color: rgb(235, 25, 45);</s:if><s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==2">color: rgb(255, 102, 0);</s:if><s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==3">color:  rgb(219, 219, 111);</s:if>">
							<s:property value="(pageNum-1)*pageSize+#irptopicliststatus.count" />
						</dt>
						<dt style="width: 40%" class="olbg11 ellipsis">
							<a class="linkb14" title='<s:property value="#votekey.title"/>' target="_blank" href='<%=rootPath%>menu/vote_detil.action?voteid=<s:property value="#votekey.voteid" />'>
								<s:property value="#votekey.title" />
							</a>
						</dt>
						<s:if test="typeindex==1">
							<dt style="width: 15%" title='<s:property value="findUsername(#votekey.cruserid)" />' class="olbg11 ellipsis">
								<s:property value="findUsername(#votekey.cruserid)" />
							</dt>
						</s:if>
						<dt style="width: 10%">
							<s:property value="#votekey.count" />
						</dt>
						<dt style="width: 18%;">
							<s:date name="#votekey.starttime" format="yyyy-MM-dd HH:mm" />
						</dt>
						<s:if test="typeindex==3">
							<dt style="width: 15%">
								<a href="<%=rootPath%>menu/vote_detilresult.action?voteid=<s:property value="#votekey.voteid" />" target="blank">查看结果>></a>
							</dt>
						</s:if>
					</dl>
					<br />
					<dl style="margin-left: 55px;text-align: left;background-image:url('');margin: 10px auto;">
						<s:iterator value="value" status="status">
							<!-- 跌代map 中的 value 值是list  -->
							<s:iterator value="value[#status.index]">
								<!-- 跌代list 中的 map  -->
								<div style="margin-left: 60px;" class="bt">
									<div style="width: 40%;" class="olbg11 ellipsis">
										<s:if test="optionurl.length()>0">
											<s:if test="optionurl!='null'">
												<a target="blank" href="<s:property value='optionurl' />">
													<s:property value="voteoption" escapeHtml="false" />
												</a>
											</s:if>
											<s:else>
												<s:property value="voteoption" escapeHtml="false" />
											</s:else>
										</s:if>
										<s:else>
											<s:property value="voteoption" escapeHtml="false" />
										</s:else>
									</div>
									<div class="sign" style="margin-left: 350px;margin-top: -20px;width: 200px;" id="resultbar">
										<span class="bar" title="<s:property value="count"/>"></span>
										<div style="width: 100px;">
											<span title="colorbar" class="colors sgn_<s:property value='#status.index'/>">&nbsp;</span>
										</div>
										&nbsp;&nbsp;&nbsp;
										<span class="num"> </span>
										<div style="float: right;">
											&nbsp;&nbsp;&nbsp;<strong><s:property value="count" />票</strong>
										</div>
									</div>
								</div>
							</s:iterator>
						</s:iterator>
					</dl>
					<!-- <h3 style="font-size: 14px;font-weight: 700; border-bottom: 1px solid rgb(224, 224, 224);padding: 0px 0px 10px;"></h3> -->
				</div>
			</s:iterator>
			<ul>
				<s:property value="pageHTML" escapeHtml="false" />
			</ul>
		</div>
	</div>
</s:if>
<s:else>
	<div>
		没有投票,
		<a href="javascript:void(0)" onclick="clickvote()">我去发起投票吧</a>
	</div>
</s:else>