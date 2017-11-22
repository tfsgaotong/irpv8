package com.tfs.irp.util.mailmessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.tfs.irp.util.SysConfigUtil;

/**
 * 发送邮件需要使用的基本信息
 * 
 * @author admin
 * 
 */
public class MailSenderInfo {
	private String mailServerHost;// 服务器
	private String mailServerPort = "25";// 端口
	private String fromAddress;// 发送者地址
	private List<String> toAddress = new ArrayList<String>();// 接收者地址
	private List<String> copyaddress = new ArrayList<String>();// 抄送
	private List<String> bcoaddress = new ArrayList<String>();// 密送
	private String userName;// 服务器邮箱账号
	private String password;// 服务器邮箱密码
	private boolean validate = false;// 是否需要身份验证
	private String subject;// 邮件主题
	private String content;// 文本内容
	private String[] attachFileNames;// 邮件附件的文件名称

	/**
	 * 构造方法初始化系统邮箱
	 */
	public MailSenderInfo(){
		this.mailServerHost = SysConfigUtil.getSysConfigValue("SYS_HOST");
		this.mailServerPort = SysConfigUtil.getSysConfigNumValue("SYS_PORT").toString();
		this.userName = SysConfigUtil.getSysConfigValue("SYS_EMAIL");
		this.password = SysConfigUtil.getSysConfigValue("SYS_PASSWORD");
		this.fromAddress = SysConfigUtil.getSysConfigValue("SYS_EMAIL");
		this.subject = SysConfigUtil.getSysConfigValue("EMAIL_NICK")+"消息提醒";
		this.validate = true;
	}
	
	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.setProperty("mail.transport.protocol","smtp");//设置使用smtp协议
		p.put("mail.smtp.host", this.mailServerHost);// 设置SMTP服务器地址
		p.put("mail.smtp.port", this.mailServerPort);// 设置SMTP端口号
		p.put("mail.smtp.auth", validate ? "true" : "false"); // SMTP服务用户认证
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public List<String> getCopyaddress() {
		return copyaddress;
	}

	public void setCopyaddress(List<String> copyaddress) {
		this.copyaddress = copyaddress;
	}

	public List<String> getBcoaddress() {
		return bcoaddress;
	}

	public void setBcoaddress(List<String> bcoaddress) {
		this.bcoaddress = bcoaddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public List<String> getToAddress() {
		return toAddress;
	}

	public void setToAddress(List<String> toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
}
