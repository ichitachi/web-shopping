package tikuka.olt.org.domains.user;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "permission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissionid")
    private Long permissionId;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "permission", length = 255, nullable = false)
    private String permission;

    @Column(name = "description")
    private String description;

    @Column(name = "createddate")
    private Instant createdDate;

    @Column(name = "modifieddate")
    private Instant modifiedDate;

    public Permission(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Permission() {
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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