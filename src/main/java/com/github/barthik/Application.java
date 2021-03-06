package com.github.barthik;

import com.github.barthik.model.Customer;
import com.github.barthik.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootApplication
public class Application {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        customerRepository.save(new Customer(null, "Company 1"));
        customerRepository.save(new Customer(null, "Company 2"));
        customerRepository.save(new Customer(null, "Company 3"));

        try (FileOutputStream fos = new FileOutputStream("test-file.txt")) {
            fos.write("Hello, it's me...".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
