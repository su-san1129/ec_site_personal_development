package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;
import com.example.domain.Topping;

/**
 * 注文トッピングを操作するリポジトリ.
 * 
 * @author takahiro.suzuki
 *
 */
@Repository
public class OrderToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 注文トッピングオブジェクトを格納するローマッパー.
	 */
	private final RowMapper<OrderTopping> ORDER_TOPPING_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		Integer toppingId = rs.getInt("topping_id");
		Integer orderItemId = rs.getInt("order_item_id");
		Topping topping = toppingRepository.load(rs.getInt("topping_id"));
		OrderTopping orderTopping = new OrderTopping(id, toppingId, orderItemId, topping);
		return orderTopping;
	};

}
