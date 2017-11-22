package com.tfs.irp.expert.dao;

import com.tfs.irp.expert.entity.IrpExpertClassifyLink;
import com.tfs.irp.expert.entity.IrpExpertClassifyLinkExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpExpertClassifyLinkDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    int countByExample(IrpExpertClassifyLinkExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    int deleteByExample(IrpExpertClassifyLinkExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    void insert(IrpExpertClassifyLink record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    void insertSelective(IrpExpertClassifyLink record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    List<IrpExpertClassifyLink> selectByExample(IrpExpertClassifyLinkExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    int updateByExampleSelective(IrpExpertClassifyLink record, IrpExpertClassifyLinkExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    int updateByExample(IrpExpertClassifyLink record, IrpExpertClassifyLinkExample example) throws SQLException;
}