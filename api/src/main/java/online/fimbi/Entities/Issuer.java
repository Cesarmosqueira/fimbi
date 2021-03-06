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
import online.fimbi.Dto.IssuerDto;

@Entity
@Table(name = "issuer")
@Data
@NoArgsConstructor
public class Issuer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 8, unique = true)
	private String market_identifier; // (ISO 10383)

	@Column(nullable = false, length = 8192)
	private String description;

	@Temporal(TemporalType.DATE)
	private Date date_joined;

	@Column(length = 65536)
	private String image_url;

	@OneToMany
	@JoinColumn(name = "issuer_id") // we need to duplicate the physical information
	@Size(min = 0, max = 16)
	private Set<Bond> bonds;

	public Issuer(IssuerDto issuerDto) {
		this.market_identifier = issuerDto.getMarket_identifier();
		this.description = issuerDto.getDescription();
		this.date_joined = new Date();
		this.image_url = issuerDto.getImage_url();
	}

}
