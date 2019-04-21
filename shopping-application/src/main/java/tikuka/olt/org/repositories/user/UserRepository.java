package tikuka.olt.org.repositories.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tikuka.olt.org.domains.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @EntityGraph(value = "User.graphentity", type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"userRoles", "userGroup"})
    @Query("FROM User u WHERE u.email =:param or u.username =:param")
    Optional<User> findOneByUsernameOrEmail(@Param("param") String param);
}
