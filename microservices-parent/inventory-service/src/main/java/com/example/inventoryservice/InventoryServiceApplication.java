package com.example.inventoryservice;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository)
	{
		return args ->
		{
			Inventory inventory1 = Inventory.builder()
					.skuCode("iPhone_13_Pro")
					.quantity(100)
					.build();

			Inventory inventory2 = Inventory.builder()
					.skuCode("iPhone_13_Pro_Max")
					.quantity(0)
					.build();

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}
