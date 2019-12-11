package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ItemService;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 商品一覧画面を表示.
	 * 
	 * @param model モデル
	 * @return 商品一覧画面
	 */
	@RequestMapping("/")
	public String showItemList(Model model, String name) {
		List<Item> itemList = itemService.fizzySearchOrFindAllItemList(name);
		List<String> nameList = itemService.itemNameList();
		model.addAttribute("itemList", itemList);
		model.addAttribute("nameList", nameList);
		return "item_list";
	}

	/**
	 * 商品詳細画面を表示.
	 * 
	 * @param id ID
	 * @return 商品詳細画面
	 */
	@RequestMapping("/item_detail")
	public String showItemDetail(Integer id, Model model) {
		Item item = itemService.showItemDetail(id);
		model.addAttribute("quantities", itemService.quantity());
		model.addAttribute("item", item);
		return "item_detail";
	}

}
