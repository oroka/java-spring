package rsys.domain.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rsys.domain.model.RoleName;
import rsys.domain.model.SectionName;
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

	/*
	 * 引数で指定するユーザー情報を検索する
	 */
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

	/*
	 * 引数で指定するユーザー情報をデータベースから削除する
	 */
	public void deleteOne(User user) {
		userRepository.delete(user);
	}

	/*
	 * 引数で指定するＩＤのユーザー情報をデータベースから削除する
	 */
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	/*
	 * 引数で指定するユーザーリストに含まれるユーザーをデータベースから削除する
	 */
	public void deleteAll(List<User> entities) {
		userRepository.deleteAll(entities);
	}

	/*
	 * 引数で指定するユーザーのデータベース情報を更新する
	 */
	public void update(User user) {
		userRepository.save(user);
	}

	/*
	 * データベースのユーザー一覧をCSV形式でファイルに書き込む
	 */
	public void createUserCsvFile(Path path, Charset cs, OpenOption... options)  {
		List<User> list = findAll();
		try(BufferedWriter bw = Files.newBufferedWriter(path, cs, options)){
			for(User u : list) {
				bw.write(u.toCsvString());
				bw.newLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * CSV形式のファイルからユーザー一覧をデータベースに登録する
	 * (id, firstName, lastName, email, password, sectionName, salary, roleName)
	 */
	public void insertFromUserCsvFile(Path path, Charset cs) {
		try(Stream<String> stream = Files.lines(path, cs)){
			stream.forEach(str->{
				String[] line = str.split(",");
				for(String s : line) {
					String[] spl = s.split(",");
					insertOne(User.of(Integer.parseInt(spl[0]), //id
							spl[1], //firstName
							spl[2], //lastName
							spl[3], //email
							spl[4], //password
							SectionName.valueOf(spl[5]), //sectionName
							Long.parseLong(spl[6]), //salary
							RoleName.valueOf(spl[7])  //roleName
						)
					);
				}
			});

		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
