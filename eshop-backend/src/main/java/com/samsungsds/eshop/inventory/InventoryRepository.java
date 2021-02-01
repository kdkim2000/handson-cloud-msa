package com.samsungsds.eshop.inventory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, String> {
    public Inventory findByProductId(String productId);
}
