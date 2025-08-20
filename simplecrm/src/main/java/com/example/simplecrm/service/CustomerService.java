package com.example.simplecrm.service;

import java.util.List;

import com.example.simplecrm.entity.Customer;
import com.example.simplecrm.entity.Interaction;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomer(Long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    // Nested route - add interaction to customer
    Interaction addInteractionToCustomer(Long id, Interaction interaction);

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    List<Customer> findByInteractionsIsEmpty();
}
