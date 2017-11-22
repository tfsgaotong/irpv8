<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<s:if test="irpSchedules.size>0">
<div>
	<div id="carddiv" style="width:760px;position: absolute; background-color:#FFF;">
			<div class="jinzhao1">
				<div class="jinzhao2" style="width:760px;">
					<span class="b222"></span>
					<div class="jz3">
						<table width="760px;" border="1px">
							<thead><tr><th>序号</th><th>名称</th><th>地点</th><th>人员</th><th>开始时间</th><th>结束时间</th><th>备注</th></tr></thead>
							<tbody>
								<s:iterator value="irpSchedules" status="status">
								<tr>
								  	<td style="width: 5%"><s:property value="#status.count"/> </td>
								  	<td style="width: 10%"><s:property value="schname"/> </td>
								  	<td style="width: 10%"><s:property value="place"/> </td>
								  	<td style="width: 20%"><s:property value="lookusers"/> </td>
								  	<td style="width: 12%"><s:date name="starttime" format="yyyy-MM-dd HH:mm"/></td>
								  	<td style="width: 12%"><s:date name="endtime" format="yyyy-MM-dd HH:mm"/></td>
								  	<td style="width: 31%"><s:property value="schbak"/> </td>
								</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="jz4"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</s:if>