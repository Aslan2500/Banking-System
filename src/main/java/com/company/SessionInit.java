package com.company;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionInit {
    public static SessionFactory getSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
