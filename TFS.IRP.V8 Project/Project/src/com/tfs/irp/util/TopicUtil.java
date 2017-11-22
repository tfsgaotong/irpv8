package com.tfs.irp.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.service.IrpTopicService;
import com.tfs.irp.topiclink.entity.IrpTopicLink;
import com.tfs.irp.topiclink.service.IrpTopicLinkService;

/**
 * 话题工具类
 * @author Administrator
 *
 */
public class TopicUtil {
	private ApplicationContext wac1;
	
	public TopicUtil(){
		 wac1 = ApplicationContextHelper.getContext();
	}
	
	/**
	 * 处理主题 #  
	 * @param _info
	 * @return
	 */
	public  Set  getSameTopicId(String _info){
		HashSet<String> getTopicInfo = new HashSet<String>();
        if(getTrueFalseStr(_info)==false){
		_info =  _info.replaceFirst("#{1,}", "#");
        String _infoStr = _info.replaceAll("#{2,}", "##").replaceAll("\\s{1,}"," ");
       
        String infoArray[] = _infoStr.split("#");
		if(infoArray.length>=3){	
			for(int i =0;i<infoArray.length/2;i++){	
			  int topicnum = _infoStr.indexOf("#");
			  int topicnumtwo = _infoStr.indexOf("#", topicnum+1);
			  if(topicnum<topicnumtwo){
				  getTopicInfo.add("#"+_infoStr.substring(topicnum+1, topicnumtwo)+"#");
			  }
			  _infoStr = _infoStr.substring(topicnumtwo+1);
			}
		}else if(infoArray.length==2){
			if(_infoStr.indexOf("#")+1<= _infoStr.lastIndexOf("#")){
				 getTopicInfo.add("#"+_infoStr.substring(_infoStr.indexOf("#")+1, _infoStr.lastIndexOf("#"))+"#");
				  
			}else{
				getTopicInfo.clear();
			}		
		}else{
			getTopicInfo.clear();
		}
        }else{
        	getTopicInfo.clear();
        }
        
        
        
		return getTopicInfo;
	}  	
	/**
	 * 判断这个字符串是否由同一个#
	 * @param _info
	 * @return
	 */
	private  boolean getTrueFalseStr(String _info){	
		boolean flag = true;
		char disposeStr[] = _info.replace(" ","").replace("<br/>","").toCharArray();
		for (int i = 0; i < disposeStr.length; i++) {
            if('#'==disposeStr[i]){
            	flag = true;
            }else{
            	flag = false;
                break;
            }
		    }	
		return flag;
	}
	/**
	 * 返回处理后的字符串
	 * @param _info
	 * @return
	 */
	public  String  getTopicStr(String _info,Set _disposeStr,long _microblogid){
        if(getTrueFalseStr(_info)==false){
    		Iterator iterators = _disposeStr.iterator();
    		while (iterators.hasNext()) {
    			String info =(String) iterators.next();
    			getIrpTopicService().addTopic(info);
    			getIrpTopicLinkService().addTopicLink(info, _microblogid, IrpTopicLink.TOPICTYPE_MICR);
			}
    		if(!_disposeStr.isEmpty()){
    			Iterator<String> iterator=_disposeStr.iterator();
    			while(iterator.hasNext()){
    			String disposeStr =	iterator.next();
    			IrpTopic irpTopics = getIrpTopicService().getIrpTopic(disposeStr);
    			_info=_info.replace(disposeStr,"<a  href=\"javascript:void(0)\" class=\"linkb14\" onclick=\"getInfoTopicPage("+irpTopics.getTopicid()+")\">"+disposeStr+"</a>");
    			}
    			}

        }
		return _info;
	}
	private   IrpTopicService getIrpTopicService(){
		return    (IrpTopicService) wac1.getBean("irpTopicService");
	}
	private   IrpTopicLinkService getIrpTopicLinkService(){
		return    (IrpTopicLinkService) wac1.getBean("irpTopicLinkService");
	}
	
}
