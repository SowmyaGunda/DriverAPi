package com.technicaltest.driverapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.driverapi.TestConfiguration;
import com.technicaltest.driverapi.core.Driver;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
class DriverControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void givenGetDriverUrl_WithNoDateParam_ThenShouldReturnAllDrives() throws Exception {
		this.mockMvc.perform(get("/drivers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*",hasSize(2)));
	}

	@Test
	public void givenCreateDriverUrl_WithValidPostBody_thenReturn200() throws Exception {
		String jsonString = objectMapper.writeValueAsString(new Driver("new Driver"));
		mockMvc.perform(
				post("/driver/create").accept("application/json").content(jsonString))
				.andExpect(status().isOk());
	}
}
