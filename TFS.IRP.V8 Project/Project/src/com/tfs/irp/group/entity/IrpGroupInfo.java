package com.tfs.irp.group.entity;

import java.util.List;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.user.entity.IrpUser;

public class IrpGroupInfo extends IrpBaseObj{
   
    private Long gid;
    private List<IrpUser> users;
    
	public List<IrpUser> getUsers() {
		return users;
	}
	public void setUsers(List<IrpUser> users) {
		this.users = users;
	}
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getIdFieldName() {
		// TODO Auto-generated method stub
		return null;
	}
}