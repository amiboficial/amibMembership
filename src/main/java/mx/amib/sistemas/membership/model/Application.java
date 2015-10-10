package mx.amib.sistemas.membership.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="t002_c_application")
public class Application implements Serializable {
	
	private static final long serialVersionUID = -8881379458558981316L;
	
	@Id
	@Column(name="id_application")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="tx_uuid")
	private String uuid;
	
	@Column(name="tx_name")
	private String name;
	@Column(name="tx_loweredname")
	private String nameLowercase;
	
	@Column(name="st_active")
	private boolean isActive;
	
	@OneToMany(mappedBy="application", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Role> roles;
	@OneToMany(mappedBy="application", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Path> paths;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameLowercase() {
		return nameLowercase;
	}
	public void setNameLowercase(String nameLowercase) {
		this.nameLowercase = nameLowercase;
	}
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Path> getPaths() {
		return paths;
	}
	public void setPaths(Set<Path> paths) {
		this.paths = paths;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"id\":\"").append(id).append("\",\"uuid\":\"")
				.append(uuid).append("\",\"name\":\"").append(name)
				.append("\",\"nameLowercase\":\"").append(nameLowercase)
				.append("\",\"isActive\":\"").append(isActive)
				.append("\",\"roles\":").append(roles)
				.append(",\"paths\":").append(paths).append("}");
		return builder.toString();
	}
}
