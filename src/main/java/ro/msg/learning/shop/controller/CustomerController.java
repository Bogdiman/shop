package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping
    @ResponseBody
    public Customer saveCustomer(@RequestBody Customer cust) {
        return customerRepository.save(cust);
    }

    @GetMapping
    @ResponseBody
    public List<Customer> getCustomers() {
        return new ArrayList<>(customerRepository.findAll());
    }
}
