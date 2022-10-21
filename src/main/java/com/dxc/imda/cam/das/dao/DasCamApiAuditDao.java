package com.dxc.imda.cam.das.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.imda.cam.das.entity.DasCamApiAudit;


@Repository
public interface DasCamApiAuditDao extends JpaRepository<DasCamApiAudit, Long> {
	
}
