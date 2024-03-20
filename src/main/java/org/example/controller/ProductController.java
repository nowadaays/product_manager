package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.service.ProductNotificationService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductNotificationService productNotificationService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto){
        Product product = productService.create(dto);
        productNotificationService.sendProductCreated(product);
        return mappingResponseProduct(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> readAll(){
        return mappingResponseListProduct(productService.readAll());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> readByCategoryId(@PathVariable Long id){
        return mappingResponseListProduct(productService.readByCategoryId(id));
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product){
        Product updatedProduct = productService.update(product);
        productNotificationService.sendProductUpdate(updatedProduct);
        return mappingResponseProduct(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        productService.delete(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<Product> mappingResponseProduct(Product product){
        return new ResponseEntity<>(product , HttpStatus.OK);
    }

    private ResponseEntity<List<Product>> mappingResponseListProduct(List<Product> products){
        return new ResponseEntity<>(products , HttpStatus.OK);
    }
}
