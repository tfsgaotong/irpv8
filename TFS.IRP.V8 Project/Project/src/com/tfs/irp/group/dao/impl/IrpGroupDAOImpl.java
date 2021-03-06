package com.tfs.irp.group.dao.impl;

import com.tfs.irp.group.dao.IrpGroupDAO;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.entity.IrpGroupExample;
import com.tfs.irp.group.entity.IrpGroupExample.Criteria;
import com.tfs.irp.group.entity.IrpUsergroupLinkExample;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpGroupDAOImpl extends SqlMapClientDaoSupport implements IrpGroupDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public int countByExample(IrpGroupExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_GROUP.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public int deleteByExample(IrpGroupExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_GROUP.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public int deleteByPrimaryKey(Long groupid) throws SQLException {
        IrpGroup key = new IrpGroup();
        key.setGroupid(groupid);
        int rows = getSqlMapClientTemplate().delete("IRP_GROUP.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public void insert(IrpGroup record) throws SQLException {
    	try {
    		//开启事务
    		//getSqlMapClientTemplate().startTransaction();
    		record.setGrouporder(record.getGrouporder()+1);
    		IrpGroupExample example = new IrpGroupExample();
    		example.createCriteria().andParentidEqualTo(record.getParentid()).andGrouporderGreaterThanOrEqualTo(record.getGrouporder());
    		//修改同级中其他的组织排序
    		getSqlMapClientTemplate().update("IRP_GROUP.updateOrderByDown", example);
    		//添加新组织
    		getSqlMapClientTemplate().insert("IRP_GROUP.ibatorgenerated_insert", record);
    		//提交事务
    		//getSqlMapClientTemplate().commitTransaction();
		} finally {
			//结束事务
	        //getSqlMapClientTemplate().endTransaction();
		}
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public void insertSelective(IrpGroup record) throws SQLException {
    	try {
    		//开启事务
    		//getSqlMapClientTemplate().startTransaction();
    		record.setGrouporder(record.getGrouporder()!=null?record.getGrouporder()+1L:1L);
    		IrpGroupExample example = new IrpGroupExample();
    		example.createCriteria().andParentidEqualTo(record.getParentid()).andGrouporderGreaterThanOrEqualTo(record.getGrouporder());
    		//修改同级中其他的组织排序
    		getSqlMapClientTemplate().update("IRP_GROUP.updateOrderByDown", example);
    		//添加新组织
    		getSqlMapClientTemplate().insert("IRP_GROUP.ibatorgenerated_insertSelective", record);
    		//提交事务
    		//getSqlMapClientTemplate().commitTransaction();
		} finally {
			//结束事务
	        //getSqlMapClientTemplate().endTransaction();
		}
    	
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpGroup> selectByExample(IrpGroupExample example) throws SQLException {
        List<IrpGroup> list = getSqlMapClientTemplate().queryForList("IRP_GROUP.ibatorgenerated_selectByExample", example);
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public List<IrpGroup> selectByExample(IrpGroupExample example, PageUtil pageUtil) throws SQLException {
        List<IrpGroup> list = getSqlMapClientTemplate().queryForList("IRP_GROUP.ibatorgenerated_selectByExample", example, pageUtil.getPageIndex(), pageUtil.getPageSize());
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public IrpGroup selectByPrimaryKey(Long groupid) throws SQLException {
        IrpGroup key = new IrpGroup();
        key.setGroupid(groupid);
        IrpGroup record = (IrpGroup) getSqlMapClientTemplate().queryForObject("IRP_GROUP.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public int updateByExampleSelective(IrpGroup record, IrpGroupExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_GROUP.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public int updateByExample(IrpGroup record, IrpGroupExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_GROUP.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpGroup record) throws SQLException {
        int rows = 0;
        try{
        	//getSqlMapClientTemplate().startTransaction();
        	//处理排序
        	IrpGroup gourp = selectByPrimaryKey(record.getGroupid());
        	IrpGroupExample example = new IrpGroupExample();
        	Criteria criteria = example.createCriteria();
        	criteria.andParentidEqualTo(record.getParentid()).andGroupidNotEqualTo(record.getGroupid());
        	if(record.getGrouporder()!=null){
        		if(record.getGrouporder()>gourp.getGrouporder()){
    				criteria.andGrouporderBetween(gourp.getGrouporder(), record.getGrouporder());
    				getSqlMapClientTemplate().update("IRP_GROUP.updateOrderByUp", example);
    			}else{
    				criteria.andGrouporderBetween(record.getGrouporder(), gourp.getGrouporder());
    				getSqlMapClientTemplate().update("IRP_GROUP.updateOrderByDown", example);
    				record.setGrouporder(record.getGrouporder()+1);
    			}
        	}
			//修改组织
        	rows = getSqlMapClientTemplate().update("IRP_GROUP.ibatorgenerated_updateByPrimaryKeySelective", record);
        	//getSqlMapClientTemplate().commitTransaction();
        } finally {
        	//getSqlMapClientTemplate().endTransaction();
        }
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    public int updateByPrimaryKey(IrpGroup record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_GROUP.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_GROUP
     *
     * @ibatorgenerated Thu Feb 28 16:10:30 CST 2013
     */
    private static class UpdateByExampleParms extends IrpGroupExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpGroupExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
   
    @Override
    @SuppressWarnings("unchecked")
	public List<IrpUser> findUsersOfPageByGroupId(Map<String, Object> _mParam, PageUtil pageUtil) throws SQLException {
		List<IrpUser> users = getSqlMapClientTemplate().queryForList("IRP_USER.findUsersByGroupId", _mParam, pageUtil.getPageIndex(), pageUtil.getPageSize());
		return users;
	}
    @Override
    @SuppressWarnings("unchecked")
	public List<IrpUser> findUsersByGroupIdAndSm(Map<String, Object> _mParam, PageUtil pageUtil) throws SQLException {
		List<IrpUser> users = getSqlMapClientTemplate().queryForList("IRP_USER.findUsersByGroupIdAndSm", _mParam, pageUtil.getPageIndex(), pageUtil.getPageSize());
		return users;
	}
    @Override
    @SuppressWarnings("unchecked")
	public List<IrpUser> findUsersByGroupId(Map<String, Object> _mParam) throws SQLException {
		List<IrpUser> users = getSqlMapClientTemplate().queryForList("IRP_USER.findUsersByGroupId", _mParam);
		return users;
	} 
    @Override
    public int findUsersCountByGroupId(Map<String, Object> _mParam) throws SQLException {
    	Integer count = (Integer) getSqlMapClientTemplate().queryForObject("IRP_USER.findUsersCountByGroupId", _mParam);
		return count;
	}
    
    @Override
    public int deleteByGroupIds(List<Long> arrGroupIds, Long _nParentId) throws SQLException {
        int rows = 0;
        try {
        	IrpGroupExample example = new IrpGroupExample();
    		example.createCriteria().andGroupidIn(arrGroupIds);
    		IrpUsergroupLinkExample example1 = new IrpUsergroupLinkExample();
    		example1.createCriteria().andGroupidIn(arrGroupIds);
        	//getSqlMapClientTemplate().startTransaction();
        	rows = getSqlMapClientTemplate().delete("IRP_GROUP.ibatorgenerated_deleteByExample", example);
        	getSqlMapClientTemplate().delete("IRP_USERGROUP_LINK.ibatorgenerated_deleteByExample", example1);
        	if(rows>0){
        		getSqlMapClientTemplate().update("IRP_GROUP.reorderByParentId", _nParentId);
        	}
        	//getSqlMapClientTemplate().commitTransaction();
		} finally {
			//getSqlMapClientTemplate().endTransaction();
		}
        return rows;
    }
}