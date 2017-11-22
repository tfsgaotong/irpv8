<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>栏目统计图</title>
<body>

<script type="text/javascript">
$(function(){ 
	
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
						var amountStr="<s:property value='bttext'/>;";
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
		title:{text:'栏目统计图...'},
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
	                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+this.y+' 篇)';
	                    }  
	                }  ,
				showInLegend: false //不显示名称在下面
			}
		},
		series: [
		         {
		        	 type: 'pie',
		        	 name: '知识数量' 
		         }]
		
			 	
			 	
		 
	 });
}
function zhuxing(){
	  var ss =$('#groupstext').val();
	  jump('<%=rootPath %>site/channelstatis.action?itype=1&groupId='+ss)
}
</script>  
<div style="padding-left: 10px;">
<input type="hidden" id="groupstext" value='<s:property value='groupstext'/>' />
<img border="0" src="images/icons/zztj.png" title="柱状" style="cursor:pointer; margin: 0 5px;" onclick="zhuxing()"/>
</div>
<div id="container"></div>
</body>
</html>
