<%@page import="com.tfs.irp.channel.entity.IrpChannel"%>
<%@page import="com.tfs.irp.util.SysConfigUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<link rel="Bookmark" href="<%=rootPath%>client/images/24pinico.ico" />
<link rel="Shortcut Icon" href="<%=rootPath%>client/images/24pinico.ico" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>client/css/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>client/css/icon.css" />
<script type="text/javascript">
	//创建企业专题和引用知识到企业专题权限
	$(function(){
		var channel_id = <s:property value='id' />;
		 $.ajax({
			url: '<%=rootPath%>site/subclientrightquote.action?cid='+channel_id,
			type:'post', 
		    async: false ,
		    success:function(mes){
		    	if(mes == "quote"){
		    		$('#addsubjecta').show();
	    		}
		    }
		}) ;
	});
	//引用知识
	 function quoteKnowledge(_id){
	 	var dialogdiv=document.createElement("div");
	  	dialogdiv.id="quotedivs";
	  	document.body.appendChild(dialogdiv);
	  	$('#quotedivs').dialog({
	  		modal:true,
	  		cache:false,
	  		href:'<%=rootPath%>site/tocheckdocument.action?channelid='+_id,
	  		height:500,
	  		width:1000,
	  		title:'知识分类',
	  		resizable:true,
	  		buttons:[{
	  			text:'提交',
	  			iconCls: 'icon-ok',
	  			handler:function(){
	  				 var str=$('#alreadyselectedknow').val();
	  		           $.ajax({
	  		        	   type:"post",
	  		        	   url:"<%=rootPath%>site/givedocumentaddmap.action?flag=sub",
	  		        	   data:{
	  		        		 "docidlist":str,
	  		        		 "id":_id 
	  		        	   },
	  		        	   success:function(dochtml){
								$('#quotedivs').dialog('destroy');
	  		        		   if(dochtml!="0" && dochtml!="-1"){
	  	  		        		  $.dialog.tips('引用成功',1,'32X32/succ.png',function(){
	  	  		        			var result=$.ajax({
	  		  	  		 				type:'post',
	  		  	  		 				url:'<%=rootPath%>site/subprobabledocumentshow.action?id=<s:property value="id"/>', 
	  		  	  		 				dataType: "json",
	  		  	  		 				async: false,
	  		  	  		 			    cache: false 
	  		  	  		 				}).responseText;  
	  		  	  			     		$('#probabledocumentshow').html(result);  
	  	  		        			 
	  	  		        			 
	  	  		        		  });
	  		        		   }
	  		        	   }
	  		           });
	  		
	  			}
	  		},{
	  			text:'取消',
	  			iconCls:'icon-cancel',
	  			handler:function(){
	  				$('#quotedivs').dialog('destroy');
	  			}
	  		}],
	  		onClose:function(){
	  			$('#quotedivs').dialog('destroy');
	  		}
	  	});
	 }	
	
	 //刷新
	function documentinfoinit(){ 
	  $('#selecQuestionQuotecontent').panel('refresh');
	} 
	/*
     * 新建专题
     */
    function createknowSubject(){
    	 var str=$.ajax({
    		 type:'post',
    		 url:'<%=rootPath %>site/addsubjectnow.action', 
    		 async: false,
    		 cache: false
    	 }).responseText;
    	 $.dialog({
    			title:'创建专题',
    			content: str ,
    			max: false,
    		    min: false,
    			ok: function(){
    				var validate = $('#createsubjectform').form('validate');
    				$('#createsubjectform').form('submit',{
    	    			url : "<%=rootPath %>site/begincreateqiyesubject.action?id=<s:property value='id'/>",
    	    			success:function(data){
    	    				if(data=='1'){
    	    					$.dialog.tips('创建成功',1,'32X32/succ.png',function(){
    	    						window.location.reload(true);
    	    					});
    	    				}else if(data=='2'){
    	    					$.dialog.tips('专题名称已存在',1,'32X32/fail.png');
    	    				}else{
    	    					$.dialog.tips('创建失败',1,'32X32/fail.png');
    	    				}
    	    			}
    	    		});
    				return validate;
    		    },
    		    okVal:'创建',
    		    cancelVal: '取消',
    		    cancel:true,
    		    lock: true,
    		    padding: 0
    		});
    	  $('#createsubjectform').find("input[name='irpChannel.chnlname']").validatebox();
    }
</script>
	
	
<script type="text/javascript"
	src="<%=rootPath %>editor/simple_ckeditor/ckeditor.js"></script>	
		
