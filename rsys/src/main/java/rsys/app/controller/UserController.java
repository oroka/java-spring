package rsys.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import rsys.domain.model.RoleName;
import rsys.domain.model.SectionName;
import rsys.domain.model.User;
import rsys.domain.model.converter.ClassConverter;
import rsys.domain.model.form.UserInputForm;
import rsys.domain.service.UserCsvService;
import rsys.domain.service.UserService;

/*
 * 仕様：ユーザー情報の管理
 *
 */

@Controller
@RequestMapping("admin/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserCsvService userCsvService;

	@Autowired
	ResourceLoader resourceLoader;

	private final String tplRoot = "/admin/user/";

	@GetMapping("list")
	public String viewAll(Model model) {
		List<User> list = userService.findAll();
		Collections.sort(list, (User s, User t)->s.getId() - t.getId());
		model.addAttribute("users", list);
		return tplRoot + "list";
	}

	/*
	 * 仕様：ユーザー情報をデータベースに登録する
	 * 引数：UserInputForm
	 */
	@PostMapping("add")
	public String addNew(Model model, @Valid @ModelAttribute UserInputForm userInputForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "admin/confirm";
		}

		User user = ClassConverter.convertToUser(userInputForm);
		userService.insertOne(user);

		model.addAttribute("users", userService.findAll());

		return tplRoot + "list";
	}

	/*
	 * 仕様：データベースのユーザー情報を更新する
	 */
	@PostMapping("update")
	public String update(Model model, User user) {
		//System.out.println("userId : " + user.getId());
		userService.update(user);
		model.addAttribute("users", userService.findAll());
		return tplRoot + "list";
	}

	/*
	 * 仕様：データベースのＩＤの選択されたユーザー情報を編集する
	 * 引数：Integer id : 選択されたID
	 */
	@PostMapping("edit")
	public String edit(Model model, Integer id) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		model.addAttribute("RoleName", RoleName.values());
		model.addAttribute("SectionName", SectionName.values());
		return tplRoot + "edit";
	}

	/*
	 * 追加項目：ファイル一覧・ファイル内容表示・データベースへの反映
	 */

	/*
	 * 仕様：ファイルからデータベースを更新する
	 * 手順：ファイルの選択->ファイルからデータベース更新
	 * 前提条件：ファイルのアップロード
	 */
	@GetMapping("file/insert")
	public String insertCsvFile(Model model, @RequestParam("path") String path) {

		userService.insertFromUserCsvFile(Paths.get("\\Users\\owner\\RsysData").resolve(path), StandardCharsets.UTF_8);

		return "redirect:" + tplRoot + "list";
	}

	/*
	 * 仕様：データベースの情報をCSVファイルへ変換
	 */
	@PostMapping("file/create")
	public String createCsvFile(Model model) {

		Path path = Paths.get("\\Users\\owner\\RsysData");
		//path = path.resolve("abc");
		userService.createUserCsvFile(path, StandardCharsets.UTF_8);

		/*
		OpenOption oo = StandardOpenOption.APPEND;
		if(!Files.exists(path)) oo = StandardOpenOption.CREATE;
		try(OutputStream os = Files.newOutputStream(path, oo)){

		}catch(IOException e) {
			System.out.println("createUserCsvFile");
			e.printStackTrace();
		}
		*/

		return "redirect:" + tplRoot + "file/list";
	}

	/*
	 * 仕様：Csvファイルをダウンロードする
	 */
	@PostMapping("file/download")
	public String download(HttpServletResponse response, @RequestParam("download_file") String download_file, Model model) throws IOException {
		Path path = Paths.get("\\Users\\owner\\RsysData");
		path = path.resolve(download_file);

		byte[] fileContent = null;
		fileContent = StreamToByte(path);

		if(fileContent == null) return "redirect:" + tplRoot + "file/list";

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + path.getFileName());
		response.setContentLength(fileContent.length);

		try(OutputStream os = response.getOutputStream()){
    	   os.write(fileContent);
    	   os.flush();
		} catch (FileNotFoundException e) {
    	   e.getStackTrace();
		}

		//model.addAttribute("users", userService.findAll());
		return "redirect:" + tplRoot + "file/list";
	}

	/*
	 * UserCSVファイルの一覧を表示
	 */
	@GetMapping("file/list")
	public String viewFileList(Model model) throws IOException {

		List<String> list = Files.list(Paths.get("\\Users\\owner\\RsysData")).map(t->t.getFileName().toString()).collect(Collectors.toList());
		model.addAttribute("list", list);

		return "admin/user/file/list";
	}

	/*
	 * 仕様：アップロードされたファイルを保存する
	 * フォルダ：Users\owner\RsysData
	 */
	@PostMapping("file/upload")
	public String upload(@RequestParam("upload_file") MultipartFile multipartFile, Model model) throws IOException {

		/*
		 * 一時的にここにパスを書く。設定クラスを作り値を登録していく。Singleton。また、既存の設定ファイルの使い方など。
		 */
		Path path = Paths.get("\\Users\\owner\\RsysData");
		if(!Files.exists(path)) {
			Files.createDirectory(path);
		}

		//System.out.println(path.toString());

		/*
		 * 拡張子を取得し、日時をベースに変更したファイル名に設定する。
		 */
		int dot = multipartFile.getOriginalFilename().lastIndexOf(".");
		String extension = "";
		if(dot > 0) {
			//dot以降の拡張子名を取得する
			extension = multipartFile.getOriginalFilename().substring(dot).toLowerCase();
		}
		// 拡張子がない、拡張子が.csvでない場合、扱わない。
		if(dot <= 0 || !extension.equals(".csv")) {
			return "redirect:" + tplRoot + "file/list";
		}
		String filename = DateTimeFormatter.ofPattern("yyyyMMddmmss").format(LocalDateTime.now());
		Path uploadfile = path.resolve(filename + extension);

		try(OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
			os.write(multipartFile.getBytes());
		}

		model.addAttribute("users", userService.findAll());
		return "redirect:" + tplRoot + "file/list";
	}

	@PostMapping("deleteConfirm")
	public String deleteConfirm(Model model, Integer id) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);

		return tplRoot + "delete";
	}

	@PostMapping("delete")
	public String delete(Model model, Integer id) {
		userService.deleteById(id);

		model.addAttribute("users", userService.findAll());
		return tplRoot + "list";
	}

	/*
	 * InputStream To byte[]
	 */
	private byte[] StreamToByte(Path path) {

		int nRead;
		byte[] fileContent = new byte[6666];
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		try(InputStream is = new FileInputStream(path.toString())){
			while((nRead = is.read(fileContent, 0, fileContent.length)) != -1) {
				buffer.write(fileContent, 0, nRead);
			}
			buffer.flush();

			return buffer.toByteArray();

		} catch (FileNotFoundException e) {
			// TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}

		return null;
	}
}
