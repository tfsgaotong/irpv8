<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 百科词条首页 -->
<html>
<head>
    <title>百科词条</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>view/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>view/css/common1.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>view/css/oacss.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>view/css/wiki-home_2685284.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>view/css/jquery.slideBox.css"/>
    <script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
    <script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=rootPath %>view/js/jquery.slideBox.js"></script>
    <style>
        .mainBox .leftContent{
            width:245px;
            display: inline-block;
        }
        
        .mainBox .rightContent{
            width: 935px;
            margin: 20px 0 0 20px;
            position:absolute;
            left:245px;
            display: inline-block;   
        }
        
        .mainBox .rightContent .rightLogo{
            width: 296px;
            margin-left: 14px;
            display: inline-block;
        }
        
        .mainBox .rightContent .hotTerm{
            display: inline-block;
            width: 445px;
        }

        .mainBox .rightContent .newTerm{
            display: inline-block;
            width: 445px;
            margin-left: 40px;
        }
        
        .nums a{
            width: 30px;
        }
        
        .content_cnt a{
            text-decoration: none;
        }
        
        .userImage{
            cursor: pointer;
        }
        .userImage:hover{
            border: 2px solid #91c6f9;
        }
    </style>
</head>

<body> 
    <jsp:include page="../../view/include/client_head.jsp" />
    <section class="mainBox">
        <nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
            <dl>
                <dt class="leftBox">全部词条分类</dt>
                <dd class="leftBox navBtns">
                </dd>
                <dd class="clear"></dd>
            </dl>
        </nav>
    </section>
    
    <section class="mainBox">
        <!-- 左侧区域 -->
        <section class="leftContent">

            <!-- 分类列表 -->
            <nav class="allBtns">
                <ul>
                    <s:iterator value="listCategory">
                        <li>
                            <a href="<s:url namespace="/term" action="termCategory"><s:param name="categoryId" value="categoryid" /></s:url>">
                                <s:property value="cdesc" />
                            </a>
                            <s:set var="childCate" value="findChildCategoryByParentId(categoryid)" />
                            <s:if test="#childCate!=null && #childCate.size()>0">
                                <div class="moreBtns">
                                    <div class="line">
                                        <s:iterator value="#childCate">
                                            <h1>
                                                <a href="<s:url namespace="/term" action="termCategory"><s:param name="categoryId" value="categoryid" /></s:url>" title="<s:property value="chnldesc" />">
                                                    <s:property value="cdesc" />
                                                </a>
                                            </h1>
                                            <s:set var="grandsonCate" value="findChildCategoryByParentId(categoryid)" />
                                            <s:if test="#grandsonCate!=null && #grandsonCate.size()>0">
                                                <s:iterator value="#grandsonCate">
                                                    <P>
                                                        <a href="<s:url namespace="/term" action="termCategory"><s:param name="categoryId" value="categoryid" /></s:url>" title="<s:property value="chnldesc" />">
                                                            <s:property value="cdesc" />
                                                        </a>
                                                    </P>
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

            <!-- 用户操作行为 -->
            <div id="viewport" style="width:245px;">
                <dl class="feeds" style="padding-bottom: 10px;background: #f5f5f5">
                    <dd class="wgt_marquee_unit">
                        <div id="termEditLogList" class="content" style="background: #f5f5f5">

                        </div>
                    </dd>
                </dl>
            </div>
        </section>
        
        <!-- 中间区域 -->
        <section class="rightContent">
            <!-- 轮播新闻 -->
            <div id="slide" class="slideBox" style="display:inline-block; width:620px; height:300px; vertical-align: top;">
                <ul class="items" style="width:620px;height:300px;">
                    <s:iterator value="chnlDocLinks51" status="wt">
                        <s:if test="#wt.index<4">
                            <s:set var="document" value="getIrpDocumentById(docid)" />
                            <s:if test="#document.urladdress!='' && #document.urladdress!=null">
                                <li>
                                    <a href="<s:property value='#document.urladdress'/>" target="_blank">
                                        <img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="620" height="300"/>
                                        <p class="img_title" style="top:190px;width:400px;"><s:property value='doctitle'/></p>
                                    </a>
                                </li>
                            </s:if>
                            <s:else>
                                <li>
                                    <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>"  target="_blank">
									   <img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="620" height="300"/>
									   <p class="img_title" style="top:190px;width:400px;"><s:property value='doctitle'/></p>
								    </a>
								</li>
						    </s:else>
					    </s:if>
                    </s:iterator>
                </ul>
            </div>    
            
            <!-- 右侧百度百科 -->
	        <div class="rightLogo">
	            <div class="bk" style="margin-top: 10px;">
	        	      <span id="termNum"></span>&nbsp;个词条<br>
	                <span id="termEditNum"></span>&nbsp;次编辑<br>
	                <span id="createTermUserNum"></span>&nbsp;人创建
	            </div>
        	    <input value="创建词条" onclick="linkCreateTwords()" style="background: url(../images/icon-02.png) no-repeat 9px center #62a53b;margin-top: 40px;border-radius:3px; font-size:18px; width: 100%;height:65px;color: white;background-color: #62a53b;" type="button">
	        </div>
	        
	        <!-- 热搜词条 -->
            <section class="hotTerm">
                <div id="hotLemmas">
                    <div class="title1">
                        <h4><a href="#">热搜词条</a></h4>
                    </div>
                    <dl id="hotTermList" class="yesterday content show" style="margin-top:10px; ">
                    </dl>
                </div>
            </section>
            
            <!-- 新增词条 -->
            <section class="newTerm">
                <div id="hotLemmas">
                    <div class="title1">
                        <h4><a href="#">新增词条</a></h4>
                    </div>
                    <dl id="newTermList" class="yesterday content show" style="margin-top:10px">
                    </dl>
                </div>
            </section>
            
            <!-- 百科用户 -->
            <section id="userCards" style="margin-top: 20px;width: 935px;">
                <div class="card users" style="width: 267px;">
                    <div class="flip">
                        <div class="front">
                            <div class="front_abs">
                                <div class="front_abs_tit">百科用户</div>
                                <div class="front_abs_sbt">最庞大的贡献组织</div>
                                <dl class="front_abs_lbl">
                                    <dd></dd>
                                    <dt class="termEditNum">超过0次编辑</dt>
                                    <dd></dd>
                                    <dt class="termNum">协作编写逾0个词条</dt>
                                </dl>
                                <div class="front_abs_rmk">共0人</div>
                            </div>
                            <div class="front_usi">
                                <div class="front_usi_avt">
                                    <div class="front_usi_avt_flg">
                                                                                                            优秀用户<em class="front_usi_avt_flg_tri"></em>
                                    </div>
                                    <img class="userImage" data-userId="" id="termUserImageOne" src="<%=rootPath%>view/images/male.jpg"/>
                                </div>
                                <div id="termUserOne" class="front_usi_nam">暂无用户</div>
                                <div class="front_usi_lem">词条 | 特色词条</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card users" style="width: 267px;margin-left: 67px;">
                    <div class="flip">
                        <div class="front">
                            <div class="front_abs">
                                <div class="front_abs_tit">百科用户</div>
                                <div class="front_abs_sbt">最庞大的贡献组织</div>
                                <dl class="front_abs_lbl">
                                    <dd></dd>
                                    <dt class="termEditNum">超过0次编辑</dt>
                                    <dd></dd>
                                    <dt class="termNum">协作编写逾0个词条</dt>
                                </dl>
                                <div class="front_abs_rmk">共0人</div>
                            </div>
                            <div class="front_usi">
                                <div class="front_usi_avt">
                                    <div class="front_usi_avt_flg">
                                                                                                              优秀用户<em class="front_usi_avt_flg_tri"></em>
                                    </div>
                                    <img class="userImage" data-userId="" id="termUserImageTwo" src="<%=rootPath%>view/images/male.jpg"/>
                                </div>
                                <div id="termUserTwo" class="front_usi_nam">暂无用户</div>
                                <div class="front_usi_lem">词条 | 特色词条</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card users" style="width: 267px;margin-left: 67px">
                    <div class="flip">
                        <div class="front">
                            <div class="front_abs">
                                <div class="front_abs_tit">百科用户</div>
                                <div class="front_abs_sbt">最庞大的贡献组织</div>
                                <dl class="front_abs_lbl">
                                    <dd></dd>
                                    <dt class="termEditNum">超过0次编辑</dt>
                                    <dd></dd>
                                    <dt class="termNum">协作编写逾0个词条</dt>
                                </dl>
                                <div class="front_abs_rmk">共0人</div>
                            </div>
                            <div class="front_usi">
                                <div class="front_usi_avt">
                                    <div class="front_usi_avt_flg">
                                                                                                                优秀用户<em class="front_usi_avt_flg_tri"></em>
                                    </div>
                                    <img class="userImage" data-userId="" id="termUserImageThree" src="<%=rootPath%>view/images/male.jpg"/>
                                </div>
                                <div id="termUserThree" class="front_usi_nam">暂无用户</div>
                                <div class="front_usi_lem">词条 | 特色词条</div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </section>
    </section>
    
    <!--尾部信息-->
    <footer style="position:absolute ;width:100%;background: #fff;top: 1535px;margin-top: 40px;">
	    <section class="mainBox">
	        <span><s:text name="page.foot.info" />&nbsp;|&nbsp;</span>
	        <a href="http://www.ruisiming.com.cn/gyrsm/125_20170314024419.html " target="_blank">关于我们</a>&nbsp;
	        <a href="http://www.ruisiming.com.cn/lxwm/127_20170314031306.html " target="_blank">商务合作</a>&nbsp;
	        <a href="http://www.ruisiming.com.cn/ " target="_blank">官方网站</a>
	    </section>
	</footer>
