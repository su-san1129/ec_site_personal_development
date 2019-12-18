package com.example.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.form.OrderForm;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // テストが終わるたびにDBがロールバックされるようになる。
public class OrderControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private OrderController target;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@WithMockUser(username="admin@admin.com", roles="USER")
	public void getOrderComfirmTest() throws Exception {
		OrderForm form = new OrderForm();
		mockMvc.perform(get("/order_confirm").with(user("admin@admin.com").roles("USER")))
			.andExpect(status().isOk())
			.andExpect(view().name("order_confirm"))
			.andExpect(model().attribute("orderForm", form));
	}

	@Test
	public void getOrderFinishedTest() throws Exception {
		mockMvc.perform(get("/order_finished"))
			.andExpect(status().isOk())
			.andExpect(view().name("order_finished"));
	}

}
