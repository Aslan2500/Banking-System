package com.company;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private final String name;
    @Column(name = "passport")
    private final int passport;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Account account;

    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public int getPassport() {
        return passport;
    }

    @Override
    public String toString() {
        return "Name - '" + name + '\'' +
                ", id - " + passport +
                '}';
    }
}
