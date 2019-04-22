package tikuka.olt.org.controllers.user;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tikuka.olt.org.common.ObjectPage;
import tikuka.olt.org.criteria.user.RoleCriteria;
import tikuka.olt.org.dto.user.RoleDTO;
import tikuka.olt.org.services.user.RoleService;
import tikuka.olt.org.utils.HeaderUtil;

import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import java.util.Optional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<ObjectPage<RoleDTO>> getAllRoles(RoleCriteria criteria, Pageable pageable) {
        Page<RoleDTO> page = roleService.findRoleByCriteria(criteria, pageable);
        return new ResponseEntity(new ObjectPage<>(page), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @Timed
    public ResponseEntity<RoleDTO> getRole(@PathVariable Long id) {
        Optional<RoleDTO> dto = roleService.findRole(id);
        return  new ResponseEntity(dto,HttpStatus.OK);
    }

    @PostMapping
    @Timed
    public void createRole(@Valid @RequestBody RoleDTO roleDTO) throws InstanceAlreadyExistsException {
        roleService.createOrUpdateRole(roleDTO);
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
