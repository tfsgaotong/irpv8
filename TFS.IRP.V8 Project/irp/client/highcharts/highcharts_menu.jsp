<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title>时间轴折线图</title>
	<link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath%>/client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>admin/css/easyui.css" />
    <script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/client/js/highcharts/highcharts.js"></script>
  </head>
	<body>
	<script type="text/javascript">
	function updateData(){
		
	}
	//知识访问量
	function documentVisitCount(){
		//发送请求得的当月
		 
		var str=$.ajax({
			 type:'post',
			 dataType: "json",
			 url:' <%=rootPath%>client/highcharts/timeline.jsp', 
			 async: false,
   		 cache: false
		 }).responseText; 
	}
  	function initHihtChart(){
  	 	 var str=$.ajax({
			 type:'post',
			 dataType: "json",
			 url:' <%=rootPath%>client/highcharts/timeline.jsp', 
			 async: false,
    		 cache: false
		 }).responseText; 
  	}
  	//修改显示
  	function initHighChart(_charts){
  		var timeselect=$("#timeselect  option:selected").val();
  		alert(timeselect);
  		if(timeselect=="thismonth"){
  			var _xAxis=[];	
  		}
  		var data=initHihtChart();//查询数据
  		showData(_charts);
  	}
  	//初始化
  	$(function(){
  		var _xAxis=['a','b','c','d','e'];
  		var _series=[//填充xy坐标的数据
 			        {name:'猪猪',
 					 	data:[2,6,190,2,57]
 			        },
 			        {name:'萍萍',
 			        	data:[1,500,1,2,3]
 			        },
 			        {name:'红红',
 	 			        	data:[100,5,544,2,7]
 			        }
 			
 			];
  		showData('line','主标题','副标题',_xAxis,'测试...',_series);
  	});
  	/**
  	_chart显示类型
  	_title主标题
  	_subtitle副标题
  	_xAxis X显示['a','b']
  	_yAxis y显示名称
  	_series 填充xy坐标的数据 json填充数据格式为[{name:'xx',data:[a,b,c]},{name:'xx',data:[a,b,c]}]
  	*/
  	//显示数据
  	function showData(_chart,_title,_subtitle,_xAxis,_yAxis,_series){
  		$('#container').highcharts({
 			chart:{
 				type:_chart//折线图
 			},
 			title:{
 				text:_title
 			},
 			subtitle:{
 				text:_subtitle
 			},
 			xAxis:{
 				categories:_xAxis
 			},
 			yAxis:{
 				title:{
 					text:_yAxis
 				}
 			},
 			tooltip:{
 				enabled: true,//鼠标移动到上面执行
 				formatter:function(){//显示的数据
 					return this.point.series.name+"--"+this.x+"--"+this.y;
 				} 
 			},
 			plotOptions:{//
 				
 			},
 			series:_series,
 		});
  	}
	
  //引入扩展验证
  $.extend($.fn.validatebox.defaults.rules, {   
  	    Maxlength:{//最大输入长度
  	    	validator:function(value){ 
  	    		if (/^[+]?[0-9]+\d*$/i.test(value)) {
  				return true;	
  				}else{
  				return false;
  				}
  	        }, 
  	            message:'请输入合法整数'
  	        },
  	        EndTime:{
  		    	validator:function(){
  		    		if($('#endtime').datebox('getValue')>=$('#starttime').datebox('getValue')){
  		    			return true;
  		    		}else{	  
  		    			return false;
  		    		}	
  		    	},
  		    	message:'结束日期必须在开始日期之后'
  		    },
  		    StartTime:{
  		    	validator:function(){
  		    		if($('#endtime').datebox('getValue')>=$('#starttime').datebox('getValue')){
  		    			
  		    			return true;
  		    		}else{
  		    			
  		    			return false;
  		    		}	
  		    	},
  		    	message:'开始日期必须在结束日期之前'
  		    }
  	
  }); 
  </script>
    	<s:include value="../include/enterprise_head.jsp"></s:include>
    	<div class="area1"></div>
     	<div class="zj_wBox">
			<div>
				<%--上面 --%>
				<div style="height: 80px;width: 100%;border-bottom: solid 1px;">
					&nbsp;&nbsp;
					<a href="javascript:void(0);" onclick="initHighChart()">按日知识访问量</a>
					||
					<a href="javascript:void(0);" onclick="initHighChart()">每天知识发布量</a>
					
				</div>
				<%--上面 --%>
				<%--左边 --%>
				<div style="width: 10%;float: left;" class="leftmenu">
					<dl>  
					 	<dt><a href="javascript:void(0);" onclick="initHighChart('pie')">饼图</a></dt>
						<dt><a href="javascript:void(0);" onclick="initHighChart('line')">折线</a></dt>
						<dt><a href="javascript:void(0);" onclick="initHighChart('spline')">曲线</a></dt>
						<dt><a href="javascript:void(0);" onclick="initHighChart('column')">柱状图</a></dt>
						<dt><a href="javascript:void(0);" onclick="initHighChart('areaspline')">横向柱状图</a></dt>
						<dt><a href="javascript:void(0);" onclick="initHighChart('area')">区域图</a></dt>
						<dt><a href="javascript:void(0);" onclick="initHighChart('scatter')">分布点图</a></dt>
        			</dl>
				</div>
				<%--左边 --%>
				<%--右边 --%>
				<div style="width: 80%;float: left;padding-left: 10px;border-left: solid 1px;">
					<div style="padding-top: 10px;">
					<select id="timeselect" onchange="initHighChart()">
						<option value="thismonth" id="thismonth" selected="selected">本月</option>
						<option value="thisweek" id="thisweek">本周</option>
						<option value="thisquarter" id="thisquarter">本季</option>
						<option value="otherdate" id="otherdate">指定</option>
					</select>
					<span id="_time_date">
						开始日期
						<input type="text" id="starttime" class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 
						结束日期
						<input type="text" id="endtime" class="easyui-datebox" editable="false" validType="EndTime">
						<a id="log_search"  class="easyui-linkbutton l-btn" onclick="updateData()" data-options="iconCls:'icon-search'" href="javascript:void(0);">
			             		 查询</a>
					</span>
					</div>
					<div id="container"></div>
				</div>
				<%--右边 --%>
			</div> 
		</div>
	<%--<div class="area2"></div>
    <s:include value="../include/enterprise_foot.jsp"></s:include> --%>
</body>
</html>
