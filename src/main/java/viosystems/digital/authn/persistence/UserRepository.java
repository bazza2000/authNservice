package viosystems.digital.authn.persistence;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import viosystems.digital.authn.domain.UserProfile;


import java.util.Optional;


public interface UserRepository extends CrudRepository<UserProfile, String> {

	@Query("select u from UserProfile u where username = :username ")
	Optional<UserProfile> findByUsername(@Param("username") String username);
}
