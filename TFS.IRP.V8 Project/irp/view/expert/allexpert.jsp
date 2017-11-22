<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>专家</title>
<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>client/css/common1.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="<%=rootPath %>view//css/jquery.slideBox.css" type="text/css"></link>
<script src="<%=rootPath%>client/images/modernizr.custom.js"></script>
<script src="<%=rootPath%>client/images/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jquery.slideBox.js"></script>
<style>
.wd_right ul{padding-top:4px;}
.wd_right li{<%-- background: url(<%=rootPath%>client/images/dot_08.jpg) left center no-repeat; --%>}
.wd_right li a{width: 100%;display: block;line-height: 30px;font-size: 14px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;text-indent: 14px;}

</style>
<jsp:include page="../include/client_skin.jsp" />
<script language="JavaScript" type="text/javascript">
//初始化右侧类别树
/* $(function(){ 
	page(1);
}); */

jQuery(function($){


	$('#slide').slideBox({

		duration : 0.1,//滚动持续时间，单位：秒

		easing : 'linear',//swing,linear//滚动特效

		delay : 5,//滚动延迟时间，单位：秒

		hideClickBar : false,//不自动隐藏点选按键

		clickBarRadius : 0

	});
});

//向专家提问
function askExpert(expertId,expertName) {
	window.open('<%=rootPath %>question/linkeditsubmit.action?expertId='+expertId);
	
}
</script>
</head>

