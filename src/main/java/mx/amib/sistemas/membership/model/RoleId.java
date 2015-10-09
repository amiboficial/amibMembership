package mx.amib.sistemas.membership.model;

import java.io.Serializable;

public class RoleId implements Serializable {
	
	private static final long serialVersionUID = 8578344615418685167L;
	private long idApplication;
	private long numberRole;
	
	public long getIdApplication() {
		return idApplication;
	}
	public void setIdApplication(long idApplication) {
		this.idApplication = idApplication;
	}
	public long getNumberRole() {
		return numberRole;
	}
	public void setNumberRole(long numberRole) {
		this.numberRole = numberRole;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idApplication ^ (idApplication >>> 32));
		result = prime * result + (int) (numberRole ^ (numberRole >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleId other = (RoleId) obj;
		if (idApplication != other.idApplication)
			return false;
		if (numberRole != other.numberRole)
			return false;
		return true;
	}
	
}
