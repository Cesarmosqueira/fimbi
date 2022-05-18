package online.fimbi.Entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bond")
@Data
@NoArgsConstructor

public class Bond {
	@Id
	Long id;

	// has only one issuer
	@ManyToOne
	@JoinColumn(name = "issuer_id", insertable = false, updatable = false)
	private Issuer issuer;

	// can have none or many relations with users
	// as long as is less than `splits`
	@OneToMany
	@JoinColumn(name = "bond_id") // we need to duplicate the physical information
	@Size(min = 0, max = 100)
	private Set<UserxBond> relations;

	@Column(nullable = false)
	@Max(100)
	Long splits;

	@Column(nullable = false)
	Long issuer_id;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	Date emission_date;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	Date due_date;

	@Column(nullable = false)
	Long nominal_value;

	@Column(nullable = false)
	float interest_rate;

	@Column(nullable = false)
	float capitalization_rate;

	@Column(nullable = false, length = 32)
	String external_interest_rate;

	@Column(nullable = false)
	int available;
}
