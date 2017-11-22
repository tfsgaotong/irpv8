<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>知识库</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>

<script type="text/javascript">
function tabUserRank(_nType){
	var result = $.ajax({
		url:'<%=rootPath %>document/user_rank.action',
		type: "GET",
		async: false,
		cache : false,
		data: {
			dateType: _nType
		}
	}).responseText;
	$('#rankList').html(result);
	if(_nType==1){
		$('#dateIndex').css('right','72px');
	}else if(_nType==2){
		$('#dateIndex').css('right','36px');
	}else if(_nType==3){
		$('#dateIndex').css('right','0px');
	}
}

//轮播图
function rollpan(){
	var rollpans = $('#rollpan').find('article');
	var switchings = $('#switching').find('a');
	if(rollpans.length<2){
		return;
	}
	for ( var i = 0; i < rollpans.length; i++) {
		var jqObj = $(rollpans[i]);
		var jqObj1 = $(switchings[i]);
		if(jqObj.css('display')!='none'){
			jqObj.hide();
			jqObj1.attr('class','');
			if((i+1)==rollpans.length){
				$(rollpans[0]).fadeIn('slow');
				$(switchings[0]).attr('class','current');
			}else{
				jqObj.next().fadeIn('slow');
				jqObj1.next().attr('class','current');
			}
			return;
		}
	}
}

function selectRollpan(oLink){
	clearInterval(t);
	var switchings = $('#switching').find('a');
	var rollpans = $('#rollpan').find('article');
	if(rollpans.length<2){
		return;
	}
	for (var i=0; i<switchings.length; i++) {
		if(switchings[i]==oLink){
			$(oLink).attr('class','current');
			$(rollpans[i]).fadeIn('slow');
		}else{
			$(switchings[i]).attr('class','');
			$(rollpans[i]).hide();
		}
	}
	t = setInterval("rollpan()", speed);
}
var t;
var speed=5000;

$(function(){
	//用户积分排行
	tabUserRank(1);
	<s:if test="essenceDocs!=null && essenceDocs.size()>1">
	t = setInterval("rollpan()", speed);
	</s:if>
});
</script>
</head>

<body>
<s:include value="../include/client_head.jsp"></s:include>
<section class="mainBox">
	<nav class="privateNav">
    	<dl>
        	<dt class="leftBox">全部知识分类</dt>
            <dd class="leftBox navBtns">
            	<a href="<s:url namespace="/document" action="document_list" />">知识分类</a>
            	<a href="<s:url namespace="/document" action="map_index" />">知识地图</a>
            	<a href="<s:url namespace="/document" action="document_subject_list" />">知识专题</a>
			</dd>
            <dd class="clear"></dd>
        </dl>
    </nav>
