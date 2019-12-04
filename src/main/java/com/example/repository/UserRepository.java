package com.example.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * ユーザ情報を操作するリポジトリ.
 * 
 * @author takahiro.suzuki
 *
 */
@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * ユーザー情報を格納するローマッパー.
	 */
	private final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String zipcode = rs.getString("zipcode");
		String address = rs.getString("address");
		String telephone = rs.getString("telephone");
		User user = new User(id, name, email, password, zipcode, address, telephone);
		return user;
	};
	
	/**
	 * ユーザー情報の一件検索.
	 * @param id ユーザーのPK
	 * @return ユーザーの一件検索情報
	 */
	public User load(Integer id) {
		try {
			String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE id = :id;";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
