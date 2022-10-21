package com.dxc.imda.cam.das.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_USR_ROLE_ACCESS")
public class UserRoleAccess implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="USR_ROLE_ACCESS_ID_GENERATOR", sequenceName="SEQ_T_USR_ROLE_ACCESS", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USR_ROLE_ACCESS_ID_GENERATOR")
	@Column(name = "PK_ID")
	private Long id;
	
	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="USR_ROLE_ID")
	private UserRole userRole;
	
	//bi-directional many-to-one association to UserFunc
	@ManyToOne
	@JoinColumn(name="USR_FUNC_MOD_ID")
	private UserFuncMode userFuncMode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserFuncMode getUserFuncMode() {
		return userFuncMode;
	}

	public void setUserFuncMode(UserFuncMode userFuncMode) {
		this.userFuncMode = userFuncMode;
	}	

	@Override
	public int hashCode() {
		return Objects.hash(userFuncMode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleAccess other = (UserRoleAccess) obj;
		return Objects.equals(userFuncMode, other.userFuncMode);
	}

	@Override
	public String toString() {
		return "UserRoleAccess [id=" + id + ", userRole=" + userRole + ", userFuncMode=" + userFuncMode + "]";
	}	
}
