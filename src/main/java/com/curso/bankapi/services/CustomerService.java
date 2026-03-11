package com.curso.bankapi.services;

import com.curso.bankapi.models.Customer;
import com.curso.bankapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    //Injetar Dependencia
    @Autowired
    private CustomerRepository customerRepository;

    //Criar novo cliente
    public Customer createCustomer(Customer customer){
        try{
            customerRepository.save(customer);
            System.out.println("Cliente criado com sucesso!");
        } catch (Exception exception){
            System.out.println("Erro ao criar cliente");
            System.out.println(exception);
        }

        return customer;
    }

    //Buscar cliente por ID
    public Customer customerById(Integer customerId){
        //Criando um cliente vazio
        Customer foundCustomer = new Customer();

        try{
            foundCustomer = customerRepository.findById(customerId).get();
            System.out.println(foundCustomer);
        } catch (Exception exception) {
            System.out.println("Erro ao buscar cliente");
            System.out.println(exception);
        }

        return foundCustomer;
    }

    //Listar todos os clientes
    public List<Customer> allCustomers(){
        List<Customer> customers = new ArrayList<>();

        customerRepository.findAll().forEach(customer -> {
            customers.add(customer);
            System.out.println(customer);
        });

        return customers;
    }
}
