package book.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Collection<User> users;

	@ManyToMany
	@JoinTable(name = "roles_privileges",//
			joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),//
			inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private Collection<Privilege> privileges;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return this.users;
	}

	public void setUsers(final Collection<User> users) {
		this.users = users;
	}

	public Collection<Privilege> getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(final Collection<Privilege> privileges) {
		this.privileges = privileges;
	}

}
