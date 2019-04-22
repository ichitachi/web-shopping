package tikuka.olt.org.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tikuka.olt.org.domains.user.RoleACL;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleACLRepository extends JpaRepository<RoleACL, Long>, JpaSpecificationExecutor<RoleACL> {

    @Query("SELECT ra.role.roleId FROM RoleACL ra " +
            " WHERE ra.permission.permissionId IN :permissionIds " +
            " and (:roleId is null or (:roleId is not null and ra.role.roleId <> :roleId ))" +
            " AND (SELECT count(ra1.role.roleId) FROM RoleACL ra1 where ra.role.roleId = ra1.role.roleId GROUP BY ra1.role.roleId) = :total " +
            " GROUP BY ra.role.roleId ")
    Optional<Long> checkRoleExists(@Param("permissionIds") List<Long> permissionIds, @Param("total") Long total, @Param("roleId") Long roleId);
}
