package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.OrderItemForm;
import com.example.service.ShoppingCartService;

@Controller
@RequestMapping("/cart_list")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private HttpSession session;

	/**
	 * カート一覧を表示.
	 * 
	 * @return カート一覧画面
	 */
	@RequestMapping("")
	public String cartList(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = session.getId().hashCode();
		if (loginUser != null) {
			userId = loginUser.getUser().getId();
		}
		Order order = shoppingCartService.showOrderByUserIdAndStatus(userId, 0);
		if (order != null) {
			model.addAttribute("order", order);
		}

		return "cart_list";
	}

	@RequestMapping("/addCart")
	public String addCart(OrderItemForm form, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = session.getId().hashCode(); // ユーザーIDを仮で設定
		if (loginUser != null) {
			// ログインしていたら、ユーザーIDを差し替える
			userId = loginUser.getUser().getId();
		} else {
			session.setAttribute("userId", userId);
		}
		shoppingCartService.addShoppingCart(form, userId, loginUser);
		return "redirect:/cart_list";
	}

}
