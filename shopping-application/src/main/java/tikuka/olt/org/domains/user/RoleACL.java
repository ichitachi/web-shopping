package tikuka.olt.org.domains.user;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roleacl")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RoleACL implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleaclid")
    private Long roleAclId;

    @ManyToOne
    @JoinColumn(name = "roleid", referencedColumnName = "roleid")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permissionid", referencedColumnName = "permissionid", columnDefinition = "permissionid")
    private Permission permission;


    public Long getRoleAclId() {
        return roleAclId;
    }

    public void setRoleAclId(Long roleAclId) {
        this.roleAclId = roleAclId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
