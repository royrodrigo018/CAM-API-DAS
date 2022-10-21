package com.dxc.imda.cam.das.service;

import org.springframework.stereotype.Service;

import com.dxc.imda.cam.das.entity.DasCamApiAudit;

@Service
public interface DasCamApiAuditService {
		
	public int saveOrUpdate(DasCamApiAudit dasCamApiAudit);

}
