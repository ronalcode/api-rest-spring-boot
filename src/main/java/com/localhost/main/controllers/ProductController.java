package com.localhost.main.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localhost.main.Entities.Product;
import com.localhost.main.controllers.dto.ProductDTO;
import com.localhost.main.handler.ResponseHandler;
import com.localhost.main.services.IProductService;


@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	private IProductService productService;
	
	@GetMapping("/index")
	public ResponseEntity<?> index(){
		List<ProductDTO> products = productService.findAll()
				.stream()
				.map(product -> ProductDTO.builder()
					.id(product.getId())
					.name(product.getName())
					.price(product.getPrice())
					.maker(product.getMaker())
					.build()
				)
				.toList();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Optional<Product> productOptional = productService.findById(id);
		
		if(productOptional.isPresent()) {
			Product product = productOptional.get();
			
			ProductDTO productDTO = ProductDTO.builder()
					.id(product.getId())
					.name(product.getName())
					.price(product.getPrice())
					.maker(product.getMaker())
					.build();
			
			return ResponseHandler.generateResponse("Lista de usuarios", HttpStatus.OK, productDTO);
		}
		
		return ResponseHandler.generateResponse("Producto no encontrado", HttpStatus.NOT_FOUND, null);
	}
	
	@PostMapping("/store")
	public ResponseEntity<?> store(@RequestBody ProductDTO productDTO){
		if(productDTO.getName().isBlank() || productDTO.getPrice() == null || productDTO.getMaker() == null) {
			return ResponseHandler.generateResponse("Error en la solicitud", HttpStatus.BAD_REQUEST, null);
		}
		
		Product product = Product.builder()
				.name(productDTO.getName())
				.price(productDTO.getPrice())
				.maker(productDTO.getMaker())
				.build();
		
		productService.save(product);
		return ResponseHandler.generateResponse("Product created successfully", HttpStatus.CREATED, productDTO);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO)
	{
		Optional<Product> productOptional = productService.findById(id);
		if(productOptional.isPresent()) {
			if(productDTO.getName().isBlank() || productDTO.getPrice() == null || productDTO.getMaker() == null) {
				return ResponseHandler.generateResponse("Error en la solicitud", HttpStatus.BAD_REQUEST, null);
			}
			
			Product product = productOptional.get();
			product.setName(productDTO.getName());
			product.setPrice(productDTO.getPrice());
			product.setMaker(productDTO.getMaker());
			
			productService.save(product);
			
			return ResponseHandler.generateResponse("Product updated successfully", HttpStatus.OK, productDTO);
		}
		
		return ResponseHandler.generateResponse("Product not found", HttpStatus.NOT_FOUND, null);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id)
	{
		if(id != null)
		{
			productService.deleteById(id);
			return ResponseHandler.generateResponse("Product deleted", HttpStatus.OK, null);
		}
		
		return ResponseHandler.generateResponse("Bad request", HttpStatus.BAD_REQUEST, null);
	}
}
