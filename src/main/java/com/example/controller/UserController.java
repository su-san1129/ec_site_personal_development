package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.form.RegisterUserForm;
import com.example.service.UserService;

/**
 * ユーザー情報を操作するコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	private UserService userService;

	@ModelAttribute
	private RegisterUserForm setUpRegisterUserForm() {
		return new RegisterUserForm();
	}

	/**
	 * ログイン画面を表示する.
	 * 
	 * @param model モデル
	 * @param error エラー
	 * @return ログイン画面
	 */
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error) {
		if (error != null) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "login";
	}

	/**
	 * ユーザー登録画面を表示.
	 * 
	 * @return ユーザ登録画面
	 */
	@RequestMapping("/register_user")
	public String toRegister() {
		return "register_user";
	}

	@RequestMapping("/register")
	public String register(RegisterUserForm form) {
		userService.registerUser(form);
		System.err.println("登録が完了しました。");
		return "login";
	}

}
