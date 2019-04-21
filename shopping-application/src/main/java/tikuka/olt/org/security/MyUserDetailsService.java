package tikuka.olt.org.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tikuka.olt.org.constant.Constants;
import tikuka.olt.org.domains.user.Permission;
import tikuka.olt.org.domains.user.Role;
import tikuka.olt.org.domains.user.User;
import tikuka.olt.org.errors.UserNotActivatedException;
import tikuka.olt.org.repositories.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
	private final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserRepository repository;

	@Override
	public MyUserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
		log.debug("Authenticating {}", login);
		String lowercaseLogin = login.toLowerCase();

		return repository.findOneByUsernameOrEmail(lowercaseLogin)
				.map(user -> createSpringSecurityUser(lowercaseLogin, user))
				.orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
	}

	private MyUserDetails createSpringSecurityUser(String lowercaseLogin, User user) {
		if (user.getStatus() != Constants.USER_STATUS_ACTIVE) {
			throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(1);

		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.DEFAULT_LOGGED_IN_ROLE));
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserGroup().getCode()));

		if (user.getUserRoles() != null && user.getUserRoles().size() > 0) {
			for (Role role : user.getUserRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
				if (role.getPermissions() != null && role.getPermissions().size() > 0) {
					for (Permission permission : role.getPermissions()) {
						grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission()));
					}
				}
			}
		}

		MyUserDetails userDetails = new MyUserDetails(user.getUsername(),
				user.getPassword(),
				grantedAuthorities);
		userDetails.setFirstName(user.getFirstName());
		userDetails.setLastName(user.getLastName());
		userDetails.setEmail(user.getEmail());
		userDetails.setUserId(user.getUserId());
		return userDetails;
	}
}