</body>
<script type="text/javascript">
jQuery(function(){
	
    // 轮播图实现
    $('#slide').slideBox({
        duration : 0.1,//滚动持续时间，单位：秒
        easing : 'linear',//swing,linear//滚动特效
        delay : 5,//滚动延迟时间，单位：秒
        hideClickBar : false,//不自动隐藏点选按键
        clickBarRadius : 0
     });
	 getTermNum();
	 getCreateUserNum();
	 getTermEditCount();
	 getTermEditLogList();
	 getHotTermList();
	 getNewTermList();
	 getCreateTermUserList();
});

/**
 * 加载已经获得审核的词条数量
 */
function getTermNum(){
	jQuery.ajax({
        url:"<%=rootPath%>term/getTermNum.action",
        type:"GET",
        data:{},
        dataType: "json",
        success:function(json){
            jQuery("#termNum").html(json.termNum);
            jQuery(".termNum").text("协作编写逾"+ json.termNum +"个词条");
        }
    });
}

/**
 * 加载编写词条人数
 */
function getCreateUserNum(){
    jQuery.ajax({
        url:"<%=rootPath%>term/getCreateTermUserNum.action",
        type:"GET",
        data:{},
        dataType: "json",
        success:function(json){
            jQuery("#createTermUserNum").html(json.createTermUserNum);
            jQuery(".front_abs_rmk").text("共"+ json.createTermUserNum +"人");
        }
    });
}

