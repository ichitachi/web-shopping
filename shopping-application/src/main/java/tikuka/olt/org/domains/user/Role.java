package tikuka.olt.org.domains.user;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Long roleId;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "role", length = 100, nullable = false)
    private String role;

    @Column(name = "description")
    private String description;

    @Column(name = "createddate")
    private Instant createdDate;

    @Column(name = "modifieddate")
    private Instant modifiedDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<RoleACL> roleACLS;

    public Set<RoleACL> getRoleACLS() {
        return roleACLS;
    }

    public void setRoleACLS(Set<RoleACL> roleACLS) {
        this.roleACLS = roleACLS;
    }

    public Role(Long roleId){
        this.roleId = roleId;
    }

    public Role(){};

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="roleacl",
            joinColumns = @JoinColumn(name="roleid", referencedColumnName = "roleid"),
            inverseJoinColumns = @JoinColumn(name="permissionid", referencedColumnName = "permissionid"))

    private Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}