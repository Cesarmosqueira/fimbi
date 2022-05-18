package online.fimbi.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * A user can hold multiple instances of the same bond.
 * The bond has a maximum of splits so as long as the 
 * lenght of the split is not exceeded by the ammount
 * of instances owned by users, people can still buy.
 */

@Entity
@Table(name = "bondxuser")
@Data
@NoArgsConstructor
public class BondxUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Long bond_id;

	@Column(nullable = false)
	private Long user_id;
}
