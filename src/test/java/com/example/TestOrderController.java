package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controller.OrderController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestOrderController {

	private MockMvc mockMvc;

	@InjectMocks
	private OrderController getAllOrder;

	@Before
	private void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(getAllOrder).build();
	}

	@Test
	private void testOrderController() throws Exception {

		mockMvc.perform(

				MockMvcRequestBuilders.get("/orders")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("orders"));

	}
}
