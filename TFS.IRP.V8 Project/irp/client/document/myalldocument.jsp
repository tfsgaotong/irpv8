<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		 
%>
 <s:if test="chnlDocLinks!=null &&  chnlDocLinks.size()!=0">
 <table style="width: 100%">
 	<tr>
 		<td align="right">
 		    按时间&nbsp;&nbsp;
 		    <a href="javascript:void(0)" onclick="documentSort('asc')">
					<img  src="<%=rootPath%>/client/images/rec_add.gif" width="16px" height="16px">
			</a> 
 		    &nbsp;&nbsp;
 		    <a href="javascript:void(0)" onclick="documentSort('desc')">
					<img  src="<%=rootPath%>/client/images/rec_subtract.gif" width="16px" height="16px">
			</a>
 		</td>
 	</tr>
 </table>
  	<div class="fyh" id="mydoclink">
  		<s:iterator value="chnlDocLinks"> 
  		<dl id="mydocdl<s:property value='docid'/>">
  		<%--这里给文档id，然后去查询一个图片，然后显示在这里 --%>
    		<dt>	
   		    <s:if test="userPicUrl!=null"> 
				<img   src="<s:property value='userPicUrl'/>" alt="用户头像" width="55px" /> 
			</s:if>
			<s:if test="userPicUrl==null"> 
				<img  src="<%=rootPath %>client/images/touxiang.png" alt="用户头像" width="55px" /> 
			</s:if>	
	    </dt>
    		<dd> 
    			<a href="javascript:void(0)" class="linkb14" onclick="documentinfo_see(<s:property value='docid'/>)">
    			<s:property value="doctitle"/>
    			</a> 
    			  &nbsp;&nbsp;<s:date name="%{crtime}" format="yyyy-MM-dd HH:mm"/> 
    			  <br>标签： 
  			 	 <s:iterator value="document.dockeywords.split(',')"  status="st" var="as">
  				 	<a href="javascript:void(0);" onclick="searchInfo1('<s:property value="#as"/>')" class="linkc12"><s:property value="#as"/></a>&nbsp;&nbsp;
  				</s:iterator>
  			 
  			<br>核心提示：<s:property value="document.docabstract"/>
  			<span style="padding-bottom:30px;">
  				<p> 
  			 <s:if test="%{cruserid==loginuserid}">
  				<a href="javascript:void(0)" class="linkc12" onclick="updatetochannel(<s:property value='docid'/>,<s:property value='channelid'/>)">分类调整</a> &nbsp;  &nbsp; 
  				<a href="javascript:void(0)" class="linkc12" onclick="documentinfo(<s:property value='docid'/>)">修改</a> &nbsp;  &nbsp;    
                <a href="javascript:void(0)" class="linkc12" onclick="clientdeletedocument(<s:property value='docid'/>)">删除</a> &nbsp;  &nbsp; 
            </s:if>
  			  </p>
  			</span>
  			<%--
  			<span style="padding-bottom:30px;">
  				<div id="speekdiv<s:property value='docid'/>" style="background-color: #F6F6F6;display: none;">
  					<textarea id="recommend<s:property value='docid'/>" rows="4" cols="65"></textarea>
  					<p style="text-align: right;"><a href="javascript:void(0)" onclick="adddocrecommend(<s:property value='docid'/>,<s:property value='cruserid'/>)" class="linkc12">回复</a></p>
  					<span id="speek<s:property value='docid'/>"></span>
  				</div>
  			</span>
  			 --%>
  			</dd>
  		</dl>
  		</s:iterator> 
  	</div> 
	<ul> <s:property value="pageHTML" escapeHtml="false" /></ul>
 </s:if><s:else><br>该分类下没有知识!</s:else>  	
<script type="text/javascript">
	//删除知识
	function clientdeletedocument(_docid){ 
		$.dialog.confirm('您确定要删除这个知识吗？',function(){
			$.ajax({
					type:'post',
					url:'<%=rootPath%>site/clientdeletedocument.action',
					data:{'docid':_docid},
					success: function(msg){
						if(msg=="1"){ //删除成功 
								//然后将这个dl隐藏
						$('#mydocdl'+_docid).fadeOut();
						$('#mydocdl'+_docid).fadeOut("slow");
						$('#mydocdl'+_docid).fadeOut(3000);
						} 
					}
	    	 });
		},function(){}); 
	}	
	 
	//调整栏目
	function updatetochannel(_docid,_channelid){ 
		  //查询所有的栏目，包括默认栏目 正序排列 
	 	$.ajax({
			type:'post',
			url:'<%=rootPath%>site/selectchannelselect.action',
			success: function(msg){   
				_selectchannels=eval(msg);//转换成附件集合   
				var str='<select  id="checkchannel">';   
					if(_selectchannels!=null){
						var len = _selectchannels.length;  
							for(var i=0 ;i<len ; i++){  
								if(_channelid==_selectchannels[i].channelid){
									if(_selectchannels[i].parentid==0){
									 	 str+='<option selected  value="'+_selectchannels[i].channelid+'">默认</option>';
	        				        }else{
								 	     str+='<option selected  value="'+_selectchannels[i].channelid+'">'+_selectchannels[i].chnldesc+'</option>';
	        				    	}
	        				     }else{ 
	        				     	if(_selectchannels[i].parentid==0){
									 	 str+='<option    value="'+_selectchannels[i].channelid+'">默认</option>';
	        				        }else{
								 	     str+='<option    value="'+_selectchannels[i].channelid+'">'+_selectchannels[i].chnldesc+'</option>';
	        				    	}
	        				     } 
	        				}
					} 
				str+='</select>'; 
				//////////
				$.dialog({
					title:'分类调整',
					max:false,
					min:false,
					lock:true, 
					resize: false, 
					content:str,
					cancelVal:'关闭',
					okVal:'确定',
					cancel:function(){ 
					},
					ok:function(){ 
						var _channelidV=$('#checkchannel').val();
						if(_channelid!=_channelidV){
							$.ajax({
								type:'post',
								data:{'irpDocument.channelid': _channelidV ,'irpDocument.docid':_docid},
								url:'<%=rootPath%>site/updatedocumentchannel.action',
								success: function(msg){ 
									if(msg=="1"){ 
										$.dialog.tips('调整分类成功',1,'32X32/succ.png',function(){});
										 //然后将这个dl隐藏
										$('#mydocdl'+_docid).fadeOut();
										$('#mydocdl'+_docid).fadeOut("slow");
										$('#mydocdl'+_docid).fadeOut(3000);
									} 
								}
							}); 
							
						}
						 
					}
				}); 
				////////// 
			}
		}); 
	}	
</script>
</html>
