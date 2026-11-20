package com.enset.customerservice;

import com.enset.customerservice.entities.Customer;
import com.enset.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CustomerService customerService) {
        //System.out.println("CustomerServiceApplication init");
        //http://localhost:8056/swagger-ui/index.html
        return args -> {
            List<String> names = List.of("Mohamed","Imane","Yassine");
            names.forEach(name->{
                customerService.saveCustomer(Customer.builder()
                        .name(name)
                        .email(name+"@gmail.com").build());
            });
        };
    }
}
