package com.company.sql;

import com.company.OptionsForAccount;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SqlOptions implements OptionsForAccount, AutoCloseable {

    Connection cn;

    public SqlOptions(Connection cn) {
        this.cn = cn;
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
    public boolean depositMoney(int amountOfMoney) {
        return false;
    }

    @Override
    public boolean withdrawMoney(int amountOfMoney) {
        return false;
    }

    @Override
    public boolean buyCrypto(int amountOfMoney) {
        return false;
    }

    @Override
    public boolean sellCrypto(int amountOfMoney) {
        return false;
    }

    @Override
    public int showBalance() {
        return 0;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
