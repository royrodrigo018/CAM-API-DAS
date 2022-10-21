package com.dxc.imda.cam.das.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dxc.imda.cam.common.model.ExtensionCamGroup;
import com.dxc.imda.cam.common.model.GroupInfo;
import com.dxc.imda.cam.common.model.Member;
import com.dxc.imda.cam.common.model.Meta;
import com.dxc.imda.cam.common.model.UpdateInfo;
import com.dxc.imda.cam.common.util.DateUtil;
import com.dxc.imda.cam.das.entity.UserOrgRole;
import com.dxc.imda.cam.das.entity.UserRole;
import com.dxc.imda.cam.das.entity.UserRoleAccess;

@Component
public class DasGroupInfoMapper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public GroupInfo convertUserRoleToJSON(UserRole userRole) {
		GroupInfo groupInfo = new GroupInfo();
		List<String> schemas = getGroupSchemas();		
		try {
			groupInfo.setId(userRole.getRoleName());
			groupInfo.setExternalId(userRole.getRoleName());
			groupInfo.setDisplayName(userRole.getRoleName());
			
			Meta meta = getMeta(userRole);
			List<Member> members = getMemberList(userRole);					
			ExtensionCamGroup extensionCamGroup = getExtensionCamGroup(userRole);
			
			groupInfo.setExtensionCamGroup(extensionCamGroup);
			groupInfo.setMembers(members);
			groupInfo.setMeta(meta);
			groupInfo.setSchemas(schemas); //TODO: schemas
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
			groupInfo = null;
		}
		return groupInfo;
	}
	
	public UpdateInfo convertUpdateInfoToJSON(UserOrgRole userOrgRole) {
		UpdateInfo updateInfo = new UpdateInfo();
		try {
			updateInfo.setGroupId(userOrgRole.getUserRole().getRoleName());
			updateInfo.setUserId(userOrgRole.getUserOrg().getUserProfile().getUserId());
			updateInfo.setStatus(userOrgRole.getStatus());
			updateInfo.setLastUpdBy(userOrgRole.getLastUpdBy());
			updateInfo.setLastUpdDate(userOrgRole.getLastUpdTime().toString());
			updateInfo.setBlnSuccess(true);
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
			updateInfo = null;
		}
		return updateInfo;		
	}
	
	private List<String> getGroupSchemas(){
		List<String> schemas = new ArrayList<String>();
		schemas.add("urn:ietf:params:scim:schemas:core:2.0:Group");
		schemas.add("urn:ietf:params:scim:schemas:extension:cam:2.0:Group");
		return schemas;
	}
	
	private Meta getMeta(UserRole userRole) {
		Meta meta = new Meta();
		meta.setResourceType("Group");
		meta.setCreated("");
		meta.setLastModified("");
		DateUtil dateUtil = new DateUtil();
		for(UserOrgRole userOrgRole: userRole.getUserOrgRoles()) {
			if (userOrgRole.getCreatedTime() != null) {
				meta.setCreated(dateUtil.convertDateToUTC(userOrgRole.getCreatedTime()));
			}
			if (userOrgRole.getLastUpdTime() != null) {
				meta.setLastModified(dateUtil.convertDateToUTC(userOrgRole.getLastUpdTime()));
			}
		}
		return meta;
	}
	
	private List<Member> getMemberList(UserRole userRole){
		List<Member> members = new ArrayList<>();
		for(UserOrgRole userOrgRole: userRole.getUserOrgRoles()) {
			if ("A".equalsIgnoreCase(userOrgRole.getStatus())){
				Member member = new Member();			
				member.setValue(userOrgRole.getUserOrg().getUserProfile().getUserId());
				member.set$ref("");
				member.setType("User");
				members.add(member);
			}			
		}
		return members;		
	}
	
	private ExtensionCamGroup getExtensionCamGroup(UserRole userRole) {
		StringBuilder sb = new StringBuilder();
		sb.append(userRole.getRoleName()+" "+ "Role");
		sb.append("=");
		String groupAccessRightInfo = "";
		//logger.info("userRole.getUserRoleAccesses(): " + userRole.getUserRoleAccesses());
		for (UserRoleAccess userRoleAccess: userRole.getUserRoleAccesses()) {			
			sb.append(userRoleAccess.getUserFuncMode().getFuncDesc());
			sb.append(", ");
		}
		//logger.info("sb.toString(): " + sb.toString());
		//logger.info("sb.toString().endsWith(\",\"): " + sb.toString().trim().endsWith(","));			
		if (sb.toString().trim().endsWith(",")) {
			groupAccessRightInfo = sb.toString().trim().substring(0, sb.toString().trim().length() - 1);
			//logger.info("groupAccessRightInfo: " + groupAccessRightInfo);
		}
		//TODO: remove last character (,)				
		ExtensionCamGroup extensionCamGroup = new ExtensionCamGroup();
		extensionCamGroup.setGroupAccessRightInfo(groupAccessRightInfo);
		return extensionCamGroup;
	}
}
