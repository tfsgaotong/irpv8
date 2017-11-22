package com.tfs.irp.assewarn.service;

import java.sql.SQLClientInfoException;
import java.util.List;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.tfs.irp.assewarn.entity.IrpAssewarn;
import com.tfs.irp.assewarn.entity.IrpAssewarnExample;

public interface IrpAssewarnService {

	/**查询所有通知类型
	 * @param example
	 * @return
	 */
	List<IrpAssewarn> selectByExample(IrpAssewarnExample example) throws Exception;
	IrpAssewarn SelectByPrimaryKey(Long id) throws Exception;
}
