package com.example.stores.controller;

import com.example.stores.domain.model.ProductsDto;
import com.example.stores.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductsController {
  private final ProductsService productsService;

  @GetMapping
  public ResponseEntity<List<ProductsDto>> listAll() {
    return ResponseEntity.ok(productsService.listAll());
  }

  @GetMapping("/{prodName}")
  public ResponseEntity<ProductsDto> findProdName(
      @PathVariable(name = "prodName") final String prodName) {
    return productsService
        .findProdName(prodName)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Void> createProduct(@RequestBody final ProductsDto productsDto) {

    productsService.createProduct(productsDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping(value = "/{prodId}", consumes = "application/json")
  public ResponseEntity<Void> update(
      @PathVariable(name = "prodId") Long prodId, @RequestBody final ProductsDto productsDto) {
    productsService.updateProduct(prodId, productsDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{prodId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable(name = "prodId") final Long prodId) {
    productsService.deleteProduct(prodId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
