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
public class ProductsDto {
  private Long id;
  private String prodName;
  private Long quantity;

  @JsonIgnore
  public boolean isEmpty() {
    return Optional.ofNullable(prodName).isPresent() || Optional.ofNullable(quantity).isPresent();
  }
}
