package com.example.stores.service.mapper;

import com.example.stores.domain.entity.Stores;
import com.example.stores.domain.model.StoresDto;
import org.springframework.stereotype.Service;

@Service
public class StoresMapper {
  public StoresDto fromEntity(Stores stores) {
    return StoresDto.builder()
        .id(stores.getId())
        .name(stores.getName())
        .address(stores.getAddress())
        .city(stores.getCity())
        .country(stores.getCountry())
        .build();
  }

  public Stores toEntity(StoresDto storesDto) {
    var entity = new Stores();
    entity.setName(storesDto.getName());
    entity.setAddress(storesDto.getAddress());
    entity.setCity(storesDto.getCity());
    entity.setCountry(storesDto.getCountry());
    entity.setCreatedBy("adm");
    return entity;
  }
}
