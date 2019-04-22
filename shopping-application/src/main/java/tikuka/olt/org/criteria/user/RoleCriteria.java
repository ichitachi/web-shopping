package tikuka.olt.org.criteria.user;

import tikuka.olt.org.common.StringFilter;

import java.io.Serializable;

public class RoleCriteria implements Serializable {

    private StringFilter role;

    public StringFilter getRole() {
        return role;
    }

    public void setRole(StringFilter role) {
        this.role = role;
    }
}

