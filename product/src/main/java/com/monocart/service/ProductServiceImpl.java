package com.monocart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monocart.entity.Product;
import com.monocart.entity.ProductDTO;
import com.monocart.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = new Product(null, dto.getName(), dto.getDescription(), dto.getCategory(), dto.getPrice(), dto.getStock());
        Product saved = productRepository.save(product);
        return new ProductDTO(saved.getId(), saved.getName(), saved.getDescription(), saved.getCategory(), saved.getPrice(), saved.getStock());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product p = productRepository.findById(id).orElseThrow();
        return new ProductDTO(p.getId(), p.getName(), p.getDescription(), p.getCategory(), p.getPrice(), p.getStock());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getDescription(), p.getCategory(), p.getPrice(), p.getStock()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        Product updated = productRepository.save(product);
        return new ProductDTO(updated.getId(), updated.getName(), updated.getDescription(), updated.getCategory(), updated.getPrice(), updated.getStock());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getDescription(), p.getCategory(), p.getPrice(), p.getStock()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category).stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getDescription(), p.getCategory(), p.getPrice(), p.getStock()))
                .collect(Collectors.toList());
    }
}