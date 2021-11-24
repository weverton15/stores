package com.example.stores.repository;

import com.example.stores.domain.entity.Products;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository
    extends PagingAndSortingRepository<Products, Long>, JpaSpecificationExecutor<Products> {
  Products findByProdName(String name);
}
