package com.company.options;

import com.company.Account;
import com.company.SessionInit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

public class SqlOptions implements OptionsForAccount {

    public SqlOptions() {
    }

    @Override
    public boolean depositMoney(int amountOfMoney, Account account) {
        SessionFactory factory = SessionInit.getSessionFactory();
        Session session = null;
        int rsl;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            String hql = "UPDATE Account SET amount_of_money = amount_of_money + " + amountOfMoney +
                    " WHERE users_id = " + account.getUser().getId();
            rsl = session.createQuery(hql).executeUpdate();
            session.getTransaction().commit();
        } finally {
            assert session != null;
            session.close();
            factory.close();
        }
        return rsl != -1;
    }

    @Override
    public boolean withdrawMoney(int amountOfMoney, Account account) {
        SessionFactory factory = SessionInit.getSessionFactory();
        Session session = null;
        int rsl;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            String hql = "UPDATE Account SET amount_of_money = amount_of_money - " + amountOfMoney +
                    " WHERE amount_of_money >= " + amountOfMoney +
                    " AND users_id = " + account.getUser().getId();
            rsl = session.createQuery(hql).executeUpdate();
            session.getTransaction().commit();
        } finally {
            assert session != null;
            session.close();
            factory.close();
        }
        if (rsl == 0) {
            throw new IllegalArgumentException("Not enough money");
        }
        return true;
    }

    @Override
    public boolean buyCrypto(int amountOfMoney, double bitcoinPrice, Account account) {
        SessionFactory factory = SessionInit.getSessionFactory();
        Session session = null;
        int rsl;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            String hql = "UPDATE Account SET amount_of_money = amount_of_money - " + amountOfMoney + "" +
                    ", amount_of_bitcoin = amount_of_bitcoin + " + (amountOfMoney / bitcoinPrice) +
                    " WHERE amount_of_money >= " + amountOfMoney +
                    " AND users_id = " + account.getUser().getId();
            rsl = session.createQuery(hql).executeUpdate();
            session.getTransaction().commit();
        } finally {
            assert session != null;
            session.close();
            factory.close();
        }
        if (rsl == 0) {
            throw new IllegalArgumentException("Not enough money");
        }
        return true;
    }

    @Override
    public boolean sellCrypto(int bitcoinPrice, double amountOfBitcoin, Account account) {
        SessionFactory factory = SessionInit.getSessionFactory();
        Session session = null;
        int rsl;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            String hql = "UPDATE Account SET amount_of_bitcoin = amount_of_bitcoin - " + amountOfBitcoin + "" +
                    ", amount_of_money = amount_of_money + " + (amountOfBitcoin * bitcoinPrice) +
                    " WHERE amount_of_bitcoin >= " + amountOfBitcoin +
                    " AND users_id = " + account.getUser().getId();
            rsl = session.createQuery(hql).executeUpdate();
            session.getTransaction().commit();
        } finally {
            assert session != null;
            session.close();
            factory.close();
        }
        if (rsl == 0) {
            throw new IllegalArgumentException("Not enough bitcoin");
        }
        return true;
    }

    @Override
    public List<String> showBalance(Account account) {
        SessionFactory factory = SessionInit.getSessionFactory();
        Session session = null;
        Account accTemp = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            String hql = "SELECT a FROM Account a WHERE users_id = " + account.getUser().getId();
            accTemp = session.createQuery(hql, Account.class).getSingleResult();
            session.getTransaction().commit();
        } finally {
            assert session != null;
            session.close();
            factory.close();
        }
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(accTemp.getAmountOfMoney()));
        list.add(String.valueOf(accTemp.getAmountOfBitcoin()));
        return list;
    }
}
