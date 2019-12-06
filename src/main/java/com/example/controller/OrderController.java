package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	/**
	 * 注文確認画面を表示.
	 * 
	 * @return 注文確認画面
	 */
	@RequestMapping("/order_confirm")
	public String toOrder() {
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
