package com.borlok.service;

import com.borlok.model.Account;
import com.borlok.model.Customer;
import com.borlok.repository.CustomerRepository;
import com.borlok.repository.hibernate.JpaCustomerRepository;

import java.util.List;

public class CustomerService {
    private CustomerRepository repository = new JpaCustomerRepository();

    public Customer create(Customer customer) {
        return repository.create(customer);
    }

    public List<Customer> getAll() {
        List<Customer> customers = repository.getAll();
        for (Customer x : customers)
            x.getAccount().setCustomer(null);
        return customers;
    }

    public Customer getById(int id) {
        Customer customer = repository.getById(id);
        customer.getAccount().setCustomer(null);
        return customer;
    }

    public Customer update(Customer customer) {
        return repository.update(customer);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
