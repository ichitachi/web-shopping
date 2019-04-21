package tikuka.olt.org.domains.user;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "userrole")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userroleid")
    private Long userRoleId;

    @ManyToOne
    @JoinColumn(name = "roleid", referencedColumnName = "roleid")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    @Column(name = "createddate")
    private Instant createdDate;

    @Column(name = "modifieddate")
    private Instant modifiedDate;

    public UserRole(){};

    public UserRole(Role role, User user, Instant createdDate, Instant modifiedDate) {
        this.role = role;
        this.user = user;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public UserRole(Role role, User user, Instant createdDate) {
        this.role = role;
        this.user = user;
        this.createdDate = createdDate;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
