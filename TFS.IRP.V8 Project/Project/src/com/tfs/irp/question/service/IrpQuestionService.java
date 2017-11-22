package com.tfs.irp.question.service;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.entity.IrpQuestionExcel;
import com.tfs.irp.sign.entity.IrpSignInfo;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;

public interface IrpQuestionService {
	/**
	 * 提问
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public long  addQuestion(String title, String _jsonFile,String _questiontitle,String conditions) throws Exception;
	public long  addQuestion(String title, String _jsonFile,String _questiontitle,String conditions,IrpUser irpUser) throws Exception;
	
	/**
	 * 显示所有问题
	 * @return
	 * @throws Exception
	 */
	public List<IrpQuestion>  listAllQuestion() throws Exception;
	/**
	 * 删除问题
	 * @return 
	 */
	public int delQuestion(long questionid) throws Exception;
	/**
	 * 查询总共有多少个问题
	 * @return
	 */
	public int findAllQuestions() throws Exception;
	
	/**
	 * 查询在此类别下有多少问题
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	public int findAllQuestionsInCategory(Map<String,Object> map) throws Exception;
	
	/**
	 * 分页显示
	 * @return
	 */
	public List<IrpQuestion> findQuestions(PageUtil pageUtil,String _sOrderBy) throws Exception;
	/**
	 * 分页
	 * @return
	 */
	public List<IrpQuestion> findQuestions(PageUtil pageUtil) throws Exception;
	/**
	 * 分页，排序
	 * @return
	 */
	public List<IrpQuestion> findQuestionsByPage(PageUtil pageUtil,String _sOrderBy) throws Exception;
	
	/**
	 * 分页,排序,分类
	 * @param pageUtil
	 * @param _sOrderBy
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<IrpQuestion> findQuestionsInCategoryByPage(PageUtil pageUtil,String _sOrderBy,Map<String,Object> map) throws Exception;
	public List<IrpQuestion> findQuestionsInCategoryByPage1(PageUtil pageUtil,String _sOrderBy,Map<String,Object> map) throws Exception;
	
	/**
	 * 待解决问题列表
	 * @return
	 */
	public List<IrpQuestion> findQuestionsOfNoResoule(PageUtil pageUtil) throws Exception;
	/**
	 * 未解决问题的总数
	 * @return
	 */
	public int findQuestionsCount() throws Exception;
	/**
	 * 已解决问题总数
	 * @return
	 */
	public int findQuestionsResoule() throws Exception;
	/**
	 * 已解决问题列表
	 * @return
	 */
	public List<IrpQuestion> findQuestionOfResoule(PageUtil pageUtil) throws Exception;
	
