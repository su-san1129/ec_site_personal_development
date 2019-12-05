package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品を操作するサービスクラス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品詳細を表示.
	 * 
	 * @param id ID
	 * @return 商品詳細情報
	 */
	public Item showItemDetail(Integer id) {
		return itemRepository.load(id);
	}
	
	/**
	 * 全商品情報を表示する.
	 * 
	 * @return 商品全件検索情報
	 */
	public List<Item> showItemList(){
		return itemRepository.findAll();
	}

}
