<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <style> 
.ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

</style>  
<script type="text/javascript">
function showMoreChannel(){
	//跳到选择栏目页面
		var result = $.ajax({
			url: '<%=rootPath%>site/to_checkchanneltoshowdocument.action',
			type:'post', 
		    async: false 
		}).responseText;   
		$.dialog({
        modal:true,
	    cache:false,  
	    content:result,
		title:'选择专业分类',
		lock:true, 
		cancelVal:'关闭',
		loadingText:'专业分类正在加载中...',
		okVal:'确定',
		max:false,
		min:false,
		cancel:function(){ 
			this.close;
   		} ,
   		ok:function(){  
	   		 var _channel=$('#checkchannelul').tree('getSelected');
	   		 if(_channel!=null){
	   			var _id=_channel.id; 
		   		 showDoc(_id); 
	   		 } 
		}
	}); 
}  
</script>
  <div class="zj_fl zj_w1">
  <s:if test="chnlDocLinkMONTH!=null && chnlDocLinkMONTH.size()>0">
     <div class="zj_title1">
	            	<div class="zj_tt"><h1>每月之星</h1></div>
 	 </div>
	  <s:iterator value="chnlDocLinkMONTH"> 
	      	<div class="zj_box1" style="overflow: auto;">
	      	<a href="javascript:void(0);" style="text-decoration: none;" onclick="showdocumentinfo(<s:property value='docid' />)"> 
	          	<h1 >
	           <img src="<s:property value='docCoverPath(docid,docflag)'/>" width="218px;" height="75px"/>   
	          	</h1>
	              <h2 style="height: 90px;"><p style="text-indent:1em;"> <s:property value="document.docabstract" /></p></h2> 
	      	</a>
	          </div> 
	  </s:iterator>
  </s:if>
  	
	            <div class="zj_title1">
	            <div class="area1"></div>
	            <div class="zj_tt2 ellipsis"   style="table-layout: fixed;">
			            <s:if test="irpChannel!=null">
			            	<h1 title="<s:property value='irpChannel.chnldesc'/>"> <s:property value="irpChannel.chnldesc"/> </h1>
			            </s:if>
		            <s:else>
		            	<h1 title="专业分类">专业分类 </h1>
		            </s:else>
		           
	            </div>	 
	            	<div class="zj_more">
	            	  <s:if test="irpChannel!=null">
	            	  	<s:if test="irpChannel.parentid==0">
		            	  	 <a href="<%=rootPath %>site/showallpublicdoc.action"  >
			                 	  返回 
			                 </a> 
	            	  	</s:if>
	            	  	<s:else>
		            	  	 <a href="javascript:void(0);" onclick="showDoc(<s:property value='irpChannel.parentid'/>)">
			                 	 返回 
			                 </a> 
	            	  	</s:else> 
	                 </s:if> 
	                 &nbsp;&nbsp;
	                 <a href="javascript:void(0);" onclick="showMoreChannel()"> 
	                 <img src="<%=rootPath %>client/images/_30.gif" />
	                 </a></div>
	            </div>
	        	<div class="zj_boxq" style="overflow: auto;">
	                <h3 style="height:100px;"> 
	                	 <ul class="ul_dtlist3 black12">
		                	<s:iterator value="channels">
		                		<a title='<s:property value="chnldesc"/>' href="javascript:void(0)" onclick="showDoc(<s:property value='channelid'/>)">
		                		<li style="float: left;">  
	             					<img src="<%=rootPath %>client/images/arrow_right.gif" width="7px;" height="7px;" /><s:property value="chnldesc"/>
	                  			</li>
 		                		</a>
		                	</s:iterator> 
	               	 </ul>
					</h3>
	            </div>
           	 <div class="area1"></div>
           	  <img src="<%=rootPath %>client/images/mapandsub.jpg" usemap="#Map"/>
              <map name="Map" id="Map">
                <area shape="rect" coords="2,2,151,51" href="<%=rootPath %>site/documentmaplistshow.action" target="_blank" />
              	<area shape="rect" coords="153,3,254,51" href="<%=rootPath %>site/subdocumentmaplistshow.action" target="_blank" />
              </map>
           	  
        </div> 