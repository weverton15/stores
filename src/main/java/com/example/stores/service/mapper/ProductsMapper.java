package com.example.stores.service.mapper;

import com.example.stores.domain.entity.Products;
import com.example.stores.domain.model.ProductsDto;
import org.springframework.stereotype.Service;

@Service
public class ProductsMapper {
  public ProductsDto fromEntity(Products products) {
    return ProductsDto.builder()
        .id(products.getId())
        .prodName(products.getProdName())
        .quantity(products.getQuantity())
        .build();
  }

  public Products toEntity(ProductsDto productsDto) {
    var entity = new Products();
    entity.setProdName(productsDto.getProdName());
    entity.setQuantity(productsDto.getQuantity());
    entity.setCreatedBy("adm");
    return entity;
  }
}
