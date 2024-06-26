package com.example.inventoryservice.service;

import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode)
    {
        return this.inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
