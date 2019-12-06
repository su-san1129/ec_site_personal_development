package com.example.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.OrderItemForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

@Service
public class ShoppingCartService {

	@Autowired
	private HttpSession session;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	/**
	 * ユーザーIdとステータスで注文情報を表示.
	 * 
	 * @param userId ユーザーID
	 * @param status ステータス
	 * @return 注文情報
	 */
	public Order showOrderByUserIdAndStatus(Integer userId, Integer status) {
		return orderRepository.findByUserIdAndStatus(userId, status);
	}

	/**
	 * ショッピングカートに商品を追加する.
	 * 
	 * @param form      フォーム
	 * @param loginUser ログイン中のユーザー
	 */
	public void addShoppingCart(OrderItemForm form, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = session.getId().hashCode(); // ユーザーIDを仮で設定

		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(form, orderItem);

		if (loginUser != null) {
			// ログインしていたら、ユーザーIDを差し替える
			userId = loginUser.getUser().getId();
		}
		Order preOrder = orderRepository.findByUserIdAndStatus(userId, 0);
		if (preOrder != null) {
			if (loginUser != null && userId != loginUser.getUser().getId()) {
				preOrder.setUserId(loginUser.getUser().getId());
			}
			preOrder.getOrderItemList().add(orderItem);
			orderItem.setOrderId(preOrder.getId());
		} else {
			Order order = new Order();
			order.setStatus(0);
			order.setTotalPrice(0);
			order.setUserId(userId);
			order = orderRepository.save(order);
			orderItemRepository.save(orderItem);
			orderItem.setOrderId(order.getId());
		}
		orderItem = orderItemRepository.save(orderItem);
		Integer orderItemId = orderItem.getId();
		if (form.getOrderToppingList() != null) {
			form.getOrderToppingList().forEach(i -> {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setOrderItemId(orderItemId);
				orderTopping.setToppingId(i);
				orderToppingRepository.save(orderTopping);
			});
		}
	}

}
