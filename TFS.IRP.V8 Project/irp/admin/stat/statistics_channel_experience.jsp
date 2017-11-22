<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>栏目统计图</title>


</head>
<body>
<script type="text/javascript">


  $(function(){  
	  $('#container').highcharts({
          chart: {
              type: 'column'
          },
          title: {
              text: '栏目知识统计'
          },
          subtitle: {
              text: ''
          },
          xAxis: {
              categories:[<s:property value="groupname" escapeHtml="false" />]
          },
          yAxis: {
        	  labels: { 
                  min:0, 
                  style: { 
                      color: '#89A54E' //设置标签颜色 
                  } 
              }, 
              title: {
                  text: '知识数量'
              }
          },
          tooltip: {
              headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
              
              pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                  '<td style="padding:0"><b>{point.y:0f} </b></td></tr>',
              footerFormat: '</table>',
              shared: true,
              useHTML: true
          },
          plotOptions: {
              column: {
                  pointPadding: 0.2,
                  borderWidth: 0
              }
          },
          series: [ {
              name: '知识数量',
              data: [<s:property value="docNum"/>]
              
  
          }]
      }); 
	  
  });
  /**
  *分页
  */
  function page(_nPageNum){
  	$('#pageNum').val(_nPageNum);
  	var queryString = $('#pageForm').serialize();
 
  }
  function caizhi(){
	  var ss =$('#groupstext').val();
	  jump('<%=rootPath %>site/channelstatis.action?itype=2&groupId='+ss)
  }
  function jump(_sUrl){
		$('body').layout('panel','center').panel('refresh',_sUrl);
	}
  /**
  *排序
  */
  function orderBy(ordername){
	    $('#orderField').val(ordername);
		var queryString = $('#pageForm').serialize();
	  	jump("<%=rootPath%>stat/statuserscoreexperience.action?"+queryString);
	  return false;
  }	
</script>
<form id="pageForm">
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
</form>
<div>
<div>
<input type="hidden" id="groupstext" value='<s:property value='groupstext'/>' />
<img border="0" src="images/icons/bttj.png" title="饼图" style="cursor:pointer; margin: 0 5px;" onclick="caizhi()"/>
</div>
<div id="container"></div>

</div>
</body>
</html>