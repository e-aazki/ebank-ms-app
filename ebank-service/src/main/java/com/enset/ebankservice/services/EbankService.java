package com.enset.ebankservice.services;

import com.enset.ebankservice.entities.BankAccount;
import com.enset.ebankservice.feign.CustomerRestClient;
import com.enset.ebankservice.model.Customer;
import com.enset.ebankservice.repository.BankAccountRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @McpTool(description = "Get all Bank Accounts")
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @McpTool(description = "Get a Bank Account By Id")
    public BankAccount getBankAccountById(@McpToolParam(description = "The bank account ID") String id) {
        BankAccount bankAccount= bankAccountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Bank account with id " + id + " not found")
        );
        bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }

    @McpTool(description = "Save a new bank account")
    public BankAccount save(@McpToolParam(description = "The bank account to save (balance ,type , customerId )") BankAccount bankAccount) {
        try {
            Customer customer= customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return bankAccountRepository.save(bankAccount);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
