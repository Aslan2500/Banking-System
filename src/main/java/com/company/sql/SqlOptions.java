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
    public boolean depositMoney(double amountOfMoney, Account account) {
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
    public boolean withdrawMoney(double amountOfMoney, Account account) {
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
    public boolean buyCrypto(double amountOfMoney, Account account, double bitcoinPrice) {
        boolean rsl = true;
        try (PreparedStatement statement =
                     cn.prepareStatement("UPDATE accounts set amountOfMoney = amountOfMoney - ?, " +
                             "amountOfBitcoin = amountOfBitcoin + (? / ?) " +
                             "WHERE amountOfMoney > ? AND usersID = ?;")) {
            statement.setDouble(1, amountOfMoney);
            statement.setDouble(2, amountOfMoney);
            statement.setDouble(3, bitcoinPrice);
            statement.setDouble(4, amountOfMoney);
            statement.setInt(5, account.getUser().getId());
            rsl = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !rsl;
    }

    @Override
    public boolean sellCrypto(double bitcoinPrice, double amountOfBitcoin, Account account) {
        boolean rsl = true;
        try (PreparedStatement statement =
                     cn.prepareStatement("UPDATE accounts set amountOfBitcoin = amountOfBitcoin - ?," +
                             " amountOfMoney = amountOfMoney + (? * ?)" +
                             " WHERE amountOfBitcoin >= ? AND usersID = ?;")) {
            statement.setDouble(1, amountOfBitcoin);
            statement.setDouble(2, amountOfBitcoin);
            statement.setDouble(3, bitcoinPrice);
            statement.setDouble(4, amountOfBitcoin);
            statement.setInt(5, account.getUser().getId());
            rsl = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !rsl;
    }

    @Override
    public List<Integer> showBalance(Account account) {
        List<Integer> list = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT amountOfMoney, " +
                             "amountOfBitcoin FROM accounts " +
                             "WHERE usersID = ?;")) {
            statement.setInt(1, account.getUser().getId());
            statement.setInt(5, account.getUser().getId());
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                list.add(resultSet.getInt("amountOfMoney"));
                list.add(resultSet.getInt("amountOfBitcoin"));
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
