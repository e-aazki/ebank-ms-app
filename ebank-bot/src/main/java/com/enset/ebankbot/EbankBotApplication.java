package com.enset.ebankbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EbankBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBotApplication.class, args);
    }
/*
    http://localhost:8761/
    http://localhost:9999/CUSTOMER-SERVICE/customers
    http://localhost:9999/EBANK-BOT/chat?query=liste%20des%20comptes
    http://localhost:9999/EBANK-SERVICE/accounts
    */


}
