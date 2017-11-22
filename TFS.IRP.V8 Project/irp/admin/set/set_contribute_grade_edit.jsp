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
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
$(function(){
	//加载记分器
	//开始积分
	$('#begingradescore').numberspinner({   
	    min: -99999999,   
	    max: 99999999,   
	    editable: true,
	    value:<s:if test="irpValueSetting.beginscore==null">0</s:if><s:else>${irpValueSetting.beginscore}</s:else>
	});
	//结束积分
	$('#gradescore').numberspinner({   
	    min: -99999999,   
	    max: 99999999,   
	    editable: true,
	    value:<s:if test="irpValueSetting.endscore==null">0</s:if><s:else>${irpValueSetting.endscore}</s:else>
	});
	
	//可创建组数
	$('#crgroupcount').numberspinner({   
	    min: -99999999,   
	    max: 99999999,   
	    editable: true,
	    value:<s:if test="irpValueSetting.crgroupnum==null">0</s:if><s:else>${irpValueSetting.crgroupnum}</s:else>
	});
	//可创建栏目数
	$('#crchannelcount').numberspinner({   
	    min: -99999999,   
	    max: 99999999,   
	    editable: true,
	    value:<s:if test="irpValueSetting.crchannelnum==null">0</s:if><s:else>${irpValueSetting.crchannelnum}</s:else>
	});
	
	
	
});
$.extend($.fn.validatebox.defaults.rules, {   
    configckeyExit: {   
        validator: function(value,param){   
            if(value.length<param[0] || value.length>param[1] ){
            	$.fn.validatebox.defaults.rules.configckeyExit.message=$.fn.validatebox.defaults.rules.length.message;
            	return false;
            }else{
            	return true;
            }
        },   
        message: ''  
    }   
}); 
</script>
<form id="valuesettingedit" method="post">
<input type="hidden" name="irpValueSetting.settingid" value="<s:property value="irpValueSetting.settingid" default="0" />" />
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright" width="40%">级别名称</td>
   <td bgcolor="#F5FAFE" class="main_bright" width="60%"><input validType="configckeyExit[2,130]" type="text" name="irpValueSetting.rankname" id="" class="easyui-validatebox" required="true" missingMessage="请输入级别名称" value="<s:property value='irpValueSetting.rankname' />"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright" width="40%">级别描述</td>
   <td bgcolor="#F5FAFE" class="main_bright" width="60%"><input type="text" required="true" missingMessage="请输入级别描述" validType="configckeyExit[1,130]" name="irpValueSetting.rankdesc" id="" class="easyui-validatebox" value="<s:property value='irpValueSetting.rankdesc' />"></td>
  </tr>
    <tr>
   <td bgcolor="#F5FAFE" class="main_bright" width="40%">开始积分</td>
   <td bgcolor="#F5FAFE" class="main_bright" width="60%"><input type="text"  name="irpValueSetting.beginscore" id="begingradescore" style="width:80px;"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright" width="40%">结束积分</td>
   <td bgcolor="#F5FAFE" class="main_bright" width="60%"><input type="text"  name="irpValueSetting.endscore" id="gradescore" style="width:80px;"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright" width="40%">可创建组数</td>
   <td bgcolor="#F5FAFE" class="main_bright" width="60%"><input type="text"  name="irpValueSetting.crgroupnum" id="crgroupcount" style="width:80px;" ></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">可创建栏目数</td>
   <td bgcolor="#F5FAFE" class="main_bright"><input type="text"  name="irpValueSetting.crchannelnum" id="crchannelcount" style="width:80px;"></td>
  </tr>
 </table>
 </form>
</body>
</html>