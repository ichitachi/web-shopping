package tikuka.olt.org.services.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tikuka.olt.org.criteria.user.RoleCriteria;
import tikuka.olt.org.dto.user.RoleDTO;

import javax.management.InstanceAlreadyExistsException;
import java.util.Optional;

public interface RoleService {

    Page<RoleDTO> findRoleByCriteria(RoleCriteria criteria, Pageable pageable);

    void createOrUpdateRole(RoleDTO roleDTO) throws InstanceAlreadyExistsException;

    Optional<RoleDTO> findRole(Long id);

    void delete(Long id);

}
