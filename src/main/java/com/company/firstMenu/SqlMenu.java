package com.company.firstMenu;

import com.company.Account;
import com.company.SessionInit;
import com.company.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SqlMenu implements Menu {

    public SqlMenu() {
    }

    @Override
    public Account createAccount(User user, String password) {
        SessionFactory factory = SessionInit.getSessionFactory();
        Session session = null;
        Account account = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            account = new Account(user, password);
            session.save(account);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
        return account;
    }

    @Override
    public Account enterAccount(User user, String password) {
        SessionFactory factory = SessionInit.getSessionFactory();
        Session session = null;
        Account account;
        User tempUser;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            String hql1 = "SELECT u FROM User u WHERE passport = " + user.getPassport() + " AND name = '" + user.getName() + "'";
            try {
                tempUser = session.createQuery(hql1, User.class).getSingleResult();
            } catch (Exception e){
                throw new IllegalArgumentException("Wrong name or passport number");
            }
            String hql2 = "SELECT a FROM Account a WHERE users_id = " + tempUser.getId();
            account = session.createQuery(hql2, Account.class).getSingleResult();
            if (!account.getPassword().equals(password)) {
                throw new IllegalArgumentException("Wrong password");
            }
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return account;
    }
}
