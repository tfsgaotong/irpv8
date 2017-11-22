package com.tfs.irp.term.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.search.AndTerm;

import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.term.dao.IrpTermDAO;
import com.tfs.irp.term.entity.IrpTerm;
import com.tfs.irp.term.entity.IrpTermExample;
import com.tfs.irp.term.entity.IrpTermExample.Criteria;
import com.tfs.irp.term.service.IrpTermService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpTermServiceImpl implements IrpTermService {

    private IrpTermDAO irpTermDAO;

    public IrpTermDAO getIrpTermDAO() {
        return irpTermDAO;
    }

    public void setIrpTermDAO(IrpTermDAO irpTermDAO) {
        this.irpTermDAO = irpTermDAO;
    }

    @Override
    public List<IrpTerm> findAllWTerm(PageUtil _pageobj, String _obystr, Integer _status, Integer _isdel,
            String _sword, boolean _qstatus, Long _classid) {
        List<IrpTerm> list = null;
        IrpTermExample example = new IrpTermExample();
        Criteria cri = example.createCriteria();
        cri.andTermstatusEqualTo((long) _status);
        if (_classid != null) {
            cri.andQuoteclassifyEqualTo(_classid);
        }
        if (_qstatus == true) {
            cri.andTermqidIsNull();
            if (_sword != null) {
                cri.andTermnameLike("%" + _sword + "%");
            }
        } else {
            cri.andTermqidIsNotNull();
        }

        example.setOrderByClause(_obystr);

        try {
            list = this.irpTermDAO.selectByExampleWithBLOBs(example, _pageobj);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int findAllWTermNum(Integer _status, Integer _isdel, String _sword, boolean _qstatus, Long _classid) {
        int num = 0;
        IrpTermExample example = new IrpTermExample();
        Criteria cri = example.createCriteria();
        cri.andTermstatusEqualTo((long) _status);

        if (_classid != null) {
            cri.andQuoteclassifyEqualTo(_classid);
        }

        if (_qstatus == true) {
            cri.andTermqidIsNull();
            if (_sword != null) {
                cri.andTermnameLike("%" + _sword + "%");
            }
        } else {
            cri.andTermqidIsNotNull();
        }

        try {
            num = this.irpTermDAO.countByExample(example);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public long addTerm(Integer _status, Integer _isdel, String _words, String _soversion, boolean _mgr, Long _tcate,
            String _latter, String attachedId, Long termId) {
        long status = 0;
        long termid = 0;
        IrpTerm irpterm = new IrpTerm();
        if (termId == null) {
            termid = TableIdUtil.getNextId(IrpTerm.TABLENAME);
        } else {
            termid = termId;
        }
        irpterm.setTermid(termid);
        irpterm.setTermname(_words);
        irpterm.setQletter(_latter);
        irpterm.setAttachedid(attachedId);
        if (_mgr == true) {
            irpterm.setTermstatus((long) IrpTerm.TERMSTATUSWORDS);
            irpterm.setTermisdel(IrpTerm.TERMISDELNORMAL);
        } else {
            irpterm.setTermstatus((long) _status);
            irpterm.setTermisdel(_isdel);
        }
        if (_tcate != null) {
            irpterm.setQuoteclassify(_tcate);
        }
        irpterm.setTermversion(_soversion);
        irpterm.setCrtime(Calendar.getInstance().getTime());
        irpterm.setCruserid(LoginUtil.getLoginUserId());
        try {
            this.irpTermDAO.insertSelective(irpterm);
            status = termid;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public int addTermExplain(Integer _status, Integer _isdel, String _content, Long _termqid, String _vision,
            String _editcause, boolean _mgr, String termcontent) {
        IrpTerm irpterm = new IrpTerm();
        int status = 0;
        irpterm.setTermid(TableIdUtil.getNextId(IrpTerm.TABLENAME));

        irpterm.setTermexplain(_content);
        if (_mgr == true) {
            irpterm.setTermstatus((long) IrpTerm.TERMSTATUSWORDS);
            irpterm.setTermisdel(IrpTerm.TERMISDELNORMAL);
        } else {
            irpterm.setTermstatus((long) _status);
            irpterm.setTermisdel(_isdel);
        }
        irpterm.setTermqid(_termqid);
        irpterm.setTermversion(_vision);
        irpterm.setCrtime(Calendar.getInstance().getTime());
        irpterm.setCruserid(LoginUtil.getLoginUserId());
        irpterm.setEidtcause(_editcause);
        irpterm.setTermcontent(termcontent);
        try {
            this.irpTermDAO.insertSelective(irpterm);
            status = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public IrpTerm findTIdByName(String _termname) {
        IrpTerm irpterm = null;
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andTermnameEqualTo(_termname);
        try {
            List<IrpTerm> list = this.irpTermDAO.selectByExampleWithoutBLOBs(example);
            if (list != null && list.size() > 0) {
                irpterm = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return irpterm;
    }

    @Override
    public int updateVersion(long _termid, String _version) {
        IrpTerm irpTerm = new IrpTerm();
        int msg = 0;
        irpTerm.setTermid(_termid);
        irpTerm.setTermversion(_version);
        try {
            msg = this.irpTermDAO.updateByPrimaryKeySelective(irpTerm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public IrpTerm irpTermById(long _termid, Integer _status, Integer _isdel, boolean _boolclob, boolean _ismgr) {
        IrpTerm irpterm = null;

        IrpTermExample example = new IrpTermExample();
        if (_ismgr == true) {
            example.createCriteria().andTermidEqualTo(_termid);
        } else {
            example.createCriteria().andTermstatusEqualTo((long) _status).andTermidEqualTo(_termid);
        }

        try {
            List<IrpTerm> list = null;
            if (_boolclob == true) {
                list = irpTermDAO.selectByExampleWithBLOBs(example);
            } else {
                list = irpTermDAO.selectByExampleWithoutBLOBs(example);
            }

            if (list.size() > 0 && list != null) {
                irpterm = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return irpterm;
    }

    @Override
    public List<IrpTerm> irpTexampleTyId(long _termid, Integer _status, Integer _isdel, PageUtil _pageutil,
            String _oderstr, boolean _ismgr) {
        List<IrpTerm> list = null;
        IrpTermExample example = new IrpTermExample();
        if (_ismgr == true) {
            example.createCriteria().andTermqidEqualTo(_termid);
        } else {
            example.createCriteria().andTermstatusEqualTo((long) _status).andTermqidEqualTo(_termid);
        }
        example.setOrderByClause(_oderstr);
        try {
            list = this.irpTermDAO.selectByExampleWithBLOBs(example, _pageutil);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int irpTexampleTyIdNum(long _termid, Integer _status, Integer _isdel, boolean _ismgr) {
        int num = 0;
        IrpTermExample example = new IrpTermExample();
        if (_ismgr == true) {
            example.createCriteria().andTermqidEqualTo(_termid);
        } else {
            example.createCriteria().andTermstatusEqualTo((long) _status).andTermqidEqualTo(_termid);
        }

        try {
            num = this.irpTermDAO.countByExample(example);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;

    }

    @Override
    public IrpTerm irpTermByVerAndId(long _termid, String _version, boolean _iszversion, Integer _status, Integer _isdel) {
        IrpTerm irpterm = null;
        IrpTermExample example = new IrpTermExample();
        if (_iszversion == true) {
            example.createCriteria().andTermqidEqualTo(_termid);
        } else {
            example.createCriteria().andTermqidEqualTo(_termid).andTermversionEqualTo(_version)
                    .andTermqidEqualTo(_termid);
        }
        try {
            List<IrpTerm> list = this.irpTermDAO.selectByExampleWithBLOBs(example);
            if (list.size() > 0 && list != null) {
                irpterm = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return irpterm;
    }

    @Override
    public int updateBCount(long _termid, int _bcount) {
        int status = 0;
        IrpTerm irpterm = new IrpTerm();
        irpterm.setTermid(_termid);
        irpterm.setTermbcount(_bcount);
        try {
            status = this.irpTermDAO.updateByPrimaryKeySelective(irpterm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public IrpTerm irpTermById(long _termid) {
        IrpTerm irpterm = null;
        if (_termid > 0) {
            try {
                irpterm = this.irpTermDAO.selectByPrimaryKey(_termid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return irpterm;
    }

    @Override
    public int updateTermStatus(long _termid, int _status) {
        int status = 0;
        IrpTerm irpterm = new IrpTerm();
        irpterm.setTermid(_termid);
        irpterm.setTermstatus((long) _status);
        try {
            status = this.irpTermDAO.updateByPrimaryKeySelective(irpterm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public IrpTerm getIrpTermFVVersion(long _termqid, int _status, int _isdel, String _orderstr) {
        IrpTerm irpterm = null;
        IrpTermExample ite = new IrpTermExample();
        ite.createCriteria().andTermstatusEqualTo((long) _status).andTermqidEqualTo(_termqid);
        ite.setOrderByClause(_orderstr);
        PageUtil pageutil = new PageUtil(1, 1, 0);
        try {
            List<IrpTerm> list = this.irpTermDAO.selectByExampleWithBLOBs(ite, pageutil);
            if (list.size() > 0) {
                irpterm = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return irpterm;
    }

    @Override
    public int updateClassifyById(long _termid, long _classify) {
        int status = 0;
        IrpTerm irpterm = new IrpTerm();
        irpterm.setTermid(_termid);
        irpterm.setQuoteclassify(_classify);
        try {
            status = this.irpTermDAO.updateByPrimaryKeySelective(irpterm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public IrpTerm getIrpTermByTName(String _tname, Integer _status, Integer _isdel) {
        IrpTerm irpterm = null;
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andTermnameEqualTo(_tname).andTermstatusEqualTo((long) _status).andTermqidIsNull();

        try {
            List<IrpTerm> list = this.irpTermDAO.selectByExampleWithoutBLOBs(example);
            if (list.size() > 0) {
                irpterm = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return irpterm;
    }

    @Override
    public IrpTerm findIrpTermByCrId(String _tname, Integer _status, Integer _isdel, Long _cruserid) {

        IrpTerm irpterm = null;
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andTermstatusEqualTo((long) _status).andTermnameEqualTo(_tname)
                .andCruseridEqualTo(_cruserid).andTermqidIsNull();
        example.setOrderByClause("crtime desc");

        try {
            List<IrpTerm> list = this.irpTermDAO.selectByExampleWithBLOBs(example);
            if (list.size() > 0) {
                irpterm = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return irpterm;
    }

    @Override
    public List<IrpTerm> getIrpTermPersonal(Integer _status, Integer _isdel, Long _cruserid, String _odrstr,
            PageUtil _pageutil, String _stermname) {
        List<IrpTerm> list = null;
        IrpTermExample example = new IrpTermExample();

        Criteria cri = example.createCriteria();

        cri.andTermstatusEqualTo((long) _status);
        cri.andCruseridEqualTo(_cruserid);
        cri.andTermqidIsNotNull();
        if (_stermname != null) {
            cri.andTermnameLike("%" + _stermname + "%");
        }
        example.setOrderByClause(_odrstr);
        try {
            list = this.irpTermDAO.selectByExampleWithoutBLOBs(example, _pageutil);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getIrpTermPersonalNum(Integer _status, Integer _isdel, Long _cruserid, String _stermname) {
        int num = 0;
        IrpTermExample example = new IrpTermExample();
        Criteria cri = example.createCriteria();

        cri.andTermstatusEqualTo((long) _status);
        cri.andCruseridEqualTo(_cruserid);
        cri.andTermqidIsNotNull();
        if (_stermname != null) {
            cri.andTermnameLike("%" + _stermname + "%");
        }
        try {
            num = this.irpTermDAO.countByExample(example);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public IrpTerm findIrpTermByPersonId(Integer _status, Integer _isdel, Long _cruserid, Long _termid) {
        IrpTerm irpterm = null;
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andTermstatusEqualTo((long) _status).andCruseridEqualTo(_cruserid)
                .andTermidEqualTo(_termid);
        try {
            List<IrpTerm> list = this.irpTermDAO.selectByExampleWithBLOBs(example);
            if (list.size() > 0) {
                irpterm = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return irpterm;
    }

    @Override
    public int findCountByTCate(Long _cid, Integer _status) {
        int num = 0;
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andQuoteclassifyEqualTo(_cid).andTermstatusEqualTo(Long.parseLong(_status.toString()));

        try {
            num = this.irpTermDAO.countByExample(example);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public int updateTermisdelById(Long _tid, Integer _isdel) {
        int status = 0;
        IrpTerm irpterm = new IrpTerm();
        irpterm.setTermid(_tid);
        irpterm.setTermisdel(_isdel);
        try {
            status = this.irpTermDAO.updateByPrimaryKeySelective(irpterm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<String> selectTNameTask(Integer _status, Integer _isdel) {
        List<String> liststr = new ArrayList<String>();
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andTermstatusEqualTo((long) _status).andTermisdelEqualTo(_isdel).andTermqidIsNull();
        try {
            List<IrpTerm> list = this.irpTermDAO.selectByExampleWithoutBLOBs(example);
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    liststr.add(list.get(i).getTermname());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liststr;
    }

    @Override
    public List<IrpTerm> findIrpTermListByCate(Integer _status, Integer _isdel, Long _cate, Integer _fnum,
            String _orderstr) {
        List<IrpTerm> list = null;
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andQuoteclassifyEqualTo(_cate).andTermstatusEqualTo((long) _status)
                .andTermisdelEqualTo(_isdel).andTermqidIsNull();
        example.setOrderByClause(_orderstr);
        PageUtil pageutil = new PageUtil(1, _fnum, 0);
        try {
            list = this.irpTermDAO.selectByExampleWithoutBLOBs(example, pageutil);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<IrpTerm> findTListByPolyseme(Integer _stuats, Integer _isdel, PageUtil pageutil, String _orderbystr,
            String _termanem) {
        List<IrpTerm> list = null;
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andTermnameEqualTo(_termanem).andTermstatusEqualTo((long) _stuats)
                .andTermisdelEqualTo(_isdel).andTermqidIsNull();
        example.setOrderByClause(_orderbystr);

        try {
            list = this.irpTermDAO.selectByExampleWithoutBLOBs(example, pageutil);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int findTListByPolysemeNum(Integer _stuats, Integer _isdel, String _termanem) {
        int num = 0;
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andTermnameEqualTo(_termanem).andTermstatusEqualTo((long) _stuats)
                .andTermisdelEqualTo(_isdel).andTermqidIsNull();
        try {
            num = this.irpTermDAO.countByExample(example);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int countCreateTermUserNum() {
        int num = 0;

        try {
            num = this.irpTermDAO.countCreateTermUserNum();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int countTermList() {
        int count = 0;

        // 创建ibatis封装好的查询实体
        IrpTermExample irpTermExample = new IrpTermExample();

        // 词条名不为空，状态正常，审核通过
        irpTermExample.createCriteria().andTermnameIsNotNull().andTermstatusEqualTo(new Long(IrpTerm.TERMSTATUSWORDS));

        try {
            count = this.irpTermDAO.countByExample(irpTermExample);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public List<IrpTerm> findTermListByCondition(PageUtil pageUtil, String condition) {
        List<IrpTerm> list = null;

        // 创建ibatis封装好的查询实体
        IrpTermExample irpTermExample = new IrpTermExample();

        // 根据排序条件排序
        irpTermExample.setOrderByClause(condition + " desc");

        // 词条名不为空，，审核通过
        irpTermExample.createCriteria().andTermnameIsNotNull().andTermstatusEqualTo(new Long(IrpTerm.TERMSTATUSWORDS));

        try {
            list = this.irpTermDAO.selectByExampleWithBLOBs(irpTermExample, pageUtil);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public IrpTerm findTermByTermqid(Long termqid) {
        IrpTerm irpterm = null;
        IrpTermExample irpTermExample = new IrpTermExample();
        irpTermExample.setOrderByClause("CRTIME desc");
        irpTermExample.createCriteria().andTermqidEqualTo(termqid)
                .andTermstatusEqualTo(Long.parseLong(IrpTerm.TERMSTATUSWORDS + ""));
        try {
            List<IrpTerm> list = this.irpTermDAO.selectByExampleWithBLOBs(irpTermExample);
            if (list != null && list.size() > 0) {
                irpterm = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return irpterm;
    }

    @Override
    public List<Integer> getCreateTermUserIdList() {
        // 初始化用于存放排序之后的用户id列表
        List<Integer> userIdList = new ArrayList<Integer>();
        try {

            // 获得所有的创建词条的用户Id列表
            List<Integer> tempList = this.irpTermDAO.selectCreateTermUserIdList();

            // 利用map来统计用户Id出现的次数
            Map<Integer, Integer> userIdMap = new HashMap<Integer, Integer>();
            for (Integer integer : tempList) {
                if (userIdMap.containsKey(integer)) {
                    Integer value = userIdMap.get(integer);
                    userIdMap.put(integer, value + 1);
                } else {
                    userIdMap.put(integer, 1);
                }
            }

            // 将userIdMap转换为List，以便排序
            List<Map.Entry<Integer, Integer>> sortList = new ArrayList<Map.Entry<Integer, Integer>>(
                    userIdMap.entrySet());

            // 通过比较器来实现排序(降序)  
            Collections.sort(sortList, new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            // 将排好序的Id放入userIdList
            for (Entry<Integer, Integer> entry : sortList) {
                int userId = entry.getKey();
                userIdList.add(userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIdList;
    }

    @Override
    public Integer updateTermAttachedId(IrpTerm irpterm) {
        int rows = 0;
        try {
            rows = irpTermDAO.updateByPrimaryKeySelective(irpterm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public IrpTerm findIrpTermContentByPersonId(int termStatus, int termIsDel, Long termId, long loginUserId) {
        IrpTerm irpterm = null;
        IrpTermExample example = new IrpTermExample();
        example.createCriteria().andTermstatusEqualTo((long) termStatus).andCruseridEqualTo(loginUserId)
                .andTermisdelEqualTo(termIsDel).andTermqidEqualTo(termId);
        try {
            List<IrpTerm> list = this.irpTermDAO.selectByExampleWithBLOBs(example);
            if (list.size() > 0) {
                irpterm = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return irpterm;
    }
}
