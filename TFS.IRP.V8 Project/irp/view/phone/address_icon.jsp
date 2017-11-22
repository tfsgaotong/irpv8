<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 

<!-- 通讯录用户卡片页 -->
<form >
    <s:hidden name="sname" id="sm"/>
    <s:hidden name="searchWord" id="kd"/>
    <s:hidden name="pageNum" id="pageNumform" />
    <s:hidden name="pageSizeclient" id="pageSizeclientform"/>
</form>
<s:if test="dataList.size!=0">
    <div class="tubiao">
        <s:iterator value="dataList">
            <div class="txl_card" style="margin-left:27px; box-shadow: 2px 2px 2px #8fb3ee;">
                <div class="tx">
                    <a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="userid" /></s:url>" target="_blank" title="进去看看">
                        <s:if test="userpic!=null">
					       <img style="width:125px" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='userpic' />" alt="用户图片" />
					    </s:if>
					    <s:else>
					       <s:if test="sex==2">
					          <img src="<%=rootPath %>view/images/female.jpg" alt="用户图片" width="67px" />
					       </s:if>
					       <s:else>
					          <img src="<%=rootPath %>view/images/male.jpg" alt="用户图片" width="67px" />
					       </s:else>
					    </s:else>		
					</a>
                </div>
                <div class="txl_msg">
                    <div class="msg_name" style="position: relative;">
                        <s:property value="truename"/>
                        <s:if test="sex==2">
			                <img src="<%=rootPath %>view/images/txl_icon_04.jpg" />
			            </s:if>
			            <s:else>
			                <img src="<%=rootPath %>view/images/txl_icon_03.jpg" />
			            </s:else>
			            
			            <!-- 判断是否关注 -->
                        <s:if test="focusStatus=='true'">
			                <img data-id="<s:property value='userid' />" data-status="true" class="concern" title="关注"  width="20" height="20" src="<%=rootPath %>view/phone/images/concern_blue.png" style="position: absolute; right: 10px; top:15px; cursor: pointer;"/>
			            </s:if>
			            <s:else>
			                <img data-id="<s:property value='userid' />" data-status="false" class="concern" title="未关注" width="20" height="20" src="<%=rootPath %>view/phone/images/concern_gray.png" style="position: absolute; right: 10px; top:15px; cursor: pointer;"/>
			            </s:else>
                    </div>
                    <div class="msg_phone">手机号：<s:property value="mobile"/></div>
                    <div class="msg_eml" style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">邮箱：<s:property value="email"/></div>
                </div>
                <div class="clear"></div>
                <div class="msg_address">地址：<s:property value="location"/></div>
            </div>
	    </s:iterator>
    </div>
    <div style="clear:both" ></div>
    <div class="main" style="">
        <div class="left" style="width: 94%;">
            <div class="fyh" style="width: 100%;">
                <ul style="width: 100%;"> <s:property value="pageHTML" escapeHtml="false" /></ul>
            </div>
        </div>
    </div>
</s:if>
<s:else>
     暂无用户
</s:else>
<script>
/**
 * 关注方法
 */
function onConcern(userId, imgDom) {
    $.post("<%=rootPath%>microblog/okMicroblogFocus.action", { userid: userId }, function(msg) {
        if (msg == 1) {
            imgDom.attr("src", "<%=rootPath %>view/phone/images/concern_blue.png");
            imgDom.attr("data-status", "true");
            imgDom.attr("title", "关注");
        }
    });
}

/**
 * 取消关注方法
 */
function cancelConcern(userId, imgDom) {
    $.post("<%=rootPath%>microblog/cancelMicroblogFocus.action", { userid: userId }, function(msg) {
        if (msg == 1) {
            imgDom.attr("src", "<%=rootPath %>view/phone/images/concern_gray.png");
            imgDom.attr("data-status", "false");
            imgDom.attr("title", "未关注");
        }
    });
}

/**
 * 点击按钮关注用户
 */
jQuery(".concern").click(function() {
    // 获取dom对象
    var imgDom = jQuery(this);

    // 获取用户Id
    var userId = imgDom.attr("data-id");
    
    // 获取关注状态
    var concernStatus = imgDom.attr("data-status");

    // 如果关注则执行取消关注的方法
    if (concernStatus === "true") {
    	$.dialog.confirm("您确定要取消关注吗？", function(){
    	    cancelConcern(userId, imgDom);
        });
    } else {
        onConcern(userId, imgDom);
    }
});
</script>