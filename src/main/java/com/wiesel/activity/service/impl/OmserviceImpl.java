package com.wiesel.activity.service.impl;

import java.util.List;

import org.activiti.engine.spi.identity.IdentityEnum;
import org.activiti.engine.spi.identity.OMService;
import org.activiti.engine.spi.identity.Participator;
import org.activiti.engine.spi.identity.ParticipatorDescriptor;
import org.springframework.stereotype.Service;

import com.bosssoft.platform.common.lang.data.Page;
import com.bosssoft.platform.common.lang.data.Searcher;

/** 
*
* @ClassName   类名：OmserviceImpl 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年12月13日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2018年12月13日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/
@Service
public class OmserviceImpl implements OMService {

	@Override
	public Participator getParticipatorInfo(String var1, IdentityEnum var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Participator> getUsers(Page var1, Searcher var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Participator> getUsersByParticipator(Page var1, String var2, IdentityEnum var3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParticipatorDescriptor> getParticipatorTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Participator> getParticipatorList(Page<Participator> var1, Searcher var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Participator> getCandidateTree(String var1, Page var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getIdentityGroupsOfUser(String var1, IdentityEnum var2) {
		// TODO Auto-generated method stub
		return null;
	}

}
