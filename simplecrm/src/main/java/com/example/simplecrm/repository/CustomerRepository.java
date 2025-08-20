package com.example.simplecrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simplecrm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // JPA Query Creation from Method Name
    // [Operation]By[FieldName][Condition]
    // Custom query to find all customers with a specific first name
    List<Customer> findByFirstName(String firstName);

    // Exact match for first name, case insensitive
    List<Customer> findByFirstNameIgnoreCase(String firstName);

    // Exact match - Can find by fields OR
    List<Customer> findByFirstNameOrLastName(String firstName, String lastName);

    // Fuzzy search for first name
    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    // findByFirstNameStartingWith(String firstName)

    // Find all customers with no interactions
    List<Customer> findByInteractionsIsEmpty();

}
