/*
*class for getting session and transaction
*/
package com.transfer.ordersweetnesswork.hibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtil {
        Session  session = null;
    Transaction transaction = null;

    public Transaction getTransaction() {
        return transaction;
    }

    public Session getSession() {
        return session;
    }
    
    public void openSession(){
        session = HibernateUtil.getSessionFactory().openSession();
    }
    public Transaction openTransaction(){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return transaction;
    }
}
