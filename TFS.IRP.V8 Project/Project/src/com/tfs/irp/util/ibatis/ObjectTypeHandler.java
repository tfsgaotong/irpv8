package com.tfs.irp.util.ibatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.ibatis.sqlmap.engine.type.BaseTypeHandler;
import com.ibatis.sqlmap.engine.type.TypeHandler;

public class ObjectTypeHandler extends BaseTypeHandler implements TypeHandler {

	/**
     * @see TypeHandler#setParameter(PreparedStatement, int, Object, String)
     */
    public void setParameter(PreparedStatement ps, int i, Object parameter, String jdbcType) throws SQLException {
        ps.setObject(i, parameter);
    }

    /**
     * @see TypeHandler#getResult(ResultSet, String)
     */
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
        	if(object instanceof java.sql.Date){
        		object = new Date(rs.getTimestamp(columnName).getTime());
        	}
            return object;
        }
    }

    /**
     * @see TypeHandler#getResult(ResultSet, int)
     */
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        Object object = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
        	if(object instanceof java.sql.Date){
        		object = new Date(rs.getTimestamp(columnIndex).getTime());
        	}
            return object;
        }
    }

    /**
     * @see TypeHandler#getResult(CallableStatement, int)
     */
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object object = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
        	if(object instanceof java.sql.Date){
        		object = new Date(cs.getTimestamp(columnIndex).getTime());
        	}
            return object;
        }
    }

    /**
     * @see TypeHandler#valueOf(String)
     */
    public Object valueOf(String s) {
        return s;
    }
}
