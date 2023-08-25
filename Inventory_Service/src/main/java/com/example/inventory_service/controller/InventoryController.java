package com.example.inventory_service.controller;

import com.example.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController
{
    private final InventoryService inventoryService;
    @GetMapping("/isAvailable/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isAvailableInStock(@PathVariable("skuCode") String skuCode)
    {
        return inventoryService.isInStock(skuCode);
    }
}
