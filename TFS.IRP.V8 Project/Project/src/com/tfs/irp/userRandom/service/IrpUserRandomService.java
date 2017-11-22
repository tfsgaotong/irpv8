package com.tfs.irp.userRandom.service;

import java.util.List;

import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.userRandom.entity.IrpUserrandom;

public interface IrpUserRandomService {

	List<IrpUserrandom> findUserRandom(long _userid);

	int addUserRandom(IrpUser irpuser, String randoms);

	int delUserRandom(long _id);


}
