<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<div> 
	<%--<- div style="float: left;<s:if test="isScore=='true'">  display: none; </s:if>" id="scorediv" class="shop-rating" >
		<!-- END 星级评分 -->
		<ul class="rating-level" id="stars2">
		<li><a class="one-star" star:value="1" href="javascript:void(0);">1</a></li>
		<li><a class="two-stars" star:value="2" href="javascript:void(0);">2</a></li>
		<li><a class="three-stars" star:value="3" href="javascript:void(0);">3</a></li>
		<li><a class="four-stars" star:value="4" href="javascript:void(0);">4</a></li>
		<li><a class="five-stars" star:value="5" href="javascript:void(0);">5</a></li>
		</ul>
		<span>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="teststars()">评分</a>&nbsp;</span> 
		<span class="result" id="stars2-tips"></span>
		<input type="hidden" id="stars2-input" name="b" value="0" size="2" /> 
	</div> --%>
	<s:if test="sumperson >= @com.tfs.irp.util.SysConfigUtil@getSysConfigNumValue('DOC_SCORE_SHOW_NUM')">
	<div style="float: left;" id="allscore" class="shop-rating" > 
		<ul class="rating-level" id="stars1">
			<li><a class="one-star <s:if test='avgScore>0 && 1.5>=avgScore'> current-rating</s:if>">1</a></li>
			<li><a class="two-stars <s:if test='avgScore>1.5 && 2.5>=avgScore'> current-rating</s:if>">2</a></li>
			<li><a class="three-stars <s:if test='avgScore>2.5 && 3.5>=avgScore'> current-rating</s:if>">3</a></li>
			<li><a class="four-stars <s:if test='avgScore>3.5 && 4.5>=avgScore'> current-rating</s:if>">4</a></li>
			<li><a class="five-stars <s:if test='avgScore>4.5'> current-rating</s:if>">5</a></li>
		</ul>
		<span>&nbsp;&nbsp;<s:property value='avgScore' default="0"/>分/<s:property value='sumperson' default="0"/>人</span>
	</div>
	</s:if>
</div>
<script type="text/javascript"> 
$(function(){
	 initScore(0);
});
//初始化评分为0
function initScore(score){
	$('#stars2-input').val(score);
}
//综合评分
var Class = {
	create: function() {
		return function() {  this.initialize.apply(this, arguments); }
	}
}
var Extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property];
	}
}
function stopDefault( e ) {
	if ( e && e.preventDefault ){
		e.preventDefault();
	}else{
		window.event.returnValue = false;
	}
	return false;
}
/**
* 星星打分组件
*
* @author    Yunsd
* @date        2010-7-5
*/
var Stars = Class.create();
Stars.prototype = {
initialize: function(star,options) {
		this.SetOptions(options); //默认属性
		var flag = 999; //定义全局指针
		var isIE = (document.all) ? true : false; //IE?
		var starlist = document.getElementById(star).getElementsByTagName('a'); //星星列表
		var input = document.getElementById(this.options.Input) || document.getElementById(star+"-input"); // 输出结果
		var nowClass = " " + this.options.nowClass; // 定义选中星星样式名
		var len = starlist.length; //星星数量
	 	for(i=0;i<len;i++){ // 绑定事件 点击 鼠标滑过
			starlist[i].value = i;
			starlist[i].onclick = function(e){
			stopDefault(e);
			this.className = this.className + nowClass;
			flag = this.value;
			input.value = this.getAttribute("star:value");
		}
		starlist[i].onmouseover = function(){
			if (flag< 999){
				var reg = RegExp(nowClass,"g");
				starlist[flag].className = starlist[flag].className.replace(reg,"");
			}
		} 
		starlist[i].onmouseout = function(){
			if (flag< 999){
				starlist[flag].className = starlist[flag].className + nowClass;
			}
		}
	};
	if (isIE){ //FIX IE下样式错误
		var li = document.getElementById(star).getElementsByTagName('li');
			for (var i = 0, len = li.length; i < len; i++) {
				var c = li[i];
					if (c) {
						c.className = c.getElementsByTagName('a')[0].className;
					}
				}
			}
		},
		//设置默认属性
		SetOptions: function(options) {
			this.options = {//默认值
				Input:            "",//设置触保存分数的INPUT
				Tips:            "",//设置提示文案容器
				nowClass:    "current-rating" //选中的样式名
			};
			Extend(this.options, options || {});
		}
}

var Stars2 = new Stars("stars2"); 
</script>

