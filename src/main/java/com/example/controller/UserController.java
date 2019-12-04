package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ユーザー情報を操作するコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("")
public class UserController {
	
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required=false) String error) {
		if( error != null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが違います。");
			return login(model, error);
		}
		return "login";
	}
	
	@RequestMapping("/register")
	public String toRegister() {
		return "register_user";
	}

}