	/**
	 * 我提问的所有问题(数量)
	 * @return
	 * @throws Exception
	 */
	public int findQuestionMineCount()  throws Exception;
	/**
	 * 我回答过的问题的(数量)
	 * @return
	 * @throws Exception
	 */
	public int findAnwserQuestionMineCount() throws Exception;
	/**
	 * 我回答过的问题的列表
	 * @param pageUtil
	 * @return
	 * @throws Exception
	 */
	public List<IrpQuestion> findAnwserQuestionMine(PageUtil pageUtil) throws Exception;
    /**
     * 我提问的所有问题列表
     * @param pageUtil
     * @return
     * @throws Exception
     */
	public List<IrpQuestion> findQuestionMine(PageUtil pageUtil) throws Exception;
	/**
	 * 根据主键id求对象
	 * @return
	 */
	public IrpQuestion findQuestionDetail(Long questionid);
	/**
	 * 根据最佳答案求相应的对象
	 * @param _parentid
	 * @return
	 * @throws Exception
	 */
    public IrpQuestion findQuestionByParentid(Long _parentid);
	/**
	 * 回答问题
	 * @return
	 */
	public int addTextAnswer(String _jsonFile,Long questionid,String text,String _contentanswer,Long browercount,Integer _status,Long _replyqid) throws Exception;
	public int addTextAnswerMobile(String _jsonFile,Long questionid,String text,String _contentanswer,Long browercount,Integer _status,Long _replyqid) throws Exception;
	/**
	 * 修改问题
	 * @return
	 */
	public int modifyQuestion(Long questionid, String _modifyTexttitle,String _modifyTextcontent) throws Exception;
	/**
	 *  获得所有答案
	 *  @return
	 */
	public List<IrpQuestion> findAnswers(Long parentid) throws Exception;
	/**
	 * 获得专家答案
	 * @return
	 */
	public List<IrpQuestion> findExpertAnswers(Long parentid) throws Exception;
	/**
	 * 获得前三条专家答案
	 * @return
	 */
	public List<IrpQuestion> findExpertAnswerThree(Long parentid) throws Exception;
	/**
	 * 获得最佳答案
	 * @return
	 */
	public List<IrpQuestion> findBestAnswer(Long parentid) throws Exception;
	/**
	 * 答案总数
	 * @return
	 */
	public int countAnswers(Long parentid) throws Exception;
	/**
	 * 分页显示所有答案
	 * @return
	 */
	public List<IrpQuestion> findAnswersByPage(PageUtil pageUtil,String _sOrderBy,Long parentid) throws Exception;
	/**
	 * 修改问题状态
	 * @return
	 */
	public int modifyStatus(Long answerid,Long questionid,String answer,Long browsecount,Long cruserid) throws Exception;
	/**
	 * 最佳答案 
	 * @return
	 */
	public int modifyBestAnswer(Long questionid,String answer) throws Exception;
	/**
	 * 回答总数
	 * @return
	 */
	public int replyTotal(Long questionid) throws Exception;
	/**
	 * 提问并选择专家
	 * @return
	 */
	public int addQuestionAndExpert(String title,String expert,Long expertId,String _questiontitle, String _jsonFile,String categories) throws Exception;
	public Long addQuestionAndExpert(String title,String expert,Long expertId,String _questiontitle, String _jsonFile,String categories,IrpUser irpUser) throws Exception;
	/**
	 * 手机端专家提问
	 */
	public int  addQuestion(String askcontext,String expert,Long expertId,IrpUser irpUser,String categories);
	/**
	 * 我提问的
	 * @return
	 */
	public List<IrpQuestion> findMeAskQuestion(PageUtil pageUtil,Long personid,String orderBy) throws Exception;
	/**
	 * 我提问的问题总数
	 * @return
	 */
	public int countMeAskQuestion(Long personid) throws Exception;
	/**
	 * 我回答的
	 * @return
	 */
	public List<IrpQuestion> findMeAnswerQuestion(PageUtil pageUtil,Long personid,String orderBy) throws Exception;
	/**
	 * 我回答的问题的总数
	 * @return
	 */
	public int countMeAnswerQuestion(Long personid) throws Exception;
	/**
	 * 浏览总数
	 * @return
	 */
	public int modifyBrowCount(Long questionid,Long browseCount) throws Exception;
	/**
	 * 根据问答ID获得问答的附件集合
	 * @param _questionid
	 * @return
	 */
	public List<IrpAttached> findQuestionAttached(Long _questionid);
	/**
	 * 根据问答ID获得问答的附件集合图片或者附件
	 * @param _questionid
	 * @param _typeid
	 * @return
	 */
	public List<IrpAttached> findQuestionAttached(Long _questionid,Long _typeid);
	/**
	 * 获得问答图片附件
	 * @param _questionid
	 * @return
	 */
	public List<IrpAttached> findQuestionPic(Long _questionid);
	
	/**
	 * 获得最新最热回答
	 * @param _orderby
	 * @return
	 */
	public List<IrpQuestion> findNewestOrHotQuestion(String _orderby,Integer _pageSize,Integer type);
	/**
	 * 问答达人
	 * 
	 */
	public List findIntelligentUser();
	
