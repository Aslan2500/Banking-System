package com.company;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User user;
    @Column(name = "password")
    private String password;
    @Column(name = "amount_of_money")
    private int amountOfMoney;
    @Column(name = "amount_of_bitcoin")
    private double amountOfBitcoin;

    public Account(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public Account() {
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public double getAmountOfBitcoin() {
        return amountOfBitcoin;
    }

    public User getUser() {
        return user;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public void setAmountOfBitcoin(double amountOfBitcoin) {
        this.amountOfBitcoin = amountOfBitcoin;
    }

    public String getPassword() {
        return password;
    }
}
