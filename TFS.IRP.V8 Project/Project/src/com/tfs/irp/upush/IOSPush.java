package com.tfs.irp.upush;

import java.text.SimpleDateFormat;

import java.util.Date;


public class IOSPush {
	
	/**
	 * @Description: IOS消息广播推送
	 * @param body	消息内容
	 * @param title	消息标题
	 * @param productionMode 是否为生产模式
	 */
	public static void sendIOSBroadcast(String body, String title, boolean productionMode) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast("59473fcff29d98579d000a11", "z92wx9onom3iynxiruw7ki7lgupbgpwk");
		// 内容 标题
		broadcast.setAlert(body, title);
		broadcast.setBadge(1);
		broadcast.setSound("default");
		broadcast.setContentAvailable(1);
		broadcast.setDescription("");
		if(productionMode){
			// set 'production_mode' to 'true' if your app is under production mode
			broadcast.setProductionMode();
		}else{
			broadcast.setTestMode();
		}
		// Set customized fields
		broadcast.setCustomizedField("test", "helloworld");
		PushClient client = new PushClient();
		client.send(broadcast);
	}

	public static void main(String[] args) {
		String body = "2017年4月6日 对中文期刊文献数据库数据进行更新。新增主题词 17863条";
		String title = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
		try {
			//测试
			IOSPush.sendIOSBroadcast(body, title, false);
		} catch (Exception e) {
			System.out.println("推送异常");
		}
	}
	
}
