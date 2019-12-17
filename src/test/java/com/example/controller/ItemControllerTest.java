package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private ItemController target;
	 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//mockMvcを利用して、仮想のリクエストを発生させてテストを実行する.
		mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * インデックスの検証.
	 * 
	 * @throws Exception 例外処理
	 */
	@Test
	public void getIndexTest() throws Exception{
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("item_list"));
	}
	
	/**
	 * 商品詳細の検証.
	 * 
	 * @throws Exception 例外処理
	 */
	@Test
	public void getItemDetailTest() throws Exception{
		mockMvc.perform(get("/item_detail/").param("id", "4000"))
		.andExpect(status().isOk())
		.andExpect(view().name("item_detail"));
	}

}
