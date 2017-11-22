package com.tfs.irp.base;

public abstract class IrpBaseObj implements Cloneable {
	public abstract Long getId();

	public abstract String getName();
	
	public abstract String getTableName();
	
	public abstract String getIdFieldName();
}
