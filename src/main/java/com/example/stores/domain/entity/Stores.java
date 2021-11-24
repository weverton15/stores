package com.example.stores.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;

import javax.persistence.*;

@Entity(name = "stores")
@AuditTable(value = "stores")
@Getter
@Setter
public class Stores extends AbstractAuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_generator")
  @SequenceGenerator(
      name = "store_generator",
      sequenceName = "store_sequence",
      initialValue = 1000,
      allocationSize = 1)
  @Column(name = "store_id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "country")
  private String country;
}