/**
 * 加载词条编辑次数
 */
function getTermEditCount(){
    jQuery.ajax({
        url:"<%=rootPath%>termeditlog/getEditCount.action",
        type:"GET",
        data:{},
        dataType: "json",
        success:function(json){
            jQuery("#termEditNum").html(json.editCount);
            jQuery(".termEditNum").text("超过"+ json.editCount +"次编辑");
        }
    });
}

/**
 * 加载词条编辑行为日志
 */
function getTermEditLogList(){
	jQuery.ajax({
        url:"<%=rootPath%>termeditlog/getTermEditLogList.action",
        type:"GET",
        data:{},
        dataType: "json",
        success:function(json){
            if(json.resultStatus === "success"){
            	var length = json.data.length;
            	var htmlStr = "";
                
            	// 拼接html代码
            	for(var i = 0; i < length; i++){
            		htmlStr += "<div class='content_cnt' style='margin-top: 5px;'>"
            			           +"<span>"+ json.data[i].friendlyDate +"</span>&nbsp;"
            			           +"<a class='user'>"+ json.data[i].username +"</a>&nbsp;编辑了&nbsp;"
            			           +"词条&nbsp;<a class='sub'>"+ json.data[i].termName +"</a>"
            			       +"</div>";
            	}
            	jQuery("#termEditLogList").html(htmlStr);
            } else {
            	jQuery("#viewport").hide();
            }
        }
    });
}

