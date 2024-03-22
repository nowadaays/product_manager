package org.example.service;

import lombok.AllArgsConstructor;
import org.example.ChangeLogProducer;
import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ChangeLogProducer changeLogProducer;

    public Product create(ProductDTO dto){
        Product product = productRepository.save(Product.builder()
                .name(dto.getName())
                .amount(dto.getAmount())
                .category(categoryService.readById(dto.getCategoryId()))
                .build());
        String changeLogMessage = "Product created: " + product.getId();
        changeLogProducer.sendChangeLog(changeLogMessage);

        return product;

    }

    public List<Product> readAll(){
        return productRepository.findAll();
    }

    public List<Product> readByCategoryId(Long id){
        return productRepository.findByCategoryId(id);
    }

    public Product update(Product product){
        Product updatedProduct = productRepository.save(product);

        String changeLogMessage = "Product updated: " + updatedProduct.getId();
        changeLogProducer.sendChangeLog(changeLogMessage);

        return updatedProduct;
    }

    public void delete(Long id){
        productRepository.deleteById(id);
        String changeLogMessage = "Product deleted: " + id;
        changeLogProducer.sendChangeLog(changeLogMessage);
    }
}
