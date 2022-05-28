package online.fimbi.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import online.fimbi.Entities.Bond;

@Repository
public interface BondRepository extends JpaRepository<Bond, Long> {
	@Query(value = "SELECT * FROM bond b ORDER BY b.emission_date DESC fetch first :size rows only", nativeQuery = true)
	List<Bond> latest_bonds(int size);
}
