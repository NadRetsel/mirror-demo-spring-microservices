package com.example.productservice;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.repository.ProductRepository;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container 	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired 	private MockMvc mockMvc;
	@Autowired 	private ObjectMapper objectMapper;
	@Autowired	private ProductRepository productRepository;


	static {
		mongoDBContainer.start();
	}


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry)
	{
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}


	@Test
	void shouldCreateProduct() throws Exception
	{

		ProductDTO productDTO = getProductRequest();
		String productDTOString = objectMapper.writeValueAsString(productDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productDTOString))
				.andExpect(status().isCreated());

		Assertions.assertTrue(productRepository.findAll().size() == 1);
	}


	private ProductDTO getProductRequest()
	{
		return ProductDTO.builder()
				.name("iPhone 13 Pro")
				.description("Apple 2021")
				.price(BigDecimal.valueOf(999))
				.build();
	}

}
