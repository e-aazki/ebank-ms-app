package com.enset.customerservice.service;

import com.enset.customerservice.entities.Customer;
import com.enset.customerservice.repository.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(description = "Get All Customers" )
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @McpTool(description = "Find Customer By Id" )
    public Customer findCustomerById(@McpToolParam(description = "The Customer Id") Long id) {
        return customerRepository.findById(id).
                orElseThrow(()->new RuntimeException("Customer not found"));
    }

    @McpTool(description = "Save a new Customer" )
    public Customer saveCustomer(@McpToolParam(description = "The Customer to save (name,email) ") Customer customer) {
        return customerRepository.save(customer);
    }
}
