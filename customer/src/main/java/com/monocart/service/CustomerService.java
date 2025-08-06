package com.monocart.service;


import java.util.List;

import com.monocart.DTO.CustomerDTO;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO dto);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(Long id, CustomerDTO dto);
    void deleteCustomer(Long id);
    CustomerDTO getCustomerById(Long id);
}
