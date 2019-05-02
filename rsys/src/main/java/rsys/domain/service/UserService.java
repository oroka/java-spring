package rsys.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rsys.domain.model.User;
import rsys.domain.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	/*
	 * 仕様：全てのユーザーをページ型で返す
	 * 引数：なし
	 * 戻り値：List<User>
	 */
	public List<User> findAll(){
		return userRepository.findAll();
	}

	/*
	 * 仕様：引数に指定されたＩＤを持つユーザーを返す
	 * 引数：Integer id
	 * 戻り値:User
	 */
	public User findOne(Integer id){
		return userRepository.getOne(id);
	}

	public Optional<User> findOne(User user) {

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("id", match->match.exact())
				.withMatcher("password", match->match.exact());
		Example<User> example = Example.of(user, matcher);

		return userRepository.findOne(example);
	}

	/*
	 * 仕様：引数に指定された名前に部分一致するユーザーをList型で返す
	 * 引数：String name
	 * 戻り値：List<User>
	 */
	public List<User> findAllByName(String name){
		User user = new User();
		user.setFirstName(name);
		user.setLastName(name);

		ExampleMatcher matcher = ExampleMatcher.matchingAny()
				.withMatcher("first_name", match -> match.ignoreCase().contains())
				.withMatcher("last_name", match -> match.ignoreCase().contains())
				.withIgnorePaths("id", "password", "role");
		Example<User> example = Example.of(user, matcher);

		return userRepository.findAll(example);
	}

	/*
	 * 仕様：引数に指定されたユーザーをデータベースに登録する
	 * 引数：User user
	 * 戻り値：なし
	 */
	public void insertOne(User user) {
		userRepository.save(user);
	}

	/*
	 * 仕様：引数に指定されたユーザーのリストからユーザーをデータベースに登録する
	 * 引数：List<User>
	 * 戻り値：なし
	 */
	public void insertAll(List<User> list) {
		userRepository.saveAll(list);
	}

	public void deleteOne(User user) {
		userRepository.delete(user);
	}

	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	public void deleteAll(List<User> entities) {
		userRepository.deleteAll(entities);
	}

	public void update(User user) {
		userRepository.save(user);
	}
}
