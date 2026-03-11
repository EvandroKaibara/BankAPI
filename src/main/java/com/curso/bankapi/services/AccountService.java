package com.curso.bankapi.services;

import com.curso.bankapi.models.Account;
import com.curso.bankapi.models.Customer;
import com.curso.bankapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    //Injetar Dependencias
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    //Criar uma conta
    public Account createAccount(Integer customerId){
        //Buscando o cliente que sera o dono da conta
        Customer customer = customerService.customerById(customerId);

        //Criando a conta
        Account newAccount = new Account();
        newAccount.setCustomer(customer);

        //Tentativa de gravar a conta no banco de dados
        try{
            accountRepository.save(newAccount);
            System.out.println("Conta criada com sucesso!");
        } catch (Exception excep) {
            System.out.println("Erro ao criar a conta:");
            System.out.println(excep);
        }

        return newAccount;
    }

    //Buscar uma conta por num de conta
    public Account accountByAcNumber(UUID acNumber){
        //Criando uma conta vazia para ser populada
        Account foundAccount = new Account();

        //Tentativa de popular a conta
        try{
            foundAccount = accountRepository.findById(acNumber).get();
            System.out.println(acNumber);
        } catch (Exception excep) {
            System.out.println("Erro ao buscar conta:");
            System.out.println(excep);
        }

        return foundAccount;
    }

    //Listar todas as contas
    public List<Account> allAccounts(){
        List<Account> accounts = new ArrayList<>();

        accountRepository.findAll().forEach(account -> {
            accounts.add(account);
            System.out.println(account);
        });

        return accounts;
    }

    //Trazer um saldo por num de conta
    public Float balanceByAcNumber(UUID acNumber){
        Float balance = accountByAcNumber(acNumber).getBalance();

        System.out.println("O saldo da conta é de: " + balance);

        return balance;
    }

    //Atualizar o saldo de uma conta
    public void updateBalance(Account account, Float newBalance){
        account.setBalance(newBalance);
        accountRepository.save(account);
        System.out.println("Novo saldo é de: " + newBalance);
    }
}
