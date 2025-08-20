package com.example.simplecrm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.simplecrm.entity.Customer;
import com.example.simplecrm.exception.CustomerNotFoundException;
import com.example.simplecrm.repository.CustomerRepository;
// import com.example.simplecrm.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    // We need to mock the CustomerRepository
    // Because we don't want to test the repository layer
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks // Inject the mocks as dependencies into CustomerServiceImpl
    CustomerServiceImpl customerService; // Instantiated and injected by Mockito

    @Test
    public void createCustomerTest() {
        Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
                .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

        // Mock the save method of the customer repository
        when((customerRepository.save(customer))).thenReturn(customer);

        // 2. EXECUTE or ACT
        // Call the method that we want to test
        Customer savedCustomer = customerService.createCustomer(customer);

        // 3. ASSERT
        // Compare the actual result with the expected result
        assertEquals(customer, savedCustomer, "The saved customer should be the same as the new customer");

        // Also verify that the save method of the customer repository is called once
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testGetCustomer() {
        // 1. SETUP
        // Create a new customer
        Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
                .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // 2. EXECUTE
        Customer retrievedCustomer = customerService.getCustomer(customerId);

        // 3. ASSERT
        assertEquals(customer, retrievedCustomer, "The retrieved customer should match the one we set up ");

    }

    @Test
    void testGetCustomerNotFound() {
        // ARRANGE
        Long customerId = 1L;
        // MOCK
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        // ACT & ASSERT
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(customerId));
    }
}
