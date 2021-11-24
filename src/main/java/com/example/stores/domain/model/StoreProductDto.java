package com.example.stores.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Setter
@ToString
@Builder
public class StoreProductDto {
  private Long storeId;
  private String storeName;
  private Long prodId;
  private String prodName;
  private String status;

  @JsonIgnore
  public boolean isEmpty() {
    return Optional.ofNullable(prodId).isPresent()
        || Optional.ofNullable(storeId).isPresent()
        || Optional.ofNullable(status).isPresent();
  }
}
