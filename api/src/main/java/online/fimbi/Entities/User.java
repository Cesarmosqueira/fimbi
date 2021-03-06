package online.fimbi.Entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import online.fimbi.Dto.UserDto;

@Entity
@Table(name = "_user")
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 32, unique = true)
	private String username;

	@Column(nullable = false, length = 32)
	private String password;

	@Column(nullable = false, length = 256)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date birth_date;

	@Column(length = 65536)
	private String image_url;

	@OneToMany
	@JoinColumn(name = "user_id") // we need to duplicate the physical information
	@Size(min = 0, max = 100)
	private Set<UserxBond> relations;

	public User(UserDto userDto) {
		this.username = userDto.getUsername().toLowerCase();
		this.password = userDto.getPassword();
		this.email = userDto.getEmail();
		this.birth_date = userDto.getBirth_date();
		this.image_url = userDto.getImage_url();
	}

}
