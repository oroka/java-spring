package com.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todolist.domain.SimpleForm;

/*
 * データベースの構成からドメインクラスを作成する（この場合マッパークラスを作成）
 * ページの構成からドメインクラスを作成する（複数のデータベースにアクセスするサービスクラスを作成）
 * 単一データベースを扱うサービスクラスもある
 * 
 * 購入画面→確認画面→メール送信
 * サインアップ→確認画面→メール送信→メールからアクセス→サインアップ完了（ログイン）
 */


@Controller
@RequestMapping("/koko")
public class IndexController {

	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("simpleForm", new SimpleForm());
		return "simpleInput";
		
	}
}
