package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {
	
	@Autowired
	private ItemService itemService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * あいまい検索のテスト.
	 */
	@Test
	public void searchTest() {
		List<Item> itemList = itemService.fizzySearchOrFindAllItemList("じゃが");
		assertThat("件数が一致しませんでした。", itemList.size(), is(2));
		assertThat("名前が一致しませんでした。", itemList.get(0).getName(), is("じゃがバターベーコン"));
	}

}
