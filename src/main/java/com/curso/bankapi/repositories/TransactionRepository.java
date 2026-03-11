package com.curso.bankapi.repositories;

import com.curso.bankapi.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    //Metodo customizado para consulta de transações por num de conta
    List<Transaction> findByAccountAcNumber(UUID acNumber);
}
