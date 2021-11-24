package com.example.stores.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;

import javax.persistence.*;

@Entity(name = "products")
@AuditTable(value = "products")
@Getter
@Setter
public class Products extends AbstractAuditingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
  @SequenceGenerator(
      name = "product_generator",
      sequenceName = "product_sequence",
      initialValue = 1000,
      allocationSize = 1)
  @Column(name = "prod_id")
  private Long id;

  @Column(name = "prod_name")
  private String prodName;

  @Column(name = "quantity")
  private Long quantity;
}
