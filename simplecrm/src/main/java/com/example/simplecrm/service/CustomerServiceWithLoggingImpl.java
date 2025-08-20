package com.example.simplecrm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.simplecrm.entity.Customer;
import com.example.simplecrm.entity.Interaction;
import com.example.simplecrm.exception.CustomerNotFoundException;
import com.example.simplecrm.repository.CustomerRepository;
import com.example.simplecrm.repository.InteractionRepository;

@Service
public class CustomerServiceWithLoggingImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceWithLoggingImpl.class);

    private CustomerRepository customerRepository;
    private InteractionRepository interactionRepository;

    public CustomerServiceWithLoggingImpl(CustomerRepository customerRepository,
            InteractionRepository interactionRepository) {
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
    // // Find the index/position of the customer based on id
    // // Retrieve the customer object and return
    // Customer foundCustomer = customerRepository.findById(id).get();
    // return foundCustomer;
    // }
    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    // Read All
    @Override
    public List<Customer> getAllCustomers() {
        logger.info("👍👍CustomerServiceWithLoggingImpl.getAllCustomers() called");
        List<Customer> allCustomers = customerRepository.findAll();
        return (List<Customer>) allCustomers;
    }

    // Update
    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer selectedCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        // update the customer retrieved from the database
        selectedCustomer.setFirstName(customer.getFirstName());
        selectedCustomer.setLastName(customer.getLastName());
        selectedCustomer.setEmail(customer.getEmail());
        selectedCustomer.setContactNo(customer.getContactNo());
        selectedCustomer.setJobTitle(customer.getJobTitle());
        selectedCustomer.setYearOfBirth(customer.getYearOfBirth());
        // save the updated customer back to the database
        return customerRepository.save(selectedCustomer);
    }

    // Delete
    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Interaction addInteractionToCustomer(Long id, Interaction interaction) {
        // Retrieve the customer from the database
        Customer selectedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
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
