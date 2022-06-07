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
@Table(name = "userxbond")
@Data
@NoArgsConstructor
public class UserxBond {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column
	Long bond_id;

	@Column
	Long user_id;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	Date purchase_date;
}
