package book.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 50)
	private String userName;

	@Column(nullable = false, length = 200)
	private String password;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<UserRole> roles;

	public User() {
		// for hibernate only
	}

	public User(final Long id, final String user, final String password) {
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

	public Collection<UserRole> getRoles() {
		return this.roles;
	}

	public void setRoles(final Collection<UserRole> roles) {
		this.roles = roles;
	}

}
