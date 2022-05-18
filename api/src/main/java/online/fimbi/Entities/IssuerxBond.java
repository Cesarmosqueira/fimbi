package online.fimbi.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * An issuer can issue multiple bonds. 
 * Issuer ID is not unique in this table.
 * Bond ID is unique in this table.
 */

@Entity
@Table(name = "issuerxbond")
@Data
@NoArgsConstructor
public class IssuerxBond {
	@Id
	@Column(name = "bond_id")
	private Long bond_id;

	@Column(nullable = false)
	private Long issuer_id;
}
