package com.app.ecom.service;

import com.app.ecom.dao.ProductRepo;
import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import com.app.ecom.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<ProductResponse> findAll(){
        return List.of(new ProductResponse());
    }

    public ProductResponse findById(Long id){
        return new ProductResponse();
    }

    public ProductResponse addProduct(ProductRequest  productRequest){
        Product product = new Product();
        System.out.println("ProductService:addProduct =  " +  productRequest);
        mapProductRequestToProduct(product, productRequest);
        Product savedProduct = productRepo.save(product);
        return mapToProductResponse(savedProduct);
    }

    // UTIL method
    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setCategory(product.getCategory());
        productResponse.setDescription(product.getDescription());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setPrice(product.getPrice());
        productResponse.setStockQuantity(product.getStockQuantity());
        productResponse.setActive(product.getActive());
        return productResponse;
    }

    // UTIL method
    private void mapProductRequestToProduct(Product product, ProductRequest productRequest){
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());
    }

    public Optional<ProductResponse> updateProduct(ProductRequest productRequest, Long id) {
        return productRepo.findById(id).map(existing -> {
            mapProductRequestToProduct(existing, productRequest);
            Product savedProduct = productRepo.save(existing);
            return mapToProductResponse(savedProduct);
        });
    }
}
