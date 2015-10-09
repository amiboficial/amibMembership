package mx.amib.sistemas.membership.model;

import java.io.Serializable;

import javax.persistence.*;

@IdClass(PathId.class)
@Entity
@Table(name="t004_c_path")
public class Path implements Serializable {
	
	private static final long serialVersionUID = 7012770554478630297L;
	
	@Id
	@Column(name="id_application")
	private long idApplication;
	@Id
	@Column(name="seq_path")
	private long numberPath;
	
	@Column(name="tx_path")
	private String path;
	@Column(name="tx_loweredpath")
	private String pathLowercase;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_application", insertable=false, updatable=false)
	private Application application;
	
	public long getIdApplication() {
		return idApplication;
	}
	public void setIdApplication(long idApplication) {
		this.idApplication = idApplication;
	}
	public long getNumberPath() {
		return numberPath;
	}
	public void setNumberPath(long numberPath) {
		this.numberPath = numberPath;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPathLowercase() {
		return pathLowercase;
	}
	public void setPathLowercase(String pathLowercase) {
		this.pathLowercase = pathLowercase;
	}
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"idApplication\":\"").append(idApplication)
				.append("\",\"numberPath\":\"").append(numberPath)
				.append("\",\"path\":\"").append(path)
				.append("\",\"pathLowercase\":\"").append(pathLowercase)
				.append("\",\"application\":\"").append(application.hashCode())
				.append("\"}");
		return builder.toString();
	}
}
