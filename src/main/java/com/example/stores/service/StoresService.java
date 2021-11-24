package com.example.stores.service;

import com.example.stores.domain.entity.Stores;
import com.example.stores.domain.model.StoresDto;
import com.example.stores.repository.StoresRepository;
import com.example.stores.service.mapper.StoresMapper;
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
public class StoresService {
  private final StoresRepository storesRepository;
  private final StoresMapper storesMapper;

  public List<StoresDto> listAll() {
    var stores = storesRepository.findAll();
    if (CollectionUtils.isEmpty((Collection<?>) stores))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    return ((Collection<Stores>) stores)
        .stream().map(storesMapper::fromEntity).collect(Collectors.toList());
  }

  public Optional<StoresDto> findProdName(String prodName) {
    Stores result =
        Optional.ofNullable(storesRepository.findByName(prodName))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return Optional.ofNullable(storesMapper.fromEntity(result));
  }

  public void createProduct(StoresDto productsDto) {
    storesRepository.save(storesMapper.toEntity(productsDto));
  }

  public void updateProduct(Long storeId, StoresDto storesDto) {
    if (!storesDto.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    final Stores stores =
        storesRepository
            .findById(storeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    stores.setName(
        Optional.ofNullable(storesDto.getName()).isPresent()
            ? storesDto.getName()
            : stores.getName());
    stores.setAddress(
        Optional.ofNullable(storesDto.getAddress()).isPresent()
            ? storesDto.getAddress()
            : stores.getAddress());
    stores.setCity(
        Optional.ofNullable(storesDto.getCity()).isPresent()
            ? storesDto.getCity()
            : stores.getCity());
    stores.setCountry(
        Optional.ofNullable(storesDto.getCountry()).isPresent()
            ? storesDto.getCountry()
            : stores.getCountry());

    storesRepository.save(stores);
  }

  public void deleteProduct(Long storeId) {
    storesRepository
        .findById(storeId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    storesRepository.deleteById(storeId);
  }
}
