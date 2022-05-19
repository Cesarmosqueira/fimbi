package online.fimbi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online.fimbi.Entities.Bond;

@Repository
public interface BondRepository extends JpaRepository<Bond, Long> {
}
