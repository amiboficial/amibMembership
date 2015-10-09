package mx.amib.sistemas.membership.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Role
 *
 */

@IdClass(RoleId.class)
@Entity
@Table(name="t003_c_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 7012770554478630299L;
	
	@Id
	@Column(name="id_application")
	private long idApplication;
	@Id
	@Column(name="seq_role")
	private long numberRole;
	
	@Column(name="tx_name")
	private String name;
	@Column(name="tx_description")
	private String description;
	@Column(name="st_active")
	private boolean active;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_application", insertable=false, updatable=false)
	private Application application;
	
	@ManyToMany(mappedBy="roles")
	private Set<User> users;
	
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"idApplication\":\"").append(idApplication)
				.append("\",\"numberRole\":\"").append(numberRole)
				.append("\",\"name\":\"").append(name)
				.append("\",\"description\":\"").append(description)
				.append("\",\"active\":\"").append(active)
				.append("\",\"application\":\"").append(application.hashCode())
				.append("\"}");
		return builder.toString();
	}
	
}
