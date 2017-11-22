package com.tfs.irp.vote.service;

import java.util.List;
import java.util.Map;

import com.tfs.irp.util.PageUtil;
import com.tfs.irp.vote.entity.IrpVote;

public interface IrpVoteService {

	/**
	 * 分页显示所有的投票
	 * 
	 * @param pageUtil
	 * @return
	 */
	public List<IrpVote> findAllVote(PageUtil pageUtil, String _oOrderby);

	/**
	 * 分页显示所有的投票数量
	 * 
	 * @param pageUtil
	 * @return
	 */
	public int findAllVotecount();

	/**
	 * 添加投票 //标题 分组标题 多个选项
	 * 
	 * @param irptitle
	 *            投票标题
	 * @param irpvotetitle
	 *            分组标题
	 * @param options
	 *            分组选项
	 */
	public Long addVoteandOption(IrpVote irptitle, IrpVote irpvotetitle,
			String options,String urloption,Long wordorurl);

	/**
	 * 添加投票 // 分组标题 多个选项
	 * 
	 * @param irptitle
	 *            投票标题
	 * @param irpvotetitle
	 *            分组标题
	 * @param options
	 *            分组选项
	 */
	public void addTitleandOption(IrpVote irpvotetitle, String options,String urloptions,Long vid);

	/**
	 * 添加投票 //标题 分组标题 多个选项
	 * 
	 * @param irptitle
	 *            投票标题
	 * @param irpvotetitle
	 *            分组标题
	 * @param options
	 *            分组选项
	 * @param attlist
	 *            图片
	 */
	public Long addPicVoteandOption(IrpVote irptitle, IrpVote irpvotetitle,
			String options,String urloption, String attlist);

	/**
	 * 根据id查询投票选项
	 * 
	 * @param voteid
	 * @return
	 */
	public IrpVote findbyvoteid(Long voteid);

	/**
	 * 查询问题和选项
	 * 
	 * @param map
	 * @return
	 */
	public List<?> findquestionandoption(Map<String, Object> map);
	
	/**
	 * 根据id找父标题
	 * @param pid
	 * @return
	 */
	
	public List<IrpVote> findVotetitleBypid(Long pid);
	
	/**
	 * 投票
	 * @param voteid
	 * @param optionids
	 */
	public void addVoterecords(Long voteid,String optionids);
	/**
	 * 判断用户是否有投票权限
	 * @return
	 */
	public boolean isuservote(Long voteid);
	/**
	 * 我发起的
	 * @return
	 */
	public int findAllMyvotecount();
	/**
	 * 我发起的
	 * @return
	 */
	public List<IrpVote> findAllMyvote(PageUtil pageUtil, String _oOrderby);
	/**
	 * 我参与的
	 * @return
	 */
	public int findPartmevotecount(Map<String, Object> map);
	/**
	 * 我参与的
	 * @return
	 */
	public List<IrpVote> findPartmevote(Map<String, Object> map,PageUtil pageUtil);
	/**
	 * 修改发布
	 * @param voteid
	 * @param ispub
	 */
	public void updatevotePublish(Long voteid,int ispub);
	
	/**
	 * 修改
	 * @param iv
	 */
	public void votetitleUpdate(IrpVote iv);
	/**
	 * 修改分组和选项
	 * @param iv
	 * @param irpVoteOptions
	 */
	public void voteandoptionsUpdate(IrpVote iv,String json);
	/**
	 * 删除选项
	 * @param optionsid
	 */
	public void voteoptionDel(Long optionsid);
	/**
	 * 只添加选项
	 * @param optiostr
	 */
	public void optionAdd(String optiostr,String urloption,String jsonoptionimg,Long pid);
	
	
	/**
	 * 删除分组
	 * @param voteid
	 */
	public void voteTitleDel(Long voteid);
	/**
	 * 删除投票
	 * @param voteid
	 */
	public void voteDel(Long voteid);
	
	/**
	 * 添加图片投票分组
	 * @param pid
	 * @param irpvotetitle
	 * @param options
	 * @param urloption
	 * @param imgs
	 */
	public void addPicVotesecond(Long pid,IrpVote irpvotetitle,String options,String urloption,String imgs);
    /**
     * 根据id查询
     * @param voteid
     * @return
     */
    public IrpVote findVotebyid(Long voteid);
    
    /**
     * 热门投票
     * @return
     */
    public List<IrpVote> findVotehot();
    
    /**
	 * 判断用户投票id
	 * @return
	 */
	public Long isuservoteid(Long voteid);
}
