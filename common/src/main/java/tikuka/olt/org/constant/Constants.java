package tikuka.olt.org.constant;

import java.net.URI;

public final class Constants {
    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";

    public static final String DEFAULT_LOGGED_IN_ROLE = "LOGGED_IN";

    public static final int USER_STATUS_ACTIVE = 1;
    public static final int USER_STATUS_INACTIVE = 0;
    public static final int USER_STATUS_DELETED = -1;

    public static final String USER_GROUP_SYSADMIN = "SYS_ADMIN";
    public static final String USER_GROUP_CUSTOMER = "CUSTOMER";
    public static final String PROBLEM_BASE_URL = "https://www.tikuka.com/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
}
