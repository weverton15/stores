package com.example.stores.repository;

import com.example.stores.domain.entity.Stores;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository
    extends PagingAndSortingRepository<Stores, Long>, JpaSpecificationExecutor<Stores> {
  Stores findByName(String name);
}
