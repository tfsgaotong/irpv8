<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<form id="addgroupform">
<ul>
<li id="checkli">&nbsp;单选/多选：<select id="addcheck" name="irpvotetitle.checktype" onchange="checkoneormore(this.id)"><option value="1">单选</option><option  value="2">复选</option> </select>
	    &nbsp;&nbsp;&nbsp;<span id="lesscheck" style="display:none;">至少选择<input style="width: 30px;" value="1" type="text" name="irpvotetitle.lesscheck"/> 个</span>
	    &nbsp;&nbsp;&nbsp;<span id="morecheck" style="display:none;">至多选择<input style="width: 30px;" value="1" type="text" name="irpvotetitle.morecheck"/> 个</span>
</li>
<li id="questionvote">
     <div><span style="color:red;">*</span>投票问题：
          <ul><li><input name="irpvotetitle.votetitle" type="text" class="inputvote"/></li></ul>
     </div>
</li>
<li id="opteionvote">
     <div><span style="color:red;">*</span>投票选项：
        <ul id="optionul">
            <li><span>1</span>.&nbsp;<input name="option" type="text" class="optoninpt"/>&nbsp;&nbsp;&nbsp;URL:&nbsp;&nbsp;<input name="urltext" type="text" class="optoninpt"/> </li>
            <li><span>2</span>.&nbsp;<input name="option" type="text" class="optoninpt"/>&nbsp;&nbsp;&nbsp;URL:&nbsp;&nbsp;<input name="urltext" type="text" class="optoninpt"/></li>
         </ul>  
     </div>
     <div>
        <ul style="margin-top: 3px;">
            <li id="addoptionli"><a href="javascript:void(0)" onclick="addoption()">再加一项</a><span style="float:right;padding-right: 76px;color:gray;">至少设置两项，每项最多200个字 </span></li>
        </ul>
     </div>
 </li>
 </ul>
 </form>