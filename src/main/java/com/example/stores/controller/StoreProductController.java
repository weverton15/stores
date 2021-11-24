package com.example.stores.controller;

import com.example.stores.domain.model.StoreProductDto;
import com.example.stores.service.StoreProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/product")
@Slf4j
@RequiredArgsConstructor
public class StoreProductController {
  private final StoreProductService service;

  @GetMapping
  public ResponseEntity<List<StoreProductDto>> listAll() {
    return ResponseEntity.ok(service.listAll());
  }

  @GetMapping("/{prodId}")
  public ResponseEntity<StoreProductDto> findProdName(
      @PathVariable(name = "prodId") final Long prodId) {
    return service
        .findProdId(prodId)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Void> includeStoreProduct(@RequestBody final StoreProductDto dto) {

    service.includeStoreProduct(dto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}", consumes = "application/json")
  public ResponseEntity<Void> update(
      @PathVariable(name = "id") Long id, @RequestBody final StoreProductDto dto) {
    service.updateStatus(id, dto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") final Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
