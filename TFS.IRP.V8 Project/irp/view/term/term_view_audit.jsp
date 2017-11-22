<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<style>
.card {
    position: relative;
    display: inline-block;
    width: 220px;
    height: 190px;
    margin-right: 7px;
    margin-bottom: 10px;
    border: 1px solid #EAEAEA;
    box-shadow: 1px 1px 1px gray;
    vertical-align: top;
    cursor: pointer;
}

.card:hover {
    background-color: #EAEAEA;
}

.card .card-img {
    width: 100%;
}

.card .card-title {
    display: inline-block;
    width: 145px;
    margin: 10px 0;
    padding: 0 5px;
    vertical-align:top;
    font-size: 15px;
    font-family: Microsoft YaHei;
    color: #393E46;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.card .card-version {
    position: absolute;
    top: 120px;
    right: 2px;
    margin-top:10px;
    font-size: 10px;
    font-family: Microsoft YaHei;
    color: #393E46;
}

.card .card-date {
    position: absolute;
    bottom: 0;
    right: 2px;
    font-size: 10px;
    font-family: Microsoft YaHei;
    color: #393E46;
}
</style>
<!-- 已审核词条卡片列表 -->
<s:if test="termWithAttachedList.size()>0">
    <s:iterator value="termWithAttachedList">
        <div class="card" title="<s:property value="termname" />" id="termview<s:property value="termid" />" onclick="linkVerContent(<s:property value="termid" />)">
            <s:if test="imgSrc == ''">
                <img src="<%=rootPath%>view/term/images/term_img_error.png" alt="词条图片" width="220" height="120" class="card-img">
            </s:if>
            <s:else>
                <img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='imgSrc' />" " alt="词条图片" width="220" height="120" class="card-img">
            </s:else>
            <div class="card-title" style="line-height: 18px">
                <s:property value="termname" />
            </div>
            <div class="card-version" style="line-height: 18px">
                <s:property value="version" />版本
            </div>
            <div class="card-date">
                创建时间：
                <s:date format="yyyy-MM-dd" name="crtime" />
            </div>
            
        </div>
    </s:iterator>
    <div class="left" style="float: none;width: 930px;margin-top:20px;">
        <div style="margin:0 0 0 0;width: 930px;" class="fyh">
            <ul style="width: 918px;">
                <s:property value="pagehtml" escapeHtml="false" />
            </ul>
        </div>
    </div>
</s:if>
<s:else>
    <br />
    <br />
    <div style="">暂无相关词条</div>
</s:else>