/**
 * 加载热搜词条
 */
function getHotTermList(){
	jQuery.ajax({
        url:"<%=rootPath%>term/getHotTermList.action",
        type:"GET",
        data:{},
        dataType: "json",
        success:function(json){
        	if(json.resultStatus === "success"){
                var length = json.data.length;
                var htmlStr = "";
            
                // 拼接html代码
                for(var i = 0; i < length; i++){
                	
                	// 如果词条文本内容为空，则用词条的html内容
                    if(json.data[i].termContent === ""){
	                	// 正则去除html标签，获得内容文本
                        termContent = json.data[i].termContentHtml.replace(/<[^>]+>/g,"");
                    }else{
                        termContent = json.data[i].termContent;
                    }
                	
                    htmlStr += "<dd class='large color-2 minusTop' style='width:413px;height:90px;' data-id='"+ json.data[i].termId +"'>"
                                   +"<a>"
                                       +"<div class='content_tit'>"
                                           +"<span>"+ json.data[i].termName +"</span>"
                                       +"</div>"
                                       +"<div class='content_cnt' style='height:auto; display:-webkit-box; -webkit-box-orient:vertical; -webkit-line-clamp:2; overflow:hidden;'>"+ termContent +"</div>"
                                   +"</a>"
                               +"</dd>";
                }
                jQuery("#hotTermList").html(htmlStr);
            }
        }
    });
}

/**
 * 加载最新词条
 */
function getNewTermList(){
	jQuery.ajax({
        url:"<%=rootPath%>term/getNewTermList.action",
        type:"GET",
        data:{},
        dataType: "json",
        success:function(json){
        	if(json.resultStatus === "success"){
                var length = json.data.length;
                var htmlStr = "";
                
                // 拼接html代码
                for(var i = 0; i < length; i++){
                	var termContent = "";
                	
                	// 如果词条文本内容为空，则用词条的html内容
                	if(json.data[i].termContent === ""){
                		// 正则去除html标签，获得内容文本
                		termContent = json.data[i].termContentHtml.replace(/<[^>]+>/g,"");
                	}else{
                		termContent = json.data[i].termContent;
                	}
                	
                    htmlStr += "<dd class='large color-2 minusTop' style='width:413px;height:90px;' data-id='"+ json.data[i].termId +"'>"
                                   +"<a>"
                                       +"<div class='content_tit'>"
                                           +"<span>"+ json.data[i].termName +"</span>"
                                       +"</div>"
                                       +"<div class='content_cnt' style='height:auto; display:-webkit-box; -webkit-box-orient:vertical; -webkit-line-clamp:2; overflow:hidden;'>"+ termContent +"</div>"
                                   +"</a>"
                               +"</dd>";
                }
                jQuery("#newTermList").html(htmlStr);
            }
        }
    });
}

/**
 * 加载创建词条次数前三的用户
 */
