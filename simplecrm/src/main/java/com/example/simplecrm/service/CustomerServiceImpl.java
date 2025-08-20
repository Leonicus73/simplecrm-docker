package com.example.simplecrm.service;

import java.util.List;
// import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.simplecrm.entity.Customer;
import com.example.simplecrm.entity.Interaction;
import com.example.simplecrm.exception.CustomerNotFoundException;
import com.example.simplecrm.repository.CustomerRepository;
import com.example.simplecrm.repository.InteractionRepository;

@Primary
@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerRepository customerRepository;
  private InteractionRepository interactionRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository, InteractionRepository interactionRepository) {
    this.customerRepository = customerRepository;
    this.interactionRepository = interactionRepository;
  }

  // CRUD
  // Create
  @Override
  public Customer createCustomer(Customer customer) {
    Customer newCustomer = customerRepository.save(customer);
    return newCustomer;
  }

  // Read
  // Read One
  // @Override
  // public Customer getCustomer(Long id) {
  // Customer foundCustomer = customerRepository.findById(id).get();
  // return foundCustomer;
  // }

  // @Override
  // public Customer getCustomer(Long id) {
  // Optional<Customer> optionalCustomer = customerRepository.findById(id);
  // if (optionalCustomer.isPresent()) {
  // // If the Optional contains a value, unwrap it and return the Customer object
  // Customer foundCustomer = optionalCustomer.get();
  // return foundCustomer;
  // }

  // throw new CustomerNotFoundException(id);
  // }

  @Override
  public Customer getCustomer(Long id) {
    return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
  }

  // Read All
  @Override
  public List<Customer> getAllCustomers() {
    System.out.println("👍CustomerServiceImpl.getAllCustomers() called");
    List<Customer> allCustomers = customerRepository.findAll();
    return allCustomers;
  }

  // Update
  @Override
  public Customer updateCustomer(Long id, Customer customer) {
    // retrieve the customer from the database
    Customer customerToUpdate = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    // update the customer retrieved from the database
    customerToUpdate.setFirstName(customer.getFirstName());
    customerToUpdate.setLastName(customer.getLastName());
    customerToUpdate.setEmail(customer.getEmail());
    customerToUpdate.setContactNo(customer.getContactNo());
    customerToUpdate.setJobTitle(customer.getJobTitle());
    customerToUpdate.setYearOfBirth(customer.getYearOfBirth());
    // save the updated customer back to the database
    return customerRepository.save(customerToUpdate);
  }

  // Delete
  @Override
  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

  @Override
  public Interaction addInteractionToCustomer(Long id, Interaction interaction) {
    // Retrieve the customer from the database
    Customer selectedCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    // Add the customer to the interaction
    interaction.setCustomer(selectedCustomer);
    return interactionRepository.save(interaction);
  }

  @Override
  public List<Customer> findByFirstName(String firstName) {
    List<Customer> foundCustomers = customerRepository.findByFirstNameIgnoreCase(firstName);
    // List<Customer> foundCustomers =
    // customerRepository.findByFirstName(firstName);
    return foundCustomers;
  }

  @Override
  public List<Customer> findByFirstNameContainingIgnoreCase(String firstName) {
    List<Customer> foundCustomers = customerRepository.findByFirstNameContainingIgnoreCase(firstName);
    return foundCustomers;
  }

  @Override
  public List<Customer> findByInteractionsIsEmpty() {
    return customerRepository.findByInteractionsIsEmpty();
  }
}