<body onload="selected('expert')" style="background: url()">
	<div class="bg01">
		<jsp:include page="../../view/include/client_head.jsp" />
		<section class="mainBox"> 
			<nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
			<dl>
				<dt class="leftBox">全部专家分类</dt>
				
				<dd class="clear"></dd>
			</dl>
			</nav> 
		</section>
		<section class="mainBox"> 
		<section class="layoutLeft">
		<nav class="allBtns">
		<ul>
			<s:iterator value="list">
				<li><a
					href="<s:url namespace="/expert" action="listcategory"><s:param name="categoryId" value="categoryid" /></s:url>"><s:property
							value="cdesc" />
				</a> <s:set var="childCate"
						value="findChildCategoryByParentId(categoryid)" /> <s:if
						test="#childCate!=null && #childCate.size()>0">
						<div class="moreBtns">
							<div class="line">
								<s:iterator value="#childCate">
									<h1>
										<a
											href="<s:url namespace="/expert" action="listcategory"><s:param name="categoryId" value="categoryid" /></s:url>"
											title="<s:property value="chnldesc" />"><s:property
												value="cdesc" />
										</a>
									</h1>
									<s:set var="grandsonCate"
										value="findChildCategoryByParentId(categoryid)" />
									<s:if test="#grandsonCate!=null && #grandsonCate.size()>0">
										<s:iterator value="#grandsonCate">
											<P>
												<a
													href="<s:url namespace="/expert" action="listcategory"><s:param name="categoryId" value="categoryid" /></s:url>"
													title="<s:property value="chnldesc" />"><s:property
														value="cdesc" />
												</a>
											</P>
										</s:iterator>
									</s:if>
								</s:iterator>
							</div>
						</div>
					</s:if></li>
			</s:iterator>
		</ul>
		</nav>

		<div class="wd_right" style="width:245px;float: left; margin-top: 10px;">
			<div class="title1">
				<h4 style="padding-left: 15px;">
					<a href="#">精彩问答</a>
				</h4>
			</div>
			<ul>
				<s:iterator value="connqlist">
					<li style="width:245px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;font-size:14px;margin:8px 0;">
  	  	   <a  href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>" target="_blank" onclick="hits(<s:property value="questionid"/>)" title="<s:property value='title'/>">
		  	  <s:if test="title!=''">
		  	  	<s:property value='title'/>
		  	  </s:if>
		  	  <s:else>
		  	  	<s:property value='htmlcontent'/>
		  	  </s:else>
		  </a>
  	  </li>
				</s:iterator>
			</ul>
		</div>

		</section>
		 <section class="layoutMiddle1">
		<div class="banner">
        
	          <!-- 轮播 -->
	            
	        <div id="slide" class="slideBox" >
			<ul class="items" >
			 <s:iterator value="chnlDocLinks50" status="wt">
		       <s:if test="#wt.index<4">
		         <s:set var="document" value="getIrpDocumentById(docid)" />
		        	 <s:if test="#document.urladdress!='' && #document.urladdress!=null">
						<li>
							<a href="<s:property value='#document.urladdress'/>" target="_blank">
								<img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="935" height="400"/>
								<p class="img_title"><s:property value='doctitle'/></p>
							</a>
						</li>
					</s:if>
					<s:else>
						<li>
							<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>"  target="_blank">
								<img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="935" height="400"/>
								<p class="img_title"><s:property value='doctitle'/></p>
							</a>
							</li>
					</s:else>
				</s:if>
			</s:iterator>
			</ul>
		</div>    
	    <!-- /轮播 -->

        </div>
		<div class="rmzj">
			<div class="title1">
				<h4>
					<a href="#">热门专家</a>
				</h4>
			</div>
			<div class="expert-box">
				<div class="expert-slide">
					<div class="swiper-box">
						<div class="expert-container">
							<div class="swiper-wrapper">
								<s:if test="listExpert.size()>0">
									<s:iterator value="listExpert">
										<div class="swiper-slide ss-one"
											style="text-align: center;margin-left: -7px">
											<div class="e-card">
												<div class="e-img">
													<a class="from_expert" href="<s:url namespace="/site" action="client_to_expert"><s:param name="personid" value="userid" /></s:url>" target="_blank" title="<s:property value="username" />">
														<!-- <img src="http://jnexpert-oss1.oss-cn-beijing.aliyuncs.com/upload/home_page_img/1486698416908037.jpg" alt="" class="jnexpert_bg ex-img "> -->
														<s:if test="userpic!=null">
															<img
																src="<%=rootPath%>file/readfile.action?fileName=<s:property value='userpic' />"
																alt="用户图片" class="jnexpert_bg ex-img " />
														</s:if> <s:else>
															<s:if test="sex==2">
																<img src="<%=rootPath%>client/images/female.jpg"
																	alt="用户图片" class="jnexpert_bg ex-img " />
															</s:if>
															<s:else>
																<img src="<%=rootPath%>client/images/male.jpg"
																	alt="用户图片" class="jnexpert_bg ex-img " />
															</s:else>
														</s:else>
														<div class="fb transition">
															<h3 style="text-align: justify;">
																<s:if test="expertintro!=null && expertintro.trim()!=''">
																	<s:property value="expertintro" />
																</s:if>
																<s:else>
																暂无简介
																</s:else>
															</h3>
														</div> </a>
												</div>
												<div class="e-name">
													<a class="from_expert" href="<s:url namespace="/site" action="client_to_expert"><s:param name="personid" value="userid" /></s:url>" target="_blank" title="<s:property value="username" />">
														<s:property
															value="username" />
													</a> <a href="javascript:void(0)"
														onclick="askExpert(<s:property value="userid"/>,'<s:property value="username"/>')">+提问+</a>
												</div>
												<div class="e-title overflow">
												<s:if test="expertintro!=null && expertintro.trim()!=''">
													<s:property value="expertintro" />
												</s:if>
												<s:else>
													暂无简介
												</s:else>
												</div>
											</div>
										</div>
									</s:iterator>
								</s:if>
								<s:else>
							 暂无专家
							</s:else>
							</div>
						</div>

					</div>
				</div>
			</div>
	</div>

			<div class="zj_left">
				<div class="title1">
					<h4>
						<a href="#">推荐专家</a>
					</h4>
				</div>
								<s:iterator value="recExpert" status="index">
					<div class="swiper-slide ss-one" 
								  <s:if test="#index.count%2==0">style="float: right;text-align: center;margin-right: 0px;"</s:if> style="text-align: center;margin-right: 0px;" >

											<div class="e-card">
												<div class="e-img">
													<a class="from_expert" href="<s:url namespace="/site" action="client_to_expert"><s:param name="personid" value="userid" /></s:url>" target="_blank" title="<s:property value="username" />">
														<!-- <img src="http://jnexpert-oss1.oss-cn-beijing.aliyuncs.com/upload/home_page_img/1486698416908037.jpg" alt="" class="jnexpert_bg ex-img "> -->
														<s:if test="userpic!=null">
															<img
																src="<%=rootPath%>file/readfile.action?fileName=<s:property value='userpic' />"
																alt="用户图片" class="jnexpert_bg ex-img " />
														</s:if> <s:else>
															<s:if test="sex==2">
																<img src="<%=rootPath%>client/images/female.jpg"
																	alt="用户图片" class="jnexpert_bg ex-img " />
															</s:if>
															<s:else>
																<img src="<%=rootPath%>client/images/male.jpg"
																	alt="用户图片" class="jnexpert_bg ex-img " />
															</s:else>
														</s:else>
														<div class="fb transition">
															<h3 style="text-align: justify;">
																<s:if test="expertintro!=null && expertintro.trim()!=''">
																	<s:property value="expertintro" />
																</s:if>
																<s:else>
																暂无简介
																</s:else>
															</h3>
														</div> </a>
												</div>
												<div class="e-name">
													<a class="from_expert" href="<s:url namespace="/site" action="client_to_expert"><s:param name="personid" value="userid" /></s:url>" target="_blank" title="<s:property value="username" />">
														<s:property
															value="username" />
													</a> <a href="javascript:void(0)"
														onclick="askExpert(<s:property value="userid"/>,'<s:property value="username"/>')">+提问+</a>
												</div>
												<div class="e-title overflow">
												<s:if test="expertintro!=null && expertintro.trim()!=''">
													<s:property value="expertintro" />
												</s:if>
												<s:else>
													暂无简介
												</s:else>
												</div>
											</div>
										</div>
				</s:iterator>
				<div class="clear"></div>
				<div class="title1">
					<h4>
						<a href="#">专家访谈</a>
					</h4>
				</div>
				<s:iterator value="chnlDocLinks27" status="index">
				<div class="tjzj" <s:if test="#index.count%2==0">style="float: right;"</s:if>>
					<div class="zj_pic">
						<s:set var="document" value="getIrpDocumentById(docid)" />
                			<s:if test="#document.urladdress!='' && #document.urladdress!=null">
								<a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
								<img width="200" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
								</a>
							</s:if>
							<s:else>
								<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
								<img width="200" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
								</a>
							</s:else>
					</div>
					<div style="width: 100%;font-size: 14px;text-align: center;color: #333;line-height: 32px;">
					<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></div>
				</div>
				</s:iterator>
			</div>

			<div class="zj_right">
				<div class="title1">
					<h4>
						<a href="#">专家观点</a>
					</h4>
				</div>
				<div class="zxwk">
				<s:iterator value="chnlDocLinks26" status='st'>
				<s:if test="#st.Odd">
					<div class="zxwk_item">
						<img src="<%=rootPath%>client/images/zxwk_ic_14.png" class="zl" />
						<s:set var="document" value="getIrpDocumentById(docid)" />
                			<s:if test="#document.urladdress!='' && #document.urladdress!=null">
								<div class="zj_pic">
									<a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
									<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
									</a>
								</div>
								<div class="wz">
									<a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
									<s:property value='doctitle'/></a>
									<span>—— <a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank" title="<s:property value="cruser" />"><s:property value='cruser'/></a></span>
								</div>
							</s:if>
							<s:else>
								<div class="zj_pic">
									<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
									<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
									</a>
								</div>
								<div class="wz">
									<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
									<s:property value='doctitle'/></a>
									<span>—— <a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank" title="<s:property value="cruser" />"><s:property value='cruser'/></a></span>
								</div>
							</s:else>
						<div class="clear"></div>
					</div>
				</s:if>
				<s:else>
					
					<div class="zxwk_item">
						<img src="<%=rootPath%>client/images/zxwk_ico_14.png" class="zr" />
						<s:set var="document" value="getIrpDocumentById(docid)" />
							<!-- 有知识链接 -->
                			<s:if test="#document.urladdress!='' && #document.urladdress!=null">
								<div class="wz">
									<a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
									<s:property value='doctitle'/></a>
									<span>—— <a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank" title="<s:property value="cruser" />"><s:property value='cruser'/></a></span>
								</div>
								<div class="zj_pic">
									<a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
									<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
									</a>
								</div>
							</s:if>
							<!-- 无知识链接 -->
							<s:else>
								<div class="wz">
									<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
									<s:property value='doctitle'/></a>
									<span>—— <a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank" title="<s:property value="cruser" />"><s:property value='cruser'/></a></span>
								</div>
								<div class="zj_pic">
									<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
									<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
									</a>
								</div>
							</s:else>
						<div class="clear"></div>
					</div>
					</s:else>
				</s:iterator>	
				</div>
			</div>
			
			
		</section> 
		</section>
		<div class="area10"></div>
		<footer>
		<section class="mainBox">
		<s:include value="../include/client_foot.jsp"></s:include></section></footer>
	</div>
</body>
</html>