function getCreateTermUserList(){
    jQuery.ajax({
        url:"<%=rootPath%>term/getCreateTermUserList.action",
        type:"GET",
        data:{},
        dataType: "json",
        success:function(json){
        	if(json.resultStatus === "success"){
        		var length = json.data.length;
        		if(length === 1){
        			// 设置用户名
                    jQuery("#termUserOne").text(json.data[0].username);
                    
        			//设置Id
                    jQuery("#termUserImageOne").attr("data-userId", json.data[0].userId);
                    
                    // 设置头像
                    if(json.data[0].userImage !== ""){
                        jQuery("#termUserImageOne").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[0].userImage);
                    }
        		}else if (length === 2){
        			// 设置用户名
                    jQuery("#termUserOne").text(json.data[0].username);
                    jQuery("#termUserTwo").text(json.data[1].username);

                    //设置Id
                    jQuery("#termUserImageOne").attr("data-userId", json.data[0].userId);
                    jQuery("#termUserImageTwo").attr("data-userId", json.data[1].userId);
                  
                    // 设置头像
                    if(json.data[0].userImage !== ""){
                        jQuery("#termUserImageOne").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[0].userImage);
                    }
                    if(json.data[1].userImage !== ""){
                        jQuery("#termUserImageTwo").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[1].userImage);
                    }
        		}else{
        			// 设置用户名
                    jQuery("#termUserOne").text(json.data[0].username);
                    jQuery("#termUserTwo").text(json.data[1].username);
                    jQuery("#termUserThree").text(json.data[2].username);
                    
                    //设置Id
                    jQuery("#termUserImageOne").attr("data-userId", json.data[0].userId);
                    jQuery("#termUserImageTwo").attr("data-userId", json.data[1].userId);
                    jQuery("#termUserImageThree").attr("data-userId", json.data[2].userId);
                    
                    // 设置头像
                    if(json.data[0].userImage !== ""){
                        jQuery("#termUserImageOne").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[0].userImage);
                    }
                    if(json.data[1].userImage !== ""){
                        jQuery("#termUserImageTwo").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[1].userImage);
                    }
                    if(json.data[2].userImage !== ""){
                        jQuery("#termUserImageThree").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[2].userImage);
                    }
        		}
        	}
        }
    });
}

/**
 * 链接到词条详情
 */
function linkVerContent(_termid){
    //浏览
    $.get("<%=rootPath%>term/updatebrowsecount.action",{termid:_termid});
    window.open('<%=rootPath%>term/showTermInfo.action?termid='+_termid);
}

/**
 * 链接到创建词条页面
 */
function linkCreateTwords(){
    window.open('<%=rootPath%>term/createtermwords.action');
}

/**
 * 点击用户头像进入用户详情页
 */
jQuery("#userCards").on("click", "img", function(){
    var imgDom = jQuery(this);
    var userId = imgDom.attr("data-userId");
    if(userId !== ""){
        window.location = "<%=rootPath%>site/client_to_index_person.action?personid=" + userId;
    }
});

/**
 * 点击热搜词条跳转到详情页
 */
jQuery("#hotTermList").on("click", "dd", function(){
	var termId = jQuery(this).attr("data-id");
	linkVerContent(termId);
});

/**
 * 点击最新词条跳转到详情页
 */
jQuery("#newTermList").on("click", "dd", function(){
	var termId = jQuery(this).attr("data-id");
	linkVerContent(termId);
});

var qidval = '<s:property value="qclassifyid"/>';
$(function(){
    //选择头部标签
    //selected('wordterm');
    chTWOrds();
    //获取数据
    if(qidval==''){
        qidval = null;
    }
    getWordList(1,qidval);
});


/**
 * 获取词条列表数据
 */
function getWordList(_pnum,_cid){
    
    var swords = $('#searchtermname').val();
    $.ajax({
        type:'post',
        url:'<%=rootPath%>term/termlist.action',
        data:{
            pagenum:_pnum,
            sword:swords,
            qclassifyid:_cid
        },
        cache:false,
        success:function(content){
            
            $('#termcontent').html(content);
        }
        
    });
}

/**
 * 分页
 */
function pageterm(_pnum){
    $('#termcontent').html("");
    if(qidval==''){
        qidval = null;
    }
    getWordList(_pnum,qidval);
    
}

/**
 * 查询词条关键字
 */
function searchTName(){
    $('#termcontent').html("");
    var swords = $('#searchtermname').val();
    <%if(LoginUtil.getLoginUser().isTermswordManager()){%>
    chTWOrds();
    <%}else{%>
    if(qidval==''){
        qidval = null;
    }
    getWordList(1,qidval);
    <%}%>
}

/**
 *已审核
 */
