package online.fimbi.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bond")
@Data
@NoArgsConstructor

public class Bond {
	@Id
	Long id;

	@Column(nullable = false)
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
