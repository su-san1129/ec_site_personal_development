package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	@RequestMapping("/order_confirm")
	public String toOrder() {
		return "order_confirm";
	}

	@RequestMapping("/order_finished")
	public String toOrderFinished() {
		return "order_finished";
	}

}