function allTerm(){
    $('#alltermg').css({'border-bottom-style':'solid'});
    $('#audittermg').css({'border-bottom-style':'none'});
    $('#termwords').css({'border-bottom-style':'none'});
    $('#minetwords').css({'border-bottom-style':'none'});
    $('#illtermg').css({'border-bottom-style':'none'});
    findauditlist(1,2);
}

/**
 * 待审核标签
 */
function auditTerm(){
    $('#alltermg').css({'border-bottom-style':'none'});
    $('#audittermg').css({'border-bottom-style':'solid'});
    $('#termwords').css({'border-bottom-style':'none'});
    $('#minetwords').css({'border-bottom-style':'none'});
    $('#illtermg').css({'border-bottom-style':'none'});
    findauditlist(1,1);
}

/**
 * 词条标签
 */
function chTWOrds(){
    $('#termwords').css({'border-bottom-style':'solid'});
    $('#audittermg').css({'border-bottom-style':'none'});
    $('#alltermg').css({'border-bottom-style':'none'});
    $('#minetwords').css({'border-bottom-style':'none'});
    $('#illtermg').css({'border-bottom-style':'none'});
    getWordList(1,null);
}

/**
 * 个人词条
 */
function mineTWords(){
    $('#minetwords').css({'border-bottom-style':'solid'});
    $('#alltermg').css({'border-bottom-style':'none'});
    $('#audittermg').css({'border-bottom-style':'none'});
    $('#termwords').css({'border-bottom-style':'none'});
    $('#illtermg').css({'border-bottom-style':'none'});
    pageTermPersonal(1);
}

/**
* 非法词条  
*/
function illTerm(){
    $('#illtermg').css({'border-bottom-style':'solid'});
    $('#minetwords').css({'border-bottom-style':'none   '});
    $('#alltermg').css({'border-bottom-style':'none'});
    $('#audittermg').css({'border-bottom-style':'none'});
    $('#termwords').css({'border-bottom-style':'none'});
    illTermList(1);
}

//个人词条
function findPersonalList(_pags){
    var swords = $('#searchtermname').val();
    $.ajax({
        type:'post',
        url:'<%=rootPath%>term/personaltwords.action',
        data:{
            auditnopsize:_pags,
            auditnoword:swords
        },
        async:false,
        cache:false,
        success:function(content){
            $('#termcontent').html(content);
        }
    });
}

function findauditlist(_pags,_status){
    var swords = $('#searchtermname').val();
    $.ajax({
        type:'post',
        url:'<%=rootPath%>term/noaudittlist.action',
        data:{
            auditstatus:_status,
            auditnopsize:_pags,
            auditnoword:swords
        },
        async:false,
        cache:false,
        success:function(content){
            $('#termcontent').html(content);
        }
    });
}

//非法词条
function illTermList(_pags){
var swords = $('#searchtermname').val();
    $.ajax({
        type:'post',
        url:'<%=rootPath%>term/illtermlist.action',
        data:{
            auditnopsize:_pags,
            auditnoword:swords
        },
        async:false,
        cache:false,
        success:function(content){
            $('#termcontent').html(content);
        }
    });
}

//鼠标移动到上面
function choiceTerV(_idstr,_tid){
    var chv = "#"+_idstr+_tid;
    $(chv).css({'background-color':'#FCFCFC'});
}
//鼠标离开
function outTerV(_idstr,_tid){
    var chv = "#"+_idstr+_tid;
    $(chv).css({'background-color':'#FFFFFF'});
}

/**
 * 分页
 */
function pagetermaudit(_pnum){
    $('#termcontent').html("");
    findauditlist(_pnum,1);
}

/**
 * 分页
 */
function pagetermnoeaudit(_pnum){
    $('#termcontent').html("");
    findauditlist(_pnum,2);
}

/**
 * 个人分页
 */
function pageTermPersonal(_pnum){
    $('#termcontent').html("");
    findPersonalList(1);
}

/**
 * 审查版本内容
 */
function linkMGVContent(_tid){
    window.open('<%=rootPath%>term/linkversionmgr.action?termid='+_tid);
}
function linkMGVContentPersonal(_tid){
    
    window.open('<%=rootPath%>term/linkversionpersonal.action?termid='+_tid);
}
</script>
</html>