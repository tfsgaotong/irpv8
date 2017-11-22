package com.tfs.irp.timedtask.entity;

public class TimedTask {

    /**
     * 每周定时发给所有的用户的模版
     * @param _username  用户名
     * @param _micronum  上周增加的微知数量
     * @param _knownum   上周增加的个人知识数量   
     * @param _confknownum  上周投稿到企业知识库的知识
     * @param _knowadopt  上周被采用的知识
     * @param _allkonwclick  上周在知识库中我的知识一共被点击次数
     * @param _allknowcomment  上周我的知识被所有的人的评论的次数
     * @param _userscore	用户目前的积分
     * @param _userrankingnum	用户在所有人员中的排名
     * @param _rankinginfo	用户本周较上周的排名变化
     * @return
     */
	public static String sendInfotemplate(String _username,Integer _micronum,
			Integer _knownum,Integer _confknownum,Integer _knowadopt,
			Integer _allknowcomment,Integer _userscore,Integer _userrankingnum
			){
		return  _username+"您好,您上周新增微知"+_micronum+",新增个人知识"
		+_knownum+",投稿到企业知识"+_confknownum+",被采用"+_knowadopt+",您所有企业知识库中知识被评论"+_allknowcomment+".您目前积分"+_userscore+",在所有人员中排名"+_userrankingnum;
		
	}
	
	/**
	 * 导入文件地址
	 * @return
	 */
	public static String DICLOCATION = "DICLOCATION";
	/**
	 * 导入文件地址 百科词条
	 * @return
	 */
	public static String TERMADDRESS = "TERMADDRESS";
	
	
	
	
	
}
