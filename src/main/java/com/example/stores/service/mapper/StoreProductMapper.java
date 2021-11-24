package com.example.stores.service.mapper;

import com.example.stores.domain.entity.StoreProduct;
import com.example.stores.domain.model.StoreProductDto;
import com.example.stores.repository.ProductsRepository;
import com.example.stores.repository.StoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreProductMapper {

  private final StoresRepository storesRepository;
  private final ProductsRepository productsRepository;

  public StoreProductDto fromEntity(StoreProduct storeProduct) {
    return StoreProductDto.builder()
        .prodId(storeProduct.getProducts().getId())
        .prodName(storeProduct.getProducts().getProdName())
        .storeId(storeProduct.getStores().getId())
        .storeName(storeProduct.getStores().getName())
        .status(storeProduct.getStatus())
        .build();
  }

  public StoreProduct toEntity(StoreProductDto dto) {
    var entity = new StoreProduct();
    var product = productsRepository.findById(dto.getProdId()).get();
    var store = storesRepository.findById(dto.getStoreId()).get();
    entity.setProducts(product);
    entity.setStores(store);
    entity.setStatus(dto.getStatus());
    entity.setCreatedBy("adm");
    return entity;
  }
}
