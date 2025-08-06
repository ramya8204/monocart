package com.monocart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocart.DTO.CustomerDTO;
import com.monocart.entity.Customer;
import com.monocart.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = new Customer(null, dto.getName(), dto.getEmail(), dto.getAddress());
        Customer saved = repository.save(customer);
        return new CustomerDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getAddress());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return repository.findAll().stream()
                .map(c -> new CustomerDTO(c.getId(), c.getName(), c.getEmail(), c.getAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer c = repository.findById(id).orElseThrow();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setAddress(dto.getAddress());
        Customer updated = repository.save(c);
        return new CustomerDTO(updated.getId(), updated.getName(), updated.getEmail(), updated.getAddress());
    }

    @Override
    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer c = repository.findById(id).orElseThrow();
        return new CustomerDTO(c.getId(), c.getName(), c.getEmail(), c.getAddress());
    }
}


