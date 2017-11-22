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
<title>积分经验统计图</title>


</head>
<body>
<script type="text/javascript">


  $(function(){  
	  $('#container').highcharts({
          chart: {
              type: 'column'
          },
          title: {
              text: '积分经验统计'
          },
          subtitle: {
              text: ''
          },
          xAxis: {
              categories:[<s:property value="usernamejson" escapeHtml="false" />]
          },
          yAxis: {
              min: 0,
              title: {
                  text: '用户积分'
              }
          },
          tooltip: {
              headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
              
              pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                  '<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',
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
          series: [{
              name: '积分',
              data: [<s:property value="scorejson" />]
  
          }, {
              name: '经验',
              data: [<s:property value="experjson" />]
  
          }]
      }); 
	  
  });
  /**
  *分页
  */
  function page(_nPageNum){
  	$('#pageNum').val(_nPageNum);
  	var queryString = $('#pageForm').serialize();
  	jump("<%=rootPath%>stat/statuserscoreexperience.action?"+queryString);
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

</div>
<div id="container"></div>
<table width="100%" border="0" align="center" cellpadding="10" >
 <tr >
   <td  align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
</div>
</body>
</html>