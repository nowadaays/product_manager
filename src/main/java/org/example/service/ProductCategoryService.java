package org.example.service;

import org.example.entity.ProductCategory;
import org.example.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public ProductCategory saveProductCategory(ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

    public void deleteProductCategory(Long id){
        productCategoryRepository.deleteById(id);
    }

    public List<ProductCategory> getAllProductCategories(){
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> getProductCategoryById(Long id){
        return productCategoryRepository.findById(id);
    }
}
