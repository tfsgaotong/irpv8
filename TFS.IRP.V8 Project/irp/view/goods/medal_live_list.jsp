<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<ul class="list3">



   
	             
            <div class="rmzj">
			<div class="expert-box">
				<div class="expert-slide">
					<div class="swiper-box">
						<div class="expert-container">
							<div class="swiper-wrapper">
								<ul class="rmzx_news">
									<s:if test="listlivemedal !=null && listlivemedal.size()>0 ">
									<s:iterator value="listlivemedal">
										<li style="height:auto;color:#333;">
										<s:date  format="yyyy-MM-dd hh:mm:ss" name="coverttime" />
										<s:property value="username" />获得一枚【<s:property value="medalname" />】勋章
										</li>
									</s:iterator>
									</s:if>
									<s:else>
										<li style="height:auto;color:#333;">暂时没有勋章动态信息...</li>
									</s:else>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
	        </div>