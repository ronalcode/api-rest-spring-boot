package com.localhost.main.controllers.dto;

import java.util.List;

import com.localhost.main.Entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakerDTO {
	private Long id;
	private String name;
	private List<Product> products;
}
