package com.example.inventory_service.service;

import com.example.inventory_service.model.Inventory;
import com.example.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService
{
    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public boolean isItemInStock(String skuCode)
    {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
    public boolean areItemsInStock(List<String> skuCodes)
    {
        if (!skuCodes.stream().allMatch(s -> inventoryRepository.findBySkuCode(s).isPresent()))
            throw new RuntimeException("Some of requested items do not exist");

        return inventoryRepository.findBySkuCodeIn(skuCodes.toArray(new String[skuCodes.size()]))
                .stream().allMatch(inventory -> inventory.get().getQuantity() > 0);
    }

}
