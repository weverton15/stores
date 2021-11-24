package com.example.stores.controller;

import com.example.stores.domain.model.StoresDto;
import com.example.stores.service.StoresService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@Slf4j
@RequiredArgsConstructor
public class StoresController {
  private final StoresService storesService;

  @GetMapping
  public ResponseEntity<List<StoresDto>> listAll() {
    return ResponseEntity.ok(storesService.listAll());
  }

  @GetMapping("/{storeName}")
  public ResponseEntity<StoresDto> findProdName(
      @PathVariable(name = "storeName") final String prodName) {
    return storesService
        .findProdName(prodName)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Void> createProduct(@RequestBody final StoresDto storesDto) {

    storesService.createProduct(storesDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping(value = "/{storeId}", consumes = "application/json")
  public ResponseEntity<Void> update(
      @PathVariable(name = "storeId") Long storeId, @RequestBody final StoresDto storesDto) {
    storesService.updateProduct(storeId, storesDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{storeId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable(name = "storeId") final Long storeId) {
    storesService.deleteProduct(storeId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