<div class="zj_title1">
  	 <div class="zj_ttj">
  	 <s:if test="irpChannel!=null">
  	 	<h1 title="<s:property value='findChnlName(id)'/>">
	  	 	<s:if test="findChnlName(id).length()>6"><s:property value="findChnlName(id).substring(0,6)"/>...</s:if>
	  	 	<s:else><s:property value="findChnlName(id)"/></s:else>
		  	 <div style="float: right;padding-top: 6px;">
		  	 <!-- 上面js第一行判断权限 -->
		  	 <a title="引用知识" id="addsubjecta" style="display: none;" href="javascript:void(0)" onclick="quoteKnowledge(<s:property value='id' />)">
				<img src="<%=rootPath %>client/images/addbuddy.gif"/>
			 </a>
		</h1>
  	 </s:if>
  	 <s:else><h1>知识专题</h1></s:else>
  	 	<%-- <div style="float: right;margin-right:10px;height: 30px;line-height: 30px;">
  	 		<a title="新建专题" id="addsubjectb" href="javascript:void(0)" style="display:none;" onclick="createknowSubject()"><img src="<%=rootPath %>client/images/addbuddy.gif"/>新建专题</a>
  	 	</div> --%>
  	 	<s:if test="irpChannel.chnltype==@com.tfs.irp.channel.entity.IrpChannel@CHANNEL_TYPE_SUPER_SUBJECT">
  	 	<%-- <div style="float: right;line-height: 30px;">
			按<select id="searchorder" onchange="page(<s:property value='pageNum' />)">
			    <option  value="0" 
			    	<s:if test="searchsort==0"> selected="selected"</s:if>
			    >相关度</option>
				<option  value="1" 
					<s:if test="searchsort==1"> selected="selected"</s:if>
				>时间</option>
			</select>排序
		</div> --%>
  	 	</s:if>
  	 </div> 
  </div>

<div class="zj_boxk">
<s:if test="irpDocumentList!=null && irpDocumentList.size()>0">
        <table cellpadding="0" cellspacing="0" border="0" background="<%=rootPath %>client/images/gl_r3_c1.jpg" width="671" class="boxcenter listTitle">
<tr>
	<td width="394" height="25" align="center">知识标题</td>
	<td width="71" align="center">所属栏目</td>
	<td width="74" align="center">
		<a href="javascript:void(0);" onclick="updatePageHtml('docpubtime','<s:if test="orderField=='docpubtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
	  	发布时间</a>
	</td>
	<td width="71" align="center">
		<a href="javascript:void(0);" onclick="updatePageHtml('hitscount','<s:if test="orderField=='hitscount'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">浏览量</a>
		/<a href="javascript:void(0);" onclick="updatePageHtml('recommendcounts','<s:if test="orderField=='recommendcounts'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">评论 </a>
	</td>
  <td width="61" align="center">创建者</td>
</tr>
</table>
 <table cellpadding="0" cellspacing="0" border="0" width="660" class="boxcenter gl_listTxt ellipsis">
	<s:iterator value="irpDocumentList">
 	<tr>
     	<td width="33" valign="middle" align="center" height="26">
		 	<s:if test="docchannel>0">
		 		<img src="<%=rootPath %>client/images/jinghua.gif" width="21px" height="14px"/> 
		 	</s:if>
		 	<s:else>
		 		<img src="<%=rootPath %>client/images/gl_r5_c3.gif" />
		 	</s:else> 
 		</td>
 	<td  width="361"> 
 		<a href="javascript:void(0)" onclick="showdocumentinfo(<s:property value='docid'/>)" title="<s:property value='doctitle'/>"> 
     		<s:property value='doctitle'/></a>
	</td>
	<td width="71"> 
		<a href="javascript:void(0);" onclick="showDoc(<s:property value='channelid'/>)">
			[<s:property value="findChnlName(channelid)"/>]
		</a>
	 </td>

         	<td width="74" align="center"><h1><s:date name="%{docpubtime}" format="yyyy-MM-dd"/></h1></td>
        
        	<td width="71" align="center"><h1><s:property value="hitscount"/>&nbsp;/&nbsp;<s:property value="recommendcounts"/> </h1></td>
           <td width="61" align="center"><h1>
              	<a  target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid'/>">
         		<s:property value="cruser"/>
         	</a>
         	</h1></td>
         	 </tr>
    	</s:iterator>   
    </table>
    <div class="main" align="right" style="width: 650px;" >
                <div class="left" style="width: 650px;" >
                  <div class="fyh"  style="width: 650px;" >
                   <ul style="width: 650px;" >
                    <s:property value="pageHTML" escapeHtml="false" />
                    </ul>
                  </div>
                </div>
                 </div>
    </s:if>
    <s:else>
  		<span style="padding-left: 10px;">  该分类下暂时没有知识...</span>
    </s:else>
</div>

<form id="pageForm">   
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
		<s:hidden name="id" id="id"></s:hidden>  
		<s:hidden name="orderField" id="orderField"></s:hidden>
		<s:hidden name="orderBy" id="orderBy"></s:hidden>
		<s:hidden name="superid" id="superid"/>
	</form>