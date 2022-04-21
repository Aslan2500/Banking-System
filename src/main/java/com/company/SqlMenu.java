package com.company;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SqlMenu implements Menu, AutoCloseable {

    private Connection cn;

    public SqlMenu(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = SqlMenu.class.getClassLoader().getResourceAsStream("app.properties")) {
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
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
    
    @Override
    public boolean createAccount(User user, int password) {
        try (PreparedStatement statement =
                     cn.prepareStatement("INSERT INTO accounts(name, usersID, password) VALUES (?, ?, ?);")) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getId());
            statement.setInt(3, password);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Account enterAccount(User user, int password) {
        Account account = null;
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT * FROM accounts WHERE usersID = ? AND password = ?;")) {
            statement.setInt(1, user.getId());
            statement.setInt(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    account = new Account(
                            new User(
                                    resultSet.getString("name"),
                                    resultSet.getInt("usersID")),
                            resultSet.getInt("password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
}
