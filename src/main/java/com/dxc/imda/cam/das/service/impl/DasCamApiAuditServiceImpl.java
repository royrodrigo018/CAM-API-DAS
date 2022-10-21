package com.dxc.imda.cam.das.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.imda.cam.das.dao.DasCamApiAuditDao;
import com.dxc.imda.cam.das.entity.DasCamApiAudit;
import com.dxc.imda.cam.das.service.DasCamApiAuditService;

@Service
public class DasCamApiAuditServiceImpl implements DasCamApiAuditService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DasCamApiAuditDao dasCamApiAuditDao;

	@Override
	public int saveOrUpdate(DasCamApiAudit tempDasCamApiAudit) {
		int result = 0;
		try {
			DasCamApiAudit dasCamApiAudit = dasCamApiAuditDao.save(tempDasCamApiAudit);
			result = dasCamApiAudit != null ? 1 : 0;
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}		
		return result;
	}
}
