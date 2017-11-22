<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>知识地图-<s:property value="irpChannel.chnldesc" /></title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
 
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
<style type="text/css">
.cover{position:relative;display:block;width:170px;height:170px;margin:0 auto;background-size:cover}
.daohangselect {
    background: #5f9ddd;
    color: #fff;
    font-style: normal;
	height: 24px;
	padding: 0 6px;
	border-radius: 3px;
	line-height: 24px;
	display: inline-block;
	margin: 0 6px;
	cursor: pointer;
}

.daohang {
   font-style: normal;
	height: 24px;
	padding: 0 6px;
	border-radius: 3px;
	line-height: 24px;
	display: inline-block;
	margin: 0 6px;
	cursor: pointer;
}


.daohang:hover{
 	background: #5f9ddd;
    color: #fff;
   font-style: normal;
	height: 24px;
	padding: 0 6px;
	border-radius: 3px;
	line-height: 24px;
	display: inline-block;
	margin: 0 6px;
	cursor: pointer;
}
</style>
<script type="text/javascript">
var c_name='tfs_irp_map';
var c_validDay=365;
$(function(){
 
	var c_value = $.cookie(c_name);
	if(c_value){
		if(c_value=='list'){
			$('#list').show();
		}else if(c_value=='view'){
			$('#view').show();
		}else{
			$.cookie(c_name,'list',{expires:c_validDay});
			$('#list').show();
		}
	}else{
		$.cookie(c_name,'list',{expires:c_validDay});
		$('#list').show();
	}
});

function tabView(tabid){
	$('.resultList').each(function(){
		$(this).hide();
	});
	$('#'+tabid).show();
	if($.cookie(c_name)!=tabid){
		$.cookie(c_name,tabid,{expires:c_validDay});
	}
}

function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	$('#pageForm').submit();
}
function DaoHang(pageNum){
		var _asseroomid="";
		 $("input[name='channels']:checked").each(
			 function(){
				 _asseroomid+=$(this).val() + ',';
		});
		if(_asseroomid==""){
					$.messager.alert("操作提示","请您先选择要删除的对象");
				return false;
		}else{
			_asseroomid=_asseroomid.substring(0, _asseroomid.length-1);
		}
		window.location='<%=rootPath  %>document/document_map_list.action?channelidss='+_asseroomid+"&pageNum="+pageNum;

	}
	function selectAll(_id){
	var x=$("#"+_id).attr('_falg');
	if(x=="0"){
		$("#divChlid"+_id+" input[name='channels']").attr("checked","checked"); 
		$("#"+_id).attr('_falg',"1");
		return true;
	}else{
		$("#divChlid"+_id+" input[name='channels']").removeAttr("checked"); 
		$("#"+_id).attr('_falg',"0");
	}
	}
	
	function listPage(pageNum){
	var channelidss= $("#channelidss").val();
		var result = $.ajax({
		url:'<%=rootPath %>document/document_map_list_page.action',
		type: "GET",
		async: false,
		cache : false,
		data: {
			channelidss: channelidss,
			pageNum:pageNum
		}
	}).responseText;
	$('#result').html(result);
	}
function daohangClick(_id){
	var channelidss= $("#channelidss").val();
		var classname=$("#em"+_id).prop("className");
		if(classname=="daohangselect"){
			$("#em"+_id).removeClass("daohangselect");
			$("#em"+_id).addClass("daohang");
			var removeid="";
			removeid=$("#em"+_id).attr('_value');
			if(removeid!=""&&channelidss!=""){
				var chids=channelidss.split(",");
				channelidss="";
				for(var i in chids){
					if(removeid!=chids[i]){
						channelidss=channelidss+chids[i]+",";
					}
				}
				if(channelidss!=""){
					channelidss=channelidss.substring(0,channelidss.length-1);
				}
			}
		}else{
			var sib=$("#td"+_id).parent();;
			var removeid="";
			sib=$(sib).find("em").each(function(){
				var a=$(this).prop("className");
				if(a=="daohangselect"){
					removeid=$(this).attr('_value');
				}
				$(this).removeClass("daohangselect");
				$(this).addClass("daohang");
			});
			if(removeid!=""&&channelidss!=""){
				var chids=channelidss.split(",");
				channelidss="";
				for(var i in chids){
					if(removeid!=chids[i]){
						channelidss=channelidss+chids[i]+",";
					}
				}
				if(channelidss!=""){
					channelidss=channelidss.substring(0,channelidss.length-1);
				}
			}
			$("#em"+_id).removeClass("daohang");
			$("#em"+_id).addClass("daohangselect");
			if(channelidss!=""){
				channelidss=channelidss+","+$("#em"+_id).attr('_value');
			}else{
				channelidss=$("#em"+_id).attr('_value');
			}
		}
		$("#channelidss").val(channelidss);
		if(channelidss==""){
			pageAll(1);
		}else{
			listPage(1);
		}
	}	
	
