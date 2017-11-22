<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<!-- <div class="title1">
	<h4>
		<a href="#">热门专家</a>
	</h4>
</div> -->
<form id="pageForm">
	<s:hidden name="categoryId" id="categoryId" />
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
</form>
<div class="expert-box">
	<div class="expert-slide">
		<div class="swiper-box" >
			<div class="expert-container">
				<div class="swiper-wrapper">
					<s:if test="listExpert.size()>0">
						<s:iterator value="listExpert">
							<div class="swiper-slide ss-one"
								style="text-align: center;padding-left: 20px;margin-right: 15px;margin-top: 0px;margin-bottom: 20px;">
								<div class="e-card">
									<div class="e-img">
										<a class="from_expert" href="<s:url namespace="/site" action="client_to_expert"><s:param name="personid" value="userid" /></s:url>" target="_blank" title="<s:property value="username" />">
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
													<img src="<%=rootPath%>client/images/male.jpg" alt="用户图片"
														class="jnexpert_bg ex-img " />
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
										<s:property value="username" />
										</a> <span class="STYLE1"> <a href="javascript:void(0)"
											onclick="askExpert(<s:property value="userid"/>,'<s:property value="username"/>')">+提问+</a>
										</span>
									</div>
									<div class="e-title overflow">
									<s:if test="expertintro!=null && expertintro.trim()!=''">
										<s:property value="expertintro" />
									</s:if>
									<s:else>
										暂无简介
									</s:else>
									</div>
									<!-- <div class="e-tab overflow">
										<span class="blue">互联网金融</span> <span class="blue">大数据</span>
										<span class="blue">人工智能</span>
									</div> -->
								</div>
							</div>
						</s:iterator>
					</s:if>
				</div>

			</div>

		</div>

	</div>
</div>
<s:if test="listExpert.size()>0">
	<div bgcolor="#FFFFFF">
		<div colspan="9" align="right" class="clientpage" style="width:890px;">
			<ul>
				<s:property value="pageHTML" escapeHtml="false" />
			</ul>
		</div>
	</div>
</s:if>
<s:else>
 	<div style="width:955px;min-height:100%;line-height:300px;text-align:center;color:#ccc;font-size:20px;">暂无专家</div>
</s:else>


