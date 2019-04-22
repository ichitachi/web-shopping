package tikuka.olt.org.mapper.user;

import org.mapstruct.Mapper;
import tikuka.olt.org.domains.user.Role;
import tikuka.olt.org.dto.user.RoleDTO;
import tikuka.olt.org.mapper.EntityMapper;

@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<RoleDTO,Role> {
}
