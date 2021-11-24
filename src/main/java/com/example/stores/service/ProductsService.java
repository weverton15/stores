package com.example.stores.service;

import com.example.stores.domain.entity.Products;
import com.example.stores.domain.model.ProductsDto;
import com.example.stores.repository.ProductsRepository;
import com.example.stores.service.mapper.ProductsMapper;
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
public class ProductsService {
  private final ProductsRepository productsRepository;
  private final ProductsMapper productsMapper;

  public List<ProductsDto> listAll() {
    var products = productsRepository.findAll();
    if (CollectionUtils.isEmpty((Collection<?>) products))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    return ((Collection<Products>) products)
        .stream().map(productsMapper::fromEntity).collect(Collectors.toList());
  }

  public Optional<ProductsDto> findProdName(String prodName) {
    Products result =
        Optional.ofNullable(productsRepository.findByProdName(prodName))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return Optional.ofNullable(productsMapper.fromEntity(result));
  }

  public void createProduct(ProductsDto productsDto) {
    productsRepository.save(productsMapper.toEntity(productsDto));
  }

  public void updateProduct(Long prodId, ProductsDto productsDto) {
    if (!productsDto.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    final Products product =
        productsRepository
            .findById(prodId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    product.setProdName(
        Optional.ofNullable(productsDto.getProdName()).isPresent()
            ? productsDto.getProdName()
            : product.getProdName());
    product.setQuantity(
        Optional.ofNullable(productsDto.getQuantity()).isPresent()
            ? productsDto.getQuantity()
            : product.getQuantity());

    productsRepository.save(product);
  }

  public void deleteProduct(Long prodId) {
    productsRepository
        .findById(prodId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    productsRepository.deleteById(prodId);
  }
}
