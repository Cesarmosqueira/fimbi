package online.fimbi.Entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
	@Id
	private Long id;

	@Column(nullable = false, length = 16)
	private String username;

	@Column(nullable = false, length = 32)
	private String password;

	@Column(nullable = false, length = 256)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date birth_date;

	@Column(length = 1024)
	private String image_url;

	@OneToMany
	@JoinColumn(name = "user_id") // we need to duplicate the physical information
	@Size(min = 0, max = 100)
	private Set<UserxBond> relations;

}
