package mx.amib.sistemas.membership.model;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="t001_t_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = -356129001606219238L;
	
	@Id
	@Column(name="id_user")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="tx_uuid")
	private String uuid;
	
	@Column(name="tx_username")
	private String userName;
	@Column(name="tx_loweredusername")
	private String userNameLowercase;
	@Column(name="fh_lastactivitydate")
	private Date lastActivity;
	@Column(name="tx_password")
	private String password;
	@Column(name="tx_pwdformat")
	private String passwordFormat;
	@Column(name="tx_pwdsalt")
	private String passwordSalt;
	
	@Column(name="tx_email")
	private String email;
	@Column(name="tx_loweredemail")
	private String emailLowercase;
	
	@Column(name="tx_pwdquestion")
	private String passwordQuestion;
	@Column(name="tx_pwdanswer")
	private String passwordAnswer;
	
	@Column(name="st_isapproved")
	private boolean isApproved;
	@Column(name="fh_lastlogindate")
	private Date lastLogin;
	@Column(name="fh_lastpwdchangedate")
	private Date lastPasswordChange;
	
	@Column(name="st_islockedout")
	private boolean isLockedOut;
	@Column(name="fh_lastlockedoutdate")
	private Date lastLockedOut;
	
	@Column(name="nu_failedattempts")
	private int failedAttempts;
	@Column(name="nu_failedanswerattempts")
	private int failedAnswerAttempts;
	
	@Column(name="tx_comment")
	private String comment;
	
	@Column(name="fh_createdate")
	private Date createdDate;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "t005_t_userinrole",
			joinColumns={@JoinColumn(name="id_user", referencedColumnName="id_user")},
			inverseJoinColumns={@JoinColumn(name="id_application", referencedColumnName="id_application")
								,@JoinColumn(name="seq_role", referencedColumnName="seq_role")}
	)
	private Set<Role> roles;
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameLowercase() {
		return userNameLowercase;
	}

	public void setUserNameLowercase(String userNameLowercase) {
		this.userNameLowercase = userNameLowercase;
	}

	public Date getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordFormat() {
		return passwordFormat;
	}

	public void setPasswordFormat(String passwordFormat) {
		this.passwordFormat = passwordFormat;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailLowercase() {
		return emailLowercase;
	}

	public void setEmailLowercase(String emailLowercase) {
		this.emailLowercase = emailLowercase;
	}

	public String getPasswordQuestion() {
		return passwordQuestion;
	}

	public void setPasswordQuestion(String passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}

	public String getPasswordAnswer() {
		return passwordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastPasswordChange() {
		return lastPasswordChange;
	}

	public void setLastPasswordChange(Date lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	public boolean isLockedOut() {
		return isLockedOut;
	}

	public void setLockedOut(boolean isLockedOut) {
		this.isLockedOut = isLockedOut;
	}

	public Date getLastLockedOut() {
		return lastLockedOut;
	}

	public void setLastLockedOut(Date lastLockedOut) {
		this.lastLockedOut = lastLockedOut;
	}

	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public int getFailedAnswerAttempts() {
		return failedAnswerAttempts;
	}

	public void setFailedAnswerAttempts(int failedAnswerAttempts) {
		this.failedAnswerAttempts = failedAnswerAttempts;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
