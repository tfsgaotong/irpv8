<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<style>
.ellipsis {
    table-layout: fixed;
}
.ellipsis td{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.pros{height:40px;padding-left:100px;padding-top:30px}
.pros span{height:38px;line-height:38px;color:#286095;margin:0 25px 0 25px;display:inline-block;}
.pros .line{border-bottom:solid 3px #4090d3}
.section-item{width:990px;margin:40px auto 0 auto;}
/* .section-item a:hover{text-decoration:none} */
.section-item .prize{float:left;width:200px;margin-bottom:40px}
.section-item .prize-card-list .prize-card{float:left;margin-right:50px;transition:.2s}
.section-item .prize-card:hover{transform:scale(1.1)}
.prize-card{position:relative;display:block;width:210px;height:226px;padding-top:10px;background:url(prize-card-bj_17bb4d4.png);text-align:center;}
.prize-card .cover{position:relative;display:block;width:170px;height:170px;margin:0 auto;background-size:cover}
@font-face{
		font-family: jianzhi;
		src:url(<%=rootPath %>client/images/jianzhi.TTF),
			url(<%=rootPath %>client/images/jianzhi.TTF); /* IE9 */
	 }
.prize-card .cover p{font-size:36px;color:white;font-family:"jianzhi";padding-top:64px}
.prize-card .prize-title{position:absolute;width:100%;bottom:5px;left:0;line-height:24px;font-size:14px;color:#fff}
.prize-card .need-score{display:block;padding-top:18px;text-align:center}
.prize-card .need-score strong{display:inline-block;vertical-align:text-bottom;height:28px;color:#425666;margin-right:4px;padding-left:20px;margin-bottom:10px}

.prize-card .need-score span{display:inline-block;vertical-align:text-bottom;padding-bottom:3px;font-size:12px;color:#425766}
.prize-card .need-score-decline{position:relative;display:block;padding-top:8px;}
.prize-card .need-score-decline .need-score-before{display:block;height:14px;line-height:14px;color:#b4b4b4;text-decoration:line-through}

.prize-card .need-score-decline .need-score-before img{vertical-align:text-top;margin-right:1px}
.prize-card .need-score-decline .need-score{padding-top:0;text-align: center;}
.prize-card .need-score-decline .need-score strong{padding:0;font-size:24px;font-family:"Bebas"}

.prize-card .need-score-decline i{display:inline-block;width:13px;height:17px;margin-right:5px;background:url(<%=rootPath %>client/images/jt.png) no-repeat center center}
.prize-card .icon-hot{position:absolute;top:-8px;right:-8px;width:62px;height:62px;background:url(//vipllq.baidu.com/statics/user_point/theme-v2/images/icon-hot_4f1e91a.png)}
.prize-card.is-lucky-bag .prize-title{color:#766450}
.download-dialog{height:250px}
.download-dialog .dialog-content{height:109px;padding:55px 0 0 136px;position:relative}
.download-dialog .dialog-content h3{font-weight:700;font-size:16px;line-height:22px;color:#425766}
.download-dialog .dialog-content p{padding-top:5px;font-size:12px;line-height:1.5em;color:#a0aab1}
.download-dialog .dialog-buttonpane{height:58px;padding-top:15px;text-align:center;background:#fff}
.download-dialog .icon{position:absolute;width:78px;height:111px;top:43px;left:28px;background-repeat: no-repeat;}
.download-dialog .button{display:block;width:216px;height:70px;margin:auto;line-height:40px;text-align:center;color:#fff;font-size:14px;font-weight:700;background:url(//vipllq.baidu.com/statics/user_point/theme-v2/images/button-blue_ead0db1.png);cursor:pointer}
.download-dialog .button.dark{background:url(//vipllq.baidu.com/statics/user_point/theme-v2/images/button-dark_4bf766d.png);cursor:default}
.prize-detail{position:relative;height:350px}
.prize-detail-side{position:absolute;top:0;left:0;width:210px;padding:46px 0 0 70px}
.prize-detail-side .prize-card{margin:auto}

.prize form,.prize .prod{padding-left:20px;}
.prize input[type="number"]{width:40px;display:inline-block;border:solid 1px #0e6ec0;border-radius:2px;line-height:28px;}
.prize input[type="submit"]{width:42px;display:inline-block;background: #0e6ec0;border-radius:2px;border:none;line-height:30px;color:#fff}
.prod h1{font-size:16px;font-weight:bold;line-height:24px;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;}
.prod p{width:178px;display:block;font-size:12px;padding-bottom:14px;color:#323232;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;line-height: 12px}
</style>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, { 
	integer : {// 验证整数
		validator : function(value) {
		return /^[+]?[1-9]+\d*$/i.test(value);
		},
		message : '请输入正确的宝贝数量。'
	},
});

	function addcovertrecord(goodsid,coverstate){
    var stocknum=$.ajax({
		 				url: '<%=rootPath %>goods/findstocknum.action?goodsid='+goodsid,
		  			   	type: 'POST',
		  			   	async: false,
						cache: false
		   		}).responseText;
		var bb="aa"+goodsid;
		var _covertnum = "0";
		obj = document.getElementsByName("covertnum");
    	for(i=0;i<obj.length;i++){
    		if(bb==obj[i].id){
        	_covertnum = obj[i].value;
    		}
    	}
	$.messager.confirm('提示信息','确认兑换？',function(r){   
	    if(r){
	    if(parseFloat(stocknum)<parseFloat(_covertnum)){
	    	$.messager.alert('提示信息','库存不足','warning');
	    }else{
	    	var count=$.ajax({
		 		url: '<%=rootPath %>usermedal/getUserMedal.action?goodsid='+goodsid,
		  		type: 'POST',
		  		async: false,
				cache: false
		   	}).responseText;
			if(coverstate==20){//积分兑换商品
	    	var dialogDiv = document.createElement("div");
			dialogDiv.id="editRole";
			document.body.appendChild(dialogDiv);
			$('#editRole').dialog({ 
				 modal:true,
				    href:'<%=rootPath %>covert/coverttype.action?goodsid='+goodsid,
				    title:'提示信息',
				    width:300,
				    height:150,
				    resizable:true,
				    buttons: [{
				    	text: '确认', 
				    	iconCls: 'icon-ok', 
				    	handler: function() {
				    		var type=$('#addCovert').find('input:radio[name="coverttype"]:checked').val();
				    		$('#editRole').dialog('destroy');
				    		if(type==10){//勋章兑换
		   						if(count>0){
		   							if(count>=parseFloat(_covertnum)){
		   								var isValid = $('#addcovert').form('validate');
										if(isValid){
											submitAdd(goodsid,_covertnum,type);
	    								}
		   							}else{
		   								$.messager.alert('提示信息','您的勋章不够','warning');
		   							}
		   						}else{
				    				$.messager.alert('提示信息','您目前没有该勋章，请选择积分兑换','warning');
		   						}
				    		}else{//积分兑换
								var isValid = $('#addcovert').form('validate');
								if(isValid){
								submitAdd(goodsid,_covertnum,type);
	    						}
				    		} 
				    	} 
				    },{ 
				    	text: '取消', 
				    	iconCls: 'icon-cancel', 
				    	handler: function() { 
				    		$('#editRole').dialog('destroy');
				    	} 
				    }],
				    onClose:function(){
		    		$('#editRole').dialog('destroy');
		   			}
			});
			}else{//勋章兑换商品
	    		if(count>0){
		   			if(count>=parseFloat(_covertnum)){
		   				var isValid = $('#addcovert').form('validate');
						if(isValid){
							submitAdd(goodsid,_covertnum,10);
	    				}
		   			}else{
		   				$.messager.alert('提示信息','您的勋章不够','warning');
		   			}
		   		}else{
				    $.messager.alert('提示信息','您目前没有该勋章!!!','warning');
		   		}
	    	}
	    }
	    /* if(parseFloat(stocknum)<parseFloat(_covertnum)){
	    	$.messager.alert('提示信息','库存不足','warning');
	    }else{
			var isValid = $('#addcovert').form('validate');
			if(isValid){
				submitAdd(goodsid,_covertnum);
	    	}
	    } */
   		}
   		})
	}
	function submitAdd(goodsid,_covertnum,_coverttype){
	$.messager.progress({text : '数据提交中...'});
	$('#addcovert').form('submit',{
		url : '<%=rootPath%>covert/addcovertrecord.action?goodsid='+goodsid+'&covertnumber='+_covertnum+'&coverttype='+_coverttype,
		success : function(data){
			$.messager.progress('close'); 
			if(data>0){ 
			    $.messager.alert('提示信息','兑换成功','info');
			    loadUnReadMessage();
			    refreshStocknum(goodsid);
			}else{
				$.messager.alert('提示信息','用户积分不足','warning');
			}
		} 
	});
}
function refreshStocknum(goodsid){
	var stocknum=$.ajax({
		 				url: '<%=rootPath %>goods/findstocknum.action?goodsid='+goodsid,
		  			   	type: 'POST',
		  			   	async: false,
						cache: false
		   		}).responseText;
	var id="kc"+goodsid;
	document.getElementById(id).innerHTML=stocknum;
}
//轮播图
function rollpan(){
	var rollpans = $('#rollpan').find('article');
	var switchings = $('#switching').find('a');
	if(rollpans.length<2){
		return;
	}
	for ( var i = 0; i < rollpans.length; i++) {
		var jqObj = $(rollpans[i]);
		var jqObj1 = $(switchings[i]);
		if(jqObj.css('display')!='none'){
			jqObj.hide();
			jqObj1.attr('class','');
			if((i+1)==rollpans.length){
				$(rollpans[0]).fadeIn('slow');
				$(switchings[0]).attr('class','current');
			}else{
				jqObj.next().fadeIn('slow');
				jqObj1.next().attr('class','current');
			}
			return;
		}
	}
}

function selectRollpan(oLink){
	clearInterval(t);
	var switchings = $('#switching').find('a');
	var rollpans = $('#rollpan').find('article');
	if(rollpans.length<2){
		return;
	}
	for (var i=0; i<switchings.length; i++) {
		if(switchings[i]==oLink){
			$(oLink).attr('class','current');
			$(rollpans[i]).fadeIn('slow');
		}else{
			$(switchings[i]).attr('class','');
			$(rollpans[i]).hide();
		}
	}
	t = setInterval("rollpan()", speed);
}
var t;
var speed=5000;

$(function(){
	t = setInterval("rollpan()", speed);
});

var timer;
 
 
function Start()
{
document.getElementById("adv").innerHTML+=document.getElementById("adv").innerHTML;
timer=window.setTimeout("Move()",10);
}
 
function Move()
{
var cover=document.getElementById("cov");
var adv=document.getElementById("adv");
if(cover.scrollTop<=adv.offsetHeight/2)
{
cover.scrollTop+=2;
}
else
{
cover.scrollTop=0;
}
window.status=cover.scrollTop;
timer=window.setTimeout("Move()",10);
}
	</script>
<form >
        <s:hidden name="goodsname" id="goodsdesc"/>
        <s:hidden name="goodsdesc" id="goodsdesc"/>
        <s:hidden name="searchWord" id="kd"/>
		<s:hidden name="pageNum" id="pageNumform" />
		<s:hidden name="pageSizeclient" id="pageSizeclientform"/>
		<s:hidden name="coverstate" id="coverstate" />
</form>
<form id="addcovert" method="post">
<s:if test="listmedal.size!=0">
<div>
	<%-- <span style="float: right;padding-right: 32px;">搜索 &nbsp;&nbsp;&nbsp;<input value='<s:property value="searchWord"/>' class="searchbox-text searchbox-prompt" type="text" name="searchWord" id="searchWord" style="width: 126px; height: 20px; line-height: 20px;">
		<span>
		   <span class="searchbox-button" style="height: 20px;" onclick="searchUser()"></span>
		</span>
	</span> --%>
	<script type="text/javascript">
    //更改回车事件
    $(function(){
        $('#searchWord').bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
            	setTimeout(function(){
            		searchUser();
           		 },0);
            	
            	event.preventDefault ? event.preventDefault() : (event.returnValue = false);
            }
        });
    });
    </script>
	
</div>
    <ul class="cardList" style="width: 950px;">
		<s:iterator value="listmedal">
		<li style="width: 210px;float: left;display: inline;margin: 8px 10px;position: relative;">
		<input type="hidden" name="medalid" value="<s:property value="medalid"/>"/>
		 <div class="prize">   
                <div class="prize-card" data-id="67" >
                 <s:if test="medalimage!=null">
                  <s:iterator value="medalimage.split(',')" var="attid"  status="index1">
                  <s:if test="#index1.first">
                    <a href="<s:url action="medalDetial" namespace="/medal"><s:param name="medalid" value="medalid" /></s:url>"  target="_blank">
                    <span class="cover" style="background:url(<s:property value='coverPath(#attid)' />);background-size:cover;">
                    </span>
                    </a>
                    </s:if>
                    </s:iterator>
                 </s:if>
                 <s:else>
                 <a href="<s:url action="medalDetial" namespace="/medal"><s:param name="medalid" value="medalid" /></s:url>"  target="_blank">
                   <span class="cover" style="background:url(<%=rootPath %>client/images/bjt.png)">
                    </span>
                    </a>
                 </s:else> 
                    <span class="need-score-decline">
                        <span class="need-score-line"></span>
                        <!-- <span class="need-score-before"><s:property value="price"/></span> -->
                        <span class="need-score">
                         <a href="<s:url action="medalDetial" namespace="/medal"><s:param name="medalid" value="medalid" /></s:url>"  target="_blank">
                            <strong><s:property value='medalname'/></strong>
                           </a>
                        </span>
                    </span>
                    
                </div>
            </div>
			<!-- <div class="darkSh"></div> -->
		 </li>
	    </s:iterator>
     </ul>
      <div style="clear:both" ></div>
      <div class="left" style="float: none;width: 930px;margin-top:20px;">
        <div style="margin:0 0 0 0;width: 930px;" class="fyh">
            <ul style="width: 930px;">
                <s:property value="pageHTML" escapeHtml="false" />
            </ul>
        </div>
    </div>
     <%-- <div class="fyh" style="text-align: center;"><ul> <s:property value="pageHTML" escapeHtml="false" /></ul></div> --%>
</s:if>
<s:else>
     <div style="height: 350px;padding-top:20px;padding-left:45%;font-size:15px;">暂无勋章</div> 
</s:else>		    
</form>   