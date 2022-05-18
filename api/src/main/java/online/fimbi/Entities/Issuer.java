package online.fimbi.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issuer")
@Data
@NoArgsConstructor
public class Issuer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 4)
	private String market_identifier; // (ISO 10383)

	@Column(nullable = false, length = 1024)
	private String description;

	@Temporal(TemporalType.DATE)
	private Date date_joined;

	@Column(length = 1024)
	private String image_url;
}
