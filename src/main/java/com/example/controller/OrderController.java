package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 注文確認画面を表示.
	 * 
	 * @return 注文確認画面
	 */
	@RequestMapping("/order_confirm")
	public String toOrder(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = loginUser.getUser().getId();
		Order order = orderService.showOrderList(userId, 0);
		model.addAttribute("order", order);
		return "order_confirm";
	}

	/**
	 * 注文完了画面を表示.
	 * 
	 * @return 注文完了画面
	 */
	@RequestMapping("/order_finished")
	public String toOrderFinished() {
		return "order_finished";
	}

}
