<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	Cookie[] cookies = request.getCookies();
	String sSkin = null;
	if (cookies != null) {  
        for (Cookie cookie : cookies) {  
            if ("nowskin".equals(cookie.getName())) {  
            	sSkin = cookie.getValue();  
            }  
        }  
    }
	if(sSkin!=null){
		sSkin = sSkin.replaceAll("%3A", ":");
		out.println("<link id=\"skin\" href=\""+sSkin+"\" rel=\"stylesheet\" type=\"text/css\" />");
		String sColor = "i"+sSkin.substring(sSkin.lastIndexOf("/oapf-")+6,sSkin.lastIndexOf(".css"));
		out.println("<link id=\"skin1\" href=\""+rootPath+"/client/js/skins/"+sColor+".css\" rel=\"stylesheet\" type=\"text/css\" />");
	}else{
		out.println("<link id=\"skin\" href=\""+rootPath+"/client/css/oapf-blue.css\" rel=\"stylesheet\" type=\"text/css\" />");
		out.println("<link id=\"skin1\" href=\""+rootPath+"/client/js/skins/iblue.css\" rel=\"stylesheet\" type=\"text/css\" />");
	}
%>
<script type="text/javascript">
function SetCookie(name, value) {
	var argv = SetCookie.arguments;
	var argc = SetCookie.arguments.length;
	var expires = (2 < argc) ? argv[2] : null;
	var path = (3 < argc) ? argv[3] : null;
	var domain = (4 < argc) ? argv[4] : null;
	var secure = (5 < argc) ? argv[5] : false;
	document.cookie = name
			+ "="
			+ escape(value)
			+ ((expires == null) ? "" : ("; expires=" + expires
					.toGMTString()))
			+ ((path == null) ? "" : ("; path=" + path))
			+ ((domain == null) ? "" : ("; domain=" + domain))
			+ ((secure == true) ? "; secure" : "");
}

function GetCookie(Name) { 
	var search = Name + "=";
	var returnvalue = "";
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1)
				end = document.cookie.length;
			returnvalue = unescape(document.cookie.substring(offset, end));
		}
	} 
	return returnvalue;
}

function changecss(url) {
	if (url != "") {
		document.getElementById('skin').href = url;
		var sColor  = 'i'+url.substring(url.lastIndexOf('/oapf-')+6,url.lastIndexOf('.css'));
	    document.getElementById('skin1').href='<%=rootPath%>client/js/skins/'+sColor+'.css';
		var expdate = new Date();
		expdate.setTime(expdate.getTime() + (24 * 60 * 60 * 1000 * 30));
		//expdate=null;
		//以下设置COOKIES时间为1年,自己随便设置该时间..
		SetCookie("nowskin", url, expdate, "/", null, false);
		if(typeof(CKEDITOR)!="undefined"){
			if(sColor=="iblack"){
				CKEDITOR.instances.editor.setUiColor('#EEEEEE');
			}else if(sColor=="iorange"){
				CKEDITOR.instances.editor.setUiColor('#F7EDD7');
			}else if(sColor=="igreen"){
				CKEDITOR.instances.editor.setUiColor('#EBF4DA');
			}else if(sColor=="iblue"){
				CKEDITOR.instances.editor.setUiColor('#ECFAFB');
			}else{
				CKEDITOR.instances.editor.setUiColor('#ECFAFB');
			}
		}
	}
}
</script>