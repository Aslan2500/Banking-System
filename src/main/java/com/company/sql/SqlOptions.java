package com.company.sql;

import com.company.Account;
import com.company.OptionsForAccount;

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
                     cn.prepareStatement("UPDATE accounts set amountOfMoney = amountOfMoney + ? " +
                             "WHERE usersID = ?;")) {
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
                     cn.prepareStatement("UPDATE accounts set amountOfMoney = amountOfMoney - ? " +
                             "WHERE amountOfMoney >= ? AND usersID = ?;")) {
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
                     cn.prepareStatement("UPDATE accounts set amountOfMoney = amountOfMoney - ?, " +
                             "amountOfBitcoin = amountOfBitcoin + (? / ?) " +
                             "WHERE amountOfMoney >= ? AND usersID = ?;")) {
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
                     cn.prepareStatement("UPDATE accounts set amountOfBitcoin = amountOfBitcoin - ?," +
                             " amountOfMoney = amountOfMoney + (? * ?)" +
                             " WHERE amountOfBitcoin >= ? AND usersID = ?;")) {
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
                     cn.prepareStatement("SELECT amountOfMoney, " +
                             "amountOfBitcoin FROM accounts " +
                             "WHERE usersID = ?;")) {
            statement.setInt(1, account.getUser().getId());
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(String.valueOf(resultSet.getInt("amountOfMoney")));
                    list.add(String.valueOf(resultSet.getDouble("amountOfBitcoin")));
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
