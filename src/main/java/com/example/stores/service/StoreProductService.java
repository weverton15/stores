package com.example.stores.service;

import com.example.stores.domain.entity.Products;
import com.example.stores.domain.entity.StoreProduct;
import com.example.stores.domain.model.StoreProductDto;
import com.example.stores.repository.ProductsRepository;
import com.example.stores.repository.StoreProductRepository;
import com.example.stores.service.mapper.StoreProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreProductService {
  private final StoreProductRepository repository;
  private final ProductsRepository productsRepository;
  private final StoreProductMapper mapper;

  public List<StoreProductDto> listAll() {
    var stores = repository.findAll();
    if (CollectionUtils.isEmpty((Collection<?>) stores))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    return ((Collection<StoreProduct>) stores)
        .stream().map(mapper::fromEntity).collect(Collectors.toList());
  }

  public Optional<StoreProductDto> findProdId(Long prodId) {
    final Products product =
        productsRepository
            .findById(prodId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    StoreProduct result =
        Optional.ofNullable(repository.findByProductsOrderByProductsDesc(product))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return Optional.ofNullable(mapper.fromEntity(result));
  }

  public void includeStoreProduct(StoreProductDto productsDto) {
    repository.save(mapper.toEntity(productsDto));
  }

  public void updateStatus(Long id, StoreProductDto dto) {
    if (!dto.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    final StoreProduct storeProduct =
        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    storeProduct.setStatus(
        Optional.ofNullable(dto.getStatus()).isPresent()
            ? dto.getStatus()
            : storeProduct.getStatus());

    repository.save(storeProduct);
  }

  public void delete(Long id) {
    repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    repository.deleteById(id);
  }
}
