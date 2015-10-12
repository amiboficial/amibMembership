package mx.amib.sistemas.membership.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PathRestriction
 *
 */
@IdClass(PathRestrictionId.class)
@Entity
@Table(name="t006_t_pathrestric")
public class PathRestriction implements Serializable {

	private static final long serialVersionUID = 7372182994436751225L;
	
	@Id
	@Column(name="id_application")
	private long idApplication;
	@Id
	@Column(name="seq_role")
	private long numberRole;
	@Id
	@Column(name="seq_path")
	private long numberPath;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="id_application",referencedColumnName="id_application", insertable=false, updatable=false),
		@JoinColumn(name="seq_path",referencedColumnName="seq_path", insertable=false, updatable=false)
	})
	private Path path;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="id_application",referencedColumnName="id_application", insertable=false, updatable=false),
		@JoinColumn(name="seq_role",referencedColumnName="seq_role", insertable=false, updatable=false)
	})
	private Role role;
	
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
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
   
	
	
}
