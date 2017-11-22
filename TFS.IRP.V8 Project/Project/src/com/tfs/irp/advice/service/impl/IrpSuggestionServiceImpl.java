package com.tfs.irp.advice.service.impl;



import java.sql.SQLException;

import com.tfs.irp.advice.dao.IrpSuggestionDAO;
import com.tfs.irp.advice.entity.IrpSuggestion;
import com.tfs.irp.advice.service.IrpSuggestionService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.TableIdUtil;

/**
 * 
 * @author Administrator
 *
 */
public class IrpSuggestionServiceImpl implements IrpSuggestionService {
	
	private IrpSuggestionDAO irpSuggestionDAO;
	
	public IrpSuggestionDAO getIrpSuggestionDAO() {
		return irpSuggestionDAO;
	}

	public void setIrpSuggestionDAO(IrpSuggestionDAO irpSuggestionDAO) {
		this.irpSuggestionDAO = irpSuggestionDAO;
	}

	@Override
	public void savesuggestion(IrpSuggestion irpsuggestion) {
		try {
			Long tableid = TableIdUtil.getNextId(IrpSuggestion.TABLE_NAME);
			irpsuggestion.setSuggestionid(tableid);
			irpSuggestionDAO.insert(irpsuggestion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
