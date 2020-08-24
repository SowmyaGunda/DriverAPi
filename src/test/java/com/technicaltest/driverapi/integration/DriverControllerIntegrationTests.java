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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
class DriverControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void givenGetAllDriversUrl_WithNoDateParam_ThenShouldReturnAllDrives() throws Exception {
		this.mockMvc.perform(get("/drivers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(2)));
	}

	@Test
	public void givenGetDriversByDateUrl_WithDateParam_ThenShouldReturnDriversCreatedAfterGivenDate() throws Exception {
		this.mockMvc.perform(get("/drivers/byDate")
				.queryParam("date", "2020-08-13"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(1)))
				.andExpect(jsonPath("$.[0].firstname").value("Driver-2-First-Name"))
				.andExpect(jsonPath("$.[0].uniqueId").value("UniqueId-2"));
	}

	@Test
	public void givenGetDriversByDateUrl_WithNoDateValue_ThenShouldReturnBadRequestResult() throws Exception {
		this.mockMvc.perform(get("/drivers/byDate")
				.queryParam("date", ""))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void givenCreateDriverUrl_WithValidPostBody_thenItShouldReturn200() throws Exception {
		String jsonString = objectMapper.writeValueAsString(new Driver("new Driver","Driver-LastName", "1990-05-24"));
		mockMvc.perform(
				post("/driver/create").accept("application/json")
						.content(jsonString).contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void givenCreateDriverUrl_WithInvalidFirstNameInPostBody_thenItShouldReturn400() throws Exception {
		String jsonString = objectMapper.writeValueAsString(new Driver("","Driver-LastName", "1990-05-24"));
		mockMvc.perform(
				post("/driver/create").accept("application/json")
						.content(jsonString).contentType("application/json"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void givenCreateDriverUrl_WithInvalidLastNameInPostBody_thenItShouldReturn400() throws Exception {
		String jsonString = objectMapper.writeValueAsString(new Driver("Drive-FirstName","", "1990-05-24"));
		mockMvc.perform(
				post("/driver/create").accept("application/json")
						.content(jsonString).contentType("application/json"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void givenCreateDriverUrl_WithInvalidDateFormatInPostBody_thenItShouldReturn400() throws Exception {
		String jsonString = objectMapper.writeValueAsString(new Driver("Drive-FirstName","", "05-24-1990"));
		mockMvc.perform(
				post("/driver/create").accept("application/json")
						.content(jsonString).contentType("application/json"))
				.andExpect(status().isBadRequest());
	}
}
