<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.tfs.irp.question.entity.IrpQuestion"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<script language="JavaScript" type="text/javascript">
	//字数限制
	function questionInfoControl(questionInfo,total){
		if(total-$.trim(questionInfo).length>=0){
			$('#errorQuestionNum').css({display:'none'});
			$('#textAreaNum').css({display:'block'});
			$('#questionNum').text(total-$.trim(questionInfo).length);
		}else{
			$('#textAreaNum').css({display:'none'});
			$('#errorQuestionNum').css({display:'block'});
			$('#errorNumCount').text(Math.abs(total-$.trim(questionInfo).length));
		}
	}
	//标题字数控制
	function questionTitleInfoControl(questionInfo,total){
		if(total-$.trim(questionInfo).length>=0){
			$('#titleAcreaNumChaochu').css({display:'none'});
			$('#titleAcreaNumfont').css({display:'block'});
			$('#titleAcreaNumfontNum').text(total-$.trim(questionInfo).length);
		}else{
			$('#titleAcreaNumfont').css({display:'none'});
			$('#titleAcreaNumChaochu').css({display:'block'});
			$('#titleerrorNumCount').text(Math.abs(total-$.trim(questionInfo).length));
		}
	}
</script>
		<div class="main" style="width: auto;">
			<!--左侧内容-->
			<div class="left">
				<div class="shuru">
					<ul style="background:url(<%=rootPath%>client/images/ico04-1.gif)">
						<li id="titleAcreaNumfont" style="text-align: right; display: block;">
								您还可以输入<label id="titleAcreaNumfontNum" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">150</label>个字
							</li>
							<li id="titleAcreaNumChaochu" style="text-align: right; display: none;">
									超出了 <label id="titleerrorNumCount" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字
							</li>
					<li><input style="width: 636px;height: 30px;" id="questiontitlecontent" class="wtgy" type="text" onkeyup="questionTitleInfoControl(this.value,150)"/>
					</li>
						<li id="textAreaNum" style="text-align: right; display: block;">
							您还可以输入
							<label id="questionNum" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">300</label>
							个字
						</li>
						<li id="errorQuestionNum" style="text-align: right;display: none;">
							超出了
							<label id="errorNumCount" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;" >
							</label>个字
						</li>
						<li><textarea name="textarea" rows="4" id="question_info"
								onkeyup="questionInfoControl(this.value,300)"></textarea>
						</li>
						<li>
							<table border="0" cellspacing="0" cellpadding="0" width="100%">
								<tbody>
									<tr>
										<td width="100"></td>
										<td width="70">
										</td>
										<td>
										</td>
										<td></td>
									</tr>
								</tbody>
							</table>
						</li>
						<li class="shuruend"></li>
					</ul>
				</div>
			</div>
			<!--左侧内容结束-->
		</div>
