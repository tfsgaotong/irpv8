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
<s:set var="keyWords" value="irpDocument.dockeywords.split(',')" />
<div id="similarity" style="width:800px;height:550px;border:1px solid #ccc;padding:1px;overflow:hidden;"></div>
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
        selected:{'知识':true,'标签':true},
        data:['标签','知识']
    },
    isShowScrollBar:false,
    series: [{
		type:'kforce',
		categories : [{
			name: '标签',
            itemStyle: {
                normal: {color : '#ff7f50'}
            }
		},
		{
		    name:'知识',
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
				name: '<s:property value="irpDocument.doctitle" />', 
				value : <s:property value="irpDocument.docid" />,
				shapeType:'rectangle',
				itemStyle:{
					normal:{ width:100, height:60 }
				}
			}
			<s:iterator value="documentList">
			,{
				category:1, 
				name: '<s:property value="doctitle" />',
				value : <s:property value="docid" />,
				shapeType:'rectangle',
				itemStyle:{
					normal:{ width:80, height:40 }
				},
				onclick:function(params){
					window.open('<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>');
				}
			}
			</s:iterator>
			<s:iterator var="word" value="#keyWords" status="status">
				<s:if test="!#word.trim().isEmpty()">
			,{
				category:0, 
				name: '<s:property value="#word.trim()" />',
				value : <s:property value="#status.count" />,
				shapeType:'ellipse',
				itemStyle:{
					normal:{a:40,b:20}
				},
				onclick:function(params){
					searchInfo1('<s:property value="#word"/>');
				}
			}
				</s:if>
			</s:iterator>
				 <s:iterator value="documentOne" >
					,{
						category:1, 
						name: '<s:property value="doctitle" />',
						value : <s:property value="docid" />,
						shapeType:'rectangle',
						itemStyle:{
						normal:{ width:80, height:40 }
						},
						onclick:function(params){
							window.open('<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>');
							
						}
					}
				 </s:iterator>
				 <s:iterator value="documentTwo" >
					,{
						category:1, 
						name: '<s:property value="doctitle" />',
						value : <s:property value="docid" />,
						shapeType:'rectangle',
						itemStyle:{
						normal:{ width:80, height:40 }
						},
						onclick:function(params){
							window.open('<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>');
							
						}
					}
				 </s:iterator>
				 
				  <s:iterator value="documentThree" >
					,{
						category:1, 
						name: '<s:property value="doctitle" />',
						value : <s:property value="docid" />,
						shapeType:'rectangle',
						itemStyle:{
						normal:{ width:80, height:40 }
						},
						onclick:function(params){
							window.open('<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>');
							
						}
					}
				 </s:iterator>
		],
		links : [
		    <s:iterator value="documentList" status="status">
		    {source : <s:property value="#status.count" />, target : 0, weight : 1}<s:if test="!#status.last">,</s:if>
		    </s:iterator>
		    <s:iterator var="word" value="#keyWords" status="status">
		    <s:if test="#status.first && !documentList.isEmpty()">,</s:if>{source : <s:property value="#status.count+documentList.size()" />, target : 0, weight : 1}<s:if test="!#status.last">,</s:if>
		    </s:iterator>
		    
		    <s:set var="key" value="#keyWords.length" /> 
		     <s:iterator value="documentOne" status="status">
		     <s:if test="#status.first && !documentList.isEmpty()">,</s:if>{source :  <s:property value="#status.count+#key+documentList.size()" />, target :  <s:property value="1+documentList.size()" />, weight : 1}<s:if test="!#status.last">,</s:if>
		    </s:iterator>
		    <s:iterator value="documentTwo" status="status">
		     <s:if test="#status.first && !documentList.isEmpty()">,</s:if>{source :  <s:property value="#status.count+#key+documentList.size()+documentOne.size()" />, target :  <s:property value="2+documentList.size()" />, weight : 1}<s:if test="!#status.last">,</s:if>
		    </s:iterator>
		    <s:iterator value="documentThree" status="status">
		     <s:if test="#status.first && !documentList.isEmpty()">,</s:if>{source :  <s:property value="#status.count+#key+documentList.size()+documentOne.size()+documentTwo.size()" />, target :  <s:property value="3+documentList.size()" />, weight : 1}<s:if test="!#status.last">,</s:if>
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