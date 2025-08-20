package com.example.simplecrm.config;

import org.springframework.stereotype.Component;

import com.example.simplecrm.entity.Customer;
import com.example.simplecrm.repository.CustomerRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
  private CustomerRepository customerRepository;

  public DataLoader(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @PostConstruct
  public void loadData() {
    // clear the database first
    customerRepository.deleteAll();

    // load data here
    customerRepository.save(Customer.builder().firstName("Tony").lastName("Stark").contactNo("12345678").yearOfBirth(1970).build());
    customerRepository.save(Customer.builder().firstName("Bruce").lastName("Banner").contactNo("76547870").yearOfBirth(1965).build());
    customerRepository.save(Customer.builder().firstName("Peter").lastName("Parker").contactNo("87654321").yearOfBirth(1998).build());
    customerRepository.save(Customer.builder().firstName("Stephen").lastName("Strange").contactNo("12345678").yearOfBirth(1955).build());

  }
}
