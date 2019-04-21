package tikuka.olt.org.domains.user;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usergroupacl")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserGroupACL implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usergroupaclid")
    private Long userGroupAclId;

    @ManyToOne
    @JoinColumn(name = "usergroupid", referencedColumnName = "usergroupid")
    private UserGroup userGroup;

    @ManyToOne
    @JoinColumn(name = "permissionid", referencedColumnName = "permissionid")
    private Permission permission;

    public Long getUserGroupAclId() {
        return userGroupAclId;
    }

    public void setUserGroupAclId(Long userGroupAclId) {
        this.userGroupAclId = userGroupAclId;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