</section>
<section class="mainBox">
	<section class="layoutLeft">
        <nav class="allBtns">
            <ul>
           	<s:iterator value="rootChannels">
                <li>
                	<a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
                	<s:set var="childChnls" value="findChildChannelByParentId(channelid)" />
                	<s:if test="#childChnls!=null && #childChnls.size()>0">
               		<div class="moreBtns">
                		<div class="line">
               			<s:iterator value="#childChnls">
                			<h1><a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></h1>
                			<s:set var="grandsonChnls" value="findChildChannelByParentId(channelid)" />
                			<s:if test="#grandsonChnls!=null && #grandsonChnls.size()>0">
                				<s:iterator value="#grandsonChnls">
                			<P><a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></P>
                				</s:iterator>
                			</s:if>
               			</s:iterator>
                		</div>
                	</div>
                	</s:if>
                </li>
			</s:iterator>
            </ul>
        </nav>
        <div class="area10"></div>
        <div class="ranking">
        	<div class="title">
        		<span>用户积分排行</span>
        		<aside>
        			<a href="javascript:void(0)" onclick="tabUserRank(1)">本周</a>
        			<a href="javascript:void(0)" onclick="tabUserRank(2)">本月</a>
        			<a href="javascript:void(0)" onclick="tabUserRank(3)">本年</a>
        			<p id="dateIndex"></p>
        		</aside>
        	</div>
            <section id="rankList" class="list">
            </section>
        </div>
    </section>
    <section class="layoutMiddle">
    	<div class="rollingNews">
        	<div class="title">
        		<!-- 每日推荐 -->
        		<div class="switching" id="switching">
        		<s:if test="essenceDocs!=null && essenceDocs.size()>1">
        		<s:iterator value="essenceDocs" status="index">
        			<a href="javascript:void(0)" onclick="selectRollpan(this)" <s:if test="#index.first">class="current"</s:if>></a>
        		</s:iterator>
        		</s:if>
        		</div>
        	</div>
        	<div class="rollpan" id="rollpan">
        	<s:iterator value="essenceDocs" status="index">
        		<s:set var="document" value="getIrpDocumentById(docid)" />
        		<article <s:if test="#index.first"></s:if><s:else>style="display:none;"</s:else>>
                	<dl>
                    	<dt class="appendix">
                    		<a href="<s:url action="document_detail" namespace="/document">
                    		<s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
                    		<img src="<s:property value='docCoverPath(docid,docflag)' />" width="260" height="170" /></a>
						</dt>
                        <dd class="text">
                        	<h1 class="ellipsis nowrap"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></h1>
                            <p style="height:140px;" class="ellipsis" title="<s:property value="#document.docabstract" />"><s:property value="#document.docabstract" /></p>
                        </dd>
                    </dl>
                </article>
        	</s:iterator>
            </div>
        </div>
        <div class="area10"></div>
        <div class="tabTitle">
        	<div class="current"><label>最新知识</label><p class="labBg"></p></div>
        </div>
        <div class="area10"></div>
        <section class="listpan1">
			<ul class="list1">
			<s:iterator value="newChnlDocs">
				<s:set var="cruser" value="findUserByUserId(cruserid)" />
				<li>
					<em style="width:56px;" class="ellipsis">
						<a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
					</em>
					<label class="ellipsis">|</label>
					<span style="width:400px;" class="ellipsis">
						<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a>
					</span>
					<aside style="width:112px;" class="ellipsis">
						<s:date name="docpubtime" format="MM.dd"/>&nbsp;&nbsp;
						<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank"><s:property value="#cruser.showName"/></a>
					</aside>
				</li>
			</s:iterator>
			</ul>
        </section>
        <div class="area10"></div>
        <div class="tabTitle">
        	<div class="current"><label>最热知识</label><p class="labBg"></p></div>
        </div>
        <div class="area10"></div>
        <section class="listpan1">
			<ul class="list1">
			<s:iterator value="hotChnlDocs">
				<s:set var="cruser" value="findUserByUserId(cruserid)" />
                <li>
                	<em style="width:56px;" class="ellipsis">
                		<a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
               		</em>
					<label class="ellipsis">|</label>
					<span style="width:400px;" class="ellipsis">
						<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a>
					</span>
					<aside style="width:112px;" class="ellipsis">
						<s:property value="hitscount"/>&nbsp;&nbsp;
						<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank"><s:property value="#cruser.showName"/></a>
					</aside>
				</li>
			</s:iterator>
			</ul>
        </section>
        <div class="area10"></div>
        <div class="tabTitle">
        	<div class="current"><label>最有价值</label><p class="labBg"></p></div>
        </div>
        <div class="area10"></div>
        <section class="listpan1">
			<ul class="list1">
			<s:iterator value="hotChnlDocs">
				<s:set var="cruser" value="findUserByUserId(cruserid)" />
                <li>
                	<em style="width:56px;" class="ellipsis">
                		<a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
               		</em>
					<label class="ellipsis">|</label>
					<span style="width:400px;" class="ellipsis">
						<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a>
					</span>
					<aside style="width:112px;" class="ellipsis">
						<s:property value="docscore"/>&nbsp;&nbsp;
						<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank"><s:property value="#cruser.showName"/></a>
					</aside>
				</li>
			</s:iterator>
			</ul>
        </section>
        <div class="area10"></div>
        <div class="title1"><span>标签导航</span></div>
       <!-- 
        <div class="tabTitle">
        	<div class="current"><label>标签导航</label></div>
        </div>
       
        -->
        
        <div class="area20"></div>
        <section class="listpan2">
        	<ul class="labList">
        	<s:iterator value="indexTags">
            	<li>
            		<strong><s:property value="key" /></strong>
            		<div class="clear">
            		<s:iterator value="value">
            			<a href="javascript:void(0)" class="nowrap" onclick="searchVal('<s:property value="tagname" />','rad_doc')" title="共检索<s:property value="ncount" />次"><s:property value="tagname" /></a>
            		</s:iterator>
           			</div>
				</li>
			</s:iterator>
            </ul>
        </section>
    </section>
    <section class="layoutRight">
    	<div class="columnInfo">
        	<input type="button" value="创建知识" onclick="window.open('<s:url namespace="/document" action="document_edit" />');" class="create" style="width: 310px;"/>
        <%-- 	<input type="button" value="去签到" onclick="window.open('<s:url namespace="/sign" action="signInit" />');" class="create" style="width: 80px ; background-image: url('../images/sign_green.png');" />
        	 
        	<input class="create" style="width: 88px;margin-top: 2px; background-image: url('../images/sign_green.png');" type="button" value="请假" onclick="window.open('<s:url namespace="/leave" action="leaveList" ><s:param name="type" value="0"></s:param></s:url>');" />
        	<input class="create" style="width: 88px;margin-top: 2px; background-image: url('../images/sign_green.png');" type="button" value="加班" onclick="window.open('<s:url namespace="/leave" action="overtime_leaveList" />');" />
			<input class="create" style="width: 120px;margin-top: 2px; background-image: url('../images/sign_green.png');" type="button" value="申请会议室" onclick="window.open('<s:url namespace="/asseroomapply" action="toAsseroomapply" />');" />        	
        	 --%>
            <div class="area10" style="margin-top: 20px;"></div>
            <div class="boxcenter line">
            	<span>已有</span>
            	<span class="font"><s:property value="sysRecommendCount" /></span>
            	<span>评论</span>
            </div>
            <div class="boxcenter">
            	<span>贡献</span>
            	<span class="font"><s:property value="sysDocumentCount" /></span>
            	<span>知识</span>
            </div>
        </div>
        <div class="area10"></div>
        <div class="title2"><span>最新评论</span></div>
        <div class="area20"></div>
        <section class="listpan1">
        	<ul class="talkingList">
        	<s:iterator value="commentsData">
        		<li>
                	<dl>
                    	<dt><a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="IrpUser.userid" /></s:url>" target="_blank"><img src="<s:property value="IrpUser.defaultUserPic" />" width="70" height="70" title="<s:property value="IrpUser.showName" />" /></a></dt>
                        <dd>
                        	<h1 style="width:200px;" class="ellipsis nowrap">
                        		<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="IrpChnlDocLink.docid" /></s:url>" target="_blank" title="<s:property value="IrpChnlDocLink.doctitle" />"><s:property value="IrpChnlDocLink.doctitle" /></a>
                       		</h1>
                        	<div style="width:200px;" class="ellipsis">
                        		<s:property value="IrpDocrecommend.recommend" escapeHtml="flase" />
                       		</div>
                            <aside>
                            	<span><s:property value="IrpChnlDocLink.commentcount" /></span>条<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="IrpChnlDocLink.docid" /></s:url>#discuss" target="_blank">评论</a>
                           	</aside>
                        </dd>
                    </dl>
                </li>
        	</s:iterator>
            </ul>
        </section>
        <div class="area10"></div>
		<div class="title2"><span>知识推荐</span></div>
        <div class="area10"></div>
        <section class="listpan1">
        	<s:if test="!pushDocs.isEmpty()">
        	<ul class="list2">
        	<s:iterator value="pushDocs">
				<li class="ellipsis nowrap" style="width:284px;"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="DOCID" /></s:url>"  target="_blank"><s:property value="DOCTITLE.toString().substring(1,DOCTITLE.toString().length()-1)" escapeHtml="false"/></a></li>
			</s:iterator>
			</ul>
        	</s:if>
        	<s:else>
        		<span style="color:#333;font-size:14px;">根据您设置的标签，系统没有找到可以推荐给您的知识...</span>
        	</s:else>
        </section>
        
        <div class="area10"></div>
		<div class="title2"><span>猜你喜欢</span></div>
        <div class="area10"></div>
        <section class="listpan1">
        	<s:if test="!likeDocs.isEmpty()">
        	<ul class="list2">
        	<s:iterator value="likeDocs">
				<li class="ellipsis nowrap" style="width:284px;"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>"  target="_blank"><s:property value="doctitle"/></a></li>
			</s:iterator>
			</ul>
        	</s:if>
        	<s:else>
        		<span style="color:#333;font-size:14px;">系统没有找到您喜欢的知识...</span>
        	</s:else>
        </section>
        <div class="area10"></div>
		<div class="title2"><span>热门收藏</span></div>
        <div class="area10"></div>
        <section class="listpan1">
        	<s:if test="!collectionChnlDocs.isEmpty()">
        	<ul class="list2">
        	<s:iterator value="collectionChnlDocs">
				<li class="ellipsis nowrap" style="width:284px;"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value="doctitle"/>，共被收藏<s:property value='collectcount'/>次。" target="_blank"><s:property value="doctitle"/></a></li>
			</s:iterator>
			</ul>
        	</s:if>
        	<s:else>
        		<span style="color:#333;font-size:14px;">系统暂时没有热门收藏的知识...</span>
        	</s:else>
        </section>
    </section>
</section>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>