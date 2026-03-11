package com.curso.bankapi.services;

import com.curso.bankapi.models.Account;
import com.curso.bankapi.models.Transaction;
import com.curso.bankapi.models.TransactionType;
import com.curso.bankapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    //Injetar Dependencia
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    //Deposito
    public Transaction deposit(UUID acNumber, Float amout){
        //Indicando a conta a ser utilizada
        Account account = accountService.accountByAcNumber(acNumber);

        //Criando novo saldo
        Float newBalance = account.getBalance() + amout;

        //Criando transação a ser salva
        Transaction transaction = new Transaction(amout, TransactionType.DEPOSIT, account);

        //Tentando salvar a transação no banco de dados
        try{
            //Atualizando saldo
            accountService.updateBalance(account, newBalance);

            //Salvado a transação no banco
            transactionRepository.save(transaction);

            System.out.println("Deposito efetuado com sucesso!");
        } catch (Exception excep) {
            System.out.println("Erro ao efetuar transação:");
            System.out.println(excep);
        }

        return transaction;
    }

    //Saque
    public Transaction withdraw(UUID acNumber, Float amout){
        //Indicando a conta a ser utilizada
        Account account = accountService.accountByAcNumber(acNumber);

        //Criando novo saldo
        Float newBalance = account.getBalance() - amout;

        //Criando transação a ser salva
        Transaction transaction = new Transaction(amout, TransactionType.WITHDRAW, account);

        //Tentando salvar a transação no banco de dados
        try{
            //Atualizando saldo
            accountService.updateBalance(account, newBalance);

            //Salvado a transação no banco
            transactionRepository.save(transaction);

            System.out.println("Saque efetuado com sucesso!");
        } catch (Exception excep) {
            System.out.println("Erro ao efetuar transação:");
            System.out.println(excep);
        }

        return transaction;
    }

    //Listar todas as transações por num de conta
    public List<Transaction> listByAccount(UUID acNumber){
        List<Transaction> trasactions = new ArrayList<>();
        transactionRepository.findByAccountAcNumber(acNumber).forEach(transaction -> {
            trasactions.add(transaction);
            System.out.println(transaction);
        });

        return trasactions;
    }
}
