package com.callor.score.service.impl;

import com.callor.score.model.AdminVO;
import com.callor.score.service.AdminService;

public class AdminServiceImplV1 implements AdminService {

	@Override
	public AdminVO login(String name, String password) {
		
		if(name.equalsIgnoreCase("korea") && password.equals("1234")) {
			
			AdminVO adminVO = new AdminVO();
			adminVO.setAdmin_name(name);
			return adminVO;
		}
		return null;
	}
}
