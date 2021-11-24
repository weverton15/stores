package com.example.stores.repository;

import com.example.stores.domain.entity.Products;
import com.example.stores.domain.entity.StoreProduct;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreProductRepository
    extends PagingAndSortingRepository<StoreProduct, Long>,
        JpaSpecificationExecutor<StoreProduct> {
  StoreProduct findByProductsOrderByProductsDesc(Products products);
}
