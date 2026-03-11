package com.curso.bankapi.models;

import jakarta.persistence.*;

@Entity @Table(name = "transacoes")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_trasacao")
    private Integer tsNumber;

    @Column(name = "quantia")
    private Float amout;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne @JoinColumn(name = "conta")
    private Account account;

    //Constructor
    public Transaction(Float amout, TransactionType type, Account account) {
        this.amout = amout;
        this.type = type;
        this.account = account;
    }

    public Transaction() {
    }

    //Getters
    public Integer getTsNumber() {
        return tsNumber;
    }

    public Float getAmout() {
        return amout;
    }

    public TransactionType getType() {
        return type;
    }

    public Account getAccount() {
        return account;
    }

    //Setters
    public void setAmout(Float amout) {
        this.amout = amout;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tsNumber=" + tsNumber +
                ", amout=" + amout +
                ", type=" + type +
                ", account=" + account +
                '}';
    }
}
