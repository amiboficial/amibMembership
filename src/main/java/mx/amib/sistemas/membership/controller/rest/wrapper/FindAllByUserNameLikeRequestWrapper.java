package mx.amib.sistemas.membership.controller.rest.wrapper;

public class FindAllByUserNameLikeRequestWrapper extends FindAllRequestWrapper{
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
