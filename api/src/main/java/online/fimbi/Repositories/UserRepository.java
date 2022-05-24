package online.fimbi.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import online.fimbi.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT u.password FROM _user u WHERE u.username=:username", nativeQuery = true)
	Optional<String> get_password_username(String username);

	@Query(value = "SELECT u.password FROM _user u WHERE u.email=:email", nativeQuery = true)
	Optional<String> get_password_email(String email);
}
