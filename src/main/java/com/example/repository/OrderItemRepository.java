package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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
		List<OrderTopping> orderToppingList = new ArrayList<>();
		OrderItem orderItem = new OrderItem(rs.getInt("id"), rs.getInt("item_id"), rs.getInt("order_id"),
				rs.getInt("quantity"), rs.getString("size").charAt(0), itemRepository.load(rs.getInt("item_id")),
				orderToppingList);
		return orderItem;
	};

	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}

	public OrderItem save(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		if (orderItem.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			orderItem.setId(key.intValue());
		} else {
			String sql = "UPDATE order_items SET id=:id, item_id=:itemId, order_id=:orderId, quantity=:quantity, size=:size WHERE id=:id;";
			template.update(sql, param);
		}
		return orderItem;
	}

}
