package com.dxc.imda.cam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dxc.imda.cam.common.constant.Constants;
import com.dxc.imda.cam.common.model.GroupInfo;
import com.dxc.imda.cam.common.model.UpdateInfo;
import com.dxc.imda.cam.common.model.UserInfo;
import com.dxc.imda.cam.das.service.DasUserOrgRoleService;
import com.dxc.imda.cam.das.service.DasUserProfileService;
import com.dxc.imda.cam.das.service.DasUserRoleService;

@SpringBootApplication
@Component
@EnableTransactionManagement
public class DasCamApplication extends SpringBootServletInitializer implements CommandLineRunner{
	
	private static Logger logger = LoggerFactory.getLogger(DasCamApplication.class);
	
	@Autowired
	DasUserProfileService dasUserProfileService;
	
	@Autowired
	DasUserRoleService dasUserRoleService;
	
	@Autowired
	DasUserOrgRoleService dasUserOrgRoleService;

	public static void main(String[] args) {
		logger.info("********** STARTING THE APPLICATION ********** ");
		SpringApplication.run(DasCamApplication.class, args);
		logger.info("********** APPLICATION END ********** ");
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DasCamApplication.class);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		String userId = "S0523099C";
		String rolaName1 = "IMDA Approver - ACE";
		String rolaName2 = "IMDA";
		
		String roleDesc1 = "Public User";
		String roleDesc2 = "Public";
		String status1 = "A";
		String status2 = "I";
		
		Pageable pageable = PageRequest.of(0, 20);
		Page<UserInfo> userInfos = null;
		Page<GroupInfo> groupInfos = null;
				
		UserInfo userInfo = dasUserProfileService.findByUserId(userId);
		userInfos = dasUserProfileService.findAll(pageable);
		userInfos = dasUserProfileService.findByRoleNameEquals(rolaName1, pageable);
		userInfos = dasUserProfileService.findByRoleNameContaining(rolaName2, pageable);
		
		userInfos = dasUserProfileService.findByRoleDescEquals(roleDesc1, pageable);
		userInfos = dasUserProfileService.findByRoleDescContaining(roleDesc2, pageable);
				
		GroupInfo groupInfo = dasUserRoleService.findByRoleName(rolaName1);
		groupInfos = dasUserRoleService.findAll(pageable);
		groupInfos = dasUserRoleService.findByRoleNameEquals(rolaName1, pageable);
		groupInfos = dasUserRoleService.findByRoleNameContaining(rolaName2, pageable);
		
		groupInfos = dasUserRoleService.findByRoleDescEquals(roleDesc1, pageable);
		groupInfos = dasUserRoleService.findByRoleDescContaining(roleDesc2, pageable);		
		*/
//		UserInfo userInfo = dasUserProfileService.updateUser(userId, status1);
//		UpdateInfo updateInfo = dasUserProfileService.removeUser(userId, status2);
//		
//		updateInfo = dasUserOrgRoleService.updateUserOrgRole("System Admin", userId, Constants.INACTIVE_STATUS);	
	}
}