</script>
</head>

<body>
<form id="pageForm" action="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="isLeft" value="isLeft" /></s:url>" method="post">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize"/>
</form>
	<s:include value="../include/client_head.jsp"></s:include>
	<section class="mainBox">
	<nav class="privateNav">
    	<dl>
        	<dt class="leftBox">全部知识地图</dt>
            <dd class="leftBox navBtns">
            	<a href="<s:url namespace="/document" action="document_list" />">知识分类</a>
            	<a href="<s:url namespace="/document" action="map_index" />" class="current">知识地图</a>
            	<a href="<s:url namespace="/document" action="document_subject_list" />">知识专题</a>
            </dd>
            <dd class="clear"></dd>
        </dl>
    </nav>
</section>
<section class="mainBox">
	<article class="location">
	<%-- <s:iterator value="rootChannels" status="index">
		<s:if test="#index.first">
		<strong><a href="<s:url namespace="/document" action="map_index" />" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></strong>
		</s:if>
		<s:else>
		 > <a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
		</s:else>
	</s:iterator> --%>
	
	<strong><a href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="maptype" /></s:url>" title="<s:property value="maptypeName" />"><s:property value="maptypeName" /></a></strong>><strong><s:property value="irpChannel.chnldesc" /></strong>
	</article>
	<section class="layoutLeft">
        <nav class="folders">
		<s:iterator value="irpChannels">
        	<div class="folder">
        		 <s:set var="childChnls11" value="findChildMapByParentId(channelid)" />
              		 <s:if test="#childChnls11!=null && #childChnls11.size()>0">
            		<p><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="maptype" value="maptype" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></p>
                  	</s:if>
                  	<s:else>
                  	<p><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="isLeft" value="1" /><s:param name="maptype" value="maptype" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></p>
                  	</s:else>
                <s:set var="childChnls" value="findChildMapByParentId(channelid)" />
                <s:if test="#childChnls!=null && #childChnls.size()>0">
                <section>
                	<ul>
                	<s:iterator value="#childChnls">
                		<%--  <s:set var="childChnls1" value="findChildMapByParentId(channelid)" />
                		 <s:if test="#childChnls1!=null && #childChnls1.size()>0">
                    		<li><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    	</s:if>
                    	<s:else>
                    		<li><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="isLeft" value="1" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    	</s:else> --%>
                    	<li><s:property value="chnldesc" /></li>
                    </s:iterator>
                    </ul>
                </section>
                </s:if>
            </div>
		</s:iterator>
        </nav>
	</section>
	<s:if test="countChannel==1">
	<script type="text/javascript">
	$(function(){
	pageOne(1);
	
	});
	
	function pageOne(pageNum){
	var channelidOne =$("#channelidOne").val();
		var result = $.ajax({
		url:'<%=rootPath %>document/document_map_one_list_page.action',
		type: "GET",
		async: false,
		cache : false,
		data: {
			channelidOne: channelidOne,
			pageNum:pageNum
		}
	}).responseText;
	$('#result').html(result);
	}
	function oneClick(_id){
		$("#channelidOne").val(_id);
		pageOne(1);
	}
	</script>
	<div id="image" style="width:950px;border:1px solid #ccc;overflow:hidden;padding-top:10px;">
	<input id="channelidOne" type="hidden" value="<s:property value='channelidOne' />" >
	<%-- <s:iterator value="rootChannels"  status="index">
	<s:if test="#index.first"> --%>
	 <s:set var="childChnls" value="findChildMapByParentId(channelid)" />
	 &nbsp; &nbsp;
		<s:iterator value="#childChnls" status="index2">
		  <s:if test="#childChnls!=null && #childChnls.size()>0">
		  	<s:set var="child" value="findChildMapByParentId(channelid)" />
		  	<s:if test="#child!=null && #child.size()>0">
		  		<s:if test="imageid!=null">
                 <s:iterator value="imageid.split(',')" var="attid"  status="index1">
                  <s:if test="#index1.first">
                  <a href="javascript:void;" onclick="oneClick(<s:property value='channelid' />)" >
                     <img  src="<s:property value='coverPath(#attid)' />" />
                    </a>
                  </s:if>
                    </s:iterator>
                 </s:if>
                 <s:else>
                 <a href="javascript:void;" onclick="oneClick(<s:property value='channelid' />)" >
                    <img  src="<%=rootPath %>client/images/a1_0<s:property value='#index2.count' />.jpg" />
                    </a>
                 </s:else> 
		  	</s:if>
		  	<s:else>
			   <s:if test="imageid!=null">
                 <s:iterator value="imageid.split(',')" var="attid"  status="index1">
                  <s:if test="#index1.first">
                  <a href="javascript:void;" onclick="oneClick(<s:property value='channelid' />)" >
                     <img  src="<s:property value='coverPath(#attid)' />" />
                    </a>
                  </s:if>
                    </s:iterator>
                 </s:if>
                 <s:else>
                 <a href="javascript:void;" onclick="oneClick(<s:property value='channelid' />)" >
                    <img  src="<%=rootPath %>client/images/a1_0<s:property value='#index2.count' />.jpg" />
                    </a>
                  
                 </s:else> 
              </s:else>
               </s:if>
          </s:iterator>
