package tikuka.olt.org.services.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tikuka.olt.org.criteria.user.RoleCriteria;
import tikuka.olt.org.domains.user.Permission;
import tikuka.olt.org.domains.user.Role;
import tikuka.olt.org.domains.user.RoleACL;
import tikuka.olt.org.domains.user.Role_;
import tikuka.olt.org.dto.user.RoleDTO;
import tikuka.olt.org.errors.DataConstraintViolationException;
import tikuka.olt.org.mapper.user.RoleMapper;
import tikuka.olt.org.repositories.user.RoleACLRepository;
import tikuka.olt.org.repositories.user.RoleRepository;
import tikuka.olt.org.services.user.RoleService;
import tikuka.olt.org.utils.QueryService;

import javax.management.InstanceAlreadyExistsException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl extends QueryService implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleACLRepository roleACLRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page<RoleDTO> findRoleByCriteria(RoleCriteria criteria, Pageable pageable) {
        final Specification<Role> specification = createSpecification(criteria);
        return roleRepository.findAll(specification, pageable).map(roleMapper::toDto);
    }

    private Specification<Role> createSpecification(RoleCriteria criteria) {
        Specification<Role> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getRole() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRole(), Role_.role));
            }
        }
        return specification;
    }

    @Override
    public void createOrUpdateRole(RoleDTO roleDTO) throws InstanceAlreadyExistsException {
        if(roleDTO.getPermissionIds() != null && roleDTO.getPermissionIds().size() > 0){
            Optional<Long> optional = roleACLRepository.checkRoleExists(roleDTO.getPermissionIds(),new Long(roleDTO.getPermissionIds().size()),roleDTO.getRoleId());
            if(optional.isPresent()){
                throw new InstanceAlreadyExistsException("This role is already exists");
            }
            Role role = null;
            if (roleDTO != null && roleDTO.getRoleId() != null) {
                role = roleRepository.findById(roleDTO.getRoleId()).get();
                role.setRole(roleDTO.getRole());
                role.setDescription(roleDTO.getDescription());
                role.setModifiedDate(new Timestamp(System.currentTimeMillis()));
                role = roleRepository.save(role);

                List<RoleACL> oldRoleACL = role.getRoleACLS() != null && role.getRoleACLS().size() > 0 ? new ArrayList<>(role.getRoleACLS())
                        : new ArrayList<>();

                if (roleDTO.getPermissionIds() != null && roleDTO.getPermissionIds().size() > 0) {
                    for (Long id : roleDTO.getPermissionIds()) {
                        boolean isExisted = false;
                        RoleACL roleACL = null;
                        if (oldRoleACL != null && oldRoleACL.size() > 0) {
                            for (int i = oldRoleACL.size() - 1; i >= 0; i--) {
                                if (oldRoleACL.get(i).getPermission().getPermissionId().equals(id)) {
                                    isExisted = true;
                                    oldRoleACL.remove(i);
                                    break;
                                }
                            }
                        }
                        if (!isExisted) {
                            roleACL = new RoleACL();
                            roleACL.setRole(role);
                            roleACL.setPermission(new Permission(id));
                            roleACLRepository.save(roleACL);
                        }
                    }
                }
                if (oldRoleACL.size() > 0) {
                    roleACLRepository.deleteAll(oldRoleACL);
                }
            }else{
                role = new Role();
                role.setRole(roleDTO.getRole());
                role.setDescription(roleDTO.getDescription());
                role.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                role = roleRepository.save(role);

                if (roleDTO != null && roleDTO.getPermissionIds() != null && roleDTO.getPermissionIds().size() > 0) {
                    for (Long id : roleDTO.getPermissionIds()) {
                        RoleACL acl = new RoleACL();
                        acl.setRole(role);
                        acl.setPermission(new Permission(id));
                        roleACLRepository.save(acl);
                    }
                }
            }
        }
    }

    @Override
    public Optional<RoleDTO> findRole(Long id) {
        return roleRepository.findById(id).map(roleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
        }catch (Exception ex){
            throw new DataConstraintViolationException("Assigned role(s) must not be deleted");
        }
    }
}
