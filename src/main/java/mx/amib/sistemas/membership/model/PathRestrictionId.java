package mx.amib.sistemas.membership.model;

import java.io.Serializable;

public class PathRestrictionId implements Serializable {

	private static final long serialVersionUID = 3766190231201399075L;
	private long idApplication;
	private long numberRole;
	private long numberPath;
	
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
	public long getNumberPath() {
		return numberPath;
	}
	public void setNumberPath(long numberPath) {
		this.numberPath = numberPath;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idApplication ^ (idApplication >>> 32));
		result = prime * result + (int) (numberPath ^ (numberPath >>> 32));
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
		PathRestrictionId other = (PathRestrictionId) obj;
		if (idApplication != other.idApplication)
			return false;
		if (numberPath != other.numberPath)
			return false;
		if (numberRole != other.numberRole)
			return false;
		return true;
	}
	
	

}
