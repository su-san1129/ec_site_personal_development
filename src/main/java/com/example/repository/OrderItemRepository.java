package com.example.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;

/**
 * 注文商品を操作するリポジトリ.
 * 
 * @author takahiro.suzuki
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 注文商品オブジェクトを格納するローマッパー.
	 */
	private final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		Integer itemId = rs.getInt("item_id");
		Integer orderId = rs.getInt("order_id");
		Integer quantity = rs.getInt("quantity");
		Character size = rs.getString("size").charAt(0);
		Item item = itemRepository.load(rs.getInt("item_id"));
		List<OrderTopping> orderToppingList = new ArrayList<>();
		OrderItem orderItem = new OrderItem(id, itemId, orderId, quantity, size, item, orderToppingList);
		return orderItem;
	};
}
