<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>首页</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/images/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common1.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=rootPath %>view//css/jquery.slideBox.css" type="text/css"></link>
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.slideBox.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>




<script type="text/javascript">
jQuery(function($){


	$('#slide').slideBox({

		duration : 0.1,//滚动持续时间，单位：秒

		easing : 'linear',//swing,linear//滚动特效

		delay : 5,//滚动延迟时间，单位：秒

		hideClickBar : false,//不自动隐藏点选按键

		clickBarRadius : 0

	}); 
});

</script>
</head>

<body>
<s:include value="../include/client_head.jsp"></s:include>
<section class="mainBox">
	<nav class="privateNav">
    	<dl>
        	<dt class="leftBox" style="width: 88px;font-size: 16px;text-indent: 14px;">首页</dt>
        	<dd class="leftBox navBtns">
        	 <li style="padding: 0px ;color:##f3e7e7;font-size: 17px;font-family: Gabriola, sans-serif;">
        	 共有<span style="color: #ce3030;font-size: 40px;"><s:property value="sysUserCount" /></span>用户，
            贡献<span style="color: #ce3030;font-size: 40px;"><s:property value="sysDocumentCount" /></span>知识，
            回答<span style="color: #ce3030;font-size: 40px;"><s:property value="sysQuestion" /></span>问题，
      聚集  <span style="color: #ce3030;font-size: 40px;"><s:property value="sysExport" /></span>专家，
          贡献<span style="color: #ce3030;font-size: 40px;"><s:property value="sysQueCom" /></span>个答案。
            知识价值共计<span style="color: #ce3030;font-size: 40px;"><s:property value="sysDocumentCount*2" /></span><s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('KNOWLEDGE')" />
            </li>
			</dd>
        </dl>
    </nav>
