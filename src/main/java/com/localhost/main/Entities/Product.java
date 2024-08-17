package com.localhost.main.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "maker_id", nullable = false, foreignKey = @ForeignKey(name = "pdt_maker_fk"))
	@JsonIgnore
	private Maker maker;
}
