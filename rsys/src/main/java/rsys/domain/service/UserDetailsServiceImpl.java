package rsys.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rsys.domain.model.User;
import rsys.domain.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO　ユーザーを取得する

		System.out.println("UserDetailsServiceImpl - loadUserByUsername");
		User user = new User();
		user.setFirstName(username);
		user.setLastName(username);

		ExampleMatcher matcher = ExampleMatcher.matchingAny()
				.withMatcher("lastName", lastName -> lastName.caseSensitive())
				.withMatcher("firstName", firstName-> firstName.caseSensitive());
		Example<User> example = Example.of(user, matcher);

		Optional<User> _user = userRepository.findOne(example);
		if(!_user.isPresent()) {
			throw new UsernameNotFoundException(username + "is not found.");
		}

		return new UserDetailsImpl(_user.get());
	}

}
