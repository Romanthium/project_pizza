package com.example.projectpizza.service;

import com.example.projectpizza.model.Customer;
import com.example.projectpizza.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer fineOne(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    public void update(int id, Customer updatedCustomer) {
        updatedCustomer.setId(id);
        customerRepository.save(updatedCustomer);
    }

    @Transactional
    public void delete(int id) {
        customerRepository.deleteById(id);
    }
}

