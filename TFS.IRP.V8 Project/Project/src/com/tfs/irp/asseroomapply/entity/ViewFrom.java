package com.tfs.irp.asseroomapply.entity;

import java.util.Date;

public class ViewFrom {
private Long id;
private Date start;//开始时间
private Date end;//结束时间
private String fullname;//会议名称
private String confname;//会议室
private String confshortname;//
private String description;//会议内容
private int confid;//是否可被删除
private String topic;
private String title;
private Long sid;//申请会议id
private boolean allDay;//是否是全天
private String backgroundColor;//背景色
public boolean isAllDay() {
	return allDay;
}
public String getBackgroundColor() {
	return backgroundColor;
}
public void setBackgroundColor(String backgroundColor) {
	this.backgroundColor = backgroundColor;
}
public void setAllDay(boolean allDay) {
	this.allDay = allDay;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Date getStart() {
	return start;
}
public void setStart(Date start) {
	this.start = start;
}
public Date getEnd() {
	return end;
}
public void setEnd(Date end) {
	this.end = end;
}
public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
public String getConfname() {
	return confname;
}
public void setConfname(String confname) {
	this.confname = confname;
}
public String getConfshortname() {
	return confshortname;
}
public void setConfshortname(String confshortname) {
	this.confshortname = confshortname;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getConfid() {
	return confid;
}
public void setConfid(int confid) {
	this.confid = confid;
}
public String getTopic() {
	return topic;
}
public void setTopic(String topic) {
	this.topic = topic;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Long getSid() {
	return sid;
}
public void setSid(Long sid) {
	this.sid = sid;
}
@Override
public String toString() {
	return "ViewFrom [id=" + id + ", start=" + start + ", end=" + end
			+ ", fullname=" + fullname + ", confname=" + confname
			+ ", confshortname=" + confshortname + ", description="
			+ description + ", confid=" + confid + ", topic=" + topic
			+ ", title=" + title + ", sid=" + sid + "]";
}

}

//sid: 1,
//uid: 1,
//title: 'Daily Scrum meeting',
//start: evtstart,
//end: evtend,
//fullname: 'terry li',
//confname: 'Meeting 1',
//confshortname: 'M1',
//confcolor: '#ff3f3f',
//confid: 'test1',
//allDay: false,
//	topic: 'Daily Scrum meeting',
//description: 'Daily Scrum meeting',
//id: 1