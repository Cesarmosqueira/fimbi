package online.fimbi.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import online.fimbi.Entities.Issuer;

@Repository
public interface IssuerRepository extends JpaRepository<Issuer, Long> {
	@Query(value = "SELECT i.id FROM issuer i WHERE i.market_identifier=:market_identifier", nativeQuery = true)
	List<Long> identifier_exists(String market_identifier);
}
