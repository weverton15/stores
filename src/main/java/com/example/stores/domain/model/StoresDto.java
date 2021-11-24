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
public class StoresDto {
  private Long id;
  private String name;
  private String address;
  private String city;
  private String country;

  @JsonIgnore
  public boolean isEmpty() {
    return Optional.ofNullable(name).isPresent()
        || Optional.ofNullable(address).isPresent()
        || Optional.ofNullable(city).isPresent()
        || Optional.ofNullable(country).isPresent();
  }
}
