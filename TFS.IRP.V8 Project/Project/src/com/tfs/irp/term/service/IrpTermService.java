package com.tfs.irp.term.service;

import java.util.List;

import com.tfs.irp.term.entity.IrpTerm;
import com.tfs.irp.util.PageUtil;

public interface IrpTermService {
    /**
     * 所有符合要求的集合
     * @param _pageobj
     * @param _obystr
     * @param _status
     * @param _isdel
     * @return
     */
    public List<IrpTerm> findAllWTerm(PageUtil _pageobj, String _obystr, Integer _status, Integer _isdel,
            String _sword, boolean _qstatus, Long _classid);

    /**
     * 所有符合要求的集合数量
     * @param _status
     * @param _isdel
     * @return
     */
    public int findAllWTermNum(Integer _status, Integer _isdel, String _sword, boolean _qstatus, Long _classid);

    /**
     * 增加词条
     * @param _status
     * @param _isdel
     * @param _words
     * @return
     */
    public long addTerm(Integer _status, Integer _isdel, String _words, String _soversion, boolean _mgr, Long _tcate,
            String _latter, String attachedId, Long termId);

    /**
     * 增加释义
     * @param _status
     * @param _isdel
     * @param _content
     * @param _termqid 增加指向词条 
     * @param _vision  增加版本
     * @param termcontent  词条文本
     * @return
     */
    public int addTermExplain(Integer _status, Integer _isdel, String _content, Long _termqid, String _vision,
            String _editcause, boolean _mgr, String termcontent);

    /**
     * 根据词条名称获得词条对戏那个
     * @param _termname
     * @return
     */
    public IrpTerm findTIdByName(String _termname);

    /**
     * 更新版本
     * @param _termid
     * @param _version
     * @return
     */
    public int updateVersion(long _termid, String _version);

    /**
     * 根据id查看正常状态下的词条对象
     * @param _termid
     * @param _status
     * @param _isdel
     * @return
     */
    public IrpTerm irpTermById(long _termid, Integer _status, Integer _isdel, boolean _boolclob, boolean _ismgr);

    /**
     * 根据词条id找到相对应释义的ID结果集
     * @param _termid
     * @param _status
     * @param _isdel
     * @return
     */
    public List<IrpTerm> irpTexampleTyId(long _termid, Integer _status, Integer _isdel, PageUtil _pageutil,
            String _oderstr, boolean _ismgr);

    /**
     * 根据词条id找到相对应释义的ID结果集数量
     * @param _termid
     * @param _status
     * @param _isdel
     * @return
     */
    public int irpTexampleTyIdNum(long _termid, Integer _status, Integer _isdel, boolean _ismgr);

    /**
     * 根据词条ID找到相对应的最高版本内容
     * @param _termid
     * @param _version
     * @return
     */
    public IrpTerm irpTermByVerAndId(long _termid, String _version, boolean _iszversion, Integer _status, Integer _isdel);

    /**
     * 根据id更新浏览量
     * @param _termid
     * @param _bcount
     * @return
     */
    public int updateBCount(long _termid, int _bcount);

    /**
     * 根据id查询对象
     * @param _termid
     * @return
     */
    public IrpTerm irpTermById(long _termid);

    /**
     * 修改词条状态
     * @param _termid
     * @param _status
     * @return
     */
    public int updateTermStatus(long _termid, int _status);

    /**
     * 获得一个词条最新的合法的版本对象
     * @param _termqid
     * @param _status
     * @param _isdel
     * @param _orderstr
     * @return
     */
    public IrpTerm getIrpTermFVVersion(long _termqid, int _status, int _isdel, String _orderstr);

    /**
     * 根据ID修改分类
     * @param _termid
     * @param _classify
     * @return
     */
    public int updateClassifyById(long _termid, long _classify);

    /**
     * 根据词条名称查找相应的对象
     * @param _tname
     * @param _status
     * @param _isdel
     * @return
     */
    public IrpTerm getIrpTermByTName(String _tname, Integer _status, Integer _isdel);

    /**
     * 查询个人最新词条
     * @param _tname
     * @param _status
     * @param _isdel
     * @return
     */
    public IrpTerm findIrpTermByCrId(String _tname, Integer _status, Integer _isdel, Long _cruserid);

