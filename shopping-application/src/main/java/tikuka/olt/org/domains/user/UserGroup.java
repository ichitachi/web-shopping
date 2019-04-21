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
@Table(name = "usergroup")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usergroupid")
    private Long userGroupId;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "code", length = 100, nullable = false)
    private String code;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "createddate")
    private Instant createdDate;

    @Column(name = "modifieddate")
    private Instant modifiedDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userGroup")
    private Set<UserGroupACL> userGroupACLS;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="usergroupacl",
            joinColumns = @JoinColumn(name="usergroupid", referencedColumnName = "usergroupid"),
            inverseJoinColumns = @JoinColumn(name="permissionid", referencedColumnName = "permissionid"))

    private Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserGroupACL> getUserGroupACLS() {
        return userGroupACLS;
    }

    public void setUserGroupACLS(Set<UserGroupACL> userGroupACLS) {
        this.userGroupACLS = userGroupACLS;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
