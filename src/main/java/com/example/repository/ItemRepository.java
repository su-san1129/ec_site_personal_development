package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Topping;

/**
 * 商品情報を操作するリポジトリ.
 * @author takahiro.suzuki
 *
 */
@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		Integer priceM = rs.getInt("price_m");
		Integer priceL = rs.getInt("price_l");
		String imagePath = rs.getString("iamge_path");
		Boolean deleted = rs.getBoolean("deleted");
		List<Topping> toppingList = new ArrayList<>();
		Item item = new Item(id, name, description, priceM, priceL, imagePath, deleted, toppingList);
		return item;
	};
	
	public Item load(Integer id) {
		try {
			String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE id = :id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