    /**
     * 查询个人词条列表
     * @param _status
     * @param _isdel
     * @param _cruserid
     * @param _odrstr
     * @param _pageutil
     * @param _stermname
     * @return
     */
    public List<IrpTerm> getIrpTermPersonal(Integer _status, Integer _isdel, Long _cruserid, String _odrstr,
            PageUtil _pageutil, String _stermname);

    /**
     * 查询个人词条列表数量
     * @param _status
     * @param _isdel
     * @param _cruserid
     * @param _stermname
     * @return
     */
    public int getIrpTermPersonalNum(Integer _status, Integer _isdel, Long _cruserid, String _stermname);

    /**
     * 找到个人创建的版本
     * @param _status
     * @param _isdel
     * @param _cruserid
     * @param _termid
     * @return
     */
    public IrpTerm findIrpTermByPersonId(Integer _status, Integer _isdel, Long _cruserid, Long _termid);

    /**
     * 分类词条
     * @param _cid
     * @return
     */
    public int findCountByTCate(Long _cid, Integer _status);

    /**
     * 更新词条的isdel状态
     * @param _tid
     * @param _isdel
     * @return
     */
    public int updateTermisdelById(Long _tid, Integer _isdel);

    /**
     * 找到所有合法状态下的词条
     * @param _status
     * @param _isdel
     * @return
     */
    public List<String> selectTNameTask(Integer _status, Integer _isdel);

    /**
     * 分类下不同的词条
     * @param _status
     * @param _isdel
     * @param _cate
     * @param _fnum
     * @return
     */
    public List<IrpTerm> findIrpTermListByCate(Integer _status, Integer _isdel, Long _cate, Integer _fnum,
            String _orderstr);

    /**
     * 查询多义词
     * @param _stuats
     * @param _isdel
     * @param pageutil
     * @param _orderbystr
     * @return
     */
    public List<IrpTerm> findTListByPolyseme(Integer _stuats, Integer _isdel, PageUtil pageutil, String _orderbystr,
            String _termanem);

    /**
     * 查询多义词数量
     * @param _stuats
     * @param _isdel
     * @return
     */
    public int findTListByPolysemeNum(Integer _stuats, Integer _isdel, String _termanem);

    /** 
     * countCreateTermUserNum:获得创建词条的人数. <br/> 
     * 
     * @author Administrator 
     * @return 
     * @since JDK 1.8 
     */
    public int countCreateTermUserNum();

    /** 
     * countTermList:获得所有带有词条题目、审核通过并且状态正常的词条列表数量. <br/> 
     * 
     * @author Administrator 
     * @return 列表数量
     * @since JDK 1.8 
     */
    public int countTermList();

    /** 
     * findTermList:根据排序条件获得所有带有词条题目、审核通过并且状态正常的词条列表. <br/> 
     * 
     * @author Administrator 
     * @param pageUtil 分页实体
     * @param condition 排序条件
     * @return 词条列表
     * @since JDK 1.8 
     */
    public List<IrpTerm> findTermListByCondition(PageUtil pageUtil, String condition);

    /** 
     * findTermByTermqid:根据termqid查找相对应的词条. <br/> 
     * 
     * @author Administrator 
     * @param termqid 对应词条题目的id
     * @return 词条实体
     * @since JDK 1.8 
     */
    public IrpTerm findTermByTermqid(Long termqid);

    /** 
     * getCreateTermUserList:获得所有创建词条的用户的Id列表. <br/> 
     * 
     * @author Administrator 
     * @return 用户的Id列表
     * @since JDK 1.8 
     */  
    public List<Integer> getCreateTermUserIdList();

    /** 
     * updateTermAttachedId:更新词条附件Id的值. <br/> 
     * 
     * @author Administrator 
     * @param irpterm 词条实体对象
     * @return 改动条目行数
     * @since JDK 1.8 
     */  
    public Integer updateTermAttachedId(IrpTerm irpterm);

    /** 
     * findIrpTermContentByPersonId:根据词条题目id和用户id获得个人草稿里的词条内容. <br/> 
     * 
     * @author Administrator 
     * @param termStatus 词条状态
     * @param termIsDel 词条是否锁定
     * @param termId 词条Id
     * @param loginUserId 用户Id
     * @return 
     * @since JDK 1.8 
     */  
    public IrpTerm findIrpTermContentByPersonId(int termStatus, int termIsDel, Long termId,
            long loginUserId);

}
