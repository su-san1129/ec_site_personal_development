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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UserController target;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getLoginTest() throws Exception{
		mockMvc.perform(get("/login/"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));
	}
	@Test
	public void registerUserTest() throws Exception{
		mockMvc.perform(get("/register_user/"))
		.andExpect(status().isOk())
		.andExpect(view().name("register_user"));
	}

}
