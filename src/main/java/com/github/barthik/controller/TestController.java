package com.github.barthik.controller;

import com.github.barthik.model.Customer;
import com.github.barthik.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@RestController
public class TestController {

    private final Environment environment;
    private final CustomerRepository customerRepository;

    @Autowired
    public TestController(Environment environment, CustomerRepository customerRepository) {
        this.environment = environment;
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/")
    public String test() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        String port = environment.getProperty("local.server.port");
        return new StringBuilder()
                .append("[")
                .append(LocalDateTime.now().toString())
                .append("] ")
                .append(hostname != null ? hostname : "[unknown]")
                .append(" : ")
                .append(port != null ? port : "[unknown]")
                .toString();
    }

    @GetMapping(value = "/customers")
    public Iterable<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}