	/**
	 * 根据状态 创建者   回答者 关键词等条件查询
	 * @param pageUtil
	 * @param map
	 * @return
	 */
	public List<IrpQuestion> findALlQuesAnsbyway(PageUtil pageUtil,Map<String, Object> map);
	public int findALlQuesAnsbywaycount(Map<String, Object> map);
	/**
	 * 最新的几条答案
	 * @return
	 */
	public Set<Long> findNnewstAnswer(int _length);
	/**
	 * 删除问题下的答案 根据id
	 * 1：成功   0：失败
	 * @param _questionid
	 * @return
	 */
	public int deleteAnswerByQuestionId(Long _questionid);
	/**
	 * 修改问题状态
	 * @param _questionid
	 * @param _status
	 * @return
	 */
	public int updateQuestionStatus(Long _questionid,Integer _status);
	/**
	 * 删除该问题下所有的答案
	 * @param _questionid
	 * @return
	 */
	public int deleteQuestionAndAnswers(Long _questionid);
	/**
	 * 张银珠 统计总的提问，关闭，答复
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param _status 状态
	 * @param parentId 问题ID
	 * @return
	 */
	public List<IrpQuestion> highChartsByExprt(Date startTime,Date endTime,Long _status,Long parentId);
	/**
	 * 根据条件查问题或者答案总的数量
	 * @param _titlestatus  1：问题   2：答案
	 * @param _status   1：已解决问题   0：其它
	 * @return
	 */
	public long getQuqestionTotalByCondition(Integer _titlestatus,Integer _status,Date _starttime,Date _endtime);
	/**
	 * 根据时间段统计问答各个时间段显示
	 * @param _starttime
	 * @param _endtime
	 * @param _titlestatus
	 * @param _status
	 * @return
	 */
	public List<IrpQuestion> getQuestionTotalByTime(Date _starttime,Date _endtime,Integer _titlestatus,Integer _status);
	/**
	 * 获得每天的知识数量
	 * @param _time
	 * @param _titlestatus
	 * @param _status
	 * @return
	 */
	public long getQuestionNumOfDate(Date _time,Integer _titlestatus,Integer _status);
	
	/**
	 * 根据id获取符合条件的分类
	 * @param questionId
	 * @return
	 */
	public List<IrpCategory> getCategoryByQuestionId(Long questionId);
	/**
	 * 根据id获取回复评论个数
	 * @param _questionid
	 * @return
	 */
	public int replyCommentNum(long _questionid);
	/**
	 * 根据questionid获取问答评论集合
	 * @param _questionid
	 * @return
	 */
	public List<IrpQuestion> getReplyCList(long _questionid,PageUtil _pageutil);
	/**
	 * 根据回答评论的id更新楼层数
	 * @param _questionid
	 * @return
	 */
	public int updateRIdByQId(long _questionid,long _num);
	/**
	 * 常见问题汇总
	 * 
	 * @return
	 */
	public List<IrpQuestion> getConnQList(PageUtil pageutil);
	public int getConnQListCount();
	public List<IrpQuestion> getConnQList();
	
	/**
	 * 导出信息到zip文件
	 */
	public void exportAllQuestionToZip(List<IrpQuestionExcel> list,String path,String fileName);
	
    /**
     * 
     * addQuestionForApp:手机端没添加专家提问方法
     * @author Administrator 
     * @param title 提问标题
     * @param _jsonFile
     * @param _questiontitle 提问内容
     * @param categories 类别id
     * @param user 通过token查询到的登录用户
     * @return 
     * @since JDK 1.8
     */
    public long addQuestionForApp(String title, String _jsonFile, String _questiontitle, String categories, IrpUser user);

    /**
     * 
     * addQuestionAndExpertForApp:手机端有专家提问方法
     * @author Administrator 
     * @param title 问题标题
     * @param expert 专家名称
     * @param expertId 专家id
     * @param _questiontitle 问题内容
     * @param _jsonFile
     * @param categories 类别id
     * @param user 通过token查询到的用户
     * @return
     * @throws Exception 
     * @since JDK 1.8
     */
    public int addQuestionAndExpertForApp(String title, String expert, Long expertId, String _questiontitle,
            String _jsonFile, String categories, IrpUser user) throws Exception;
	/**
	 * 所有已解答的专家答案。
	 * @return
	 */
	public int findAllExpertAnswersCount();
	/**
	 * 根据用户id查找用户问答
	 * @return
	 * @author   npz
	 * @date 2017年10月27日
	 */
	public List<IrpQuestion> findQuestionById(Long userid,String _sOrderBy,Map<String,Object> map,PageUtil pageutil);
	public int findQuestioncountById(Map<String,Object> map,Long userid);
	
}
