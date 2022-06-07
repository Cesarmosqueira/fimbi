package online.fimbi.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import online.fimbi.Entities.UserxBond;

@Repository
public interface UserxbondRepository extends JpaRepository<UserxBond, Long> {

	@Query(value = "SELECT COUNT(*) FROM userxbond WHERE bond_id=:bond_id", nativeQuery = true)
	int getBondOccurrences(Long bond_id);

	@Query(value = "SELECT * FROM userxbond fetch first :size rows only", nativeQuery = true)
	List<UserxBond> get_lastest_purchases(int size);

}
