package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

@Repository
public class OrderRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	private final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		
		Integer preOrderId = 0; // 一行前のオーダーID
		Integer preOrderItemId = 0; // 一行前のオーダーアイテムID
		List<Order> orderList = new ArrayList<>(); // 返却するオーダーリスト
		List<OrderItem> orderItemList = null; // 注文商品リスト（オーダーオブジェクトが所持）
		List<OrderTopping> orderToppingList = null; // 注文トッピングリスト(注文商品オブジェクトが所持)
		
		while(rs.next()) {
			if( rs.getInt("id") != preOrderId ) {
				orderItemList = new ArrayList<>();
				Order order = new Order(
						rs.getInt("id")
						, rs.getInt("user_id")
						, rs.getInt("status")
						, rs.getInt("total_price")
						, rs.getDate("order_date")
						, rs.getString("destination_name")
						, rs.getString("destination_email")
						, rs.getString("destination_zipcode")
						, rs.getString("destination_address")
						, rs.getString("destination_tel")
						, rs.getTimestamp("delivery_time")
						, rs.getInt("payment_method")
						, userRepository.load(rs.getInt("user_id"))
						, orderItemList
						);
				orderList.add(order);
			}
			preOrderId = rs.getInt("id"); // 一行前のIDを現在のIDに更新
			
			if( rs.getInt("order_item_id") != preOrderItemId && rs.getInt("order_item_id") != 0 ) {
				orderToppingList = new ArrayList<>();
				OrderItem orderItem = new OrderItem(
						rs.getInt("order_item_id")
						, rs.getInt("itemId")
						, rs.getInt("id")
						, rs.getInt("quantity")
						, rs.getString("size").charAt(0)
						, itemRepository.load(rs.getInt("itemId"))
						, orderToppingList
						);
				orderItemList.add(orderItem);
			}
			preOrderItemId = rs.getInt("order_item_id"); // 一行前のIDを現在のIDに更新
			
			if( rs.getInt("order_topping_id") != 0 ) {
				OrderTopping orderTopping = new OrderTopping(
						rs.getInt("order_topping_id")
						, rs.getInt("topping_id")
						, rs.getInt("order_item_id")
						, toppingRepository.load( rs.getInt("topping_id") )
						);
				orderToppingList.add(orderTopping);
			}
		}
		return orderList;
	};
	

}
