package com.dxc.imda.cam.das.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_USR_FUNC_MOD")
public class UserFuncMode implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="USR_FUNC_MOD_ID_GENERATOR", sequenceName="SEQ_T_USR_FUNC_MOD", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USR_FUNC_MOD_ID_GENERATOR")
	@Column(name = "PK_ID")
	private Long id;
	
	@Column(name="FUNC_CODE")
	private String funcCode;
	
	@Column(name="FUNC_DESC")
	private String funcDesc;
	
	//bi-directional many-to-one association to UserRoleAccess
	@OneToMany(mappedBy="userFuncMode")
	private Set<UserRoleAccess> userRoleAccesses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getFuncDesc() {
		return funcDesc;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}

	public Set<UserRoleAccess> getUserRoleAccesses() {
		return userRoleAccesses;
	}

	public void setUserRoleAccesses(Set<UserRoleAccess> userRoleAccesses) {
		this.userRoleAccesses = userRoleAccesses;
	}

	@Override
	public int hashCode() {
		return Objects.hash(funcCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFuncMode other = (UserFuncMode) obj;
		return Objects.equals(funcCode, other.funcCode);
	}

	@Override
	public String toString() {
		return "UserFuncMode [id=" + id + ", funcDesc=" + funcDesc + "]";
	}		
}
