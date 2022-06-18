package online.fimbi.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import online.fimbi.Entities.Bond;

@Repository
public interface BondRepository extends JpaRepository<Bond, Long> {
	@Query(value = "SELECT * FROM bond b WHERE b.available=1 ORDER BY b.emission_date DESC  fetch first :size rows only", nativeQuery = true)
	List<Bond> latest_bonds(int size);

	// @Modifying
	// @Query(value = "UPDATE bond SET splits = splits - 1 WHERE id=:bond_id",
	// nativeQuery = true)
	// void update_splits(Long bond_id);

	@Query(value = "SELECT b.id, b.available, b.capitalization_rate, b.due_date, b.emission_date, b.external_interest_rate, b.interest_rate, b.issuer_id, b.nominal_value, b.splits  FROM issuer i JOIN bond b ON b.issuer_id = i.id WHERE i.market_identifier=:identifier", nativeQuery = true)
	List<Bond> list_bonds_by_issuer(String identifier);

	@Modifying
	@Query(value = "UPDATE bond SET available = 1 WHERE id=:bond_id", nativeQuery = true)
	void enable_bond(Long bond_id);

	@Modifying
	@Query(value = "UPDATE bond SET available = 0 WHERE id=:bond_id", nativeQuery = true)
	void disable_bond(Long bond_id);

	@Query(value = "SELECT b.id, b.available, b.capitalization_rate, b.due_date, b.emission_date, b.external_interest_rate, b.interest_rate, b.issuer_id, b.nominal_value, b.splits FROM userxbond ub JOIN bond b ON ub.bond_id = b.id WHERE ub.user_id=:user_id", nativeQuery = true)
	List<Bond> bondsByUserId(Long user_id);

	@Query(value = "SELECT b.id, b.available, b.capitalization_rate, b.due_date, b.emission_date, b.external_interest_rate, b.interest_rate, b.issuer_id, b.nominal_value, b.splits FROM userxbond ub JOIN bond b ON ub.bond_id = b.id WHERE b.issuer_id=:issuer_id", nativeQuery = true)
	List<Bond> bondsByIssuerId(Long issuer_id);

}
