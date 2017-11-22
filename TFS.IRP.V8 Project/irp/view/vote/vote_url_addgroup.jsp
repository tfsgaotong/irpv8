<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<form id="addgroupform">
<s:hidden name="irpvotetitle.lesscheck" value="0"/>
<s:hidden name="irpvotetitle.checktype" value="0"/>
<ul>
<li id="questionvote">
     <div><span style="color:red;">*</span>投票问题：
          <ul><li><input name="irpvotetitle.votetitle" type="text" class="inputvote"/></li></ul>
     </div>
</li>
<li id="opteionvote">
     <div><span style="color:red;">*</span>投票选项：
        <ul id="optionul">
            <li><span>1</span>.&nbsp;<input name="option" type="text" class="optoninpt"/>&nbsp;&nbsp;&nbsp;URL:&nbsp;&nbsp;<input name="urltext" type="text" class="optoninpt"/> </li>
         </ul>  
     </div>
 </li>
 </ul>
 </form>