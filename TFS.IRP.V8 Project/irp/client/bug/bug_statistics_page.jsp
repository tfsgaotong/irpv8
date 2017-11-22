<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<style type="text/css">
.clean{
	clear: both;
}
.fenbu{
	margin-top:20px;
}
.fenbu_c{
	width: 450px;
	height: 300px;
	float: left;
	border: 1px solid #eee;
	margin-left:20px;
}
.zoushi, .bili, .renyuan{
	height:300px;
	width:920px;
	float: left;
	border: 1px solid #eee;
	margin-left:20px;
}
.zoushi{
	height:300px;
}
.bili, .renyuan{
	padding-top: 20px;
}
</style>
 
</head>
  <body>
  <script src="<%=rootPath%>client/js/echarts.min.js"  type="text/javascript"></script>
 	<div id="fenbu" class="fenbu">
		<div id="fenbu1" class="fenbu_c"></div>
		<div id="fenbu2" class="fenbu_c"></div>
	</div>
	<div id="zoushi" class="zoushi"></div>
	<div class="clean"></div>
 	<div id="bili" class="bili"></div>
 	<div id="renyuan" class="renyuan"></div>
 	<!-- js代码start -->
 	<script type="text/javascript">
		$(function (){
		
			
			var userlen=<s:property  value="projectUsers.size()"  />;
			var modlen=<s:property  value="bugmoduls.size()"  />;
			//根据人员数量确定容器高度   我叫她数据响应式
			if(userlen>6){
				$('#renyuan').css('height',userlen*50+'px');
			}
			if(modlen>6){
				$('#bili').css('height',modlen*50+'px');
			}
			
 	 	 // 基于准备好的dom，初始化echarts实例
 		var myChartFenbu1 = echarts.init(document.getElementById('fenbu1'));
 		var myChartFenbu2 = echarts.init(document.getElementById('fenbu2'));
 		var myChartZoushi = echarts.init(document.getElementById('zoushi'));
 		var myChartBili = echarts.init(document.getElementById('bili'));
 		var myChartRenyuan = echarts.init(document.getElementById('renyuan'));
 		var optionFenbu1 = {
			    title : {
			        text: '优先级',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['低','中','高','紧急','严重']
			    },
			    series : [
			        {
			            name: '优先级',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[
								<s:iterator value="fenbu1" var="map">
									<s:iterator value="#map" >
									{
										value:<s:property value="value" />,name:'<s:property value="key" />'
									},
									</s:iterator>
								</s:iterator>
			                  ],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
			myChartFenbu1.setOption(optionFenbu1);
			/* fenbu2*/
			var optionFenbu2 = {
				    title : {
				        text: '状态',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
				        data: ['未修复','待审核','已解决']
				    },
				    series : [
				        {
				            name: '完成情况',
				            type: 'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:[
								<s:iterator value="fenbu2" var="map">
									<s:iterator value="#map" >
									{
										value:<s:property value="value" />,name:'<s:property value="key" />'
									},
									</s:iterator>
								</s:iterator>
			                  ],
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};
				myChartFenbu2.setOption(optionFenbu2);
				/*fenbu2*/
				/*zoushi*/
				var optionZoushi = {
					    title: {
					        text: 'Bug总量趋势图'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['Bug总数']
					    },
					    toolbox: {
					        feature: {
					        	 dataZoom: {},
					             dataView: {readOnly: false},
					             //magicType: {type: ['line', 'bar']},
					             restore: {},
					             //saveAsImage: {}
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false,
					            data : <s:property  value="zoushiDate" escapeHtml="false" />
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'Bug总数',
					            type:'line',
					            stack: '总量',
					            // label: {
					            //     normal: {
					            //         show: true,
					            //         position: 'top'
					            //     }
					            // },
					            //areaStyle: {normal: {}},
					            data:<s:property  value="zoushiCount" escapeHtml="false" />
					        }
					    ]
					};

				myChartZoushi.setOption(optionZoushi);
				/*zoushi*/
				/*bili*/
				var optionBili = {
					    title : {
					        text: '各模块Bug比例图'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['未修复','待审核','已解决']
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'value',
					            boundaryGap : [0, 5]
					        }
					    ],
					    yAxis : [
					        {
					            type : 'category',
					            data : <s:property  value="bugsForModulJson" escapeHtml="false" />
					        }
					    ],
					    series : [
					        {
					            name:'未修复',
					            type:'bar',
					            data:<s:property  value="mweiBugsJson" escapeHtml="false" />,
					            itemStyle: {
					                normal: {
					                    color: '#fec514'
					                }
					            }
					        },
					        {
					            name:'待审核',
					            type:'bar',
					            data:<s:property  value="mshenBugsJson" escapeHtml="false" />,
					            itemStyle: {
					                normal: {
					                    color: '#ad81d9'
					                }
					            }
					        },
					        {
					            name:'已解决',
					            type:'bar',
					            data:<s:property  value="mwanBugsJson" escapeHtml="false" />,
					            itemStyle: {
					                normal: {
					                    color: '#add981'
					                }
					            }
					        }
					    ]
					};
				myChartBili.setOption(optionBili);
				/*bili*/
				/*renyuan*/
					var optionRenyuan = {
					    title : {
					        text: '开发人员Bug分配情况'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['未修复','待审核','已解决']
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'value',
					            boundaryGap : [0, 5]
					        }
					    ],
					    yAxis : [
					        {
					            type : 'category',
					            data : <s:property  value="projectPersonsJson" escapeHtml="false" />
					        }
					    ],
					    series : [
					        {
					            name:'未修复',
					            type:'bar',
					            data:<s:property  value="weiBugsJson" escapeHtml="false" />,
					            itemStyle: {
					                normal: {
					                    color: '#fec514'
					                }
					            }
					        },
					        {
					            name:'待审核',
					            type:'bar',
					            data: <s:property  value="shenBugsJson" escapeHtml="false" />,
					            itemStyle: {
					                normal: {
					                    color: '#ad81d9'
					                }
					            }
					        },
					        {
					            name:'已解决',
					            type:'bar',
					            data: <s:property  value="wanBugsJson" escapeHtml="false" />,
					            itemStyle: {
					                normal: {
					                    color: '#add981'
					                }
					            }
					        }
					    ]
					};
				myChartRenyuan.setOption(optionRenyuan);
				/*bili*/
			
		});
 	</script>
 	<!-- js代码end -->
  </body>
</html>