</section>
<section style="margin-top:20px">
    <div class="w1200">
       <%--  <ul class="t_ban">
            <li style="padding: 0px ;color:#4b4747">截至当前时间，共有<span style="color: #ce3030;"><s:property value="sysUserCount" /></span>注册用户，
            <span style="color: #209822;"><s:property value="sysExport" /></span>专家，
            用户贡献了<span style="color: #0c26b3;"><s:property value="sysDocumentCount" /></span>知识，
            回答了<span style="color: #209822;"><s:property value="sysQuestion" /></span>问题，
            专家贡献了<span style="color: #0c26b3;"><s:property value="sysQueCom" /></span>个解答。
            知识价值共计<span style="color: #ce3030;"><s:property value="sysDocumentCount*2" /></span>元。
            </li>
        </ul> --%>
        <div class="banner">
            <!-- 轮播 -->
            
        <div id="slide" class="slideBox" style="width:730px;height:400px;">
		<ul class="items" style="width:730px;height:400px;">
		 <s:iterator value="chnlDocLinks15" status="wt">
	       <s:if test="#wt.index<4">
	         <s:set var="document" value="getIrpDocumentById(docid)" />
	        	 <s:if test="#document.urladdress!='' && #document.urladdress!=null">
					<li>
						<a href="<s:property value='#document.urladdress'/>" target="_blank">
							<img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="730" height="400"/>
							<p  class="img_title" style="width: 500px;"><s:property value='doctitle'/></p>
						</a>
					</li>
				</s:if>
				<s:else>
					<li>
						<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>"  target="_blank">
							<img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="730" height="400"/>
							<p class="img_title" style="width: 500px;"><s:property value='doctitle'/></p>
						</a>
						</li>
				</s:else>
			</s:if>
		</s:iterator>
		</ul>
	</div>    
    <!-- /轮播 -->
    </div>
        <div class="my_psg">
            <ul class="m_tab">
                <li class="current">我的待办信息（<s:property value="count" />）</li>
                <li>我的待阅信息（<s:property value="countnum" />）</li>
                <div class="clear"></div>
            </ul>
            <ul class="tab_cont">
                <li>
                    <table >
                        <tr>
                            <th width="150">标题</th>
                            <th width="80">类型</th>
                            <th width="80">发起人</th>
                            <th width="80">发起时间</th>
                        </tr>
                        <s:iterator value="flowInfos" status="num">
                        <s:set var="flowUser" value="@com.tfs.irp.util.LoginUtil@findUserById(CRUSERID)" />
                        <s:if test="#num.index<7">
                        <tr>
                            <td style="width: 150px;"><a href="javascript:void(0)" onclick="processFlow(<s:property value="FLOWOBJID" />,<s:property value="OBJID" />)"><s:property value="DOCTITLE" /></a></td>
                            <td style="width: 80px;">知识库</td>
                            <td style="width: 80px;"><s:property value="#flowUser.showName" /></td>
                            <td style="width: 80px;"><s:date name="CRTIME" format="MM-dd HH:mm" /></td>
                        </tr>
                        </s:if>
                        </s:iterator>
                    </table>
                    <s:if test="flowInfos.size()>7">
                    <span><a href="<%=rootPath %>user/user_manage.action">更多...</a></span>
                    </s:if>
                </li>
                <li style="display: none">
                    <table>
                        <tr>
                            <th width="150">标题</th>
                            <th width="80">类型</th>
                            <th width="80">发起人</th>
                            <th width="80">发起时间</th>
                        </tr>
                        <s:iterator  value="irpMessageContentlist" status="number">
                        <s:set var="message" value="@com.tfs.irp.util.LoginUtil@findUserById(cruserid)" />
                        <s:if test="#number.index<7">
                        <tr >
                            <td style="width: 150px;"><a  href="<%=rootPath%>microblog/linkmessageview.action" ><s:property value="content" /></a></td>
                            <td style="width: 80px;">消息</td>
                            <td style="width: 80px;"><s:property value="#message.showName" /></td>
                            <td style="width: 80px;"><s:date name="crtime" format="MM-dd HH:mm" /></td>
                        </tr>
                        </s:if>
                        </s:iterator>
                    </table>
                    <s:if test="irpMessageContentlist.size()>7">
                    <span><a href="<%=rootPath %>microblog/linkmessageview.action">更多...</a></span>
                    </s:if>
                </li>
            </ul>
        </div>
        <div class="clear"></div>
        <div class="rsmzsk">
            <h1><s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('KNOWLEDGE_NAME')" /></h1>
            <span></span>
            <div style="width: 558px;float: left;margin-top:22px">
                <div class="tabTitle">
                    <div class="current"><label><s:property value="TwoName"/></label><p class="labBg"></p></div>
                </div>
                <div class="area10"></div>
                <section class="listpan1">
                    <ul class="list1">
                    
                        <s:iterator value="hotChnlDocs">
                        <li>
		                	<em style="width:56px;" class="ellipsis">
		                		<a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
		               		</em>
							<label class="ellipsis">|</label>
							<span style="width:400px;" class="ellipsis">
								<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
								<s:if test="doctitle.length()>20"><s:property value="doctitle.substring(0,20)"/>...</s:if><s:else><s:property value='doctitle'/></s:else>
								</a>
							</span>
							<aside style="" class="ellipsis">
								<!-- <s:property value="hitscount"/>&nbsp;&nbsp; -->
								<s:date name="docpubtime" format="MM.dd"/>&nbsp;&nbsp;
								<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank">
								<s:if test="cruser.length()>5"><s:property value="cruser.substring(0,5)"/>...</s:if><s:else><s:property value='cruser'/></s:else>
								</a>
							</aside>
						</li>
                    	</s:iterator>
                    </ul>
                </section>
            </div>
            <div style="width: 558px;float: right;margin-top:22px">
                <div class="tabTitle">
                    <div class="current"><label><s:property value="oneName"/></label><p class="labBg"></p></div>
                </div>
                <div class="area10"></div>
                <section class="listpan1">
                    <ul class="list1">
                    
                        <s:iterator value="newChnlDocs">
                        <li>
						<em style="width:56px;" class="ellipsis">
							<a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
						</em>
						<label class="ellipsis">|</label>
						<span style="width:400px;" class="ellipsis">
							<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
							<s:if test="doctitle.length()>20"><s:property value="doctitle.substring(0,20)"/>...</s:if><s:else><s:property value='doctitle'/></s:else>
							</a>
						</span>
						<aside style="" class="ellipsis">
							<s:date name="docpubtime" format="MM.dd"/>&nbsp;&nbsp;
							<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank">
							<s:if test="cruser.length()>5"><s:property value="cruser.substring(0,5)"/>...</s:if><s:else><s:property value='cruser'/></s:else>
							</a>
						</aside>
						</li>
                        </s:iterator>
                    </ul>
                    
                </section>

            </div>
            <div class="clear"></div>
        </div>
        <div class="ydwt">
            <h1>您可能遇到的问题</h1>
            <span></span>
            <div class="clear"></div>
            <s:iterator value="chnlDocLinks16" status="wt">
            <s:if test="#wt.index<3">
            <div class="wt_tw">
            	<s:set var="document" value="getIrpDocumentById(docid)" />
                <s:if test="#document.urladdress!='' && #document.urladdress!=null">
		                <div class="tp">
			                <a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
								<img width="350" height="230" src="<s:property value='docCoverPathSource(docid,docflag)' />" />
							</a>
		                </div>
		                <div class="p">
			                <a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
			                <s:property value='doctitle'/>
			                </a>
		            	</div>
            	</s:if>
            		<s:else>
            			<div class="tp">
			                <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
								<img width="350" height="230" src="<s:property value='docCoverPathSource(docid,docflag)' />" />
							</a>
		                </div>
		                <div class="p">
			                <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
			                <s:property value='doctitle'/>
			                </a>
		            	</div>
            		</s:else>
            </div>
            </s:if>
            </s:iterator>
        </div>
        <div class="act">
            <h1>你关注的人动态</h1>
            <span></span>
            	<s:iterator value="irpMicrobloglist" status="wb">
            	<s:if test="#wb.index<4">
            	<s:if test="#wb.count%2==0">
            	            <div class="act_right">
                <ul>
                    <li>
                        <div class="act_img">
                       <s:if test="USERPIC!=NULL&&USERPIC!=''">
               				<img style="width:150px;height:150px" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='USERPIC' />" alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
                			</s:if>
                			<s:else>
                			<img style="width:150px;height:150px" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" >
                			</s:else>
                        <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />" target="_blank">
                			
               			</a>
                        </div>
                    </li>
                     <div class="date_right"><s:date name="CRTIME" format="yyyy-MM-dd HH:mm" /></div>
                    <li class="cont_h"><s:property value='MICROBLOGCONTENT' escapeHtml="false" /></li>
                    <li class="act_msg">
                        <div class="name"><a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="USERID" /></s:url>" target="_blank"><s:property value="getShowPageViewNameByUserId(USERID)" /></a></div>
                        <ul class="zfpldz">
                            <li class="zf">转发(<s:property value='TRANSPONDCOUNT' />)</li>
                            <li class="pl">评论(<s:property  value='COMMENTCOUNT' />)</li>
                        </ul>
                    </li>
                </ul>
             </div>
            	
            	
            	</s:if>
            	<s:else>
            	
            	            <div class="act_left">
                <ul>
                    <li>
                        <div class="act_img">
                        <s:if test="USERPIC!=NULL&&USERPIC!=''">
               				<img style="width:150px;height:150px" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='USERPIC' />" alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
                			</s:if>
                			<s:else>
                			<img style="width:150px;height:150px" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" >
                			</s:else>
                        <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />" target="_blank">
                			
               			</a>
                        </div>
                    </li>
                     <div class="date_right"><s:date name="CRTIME" format="yyyy-MM-dd HH:mm" /></div>
                    <li class="cont_h"><s:property value='MICROBLOGCONTENT' escapeHtml="false" /></li>
                    <li class="act_msg">
                        <div class="name"><a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="USERID" /></s:url>" target="_blank"><s:property value="getShowPageViewNameByUserId(USERID)" /></a></div>
                        <ul class="zfpldz">
                               <li class="zf">转发(<s:property value='TRANSPONDCOUNT' />)</li>
                            <li class="pl">评论(<s:property  value='COMMENTCOUNT' />)</li>
                        </ul>
                    </li>
                </ul>
             </div>
            	
            	</s:else>

                </s:if>
                </s:iterator>
        </div>
        <div class="expert-box">
            <h1>业内热门专家</h1>
            <span></span>
                <div class="expert-slide">
                    <div class="swiper-box">
                        <div class="expert-container">
                            <div class="swiper-wrapper">
                            <s:iterator value="chnlDocLinks18" status="rmzj">
                            <s:if test="#rmzj.index<5">
                                   <div class="swiper-slide ss-one" >
                                    <div class="e-card">
                                    	<s:set var="document" value="getIrpDocumentById(docid)" />
                                        	<s:if test="#document.urladdress!='' && #document.urladdress!=null">
                                        		<div class="e-img">
                                        			<a class="from_expert" href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
	                                                <!-- <img src="http://jnexpert-oss1.oss-cn-beijing.aliyuncs.com/upload/home_page_img/1486698416908037.jpg" alt="" class="jnexpert_bg ex-img "> -->
	                                                <img width="320" height="230" src="<s:property value='docCoverPathSource(docid,docflag)' />" />
	                                                <div class="fb transition">
	                                                    <h3 style="text-align: justify;">
	                                                    <s:if test="#document.docabstract!=null && #document.docabstract.trim()!=''">
														<s:property value="#document.docabstract"/>
														</s:if>
														<s:else>
														暂无简介
														</s:else>
	                                                    </h3>
	                                                </div>
	                                            	</a>
	                                            </div>
	                                            <div class="e-name" style="algin:center;">
		                                        	<a class="from_expert" href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
														<s:if test="doctitle.length()>20"><s:property value="doctitle.substring(0,20)"/>...</s:if><s:else><s:property value="doctitle" /></s:else>
													</a>
												</div>
                                            </s:if>
                                            <s:else>
                                            	<div class="e-img">
	                                            	<a class="from_expert" href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
		                                                <!-- <img src="http://jnexpert-oss1.oss-cn-beijing.aliyuncs.com/upload/home_page_img/1486698416908037.jpg" alt="" class="jnexpert_bg ex-img "> -->
		                                                <img width="320" height="230" src="<s:property value='docCoverPathSource(docid,docflag)' />" />
		                                                <div class="fb transition">
		                                                    <h3 style="text-align: justify;">
		                                                    <s:if test="#document.docabstract!=null && #document.docabstract.trim()!=''">
															<s:property value="#document.docabstract"/>
															</s:if>
															<s:else>
															暂无简介
															</s:else>
		                                                    </h3>
		                                                </div>
		                                            </a>
	                                            </div>
	                                            <div class="e-name" style="algin:center;">
		                                        	<a class="from_expert" href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
														<s:if test="doctitle.length()>20"><s:property value="doctitle.substring(0,20)"/>...</s:if><s:else><s:property value="doctitle" /></s:else>
													</a>
												</div>
                                            </s:else>
                                        <s:set var="document" value="getIrpDocumentById(docid)" />
                                        <div class="e-title overflow"><s:property value="#document.docabstract" /></div>
                                    </div>
                                </div>
                                </s:if>
                                </s:iterator>
                                
                        </div>

                    </div>

                </div>
            </div>
        </div>
        <div class="zxzt">
            <h1>最新专题</h1>
            <span></span>
            <ul>
            	<s:iterator value="chnlDocLinks21" status="zxzt">
            	<s:if test="#zxzt.index<3">
                <li>
                	<s:set var="document" value="getIrpDocumentById(docid)" />
                	<s:if test="#document.urladdress!='' && #document.urladdress!=null">
	                    <div class="zxzt_img">
		                    <a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
							<img width="350" height="230" src="<s:property value='docCoverPathSource(docid,docflag)' />" />
							</a>
	                    </div>
	                    <p>
			                <a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
			               <s:property value='doctitle'/>
			                </a>
		            	</p>
	            	</s:if>
	            	<s:else>
	            		<div class="zxzt_img">
		                    <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
							<img width="350" height="230" src="<s:property value='docCoverPathSource(docid,docflag)' />" />
							</a>
	                    </div>
	                    <p>
			                <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
			               <s:property value='doctitle'/>
			                </a>
		            	</p>
	            	</s:else>
                </li>
                </s:if>
                </s:iterator>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="rsmbk">
            <h1>百科</h1>
            <span></span>
            <ul>
            	<s:iterator value="chnlDocLinks22" status="bk">
            	<s:if test="#bk.index<4">
                <li>
                	<s:set var="document" value="getIrpDocumentById(docid)" />
                    <s:if test="#document.urladdress!='' && #document.urladdress!=null">
                    <div class="rsmbk_img">
	                    <a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
							<img width="265" height="230" src="<s:property value='docCoverPathSource(docid,docflag)' />" />
						</a>
                    </div>
                   	<a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank"> <h2><s:property value='doctitle'/></h2>
                    </a>
                    <p>
                      <s:property value="#document.docabstract" />
	            	</p>
	            	</s:if>
	            	<s:else>
		            	<div class="rsmbk_img">
		                    <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
								<img width="265" height="230" src="<s:property value='docCoverPathSource(docid,docflag)' />" />
							</a>
	                    </div>
	                   	<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"> <h2><s:property value='doctitle'/></h2>
	                    </a>
	                    <p>
	                      <s:property value="#document.docabstract" />
		            	</p>
	            	</s:else>
                </li>
                </s:if>
                </s:iterator>
            </ul>
        </div>
    </div>
</section>
<s:include value="../include/client_foot.jsp"></s:include>
<script type="text/javascript">
$('.m_tab li').eq(0).click(
    function(){
        $('.tab_cont li').css('display','none');
        $('.tab_cont li').eq(0).css('display','block');
        $('.m_tab li').removeClass('current');
        $('.m_tab li').eq(0).addClass('current')
    }
)
$('.m_tab li').eq(1).click(
    function(){
        $('.tab_cont li').css('display','none');
        $('.tab_cont li').eq(1).css('display','block');
        $('.m_tab li').removeClass('current');
        $('.m_tab li').eq(1).addClass('current')
    }
)
</script>



</body>
</html>