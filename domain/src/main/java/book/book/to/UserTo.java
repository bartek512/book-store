package book.book.to;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserTo {

	private Long id;

	private String userName;

	@JsonIgnore
	private String password;

	public UserTo() {
	}

	public UserTo(final Long id, final String user, final String password) {
		this.id = id;
		this.userName = user;
		this.password = password;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(final String user) {
		this.userName = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
