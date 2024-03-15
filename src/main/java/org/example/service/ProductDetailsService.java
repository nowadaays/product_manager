package org.example.service;

import org.example.entity.ProductDetails;
import org.example.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailsService {

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    public ProductDetails saveProductDetails(ProductDetails productDetails){
        return productDetailsRepository.save(productDetails);
    }

    public Optional<ProductDetails> getProductDetailsById(Long id){
        return productDetailsRepository.findById(id);
    }

    public void deleteProductDetails(Long id){
        productDetailsRepository.deleteById(id);
    }

    public List<ProductDetails> getAllProductDetails(){
        return productDetailsRepository.findAll();
    }
}
