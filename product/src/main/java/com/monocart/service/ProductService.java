package com.monocart.service;

import java.util.List;

import com.monocart.entity.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(ProductDTO dto);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long id, ProductDTO dto);
    void deleteProduct(Long id);

    List<ProductDTO> searchByName(String name);
    List<ProductDTO> searchByCategory(String category);
}
