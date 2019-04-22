package tikuka.olt.org.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tikuka.olt.org.domains.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role> {

}
