package com.samsungsds.eshop.inventory;

import com.samsungsds.eshop.product.Product;
import com.samsungsds.eshop.product.ProductService;
import com.samsungsds.eshop.product.Products;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {
  private final InventoryService inventoryService;

  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

}
