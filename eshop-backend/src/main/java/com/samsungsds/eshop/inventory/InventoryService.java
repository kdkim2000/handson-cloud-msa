package com.samsungsds.eshop.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean plusInventory(String productId, Integer quantity) {
        try {
            Inventory inventory = inventoryRepository.findByProductId(productId);
            inventory.setQuantity(inventory.getQuantity() + quantity);
            inventoryRepository.save(inventory);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean minusInventory(String productId, Integer quantity) {
        try {
            Inventory inventory = inventoryRepository.findByProductId(productId);
            if (inventory.getQuantity() - quantity < 0) {
                throw new Exception("Out of stock Exception");
            }
            inventory.setQuantity(inventory.getQuantity() - quantity);
            inventoryRepository.save(inventory);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
