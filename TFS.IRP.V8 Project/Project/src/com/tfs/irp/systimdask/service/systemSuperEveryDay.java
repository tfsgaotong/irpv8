package com.tfs.irp.systimdask.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.solr.client.solrj.SolrServerException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.entity.IrpChannelExample;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.documentmap.entity.IrpDocumentMap;
import com.tfs.irp.documentmap.entity.IrpDocumentMapExample;
import com.tfs.irp.documentmap.service.IrpDocumentMapService;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.solr.service.TRSSearchService;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;

public class systemSuperEveryDay implements Job{
	private IrpChannelService irpChannelService;
	private TRSSearchService trsSearchService;
	private SolrService solrService;
	private IrpDocumentMapService irpDocumentMapService;
	
	/**
	 * 构造方法初始化数据
	 */
	public systemSuperEveryDay(){
		ApplicationContext wac = ApplicationContextHelper.getContext();
		irpChannelService = (IrpChannelService) wac.getBean("irpChannelService");
		trsSearchService=(TRSSearchService) wac.getBean("trsSearchService");
		solrService=(SolrService) wac.getBean("solrService");
		irpDocumentMapService= (IrpDocumentMapService) wac.getBean("irpDocumentMapService");
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		getQiyeSuperSub();
	}
	
	
	/**
	 * 执行
	 * @param key
	 * @return
	 */
	public void getQiyeSuperSub(){
		String searchwhere = SysConfigUtil.getSysConfigValue("searchOptions");
		IrpChannelExample channelExample = new IrpChannelExample();
		channelExample.createCriteria().andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT)
										.andSiteidEqualTo(0L)
										.andStatusEqualTo(IrpChannel.IS_DOCSTATIUS);
		List<IrpChannel> channels = irpChannelService.findChannelByExample(channelExample);
		if(channels!=null && channels.size()>0){
			List<Long> channelids = new ArrayList<Long>();
			for(int i =0;i<channels.size();i++){
				channelids.add(channels.get(i).getChannelid());
			}
			IrpDocumentMapExample mapExample = new IrpDocumentMapExample();
			mapExample.createCriteria().andChannelidIn(channelids)
										.andTypeEqualTo(IrpDocumentMap.KNOWLEDGE_SUBJECT);
			irpDocumentMapService.deleteDocSubByExample(mapExample);
			for(int j = 0;j<channels.size();j++){
				String searchInfo = channels.get(j).getChnlquery();
				List exampleList = new ArrayList();
				if(searchInfo!=null && !"".equals(searchInfo)){
					if("t".equals(searchwhere)){
						PageUtil pageutil = new PageUtil();
						pageutil.setPageNum(1);
						pageutil.setPageSize(999999999);
						exampleList = trsSearchService.selectDocbypage(pageutil, "IRP_DOC", searchInfo,
								"DOCCONTENT", null);
						//添加关系
						if(exampleList!=null && exampleList.size()>0){
							IrpDocumentMap map ;
							IrpDocumentWithBLOBs document;
							for(int i = 0;i<exampleList.size();i++){
								map = new IrpDocumentMap();
								document = (IrpDocumentWithBLOBs) exampleList.get(i);
								map.setMid(TableIdUtil.getNextId(IrpChannel.TABLE_NAME));
								map.setChannelid(channels.get(j).getChannelid());
								map.setDocid(document.getDocid());
								map.setType(IrpDocumentMap.KNOWLEDGE_SUBJECT);
								map.setCrtime(document.getCrtime());
								irpDocumentMapService.addDocSubByExample(map);
							}
						}
					}else{
						try {
							int docnum = this.solrService.searchDocumentNum(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),null);
							if(docnum>0){
								PageUtil pageUtildocumentExample = new PageUtil(1,docnum,docnum);
								exampleList = this.solrService.searchDocument(searchInfo,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtildocumentExample,0,null);
								//添加关系
								if(exampleList!=null && exampleList.size()>0){
									IrpDocumentMap map ;
									for(int i = 0;i<exampleList.size();i++){
										String docid = exampleList.get(i).toString().substring(exampleList.get(i).toString().indexOf("DOCID=")+"DOCID=".length()).split(",")[0];
										String crtime = exampleList.get(i).toString().substring(exampleList.get(i).toString().indexOf("CRTIME=")+"CRTIME=".length()).split(",")[0];
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										Date date;
										try {
											date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US).parse(crtime);
										} catch (ParseException e) {
											e.printStackTrace();
											date = new Date();
										}
										map = new IrpDocumentMap();
										map.setMid(TableIdUtil.getNextId(IrpChannel.TABLE_NAME));
										map.setChannelid(channels.get(j).getChannelid());
										map.setDocid(Long.valueOf(docid));
										map.setType(IrpDocumentMap.KNOWLEDGE_SUBJECT);
										map.setCrtime(date);
										irpDocumentMapService.addDocSubByExample(map);
									}
								}
							}
						} catch (SolrServerException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}
	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}
	public TRSSearchService getTrsSearchService() {
		return trsSearchService;
	}
	public void setTrsSearchService(TRSSearchService trsSearchService) {
		this.trsSearchService = trsSearchService;
	}
	public SolrService getSolrService() {
		return solrService;
	}
	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}
	public IrpDocumentMapService getIrpDocumentMapService() {
		return irpDocumentMapService;
	}
	public void setIrpDocumentMapService(IrpDocumentMapService irpDocumentMapService) {
		this.irpDocumentMapService = irpDocumentMapService;
	}
	
}
