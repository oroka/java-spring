package rsys.domain.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import rsys.domain.model.RoleName;
import rsys.domain.model.User;

public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {

	private final User user;

	public UserDetailsImpl(User user) {
		super(user.getLastName(), user.getPassword(), determineRoles(user.getRoleName()));
		this.user = user;
	}

	private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private static List<GrantedAuthority> determineRoles(RoleName role) {
        return role.equals(RoleName.ADMIN) ? ADMIN_ROLES : USER_ROLES;
    }

	public User getUser() {
		return this.user;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// ユーザーエンティティのRoleName型からGrantedAuthorityに変換する。
		return AuthorityUtils.createAuthorityList("ROLE_" + this.user.getRoleName().name());
	}

	@Override
	public String getPassword() {
		// パスワードを返す
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO
		return this.user.getLastName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO　アカウントの期限は有効か？
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO　アカウントがロックされていないか？
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO　パスワードの期限は有効か？
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO　アカウントは有効か？
		return true;
	}

}