<%--                  </s:if>
	</s:iterator> --%>
	</div>
</s:if>
<s:elseif test="countChannel==2">


<div id="main" style="width:900px;height:400px;border:1px solid #ccc;margin-left: 50px;padding:1px;overflow:hidden;"></div>
<input id="channelidss" type="hidden" value="<s:property value='channelidss' />" >
<script type="text/javascript" src="<%=rootPath %>view/js/echarts3/esl.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/echarts3/src/echarts.js"></script>
 <script type="text/javascript" src="<%=rootPath %>view/js/echarts3/theme/vintage.js"></script>
	<script type="text/javascript">
	$(function(){
	
	  require.config({
		    packages: [
		        {
		            name: 'echarts',
		            location: '<%=rootPath %>view/js/echarts3/src',
		            main: 'echarts'
		        },
		        {
		            name: 'zrender',
		            location: '<%=rootPath %>view/js/zrender3/src', 
		            main: 'zrender'
		        }
		    ]
		});                 
	
        
         require(
            [
                'echarts',
                'echarts/chart/heatmap' ,
                'echarts/component/legend',
                'echarts/component/visualMap',
                'echarts/component/grid',
                'echarts/component/polar',
                'echarts/component/tooltip'
            ],
             function (ec) {
                var myChart = ec.init(document.getElementById('main'));
                myChart.setOption(option); 
                myChart.on('click', function (params) {
				for(var i in data3){
				    var s=data3[i];
					    if(s[0]==params.data[0]&&s[1]==params.data[1]){
					        var str=s[2]+","+s[3];
					        $("#channelidss").val(str);
					        listPage(1);
					    }
					}		
				});
            }
        );
        
        listPage(1);
});	
	   
 var hours =${xJson};
var days =${yJson};

var data = ${xyJson};
var data3 = ${channelidJson};
data = data.map(function (item) {
    return [item[0], item[1], item[2]];
});

var option = {
	title : {
	text : '${xName}',
	x : "center",
	y : "top"
	},
    tooltip: {
        position: 'top',
          formatter: function(params) {return "文章："+params.data[2]; }
    },
    animation: true,
    grid: {
        height: '50%',
        y: '10%'
    },
    xAxis: {
    	name:'${xName}',
        type: 'category',
        data: hours,
        splitArea: {
            show: true
        }
    },
    yAxis: {
    	name:'${yName}',
    	color:'red',
        type: 'category',
        data: days,
        splitArea: {
            show: true
        }
    },
    visualMap: {
        color:['#33CCFF','#DDFF77'],
        itemWidth: 20,
        itemHeight: 150,
        min: 0,
        max: 20,
        calculable: true,
        orient: 'horizontal',
        left: 'center',
        bottom: '10%'
    },
    series: [{
    	name:'文章',
        type: 'heatmap',
        data:data,
        label: {
            normal: {
                show: true
               
            }
        },
      
        itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
            
        }
    }]
};


	

</script>
	


