package tikuka.olt.org.domains.user;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tikuka.olt.org.constant.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "User.graphentity",
                attributeNodes = {@NamedAttributeNode(value = "userRoles", subgraph = "userRoles"),
                        @NamedAttributeNode(value = "userGroup")},
                subgraphs = @NamedSubgraph(name = "userRoles", attributeNodes = @NamedAttributeNode("permissions"))),
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @NotNull
    @Size(min = 4, max = 255)
    @Column(name = "username", length = 255, nullable = false)
    private String username;

    @NotNull
    @Size(min = 8, max = 255)
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @NotNull
    @Size(min = 5, max = 255)
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "firstname", length = 255, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lastname", length = 255, nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fullname", length = 255, nullable = false)
    private String fullName;


    @Column(name = "status")
    private Integer status;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usergroupid", referencedColumnName = "usergroupid")
    private UserGroup userGroup;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRoleACLS;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="userrole",
            joinColumns = @JoinColumn(name="userid", referencedColumnName = "userid"),
            inverseJoinColumns = @JoinColumn(name="roleid", referencedColumnName = "roleid"))
    private List<Role> userRoles;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserProfile userProfile;

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public User(){};

    public User(Long userId){this.userId = userId;};

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<UserRole> getUserRoleACLS() {
        return userRoleACLS;
    }

    public void setUserRoleACLS(Set<UserRole> userRoleACLS) {
        this.userRoleACLS = userRoleACLS;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}