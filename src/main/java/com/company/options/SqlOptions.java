package com.company.options;

import com.company.Account;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlOptions implements OptionsForAccount, AutoCloseable {

    Connection cn;

    public SqlOptions(Connection cn) {
        this.cn = cn;
    }

    public SqlOptions() {
    }

    public void init() {
        try (InputStream in = SqlOptions.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean depositMoney(int amountOfMoney, Account account) {
        boolean rsl = true;
        try (PreparedStatement statement =
                     cn.prepareStatement("UPDATE accounts set amount_of_money = amount_of_money + ? " +
                             "WHERE users_id = ?;")) {
            statement.setDouble(1, amountOfMoney);
            statement.setInt(2, account.getUser().getId());
            rsl = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !rsl;
    }

    @Override
    public boolean withdrawMoney(int amountOfMoney, Account account) {
        boolean rsl = true;
        try (PreparedStatement statement =
                     cn.prepareStatement("UPDATE accounts set amount_of_money = amount_of_money - ? " +
                             "WHERE amount_of_money >= ? AND users_id = ?;")) {
            statement.setDouble(1, amountOfMoney);
            statement.setDouble(2, amountOfMoney);
            statement.setInt(3, account.getUser().getId());
            rsl = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !rsl;
    }

    @Override
    public boolean buyCrypto(int amountOfMoney, int bitcoinPrice, Account account) {
        boolean rsl = true;
        try (PreparedStatement statement =
                     cn.prepareStatement("UPDATE accounts set amount_of_money = amount_of_money - ?, " +
                             "amount_of_bitcoin =amount_of_bitcoin + (? / ?) " +
                             "WHERE amount_of_money >= ? AND users_id = ?;")) {
            statement.setInt(1, amountOfMoney);
            statement.setDouble(2, amountOfMoney);
            statement.setDouble(3, bitcoinPrice);
            statement.setInt(4, amountOfMoney);
            statement.setInt(5, account.getUser().getId());
            rsl = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !rsl;
    }

    @Override
    public boolean sellCrypto(int bitcoinPrice, double amountOfBitcoin, Account account) {
        boolean rsl = true;
        try (PreparedStatement statement =
                     cn.prepareStatement("UPDATE accounts set amount_of_bitcoin = amount_of_bitcoin - ?," +
                             " amount_of_money = amount_of_money + (? * ?)" +
                             " WHERE amount_of_bitcoin >= ? AND users_id = ?;")) {
            statement.setDouble(1, amountOfBitcoin);
            statement.setDouble(2, amountOfBitcoin);
            statement.setInt(3, bitcoinPrice);
            statement.setDouble(4, amountOfBitcoin);
            statement.setInt(5, account.getUser().getId());
            rsl = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !rsl;
    }

    @Override
    public List<String> showBalance(Account account) {
        List<String> list = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT amount_of_money, " +
                             "amount_of_bitcoin FROM accounts " +
                             "WHERE users_id = ?;")) {
            statement.setInt(1, account.getUser().getId());
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(String.valueOf(resultSet.getInt("amount_of_money")));
                    list.add(String.valueOf(resultSet.getDouble("amount_of_bitcoin")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
