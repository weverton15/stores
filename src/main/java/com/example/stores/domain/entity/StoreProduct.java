package com.example.stores.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;

import javax.persistence.*;

@Entity(name = "store_product")
@AuditTable(value = "store_product")
@Getter
@Setter
public class StoreProduct extends AbstractAuditingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_product_generator")
  @SequenceGenerator(
      name = "store_product_generator",
      sequenceName = "store_product_sequence",
      initialValue = 1000,
      allocationSize = 1)
  @Column(name = "store_product_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "fk_store_id", referencedColumnName = "store_id")
  private Stores stores;

  @OneToOne
  @JoinColumn(name = "fk_prod_id", referencedColumnName = "prod_id")
  private Products products;

  @Column(name = "status")
  private String status;
}