</s:elseif>
<s:elseif test="countChannel==3">
	<script type="text/javascript">
	var channelid =<s:property value='channelid'/>;
	$(function(){
	pageAll(1);
	
	});
	
	function pageAll(pageNum){
	var channelidStr =$("#channelidStr").val();
		var result = $.ajax({
		url:'<%=rootPath %>document/document_map_all_list_page.action',
		type: "GET",
		async: false,
		cache : false,
		data: {
			channelid: channelid,
			channelidStr:channelidStr,
			pageNum:pageNum
		}
	}).responseText;
	$('#result').html(result);
	}
	
	</script>
	<div  id="image" style="width:900px;padding: 25px;border:1px solid #ccc;margin-left: 50px;overflow:hidden;background-color: #ece9e9;">
		<input id="channelidss" type="hidden" value="<s:property value='channelidss' />" >
		
	
	<s:iterator value="rootChannels"  status="index">
		 <s:set var="childChnls" value="findChildMapByParentId(channelid)" />
		 <table style="width: 100%;font-size: 15px;">
			<s:iterator value="#childChnls" status="index2">
			  <s:if test="#childChnls!=null && #childChnls.size()>0">
	         		 <tr  style="border-bottom: 2px dashed #cecece;">
	         		 <s:set var="childChnlss" value="findChildMapByParentId(channelid)" />
	         		 <s:if test="#childChnlss!=null && #childChnlss.size()>0">
						<td style="padding: 10px;color: #4eb0d7;">
			         		<s:property value='chnldesc'/>&nbsp;&nbsp;:
	         		 	 </td>
	         		 	<s:iterator value="#childChnlss" status="index2">
	         		 		<s:if test="#childChnlss!=null && #childChnlss.size()>0">
	         		 			<td id="td<s:property value='channelid'/>">
	         		 			<em class="daohang" id="em<s:property value='channelid'/>" _value="<s:property value='channelid'/>" onclick="daohangClick(<s:property value='channelid'/>)"><s:property value='chnldesc'/></em>
	         				</td>
	         				</s:if>
	         		 	</s:iterator>
	         		 	</s:if>
	         		</tr>
	         </s:if>
	     </s:iterator>
	     </table>
	</s:iterator>
	 <div style="float: right;padding-top: 20px;">
	 <!-- <input style="width: 80px;height: 40px;border: 2px solid #fff;background-color: #5f69dd;" type="button" value="导航" onclick="DaoHang(1);"> -->
	 </div>
	</div>
</s:elseif>
    <s:elseif test="countChannel==0">
    	<section class="result" >
    	<ul class="list1"style="padding-left: 30%;">
				<li>请联系管理员获得权限...</li>
			</ul>
			</section>
    </s:elseif>
    <section class="result" id="result">
    
<%--     <input id="channelidStr" type="hidden" value="<s:property value='channelidStr' />" >
        <section id="list" class="resultList" style="display:none;">
        	<div class="reorder"><span>显示方式：</span><em class="current" onclick="tabView('list')">列表</em><em onclick="tabView('view')">视图</em></div>
			<s:if test="chnlDocLinks!=null && chnlDocLinks.size()>0">
			<ul class="list1">
				<s:iterator value="chnlDocLinks">
					<s:set var="cruser" value="findUserByUserId(cruserid)" />
            	<li>
            		<em class="ellipsis"><s:if test="docchannel>0">
				 		<img src="<%=rootPath %>view/images/jinghua.gif"/> 
				 	</s:if>
				 	<s:else>
				 		<img src="<%=rootPath %>view/images/gl_r5_c3.gif" />
				 	</s:else></em>
            		<label class="ellipsis">|</label>
            		<span style="width:680px;" class="ellipsis">
            			<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a>
            		</span>
            		<aside style="width:200px;" class="ellipsis">
            			<s:property value="hitscount"/>&nbsp;/&nbsp;<s:property value="commentcount"/>
						·
						<s:date name="docpubtime" format="yyyy-MM-dd" />
						<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a>
					</aside>
            	</li>
	            </s:iterator>
			</ul>
			<div class="area20"></div>
            <div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
			</s:if>
			<s:else>
			<ul class="list1">
				<li>该分类下暂时没有知识...</li>
			</ul>
			</s:else>
        </section>
        
		<section id="view" class="resultList" style="display:none;">
        	<div class="reorder"><span>显示方式：</span><em onclick="tabView('list')">列表</em><em onclick="tabView('view')" class="current">视图</em></div>
		<s:if test="chnlDocLinks!=null && chnlDocLinks.size()>0">
			<s:iterator value="chnlDocLinks">
			<s:set var="cruser" value="findUserByUserId(cruserid)" />
			<s:set var="document" value="getIrpDocumentById(docid)" />
			<article class="item">
            	<h1 class="ellipsis nowrap"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></h1>
            	<aside>
					<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
            	</aside>
                <section>
                	<p style="max-height:123px;" class="ellipsis" title="<s:property value="#document.docabstract" />">
                		<s:property value="#document.docabstract" />
               		</p>
                	<div class="date">
                		<s:property value="hitscount"/>&nbsp;/&nbsp;<s:property value="commentcount"/>
						·
						<s:date name="docpubtime" format="yyyy-MM-dd" />
						<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a>
					</div>
                </section>
                <div class="clear"></div>
			</article>
			</s:iterator>
			<div class="area20"></div>
            <div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
		</s:if>
		<s:else>
			<ul class="list1">
				<li>该分类下暂时没有知识...</li>
			</ul>
		</s:else>
        </section> --%>
    </section>
</section>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>