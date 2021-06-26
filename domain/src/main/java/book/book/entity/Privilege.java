package book.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@ManyToMany(mappedBy = "privileges")
	private Collection<UserRole> roles;

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

	public Collection<UserRole> getRoles() {
		return this.roles;
	}

	public void setRoles(final Collection<UserRole> roles) {
		this.roles = roles;
	}

}
