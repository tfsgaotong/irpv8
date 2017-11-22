package com.tfs.irp.term.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.term.entity.IrpTerm;
import com.tfs.irp.term.service.IrpTermService;
import com.tfs.irp.termeditlog.entity.IrpTermeditLog;
import com.tfs.irp.termeditlog.service.IrpTermEditLogService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;

@SuppressWarnings("serial")
public class IrpTermAction extends ActionSupport {
    private IrpAttachedService irpAttachedService;// 附件表service
    private IrpTermService irpTermService;
    private IrpDocumentService irpDocumentService;// 文档service
    private List<IrpTerm> termlist;
    private IrpTermEditLogService irpTermEditLogService;
    private String attachedFile;
    private IrpAttachedTypeService irpAttachedTypeService;
    private List<Map<String, Object>> termWithAttachedList;

    public List<Map<String, Object>> getTermWithAttachedList() {
        return termWithAttachedList;
    }

    public void setTermWithAttachedList(List<Map<String, Object>> termWithAttachedList) {
        this.termWithAttachedList = termWithAttachedList;
    }

    public String getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
    }

    public IrpAttachedTypeService getIrpAttachedTypeService() {
        return irpAttachedTypeService;
    }

    public void setIrpAttachedTypeService(IrpAttachedTypeService irpAttachedTypeService) {
        this.irpAttachedTypeService = irpAttachedTypeService;
    }

    public IrpTermEditLogService getIrpTermEditLogService() {
        return irpTermEditLogService;
    }

    public void setIrpTermEditLogService(IrpTermEditLogService irpTermEditLogService) {
        this.irpTermEditLogService = irpTermEditLogService;
    }

    private int pagenum = 1;

    private String pagehtml;

    private String sword;

    private String termname;

    private String thtmlcontent;

    private int ternamenum;

    private long termid;

    private IrpTerm irptermofword;

    private IrpTerm irptermofexpl;

    private long hostorytermid;

    private long historyid;

    private IrpTerm termhobj;

    private IrpTerm qhobj;

    private int pagenumver = 1;

    private String pagehtmlver;

    private long edittermid;

    private String termwordname;

    private String termwordcontent;

    private int auditstatus;

    private String auditnoword;

    private int auditnopsize;

    private int audityespsize;

    private long termzqid;

    private long passid;

    private long illegid;

    private String upcausevalue;

    private int updatevalsize;

    private String firsttermid;

    private String secondtermid;

    private Long termcateval;

    private Long termwordclassifyid;

    private Long editclassifyid;

    private Long qclassifyid;

    private Long lockid;

    private Integer lockstatus;

    private String termcontent;

    private List<IrpAttached> attachedList;

    public String getTermcontent() {
        return termcontent;
    }

    public void setTermcontent(String termcontent) {
        this.termcontent = termcontent;
    }

    public List<IrpAttached> getAttachedList() {
        return attachedList;
    }

    public void setAttachedList(List<IrpAttached> attachedList) {
        this.attachedList = attachedList;
    }

    public Integer getLockstatus() {
        return lockstatus;
    }

    public void setLockstatus(Integer lockstatus) {
        this.lockstatus = lockstatus;
    }

    public Long getLockid() {
        return lockid;
    }

    public void setLockid(Long lockid) {
        this.lockid = lockid;
    }

    public Long getQclassifyid() {
        return qclassifyid;
    }

    public void setQclassifyid(Long qclassifyid) {
        this.qclassifyid = qclassifyid;
    }

    public IrpAttachedService getIrpAttachedService() {
        return irpAttachedService;
    }

    public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
        this.irpAttachedService = irpAttachedService;
    }

    public Long getEditclassifyid() {
        return editclassifyid;
    }

    public void setEditclassifyid(Long editclassifyid) {
        this.editclassifyid = editclassifyid;
    }

    public Long getTermwordclassifyid() {
        return termwordclassifyid;
    }

    public void setTermwordclassifyid(Long termwordclassifyid) {
        this.termwordclassifyid = termwordclassifyid;
    }

    private IrpCategoryService irpCategoryService;

    public IrpCategoryService getIrpCategoryService() {
        return irpCategoryService;
    }

    public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
        this.irpCategoryService = irpCategoryService;
    }

    public Long getTermcateval() {
        return termcateval;
    }

    public void setTermcateval(Long termcateval) {
        this.termcateval = termcateval;
    }

    public String getFirsttermid() {
        return firsttermid;
    }

    public void setFirsttermid(String firsttermid) {
        this.firsttermid = firsttermid;
    }

    public String getSecondtermid() {
        return secondtermid;
    }

    public void setSecondtermid(String secondtermid) {
        this.secondtermid = secondtermid;
    }

    private IrpTerm cponeterm;

    private IrpTerm cptwoterm;

    public IrpTerm getCponeterm() {
        return cponeterm;
    }

    public void setCponeterm(IrpTerm cponeterm) {
        this.cponeterm = cponeterm;
    }

    public IrpTerm getCptwoterm() {
        return cptwoterm;
    }

    public void setCptwoterm(IrpTerm cptwoterm) {
        this.cptwoterm = cptwoterm;
    }

    public int getUpdatevalsize() {
        return updatevalsize;
    }

    public void setUpdatevalsize(int updatevalsize) {
        this.updatevalsize = updatevalsize;
    }

    public String getUpcausevalue() {
        return upcausevalue;
    }

    public void setUpcausevalue(String upcausevalue) {
        this.upcausevalue = upcausevalue;
    }

    public long getIllegid() {
        return illegid;
    }

    public void setIllegid(long illegid) {
        this.illegid = illegid;
    }

    public long getPassid() {
        return passid;
    }

    public void setPassid(long passid) {
        this.passid = passid;
    }

    public long getTermzqid() {
        return termzqid;
    }

    public void setTermzqid(long termzqid) {
        this.termzqid = termzqid;
    }

    public String getAuditnoword() {
        return auditnoword;
    }

    public void setAuditnoword(String auditnoword) {
        this.auditnoword = auditnoword;
    }

    public int getAuditnopsize() {
        return auditnopsize;
    }

    public void setAuditnopsize(int auditnopsize) {
        this.auditnopsize = auditnopsize;
    }

    public int getAudityespsize() {
        return audityespsize;
    }

    public void setAudityespsize(int audityespsize) {
        this.audityespsize = audityespsize;
    }

    public int getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(int auditstatus) {
        this.auditstatus = auditstatus;
    }

    public String getTermwordname() {
        return termwordname;
    }

    public void setTermwordname(String termwordname) {
        this.termwordname = termwordname;
    }

    public String getTermwordcontent() {
        return termwordcontent;
    }

    public void setTermwordcontent(String termwordcontent) {
        this.termwordcontent = termwordcontent;
    }

    public long getEdittermid() {
        return edittermid;
    }

    public void setEdittermid(long edittermid) {
        this.edittermid = edittermid;
    }

    public int getPagenumver() {
        return pagenumver;
    }

    public void setPagenumver(int pagenumver) {
        this.pagenumver = pagenumver;
    }

    public String getPagehtmlver() {
        return pagehtmlver;
    }

    public void setPagehtmlver(String pagehtmlver) {
        this.pagehtmlver = pagehtmlver;
    }

    public IrpTerm getTermhobj() {
        return termhobj;
    }

    public void setTermhobj(IrpTerm termhobj) {
        this.termhobj = termhobj;
    }

    public IrpTerm getQhobj() {
        return qhobj;
    }

    public void setQhobj(IrpTerm qhobj) {
        this.qhobj = qhobj;
    }

    public long getHistoryid() {
        return historyid;
    }

    public void setHistoryid(long historyid) {
        this.historyid = historyid;
    }

    private List<IrpTerm> hisirptermlist;

    public List<IrpTerm> getHisirptermlist() {
        return hisirptermlist;
    }

    public void setHisirptermlist(List<IrpTerm> hisirptermlist) {
        this.hisirptermlist = hisirptermlist;
    }

    public long getHostorytermid() {
        return hostorytermid;
    }

    public void setHostorytermid(long hostorytermid) {
        this.hostorytermid = hostorytermid;
    }

    private IrpUserService irpUserService;

    public IrpUserService getIrpUserService() {
        return irpUserService;
    }

    public void setIrpUserService(IrpUserService irpUserService) {
        this.irpUserService = irpUserService;
    }

    public IrpDocumentService getIrpDocumentService() {
        return irpDocumentService;
    }

    public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
        this.irpDocumentService = irpDocumentService;
    }

    public IrpTerm getIrptermofword() {
        return irptermofword;
    }

    public void setIrptermofword(IrpTerm irptermofword) {
        this.irptermofword = irptermofword;
    }

    public IrpTerm getIrptermofexpl() {
        return irptermofexpl;
    }

    public void setIrptermofexpl(IrpTerm irptermofexpl) {
        this.irptermofexpl = irptermofexpl;
    }

    public long getTermid() {
        return termid;
    }

    public void setTermid(long termid) {
        this.termid = termid;
    }

    public int getTernamenum() {
        return ternamenum;
    }

    public void setTernamenum(int ternamenum) {
        this.ternamenum = ternamenum;
    }

    public String getTermname() {
        return termname;
    }

    public void setTermname(String termname) {
        this.termname = termname;
    }

    public String getThtmlcontent() {
        return thtmlcontent;
    }

    public void setThtmlcontent(String thtmlcontent) {
        this.thtmlcontent = thtmlcontent;
    }

    public String getSword() {
        return sword;
    }

    public void setSword(String sword) {
        this.sword = sword;
    }

    public String getPagehtml() {
        return pagehtml;
    }

    public void setPagehtml(String pagehtml) {
        this.pagehtml = pagehtml;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public List<IrpTerm> getTermlist() {
        return termlist;
    }

    public void setTermlist(List<IrpTerm> termlist) {
        this.termlist = termlist;
    }

    public IrpTermService getIrpTermService() {
        return irpTermService;
    }

    public void setIrpTermService(IrpTermService irpTermService) {
        this.irpTermService = irpTermService;
    }

    /**
     * 链接到词条页面
     * @return
     */
    public String findTerm() {
        //词条名称字数限制

        return SUCCESS;
    }

    /**
     * 链接到创建词条页面
     * @return
     */
    public String linkCTWords() {

        ternamenum = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMNAMENUM);

        return SUCCESS;
    }

    /**
     * 查看词条列表
     * @return
     */
    public String getTermList() {
        int num = this.irpTermService.findAllWTermNum(IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL, sword, true,
                qclassifyid);
        int pagesize = 10;
        pagesize = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMSIZE);
        PageUtil pageutil = new PageUtil(pagenum, pagesize, num);

        String _obystr = "crtime desc";

        termlist = this.irpTermService.findAllWTerm(pageutil, _obystr, IrpTerm.TERMSTATUSWORDS,
                IrpTerm.TERMISDELNORMAL, sword, true, qclassifyid);
        termWithAttachedList = new ArrayList<Map<String, Object>>();
        for (IrpTerm irpTerm : termlist) {
            Map<String, Object> tempMap = new HashMap<String, Object>();
            if ("".equals(irpTerm.getAttachedid()) || irpTerm.getAttachedid() == null) {
                tempMap.put("imgSrc", "");
            } else {
                IrpAttached irpAttached = irpAttachedService.getIrpATttachedByDocIDFIle(irpTerm.getTermid());
                if (irpAttached == null) {
                    tempMap.put("imgSrc", "");
                } else {
                    tempMap.put("imgSrc", irpAttached.getAttfile());
                }
            }
            tempMap.put("termname", irpTerm.getTermname());
            tempMap.put("termid", irpTerm.getTermid());
            tempMap.put("crtime", irpTerm.getCrtime());
            tempMap.put("version", irpTerm.getTermversion());

            termWithAttachedList.add(tempMap);
            tempMap = new HashMap<String, Object>();
        }

        this.pagehtml = pageutil.getClientPageHtml("pageterm(#PageNum#)");

        return SUCCESS;
    }

    /**
     * 非法词条
     * @return
     */
    public String illTermList() {
        int pagesize = 10;
        pagesize = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMSIZE);
        String _obystr = "crtime desc";
        int ncount = this.irpTermService.findAllWTermNum(IrpTerm.TERMSTATUSILLEGALITY, IrpTerm.TERMISDELNORMAL,
                auditnoword, false, null);

        PageUtil pageutil = new PageUtil(auditnopsize, pagesize, ncount);
        termlist = this.irpTermService.findAllWTerm(pageutil, _obystr, IrpTerm.TERMSTATUSILLEGALITY,
                IrpTerm.TERMISDELNORMAL, auditnoword, false, null);
        this.pagehtml = pageutil.getClientPageHtml("pagetermnoeaudit(#PageNum#)");

        return SUCCESS;
    }

    /**
    * 将中文转换为拼音
    * @param name
    * @return
    */
    @SuppressWarnings("deprecation")
    private String getEnameFirst(String name) {
        String str = "";
        HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
        pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); //设置样式
        pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        pyFormat.getToneType();
        try {
            str = PinyinHelper.toHanyuPinyinString(name.toLowerCase(), pyFormat, "");
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        char csm = str.charAt(0);
        int ism = csm;
        if (ism >= 97 && ism <= 122) {
            return csm + "";
        } else {
            return "other";
        }
    }

    /**
     * 增加词条或释义
     * @return
     */
    public void addTerm() {
        IrpTerm irpterm = null;
        int msg = 0;

        if (termname != null) {
            irpterm = this.irpTermService.findTIdByName(termname);

            /**
             * 如果词条为null，则添加词条。
             * 否则，更新词条。
             */
            if (irpterm == null) {

                long tableid = TableIdUtil.getNextId(IrpTerm.TABLENAME);

                // 词条附件id字符串
                String attachedIdStr = "";

                // 传入的附件判空
                if (attachedFile != null && !"[]".equals(attachedFile)) {
                    attachedIdStr = getAttachedId(attachedFile, tableid);
                }

                //增加新词条
                long irptermid = 0;
                if (termcateval != null) {
                    String latters = "";
                    String sm = getEnameFirst(termname);
                    if (sm.length() < 2) {
                        String smfirest = sm.substring(0, 1).toUpperCase();
                        latters = smfirest;
                    }
                    irptermid = this.irpTermService.addTerm(IrpTerm.TERMSTATUSWORDSNO, IrpTerm.TERMISDELNORMAL,
                            termname, IrpTerm.STARTDEFAULTVISION, LoginUtil.getLoginUser().isTermswordManager(),
                            termcateval, latters, attachedIdStr, tableid);
                }
                if (thtmlcontent != null && irptermid > 0) {
                    //增加词条新释义
                    this.irpTermService.addTermExplain(IrpTerm.TERMSTATUSWORDSNO, IrpTerm.TERMISDELNORMAL,
                            thtmlcontent, irptermid, IrpTerm.STARTDEFAULTVISION, SysConfigUtil
                                    .getSysConfigValue(IrpTerm.TERMUPDATECAUSE), LoginUtil.getLoginUser()
                                    .isTermswordManager(), termcontent);
                }

            } else {

                /*
                 * 如果传入的附件集合为空，则删除附件表里所有关联的附件。
                 * 否则，先把传入的附件集合添加进附件表，然后再删除附件表里之前的数据。
                 */
                if (attachedFile != null && !"[]".equals(attachedFile)) {
                    String attachedId = getAttachedId(attachedFile, irpterm.getTermid());

                    // 构建参数List
                    String[] split = attachedId.split(",");
                    List<Long> idList = new ArrayList<Long>();
                    for (int i = 0; i < split.length; i++) {
                        if (!"null".equals(split[i])) {
                            idList.add(Long.parseLong(split[i]));
                        }
                    }
                    irpAttachedService.deleteFileNotInList(idList, irpterm.getTermid(), IrpAttached.TERM_ATTACHED);
                    irpterm.setAttachedid(attachedId);
                } else {
                    irpAttachedService.deleteFileByExpt(irpterm.getTermid(), IrpAttached.TERM_ATTACHED);
                    irpterm.setAttachedid("");

                }

                irpTermService.updateTermAttachedId(irpterm);

                if (thtmlcontent != null) {
                    // 增加新纪录 更新版本
                    String version = irpterm.getTermversion();

                    Float verstr = Float.parseFloat(version) + Float.parseFloat(IrpTerm.STARTDEFAULTVISION);

                    this.irpTermService.updateVersion(irpterm.getTermid(), "" + verstr);

                    this.irpTermService.addTermExplain(IrpTerm.TERMSTATUSWORDSNO, IrpTerm.TERMISDELNORMAL,
                            thtmlcontent, irpterm.getTermid(), "" + verstr, upcausevalue, LoginUtil.getLoginUser()
                                    .isTermswordManager(), termcontent);

                    IrpTermeditLog irpTermeditLog = new IrpTermeditLog();

                    // 设置主键
                    Long irpTermEditLogId = TableIdUtil.getNextId(IrpTermeditLog.TABLE_NAME);
                    irpTermeditLog.setTermeditlogid(irpTermEditLogId);

                    // 设置词条id
                    irpTermeditLog.setTermid(irpterm.getTermid());

                    // 设置用户id
                    irpTermeditLog.setTermedituserid(LoginUtil.getLoginUserId());

                    // 设置日期
                    irpTermeditLog.setTermeditdate(new Date());

                    this.irpTermEditLogService.addTermEditLog(irpTermeditLog);
                }
            }
            msg = 1;
        }
        ActionUtil.writer(msg + "");
    }

    /**
     * 
     * linkVersion:用户是普通用户，跳转到词条详情页. <br/> 
     * 
     * @author Administrator 
     * @return 
     * @since JDK 1.8
     */
    public String linkVersion() {

        irptermofword = this.irpTermService.irpTermById(termid, IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL, true,
                LoginUtil.getLoginUser().isTermswordManager());
        if (irptermofword != null) {
            irptermofexpl = this.irpTermService.getIrpTermFVVersion(irptermofword.getTermid(), IrpTerm.TERMSTATUSWORDS,
                    IrpTerm.TERMISDELNORMAL, "crtime desc");
        }
        attachedList = irpAttachedService.getAttachedByAttDocId(irptermofword.getTermid(), IrpAttached.TERM_ATTACHED);
        return SUCCESS;

    }

    /**
     * 
     * linkVersionMgr:用户是管理员，跳转到词条详情页. <br/> 
     * 
     * @author Administrator 
     * @return 
     * @since JDK 1.8
     */
    public String linkVersionMgr() {

        irptermofexpl = this.irpTermService.irpTermById(termid, IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL, true,
                LoginUtil.getLoginUser().isTermswordManager());
        if (irptermofexpl != null) {
            irptermofword = this.irpTermService.irpTermById(irptermofexpl.getTermqid());
        }
        attachedList = irpAttachedService.getAttachedByAttDocId(irptermofword.getTermid(), IrpAttached.TERM_ATTACHED);
        return SUCCESS;
    }

    /**
     * 连接到个人版本
     * @return
     */
    public String linkVersionPersonal() {
        // 获得词条内容
        irptermofexpl = this.irpTermService.findIrpTermByPersonId(IrpTerm.TERMSTATUSPERSONAL, IrpTerm.TERMISDELNORMAL,
                LoginUtil.getLoginUserId(), termid);

        // 获得词条题目
        if (irptermofexpl != null) {
            irptermofword = this.irpTermService.irpTermById(irptermofexpl.getTermqid());
        }

        // 根据词条题目里的附件id查询对应附件
        attachedList = irpAttachedService.getAttachedByAttDocId(irptermofword.getTermid(), IrpAttached.TERM_ATTACHED);
        return SUCCESS;
    }

    /**
    * 根据id获得用户在页面显示的名字
    * @param _userid
    * @return
    */
    public String getShowPageViewNameByUserId(Long _userid) {
        return this.irpUserService.findShowNameByUserid(_userid);
    }

    /**
     * 查看历史版本
     * @return
     */
    public String linkHistoryCompare() {

        return SUCCESS;
    }

    /**
     * 历史版本数据
     * @return
     */
    public String getHData() {

        int pcount = this.irpTermService.irpTexampleTyIdNum(this.hostorytermid, IrpTerm.TERMSTATUSWORDS,
                IrpTerm.TERMISDELNORMAL, LoginUtil.getLoginUser().isTermswordManager());

        PageUtil pageutil = new PageUtil(pagenumver, SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMVERSIONSIZE),
                pcount);

        hisirptermlist = this.irpTermService.irpTexampleTyId(this.hostorytermid, IrpTerm.TERMSTATUSWORDS,
                IrpTerm.TERMISDELNORMAL, pageutil, "crtime desc", LoginUtil.getLoginUser().isTermswordManager());

        this.pagehtmlver = pageutil.getClientPageHtml("pagetermver(#PageNum#)");

        return SUCCESS;
    }

    /**
     * 查看具体历史版本
     * @return
     */
    public String linkHVContent() {

        termhobj = this.irpTermService.irpTermById(historyid, IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL, true,
                LoginUtil.getLoginUser().isTermswordManager());
        if (termhobj != null) {
            qhobj = this.irpTermService.irpTermById(termhobj.getTermqid(), IrpTerm.TERMSTATUSWORDS,
                    IrpTerm.TERMISDELNORMAL, false, true);
        }

        return SUCCESS;
    }

    /**
     * 编辑词条
     * @return
     */
    public String editTerm() {
        ternamenum = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMNAMENUM);
        updatevalsize = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMCAUSESIZE);
        IrpTerm irpterm = this.irpTermService.irpTermById(edittermid, IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,
                true, LoginUtil.getLoginUser().isTermswordManager());
        if (irpterm != null) {
            IrpTerm irptermobj = this.irpTermService.irpTermById(irpterm.getTermqid(), IrpTerm.TERMSTATUSWORDS,
                    IrpTerm.TERMISDELNORMAL, false, LoginUtil.getLoginUser().isTermswordManager());
            termwordcontent = irpterm.getTermexplain();
            termwordclassifyid = irptermobj.getQuoteclassify();
            termwordname = irptermobj.getTermname();

        }
        return SUCCESS;
    }

    /**
     * 编辑词条 管理员
     * @return
     */
    public String editTermMgr() {
        updatevalsize = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMCAUSESIZE);
        ternamenum = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMNAMENUM);
        IrpTerm irpterm = this.irpTermService.irpTermById(edittermid, IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,
                true, LoginUtil.getLoginUser().isTermswordManager());
        if (irpterm != null) {
            termwordcontent = irpterm.getTermexplain();

            IrpTerm irptermobj = this.irpTermService.irpTermById(irpterm.getTermqid(), IrpTerm.TERMSTATUSWORDS,
                    IrpTerm.TERMISDELNORMAL, false, LoginUtil.getLoginUser().isTermswordManager());
            termwordname = irptermobj.getTermname();
            termwordclassifyid = irptermobj.getQuoteclassify();
        }
        return SUCCESS;
    }

    /**
     * 更新浏览量
     * @return
     */
    public void updateTBCounts() {

        IrpTerm irpterm = this.irpTermService.irpTermById(termid, IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,
                false, LoginUtil.getLoginUser().isTermswordManager());
        int bcount = irpterm.getTermbcount() + 1;
        this.irpTermService.updateBCount(termid, bcount);

    }

    /**
     * 根据id查询某个对应的对象
     * @param _termid
     * @return
     */
    public IrpTerm getIrpTermById(long _termid) {
        IrpTerm irpterm = null;
        if (_termid > 0) {
            irpterm = this.irpTermService.irpTermById(_termid);
        }
        return irpterm;
    }

    /**
     * 返回未审核的列表
     * @return
     */
    public String termNoAuditList() {
        int pagesize = 10;
        pagesize = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMSIZE);
        if (auditstatus == 1) {
            //未审核
            String _obystr = "crtime desc";
            int ncount = this.irpTermService.findAllWTermNum(IrpTerm.TERMSTATUSWORDSNO, IrpTerm.TERMISDELNORMAL,
                    auditnoword, false, null);
            PageUtil pageutil = new PageUtil(auditnopsize, pagesize, ncount);
            termlist = this.irpTermService.findAllWTerm(pageutil, _obystr, IrpTerm.TERMSTATUSWORDSNO,
                    IrpTerm.TERMISDELNORMAL, auditnoword, false, null);
            this.pagehtml = pageutil.getClientPageHtml("pagetermaudit(#PageNum#)");
            return INPUT;
        } else {
            //审核通过
            String _obystr = "crtime desc";
            int ncount = this.irpTermService.findAllWTermNum(IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,
                    auditnoword, false, null);

            PageUtil pageutil = new PageUtil(auditnopsize, pagesize, ncount);
            termlist = this.irpTermService.findAllWTerm(pageutil, _obystr, IrpTerm.TERMSTATUSWORDS,
                    IrpTerm.TERMISDELNORMAL, auditnoword, false, null);
            this.pagehtml = pageutil.getClientPageHtml("pagetermnoeaudit(#PageNum#)");
            return SUCCESS;
        }

    }

    /**
     * 个人词条列表
     * @return
     */
    public String personalTWords() {

        int pagesize = 10;
        pagesize = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMSIZE);
        int ncount = this.irpTermService.getIrpTermPersonalNum(IrpTerm.TERMSTATUSPERSONAL, IrpTerm.TERMISDELNORMAL,
                LoginUtil.getLoginUserId(), null);
        String _obystr = "crtime desc";
        PageUtil pageutil = new PageUtil(auditnopsize, pagesize, ncount);

        termlist = this.irpTermService.getIrpTermPersonal(IrpTerm.TERMSTATUSPERSONAL, IrpTerm.TERMISDELNORMAL,
                LoginUtil.getLoginUserId(), _obystr, pageutil, null);

        this.pagehtml = pageutil.getClientPageHtml("pageTermPersonal(#PageNum#)");
        return SUCCESS;
    }

    /**
     * 
     * 通过审核
     * @return
     */
    public void passAuditTerm() {
        int msg = 0;
        if (LoginUtil.getLoginUser().isTermswordManager()) {
            msg = this.irpTermService.updateTermStatus(passid, IrpTerm.TERMSTATUSWORDS);
            if (msg == 1) {
                //更新词条本体为合法
                IrpTerm irpterm = this.irpTermService.irpTermById(passid);
                String termStatusStr = irpterm.getTermstatus().toString();
                if (Integer.valueOf(termStatusStr).equals(Integer.valueOf(IrpTerm.TERMSTATUSWORDS))) {
                    this.irpTermService.updateTermStatus(irpterm.getTermqid(), IrpTerm.TERMSTATUSWORDS);
                }
                //更新词条最新版本	
                IrpTerm virpterm = this.irpTermService.getIrpTermFVVersion(irpterm.getTermqid(),
                        IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL, "termversion desc");
                this.irpTermService.updateVersion(irpterm.getTermqid(), virpterm.getTermversion());
            }
        }
        ActionUtil.writer(msg + "");
    }

    /**
     * 
     * lockByTermId:词条的锁定与解锁(lockstatus为1置为锁定，lockstatus为2为解锁). <br/> 
     * lockid为词条内容id
     * @author Administrator  
     * @since JDK 1.8
     */
    public void lockByTermId() {
        int msg = 0;
        if (LoginUtil.getLoginUser().isTermswordManager()) {
            IrpTerm irpTermContent = this.irpTermService.irpTermById(lockid);
            if (lockstatus == 1) {
                msg = this.irpTermService.updateTermisdelById(lockid, IrpTerm.TERMLOCK);
                msg = this.irpTermService.updateTermisdelById(irpTermContent.getTermqid(), IrpTerm.TERMLOCK);
            } else if (lockstatus == 2) {
                msg = this.irpTermService.updateTermisdelById(lockid, IrpTerm.TERMISDELNORMAL);
                msg = this.irpTermService.updateTermisdelById(irpTermContent.getTermqid(), IrpTerm.TERMISDELNORMAL);
            }

        }
        ActionUtil.writer(msg + "");
    }

    private Integer illstatus;

    public Integer getIllstatus() {
        return illstatus;
    }

    public void setIllstatus(Integer illstatus) {
        this.illstatus = illstatus;
    }

    /**
     * 
     * illegaAudiTerm:词条置于非法与解除非法(illstatus为1置为置为非法，illstatus为2为恢复). <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void illegaAudiTerm() {
        int msg = 0;
        if (LoginUtil.getLoginUser().isTermswordManager()) {
            // 1为置为非法
            if (illstatus == 1) {
                msg = this.irpTermService.updateTermStatus(illegid, IrpTerm.TERMSTATUSILLEGALITY);
            } else if (illstatus == 2) {
                msg = this.irpTermService.updateTermStatus(illegid, IrpTerm.TERMSTATUSWORDSNO);
            }
        }
        ActionUtil.writer(msg + "");
    }

    /**
     * 比较两个版本
     * @return
     */
    public String compareTermVersion() {
        if (firsttermid != null) {
            boolean isnum = firsttermid.matches("[0-9]+");
            if (isnum == true) {
                cponeterm = this.irpTermService.irpTermById(Long.parseLong(firsttermid), IrpTerm.TERMSTATUSWORDS,
                        IrpTerm.TERMISDELNORMAL, true, LoginUtil.getLoginUser().isTermswordManager());
            }
        }
        if (secondtermid != null) {
            boolean isnum = secondtermid.matches("[0-9]+");
            if (isnum == true) {
                cptwoterm = this.irpTermService.irpTermById(Long.parseLong(secondtermid), IrpTerm.TERMSTATUSWORDS,
                        IrpTerm.TERMISDELNORMAL, true, LoginUtil.getLoginUser().isTermswordManager());
            }
        }
        return SUCCESS;
    }

    /**
     * 根据类别找到相应的对象
     * @param _cid
     * @return
     */
    public IrpCategory irpCategoryById(Long _cid) {
        IrpCategory irpcategory = null;
        if (_cid != null) {
            irpcategory = this.irpCategoryService.findCategoryByPrimaryKey(_cid);
        }

        return irpcategory;
    }

    /**
     * 根据当前的词条判断未审核 的此条里是否包括了
     * @return
     */
    private String audittwords;

    public String getAudittwords() {
        return audittwords;
    }

    public void setAudittwords(String audittwords) {
        this.audittwords = audittwords;
    }

    /**
     * 判断词条是否在审核中
     */
    public void boolAuditTWords() {
        int msg = 0;
        if (audittwords != null) {
            IrpTerm irpterm = this.irpTermService.getIrpTermByTName(audittwords, IrpTerm.TERMSTATUSWORDSNO,
                    IrpTerm.TERMISDELNORMAL);
            if (irpterm != null) {
                msg = 1;
            }
        }
        ActionUtil.writer(msg + "");
    }

    /**
     * 
     * 保存词条到个人
     */
    public void modifyTermWordsIsdel() {
        int msg = 0;
        if (termname != null) {
            //判断登录用户的此个人词条是否创建
            IrpTerm irpterm = this.irpTermService.findIrpTermByCrId(termname, IrpTerm.TERMSTATUSPERSONAL,
                    IrpTerm.TERMISDELNORMAL, LoginUtil.getLoginUserId());
            if (irpterm == null) {
                //增加新词条
                long irptermid = 0;
                if (termcateval != null) {
                    String latters = "";
                    String sm = getEnameFirst(termname);
                    if (sm.length() < 2) {
                        String smfirest = sm.substring(0, 1).toUpperCase();
                        latters = smfirest;
                    }
                    irptermid = this.irpTermService.addTerm(IrpTerm.TERMSTATUSPERSONAL, IrpTerm.TERMISDELNORMAL,
                            termname, IrpTerm.STARTDEFAULTVISION, false, termcateval, latters, "", null);
                    msg = 1;
                }
                if (thtmlcontent != null && irptermid > 0) {
                    //增加词条新释义
                    this.irpTermService.addTermExplain(IrpTerm.TERMSTATUSPERSONAL, IrpTerm.TERMISDELNORMAL,
                            thtmlcontent, irptermid, IrpTerm.STARTDEFAULTVISION,
                            SysConfigUtil.getSysConfigValue(IrpTerm.TERMUPDATECAUSE), false, termcontent);
                }
            } else {
                //更新个人词条版本
                String version = irpterm.getTermversion();

                Float verstr = Float.parseFloat(version) + Float.parseFloat(IrpTerm.STARTDEFAULTVISION);

                this.irpTermService.updateVersion(irpterm.getTermid(), "" + verstr);
                //			 	if (editclassifyid!=null) {
                //			 		this.irpTermService.updateClassifyById(irpterm.getTermid(), editclassifyid);
                //				}
                msg = this.irpTermService.addTermExplain(IrpTerm.TERMSTATUSPERSONAL, IrpTerm.TERMISDELNORMAL,
                        thtmlcontent, irpterm.getTermid(), "" + verstr, upcausevalue, false, termcontent);

            }
        }
        ActionUtil.writer(msg + "");
    }

    /**
     * 根据词语查找释义
     * @return
     */
    private String hotdocword;

    public String getHotdocword() {
        return hotdocword;
    }

    public void setHotdocword(String hotdocword) {
        this.hotdocword = hotdocword;
    }

    public void findTermDocHWord() {
        IrpTerm irpterm = null;
        if (hotdocword != null) {
            irpterm = this.irpTermService.findTIdByName(hotdocword);
        }
        if (irpterm != null) {
            termhobj = this.irpTermService.irpTermByVerAndId(irpterm.getTermid(), null, true, IrpTerm.TERMSTATUSWORDS,
                    IrpTerm.TERMISDELNORMAL);
        }
        ActionUtil.writer(termhobj.getTermexplain());
    }

    /**
     * 相关热词
     * 同一分类下 点击次数最多的词条集合
     * @return
     */
    private List<IrpTerm> termcatelist;

    public List<IrpTerm> getTermcatelist() {
        return termcatelist;
    }

    public void setTermcatelist(List<IrpTerm> termcatelist) {
        this.termcatelist = termcatelist;
    }

    public String correlationTermCate() {
        IrpTerm irpterm = null;
        if (hotdocword != null) {
            irpterm = this.irpTermService.findTIdByName(hotdocword);
        }

        if (irpterm != null) {
            termcatelist = this.irpTermService.findIrpTermListByCate(IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,
                    irpterm.getQuoteclassify(), SysConfigUtil.getSysConfigNumValue("CORRELATIONTERM"),
                    "termbcount desc");
        }
        return SUCCESS;
    }

    /**
     * 过滤多义词
     * @reutrn
     */
    public void boolTWrodRolyseme() {
        int status = 0;
        if (audittwords != null && (!audittwords.equals(""))) {
            status = this.irpTermService.findTListByPolysemeNum(IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,
                    audittwords);
        }
        ActionUtil.writer(status + "");
    }

    /**
     * 链接到单个义词
     * @return
     */
    public String addSingleRSeme() {

        return SUCCESS;
    }

    private List<IrpCategory> listCategory;
    private List<IrpCategory> list;
    private List<IrpChnlDocLink> chnlDocLinks31;
    private List<IrpChnlDocLink> chnlDocLinks32;
    private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;// 文档栏目中间表service

    private List<IrpChnlDocLink> chnlDocLinks51;

    public List<IrpChnlDocLink> getChnlDocLinks51() {
        return chnlDocLinks51;
    }

    public void setChnlDocLinks51(List<IrpChnlDocLink> chnlDocLinks51) {
        this.chnlDocLinks51 = chnlDocLinks51;
    }

    public List<IrpCategory> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<IrpCategory> listCategory) {
        this.listCategory = listCategory;
    }

    public List<IrpChnlDocLink> getChnlDocLinks31() {
        return chnlDocLinks31;
    }

    public void setChnlDocLinks31(List<IrpChnlDocLink> chnlDocLinks31) {
        this.chnlDocLinks31 = chnlDocLinks31;
    }

    public List<IrpChnlDocLink> getChnlDocLinks32() {
        return chnlDocLinks32;
    }

    public void setChnlDocLinks32(List<IrpChnlDocLink> chnlDocLinks32) {
        this.chnlDocLinks32 = chnlDocLinks32;
    }

    public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
        return irpChnl_Doc_LinkService;
    }

    public void setIrpChnl_Doc_LinkService(IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
        this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
    }

    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<IrpCategory> getList() {
        return list;
    }

    public void setList(List<IrpCategory> list) {
        this.list = list;
    }

    /**
     * 链接到词条首页
     * @return
     */
    public String toTermIndex() {
        String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.CALEGORYTERM);
        PageUtil pageUtil = new PageUtil(this.pagenum, 4, 0);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("parentid", categoryQuestionId);
        listCategory = irpCategoryService.findCategoryByConditions(map);
        String s = "";
        String sOrderByClause = "docpubtime desc";
        List<Long> _channelIds4 = new ArrayList<Long>();
        s = SysConfigUtil.getSysConfigValue("HEAD_BAIKE");
        _channelIds4.add(Long.parseLong(s));//首页元数据50
        chnlDocLinks51 = irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM,
                IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds4,
                sOrderByClause, pageUtil);
        return SUCCESS;
    }

    /**
     * 
     * toTermCategory:跳转到分类列表页. <br/> 
     * 
     * @author Administrator 
     * @return SUCCESS
     * @since JDK 1.8
     */
    public String toTermCategory() {
        String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.CALEGORYTERM);
        HashMap<String, Object> map = new HashMap<String, Object>();
        IrpCategory cate = irpCategoryService.findCategoryByPrimaryKey(categoryId);
        map.put("parentid", cate.getCparentid());
        list = irpCategoryService.findCategoryByConditions(map);
        listCategory = irpCategoryService.currentCategory(categoryId, listCategory, Long.parseLong(categoryQuestionId));
        return SUCCESS;
    }

    /**
     * 
     * findChildCategoryByParentId:根据父级分类查找子分类. <br/> 
     * 
     * @author Administrator 
     * @param _nParentId
     * @return 子分类列表
     * @since JDK 1.8
     */
    public List<IrpCategory> findChildCategoryByParentId(long _nParentId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("parentid", _nParentId);
        List<IrpCategory> childCategory = irpCategoryService.findCategoryByConditions(map);
        return childCategory;
    }

    /**
     * 原图
     * @param _docid
     * @return
     */
    public String docCoverPathSource(Long _docid, Integer docflag) {
        String filePath = "";
        IrpAttached attached = irpAttachedService.getIrpATttachedByDocIDFIle(_docid);
        if (attached != null) {
            String myFileName = attached.getAttfile();
            //获得文件路径 
            filePath = ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="
                    + ThumbnailPic.searchFileName(myFileName, "");
        } else if (docflag != null && docflag > 0) {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand" + docflag + ".jpg";
        } else {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand1.jpg";
        }
        return filePath;
    }

    /**
     * 获得知识的封面
     * @param _docid
     * @return
     */
    public String docCoverPath(Long _docid, Integer docflag) {
        String filePath = "";
        IrpAttached attached = irpAttachedService.getIrpATttachedByDocIDFIle(_docid);
        if (attached != null) {
            String myFileName = attached.getAttfile();
            //获得文件路径 
            filePath = ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="
                    + ThumbnailPic.searchFileName(myFileName, "_150X150");
        } else if (docflag != null && docflag > 0) {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand" + docflag + ".jpg";
        } else {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand1.jpg";
        }
        return filePath;
    }

    /**
     * 根据ID获得知识
     * @param _docid
     * @return
     */
    public IrpDocument getIrpDocumentById(Long _docid) {
        IrpDocument irpDocument = null;
        if (_docid != null) {
            irpDocument = this.irpDocumentService.irpDocument(_docid);
        }
        return irpDocument;
    }

    /**
     * 
     * getTermNum:获取已经通过审核的词条数量. <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getTermNum() {
        int termNum = this.irpTermService.findAllWTermNum(10, 10, null, true, null);
        ActionUtil.writer("{\"termNum\":" + termNum + "}");
    }

    /**
     * 
     * getCreateUserNum:获得创建词条的人数. <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getCreateTermUserNum() {
        int createTermUserNum = this.irpTermService.countCreateTermUserNum();
        ActionUtil.writer("{\"createTermUserNum\":" + createTermUserNum + "}");
    }

    /**
     * 
     * getHotTermList:获得最热词条列表. <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getHotTermList() {
        // 初始化一个用于存放json数据的列表
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        // 用于生成json对象的map
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        int count = this.irpTermService.countTermList();

        PageUtil pageUtil = new PageUtil(1, 4, count);

        List<IrpTerm> list = this.irpTermService.findTermListByCondition(pageUtil, "TERMBCOUNT");

        if (list != null) {
            for (IrpTerm irpTerm : list) {
                // 借用map将对象以json格式封装起来
                Map<String, Object> irpTermMap = new HashMap<String, Object>();

                // 获得词条文本
                IrpTerm irpTermForContent = this.irpTermService.findTermByTermqid(irpTerm.getTermid());
                if (irpTermForContent != null) {
                    irpTermMap.put("termContent", irpTermForContent.getTermcontent());
                    irpTermMap.put("termContentHtml", irpTermForContent.getTermexplain());
                }

                irpTermMap.put("termId", irpTermForContent.getTermid());
                irpTermMap.put("termName", irpTerm.getTermname());

                jsonList.add(irpTermMap);

                irpTermMap = new HashMap<String, Object>();
            }

            jsonMap.put("resultStatus", "success");
            jsonMap.put("data", jsonList);

            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        } else {
            jsonMap.put("resultStatus", "error");
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        }
    }

    /**
     * 
     * getNewTermList:获得最新词条列表. <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getNewTermList() {
        // 初始化一个用于存放json数据的列表
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        // 用于生成json对象的map
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        int count = this.irpTermService.countTermList();

        PageUtil pageUtil = new PageUtil(1, 4, count);

        List<IrpTerm> list = this.irpTermService.findTermListByCondition(pageUtil, "CRTIME");

        if (list != null) {
            for (IrpTerm irpTerm : list) {
                // 借用map将对象以json格式封装起来
                Map<String, Object> irpTermMap = new HashMap<String, Object>();

                // 获得词条文本
                IrpTerm irpTermForContent = this.irpTermService.findTermByTermqid(irpTerm.getTermid());
                if (irpTermForContent != null) {
                    irpTermMap.put("termContent", irpTermForContent.getTermcontent());
                    irpTermMap.put("termContentHtml", irpTermForContent.getTermexplain());
                }

                irpTermMap.put("termId", irpTermForContent.getTermid());
                irpTermMap.put("termName", irpTerm.getTermname());

                jsonList.add(irpTermMap);

                irpTermMap = new HashMap<String, Object>();
            }

            jsonMap.put("resultStatus", "success");
            jsonMap.put("data", jsonList);

            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        } else {
            jsonMap.put("resultStatus", "error");
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        }
    }

    /**
     * 
     * getCreateTermUserList:加载创建词条的次数前三的用户. <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getCreateTermUserList() {
        // 初始化json对象
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        // 初始化存放封装成json格式的用户对象的列表
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        List<Integer> list = this.irpTermService.getCreateTermUserIdList();

        // 判断list集合个数，为空则返回error
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> userMap = new HashMap<String, Object>();

                IrpUser irpUser = this.irpUserService.findUserByUserId(new Long(list.get(i)));

                userMap.put("userId", irpUser.getUserid());
                userMap.put("username", irpUser.getUsername());

                // 用户头像为空，给默认头像
                if (irpUser.getUserpic() == null) {
                    userMap.put("userImage", "");
                } else {
                    userMap.put("userImage", irpUser.getUserpic());
                }

                jsonList.add(userMap);

                userMap = new HashMap<String, Object>();
            }
            jsonMap.put("resultStatus", "success");
            jsonMap.put("data", jsonList);

            ActionUtil.writer(JsonUtil.map2json(jsonMap));

        } else {
            jsonMap.put("resultStatus", "error");
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        }
    }

    /**
     * 
     * getAllAttachedForTerm:获得词条的所有附件. <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getAllAttachedForTerm() {
        // 获得词条内容
        IrpTerm termContent = this.irpTermService.irpTermById(termid);

        // 根据内容中的termqid查询附件
        List<IrpAttached> attachedList = irpAttachedService.getAttachedByAttDocId(termContent.getTermqid(),
                IrpAttached.TERM_ATTACHED);
        ActionUtil.writer(JsonUtil.list2json(attachedList));
    }

    /**
     * 
     * getAllNoCheckTerm:获得未审核的词条列表. <br/> 
     * 
     * @author Administrator 
     * @return 成功
     * @since JDK 1.8
     */
    public String getAllNoCheckTerm() {
        int num = this.irpTermService.findAllWTermNum(IrpTerm.TERMSTATUSWORDSNO, IrpTerm.TERMISDELNORMAL, sword, false,
                qclassifyid);
        int pagesize = 10;
        PageUtil pageutil = new PageUtil(pagenum, pagesize, num);

        String orderStr = "crtime desc";

        List<IrpTerm> irpTermContentList = this.irpTermService.findAllWTerm(pageutil, orderStr,
                IrpTerm.TERMSTATUSWORDSNO, IrpTerm.TERMISDELNORMAL, sword, false, qclassifyid);
        termWithAttachedList = new ArrayList<Map<String, Object>>();

        // 遍历词条内容，根据词条内容里的termqid查出对应的词条题目
        for (IrpTerm irpTermContent : irpTermContentList) {
            Map<String, Object> tempMap = new HashMap<String, Object>();

            // 根据词条内容里的termqid查出对应的词条题目
            Long termqid = irpTermContent.getTermqid();
            IrpTerm irpTermTitle = this.irpTermService.irpTermById(termqid);

            // 判断词条题目里是否有设为封面的附件
            if ("".equals(irpTermTitle.getAttachedid()) || irpTermTitle.getAttachedid() == null) {
                tempMap.put("imgSrc", "");
            } else {
                IrpAttached irpAttached = irpAttachedService.getIrpATttachedByDocIDFIle(irpTermTitle.getTermid());
                if (irpAttached == null) {
                    tempMap.put("imgSrc", "");
                } else {
                    tempMap.put("imgSrc", irpAttached.getAttfile());
                }
            }
            tempMap.put("termname", irpTermTitle.getTermname());
            tempMap.put("termid", irpTermContent.getTermid());
            tempMap.put("version", irpTermContent.getTermversion());
            tempMap.put("crtime", irpTermContent.getCrtime());

            termWithAttachedList.add(tempMap);
            tempMap = new HashMap<String, Object>();
        }

        this.pagehtml = pageutil.getClientPageHtml("pageterm(#PageNum#)");

        return SUCCESS;
    }

    /**
     * 
     * getAllCheckTerm:获得已审核的词条列表. <br/> 
     * 
     * @author Administrator 
     * @return 成功
     * @since JDK 1.8
     */
    public String getAllCheckTerm() {
        int num = this.irpTermService.findAllWTermNum(IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL, sword, false,
                qclassifyid);
        int pagesize = 10;
        PageUtil pageutil = new PageUtil(pagenum, pagesize, num);

        String orderStr = "crtime desc";

        List<IrpTerm> irpTermContentList = this.irpTermService.findAllWTerm(pageutil, orderStr,
                IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL, sword, false, qclassifyid);
        termWithAttachedList = new ArrayList<Map<String, Object>>();

        // 遍历词条内容，根据词条内容里的termqid查出对应的词条题目
        for (IrpTerm irpTermContent : irpTermContentList) {
            Map<String, Object> tempMap = new HashMap<String, Object>();

            // 根据词条内容里的termqid查出对应的词条题目
            Long termqid = irpTermContent.getTermqid();
            IrpTerm irpTermTitle = this.irpTermService.irpTermById(termqid);

            // 判断词条题目里是否有设为封面的附件
            if ("".equals(irpTermTitle.getAttachedid()) || irpTermTitle.getAttachedid() == null) {
                tempMap.put("imgSrc", "");
            } else {
                IrpAttached irpAttached = irpAttachedService.getIrpATttachedByDocIDFIle(irpTermTitle.getTermid());
                if (irpAttached == null) {
                    tempMap.put("imgSrc", "");
                } else {
                    tempMap.put("imgSrc", irpAttached.getAttfile());
                }
            }
            tempMap.put("termname", irpTermTitle.getTermname());
            tempMap.put("termid", irpTermContent.getTermid());
            tempMap.put("version", irpTermContent.getTermversion());
            tempMap.put("crtime", irpTermContent.getCrtime());

            termWithAttachedList.add(tempMap);
            tempMap = new HashMap<String, Object>();
        }

        this.pagehtml = pageutil.getClientPageHtml("pageterm(#PageNum#)");

        return SUCCESS;
    }

    /**
     * 
     * toCheckAndIllegalTermList:跳转到显示为审核词条和非法词条的页面. <br/> 
     * 
     * @author Administrator 
     * @return 成功
     * @since JDK 1.8
     */
    public String toCheckAndIllegalTermList() {
        return SUCCESS;
    }

    /**
     * 
     * toPersonalTermList:跳转到显示为个人词条草稿的页面. <br/> 
     * 
     * @author Administrator 
     * @return 成功
     * @since JDK 1.8
     */
    public String toPersonalTermList() {
        return SUCCESS;
    }

    /**
     * 
     * getAllIllegalTerm:获得所有非法的词条. <br/> 
     * 
     * @author Administrator 
     * @return 成功
     * @since JDK 1.8
     */
    public String getAllIllegalTerm() {
        int num = this.irpTermService.findAllWTermNum(IrpTerm.TERMSTATUSILLEGALITY, IrpTerm.TERMISDELNORMAL, sword,
                false, qclassifyid);
        int pagesize = 10;
        PageUtil pageutil = new PageUtil(pagenum, pagesize, num);

        String orderStr = "crtime desc";

        List<IrpTerm> irpTermContentList = this.irpTermService.findAllWTerm(pageutil, orderStr,
                IrpTerm.TERMSTATUSILLEGALITY, IrpTerm.TERMISDELNORMAL, sword, false, qclassifyid);
        termWithAttachedList = new ArrayList<Map<String, Object>>();

        // 遍历词条内容，根据词条内容里的termqid查出对应的词条题目
        for (IrpTerm irpTermContent : irpTermContentList) {
            Map<String, Object> tempMap = new HashMap<String, Object>();

            // 根据词条内容里的termqid查出对应的词条题目
            Long termqid = irpTermContent.getTermqid();
            IrpTerm irpTermTitle = this.irpTermService.irpTermById(termqid);

            // 判断词条题目里是否有设为封面的附件
            if ("".equals(irpTermTitle.getAttachedid()) || irpTermTitle.getAttachedid() == null) {
                tempMap.put("imgSrc", "");
            } else {
                IrpAttached irpAttached = irpAttachedService.getIrpATttachedByDocIDFIle(irpTermTitle.getTermid());
                if (irpAttached == null) {
                    tempMap.put("imgSrc", "");
                } else {
                    tempMap.put("imgSrc", irpAttached.getAttfile());
                }
            }
            tempMap.put("termname", irpTermTitle.getTermname());
            tempMap.put("termid", irpTermContent.getTermid());
            tempMap.put("version", irpTermContent.getTermversion());
            tempMap.put("crtime", irpTermContent.getCrtime());

            termWithAttachedList.add(tempMap);
            tempMap = new HashMap<String, Object>();
        }

        this.pagehtml = pageutil.getClientPageHtml("pageterm(#PageNum#)");

        return SUCCESS;
    }

    /**
     * 
     * getPersonalTermList:获得个人词条草稿. <br/> 
     * 
     * @author Administrator 
     * @return 成功
     * @since JDK 1.8
     */
    public String getPersonalTermList() {
        int count = this.irpTermService.getIrpTermPersonalNum(IrpTerm.TERMSTATUSPERSONAL, IrpTerm.TERMISDELNORMAL,
                LoginUtil.getLoginUserId(), null);

        PageUtil pageutil = new PageUtil(pagenum, 10, count);

        String orderStr = "crtime desc";

        // 获得所有属于个人词条草稿的词条内容列表
        List<IrpTerm> irpTermContentList = this.irpTermService.getIrpTermPersonal(IrpTerm.TERMSTATUSPERSONAL,
                IrpTerm.TERMISDELNORMAL, LoginUtil.getLoginUserId(), orderStr, pageutil, null);

        termWithAttachedList = new ArrayList<Map<String, Object>>();

        // 遍历词条内容，根据词条内容里的termqid查出对应的词条题目
        for (IrpTerm irpTermContent : irpTermContentList) {
            Map<String, Object> tempMap = new HashMap<String, Object>();

            // 根据词条内容里的termqid查出对应的词条题目
            Long termqid = irpTermContent.getTermqid();
            IrpTerm irpTermTitle = this.irpTermService.irpTermById(termqid);

            // 判断词条题目里是否有设为封面的附件
            if ("".equals(irpTermTitle.getAttachedid()) || irpTermTitle.getAttachedid() == null) {
                tempMap.put("imgSrc", "");
            } else {
                IrpAttached irpAttached = irpAttachedService.getIrpATttachedByDocIDFIle(irpTermTitle.getTermid());
                if (irpAttached == null) {
                    tempMap.put("imgSrc", "");
                } else {
                    tempMap.put("imgSrc", irpAttached.getAttfile());
                }
            }
            tempMap.put("termname", irpTermTitle.getTermname());
            tempMap.put("termid", irpTermContent.getTermid());
            tempMap.put("crtime", irpTermContent.getCrtime());

            termWithAttachedList.add(tempMap);
            tempMap = new HashMap<String, Object>();
        }

        this.pagehtml = pageutil.getClientPageHtml("pageterm(#PageNum#)");

        return SUCCESS;
    }

    /**
     * 
     * getAttachedId:获得附件Id组成的字符串. <br/> 
     * 
     * @author Administrator 
     * @param file json格式的文件字符串
     * @param tableid 主键
     * @return 逗号拼接的id字符串
     * @since JDK 1.8
     */
    private String getAttachedId(String file, Long tableid) {
        // 如果存在附件，则添加附件
        JSONArray _Array = JSONArray.fromObject(file);
        String attachedIdStr = "";
        for (int i = 0; i < _Array.size(); i++) {
            JSONObject jsonObject = (JSONObject) _Array.getJSONObject(i);
            String sattfile = jsonObject.getString("attfile");
            String sattdesc = jsonObject.getString("attdesc");
            String sattlinkalt = jsonObject.getString("attlinkalt");
            String seditversions = jsonObject.getString("editversions");
            String attflag = jsonObject.getString("attflag");
            // 获得文件类型
            Long typeid = irpAttachedTypeService.findAttachedTypeIdByFileExt(FileUtil.findFileExt(sattfile));
            // 入库
            Long attachedId = addAttachedFileForTerm(Integer.parseInt(attflag), sattfile, typeid, tableid, sattdesc,
                    sattlinkalt, seditversions, false, null, false);
            attachedIdStr = attachedId + "," + attachedIdStr;
        }
        return attachedIdStr;
    }

    /**
     * 
     * showTermInfo:点击最新最热词条显示详情页. <br/> 
     * 
     * @author Administrator 
     * @return SUCCESS
     * @since JDK 1.8
     */
    public String showTermInfo() {
        // 获得词条内容
        irptermofexpl = this.irpTermService.irpTermById(termid);

        // 获得词条题目
        irptermofword = this.irpTermService.irpTermById(irptermofexpl.getTermqid());

        // 加载附件
        attachedList = this.irpAttachedService.getAttachedByAttDocId(irptermofword.getTermid(),
                IrpAttached.TERM_ATTACHED);
        return SUCCESS;
    }

    /**
     * 
     * addAttachedFileForTerm:保存添加的附件. <br/> 
     * 
     * @author Administrator 
     * @param _attflag
     * @param _sAttFile
     * @param TypeId
     * @param _docid
     * @param _sAttDesc
     * @param _sAttLinkAlt
     * @param _sEditversions
     * @param isClient
     * @param _touChannelid
     * @param addUserFile
     * @return 附件id
     * @since JDK 1.8
     */
    private Long addAttachedFileForTerm(Integer _attflag, String _sAttFile, Long TypeId, Long _docid, String _sAttDesc,
            String _sAttLinkAlt, String _sEditversions, boolean isClient, Long _touChannelid, Boolean addUserFile) {
        String filePath = SysFileUtil.getFilePathByFileName(_sAttFile);
        File newFile = new File(filePath);
        String newFileName = "";
        Long attachedId = null;
        if (newFile.exists()) {
            if (IrpAttachedType.JPG_FIELD_NAME.toString().equals(TypeId.toString())) { // 这是创建文档之后的附件名字
                newFileName = SysFileUtil.saveFileDoc(newFile, SysFileUtil.FILE_TYPE_ATTACHED_PIC, addUserFile);
            } else {
                newFileName = SysFileUtil.saveFileDoc(newFile, SysFileUtil.FILE_TYPE_ATTACHED_FILE, addUserFile);
            }
            // 删除临时表中的文件
            String newFilePath = SysFileUtil.getFilePathByFileName(newFileName);

            attachedId = irpAttachedService.addFile(_docid, 0L, newFileName, _sAttLinkAlt, _sAttDesc,
                    IrpAttached.TERM_ATTACHED, newFilePath, Integer.parseInt(_sEditversions), TypeId, _attflag);
        }
        return attachedId;
    }
}
