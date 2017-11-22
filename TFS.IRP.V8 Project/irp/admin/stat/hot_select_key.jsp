<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>热门检索词统计图</title>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
$(function(){ 
	$('#amountselect').combobox({
		panelHeight:'160',
		width:'50',
		editable:false,
		onSelect:function(){
			var amount=$('#amountselect').combobox('getValue');
			jump('<%=rootPath %>site/hotselectkey.action?keyAmount='+amount);
		}
	});
	hotKey();
});
function hotKey(){
	 new Highcharts.Chart({
		 chart: { 
				renderTo: 'container', //画布所在的div 
				plotBackgroundColor: null,
				plotBorderWidth: null,
			 	plotShadow: false ,
			 	events:{
					load:function(){
						var amountStr="<s:property value='amountJsonString'/>";
						if(amountStr!=null && amountStr.length>0){
							var data = []; 
							var series = this.series[0]; 
							var arrays=amountStr.split("-");
							for(var i=0;i<arrays.length;i++){
								var  str=arrays[i];
								var datas= decodeURI(str);
								var options=datas.split(",");
								var name=options[0];
								var value=window.parseInt(options[1]);
								data.push([name, value]);
							}
							series.setData(data); 
						}
					}
				} 
		},
		title:{text:'热门检索词统计图...'},
		tooltip:{
			 pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions:{
			pie:{
				allowPointSelect: true,
				cursor: 'pointer', 
				size:'80%', //圆形的直径大小比例
				 dataLabels: {  
	                    enabled: true,  
	                    color: '#000000',
	                    connectorColor: '#000000',  
	                    formatter: function() {  
	                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+this.y+' 次)';
	                    }  
	                }  ,
				showInLegend: false //不显示名称在下面
			}
		},
		series: [
		         {
		        	 type: 'pie',
		        	 name: '检索比例' 
		         }]
		
			 	
			 	
		 
	 });
}
</script>  
<div style="padding-left: 10px;">
<select id="amountselect">
	<option value="5" <s:if test="keyAmount==5">selected="selected"</s:if>>5</option>
	<option value="10" <s:if test="keyAmount==10">selected="selected"</s:if>>10</option>
	<option value="20" <s:if test="keyAmount==20">selected="selected"</s:if>>20</option>
	<option value="30" <s:if test="keyAmount==30">selected="selected"</s:if>>30</option>
</select>
</div>
<div id="container"></div>
</body>
</html>
