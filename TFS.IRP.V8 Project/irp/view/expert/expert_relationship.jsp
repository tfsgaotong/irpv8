<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<script src="<%=rootPath%>view/js/esl.js" type="text/javascript"></script>
</head>

<body>
<div id="similarity" style="width:856px;height:600px;padding:1px;overflow:hidden;"></div>
<script type="text/javascript">
require.config({
	packages:[{
		name:'echarts',
		location:'<%=rootPath%>view/js/echarts/src',
		main:'echarts'
	},{
		name:'zrender',
		location:'<%=rootPath%>view/js/zrender/src',
		main:'zrender'
	}]
});
var option = {
    title : {
        text: '',
        subtext: '',
        x:'center',
        y:'bottom'
    },
    tooltip : {
        trigger: 'item',
        formatter: '{a} : {b}'
    },
    legend: {
        x: 'left',
        selected:{'擅长领域':true,'专家':true},
        data:['擅长领域','专家']
    },
    isShowScrollBar:false,
    series: [{
		type:'kforce',
		categories : [{
			name: '擅长领域',
            itemStyle: {
                normal: {color : '#ff7f50'}
            }
		},
		{
		    name:'专家',
		    itemStyle: {
		        normal: {color : '#87cdfa'}
		    }
		}],
		itemStyle: {
			normal: {
				label: {
					show: true,
					textStyle: {
					    color: '#000000'
					}
				},
				nodeStyle : {
				    brushType : 'both',
				    strokeColor : 'rgba(255,215,0,0.4)',
				    lineWidth : 2
				}
			},
			emphasis:{
				linkStyle : { strokeColor : '#5182AB'}
			}
		},
		minRadius : 15,
		maxRadius : 25,
		density : 0.8,
		attractiveness: 0.8,
		nodes:[
			{
				category:1, 
				name: '<s:property value="irpUser.username" />', 
				value : <s:property value="irpUser.userid" />,
				shapeType:'rectangle',
				itemStyle:{
					normal:{ width:80, height:60 }
				},
			}
				
				<s:iterator value="irpCategorieList">
			,{
				category:0, 
				name: '<s:property value="CNAME" />',
				value : <s:property value="CATEGORYID" />,
				shapeType:'ellipse',
				itemStyle:{
					normal:{a:40,b:20}
				},
				onclick:function(params){
				window.open('<s:url action="listcategory" namespace="/expert"><s:param name="categoryId" value="CATEGORYID" /></s:url>');
							
						}
			}
			</s:iterator>
				<s:iterator value="irpCategorieList">
					<s:iterator value="getUserBycateforyid(CATEGORYID,irpUser.userid)">
						,{
						category:1, 
						name: '<s:property value="USERNAME" />', 
						value : <s:property value="USERID" />,
						shapeType:'rectangle',
						itemStyle:{
							normal:{ width:60, height:40 }
						},
						onclick:function(params){
							window.open('<s:url action="client_to_expert" namespace="/site"><s:param name="personid" value="USERID" /></s:url>');
							
						}
					}
					</s:iterator>
				</s:iterator>
		],
		links : [
		     <s:iterator value="irpCategorieList"  status="status">
		    {source : <s:property value="#status.count" />, target : 0, weight : 1}<s:if test="!#status.last">,</s:if>
		    </s:iterator>
		    <s:set var="i"  value="irpCategorieList.size()"></s:set>
		    <s:iterator value="irpCategorieList" status="status">
					<s:iterator value="getUserBycateforyid(CATEGORYID,irpUser.userid)" status="status1" >
		   		 ,{source : <s:property value="#status1.count+#i" />, target : <s:property value="#status.count" />, weight : 1}
		    </s:iterator>
		    <s:set var="i"  value="#i+getUserBycateforyid(CATEGORYID,irpUser.userid).size()"></s:set>
		   
		     </s:iterator>
		    
		]}
    ]
};
require(
    [
        'echarts',
        'echarts/chart/kforce',
    ],
    function(ec) {
        var myChart = ec.init(document.getElementById('similarity'));
        myChart.setOption(option);
    }
)
</script>
</body>
</html>