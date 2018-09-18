/*
 * several method for worc wit table Present
 */
package com.transfer.ordersweetnesswork.Service;

import com.transfer.ordersweetnesswork.DAO.IPresentDAO;
import com.transfer.ordersweetnesswork.entity.ChocolateCandy;
import com.transfer.ordersweetnesswork.entity.Present;
import com.transfer.ordersweetnesswork.hibernateUtil.SessionUtil;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PresentDAO implements IPresentDAO {

    private SessionUtil sessionUtil = new SessionUtil();

    //add new object Present in table Present
    @Override
    public boolean addPresent(Present present) throws SQLException {
        Session session = null;
        boolean flag = false;
        Transaction transaction = null;
        try {
            transaction = sessionUtil.openTransaction();
            session = sessionUtil.getSession();

            session.save(present);

            transaction.commit();
            flag = true;

        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return flag;
    }

    //get object Present from  table Present by id
    @Override
    public Present getById(Integer id) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        Present present = null;
        try {
            transaction = sessionUtil.openTransaction();
            session = sessionUtil.getSession();

            String sqlQuery = "FROM Present WHERE IdPresent = :id";
            Query query = session.createQuery(sqlQuery);
            query.setParameter("id", id);
            present = (Present) query.uniqueResult();
            System.out.println(present.getId());
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return present;
    }

    //get Present by from desired amount
    @Override
    public List<Present> getByLimetAmout(int numbers) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        List<Present> present = null;
        try {
            transaction = sessionUtil.openTransaction();
            session = sessionUtil.getSession();

            String sqlQuery = "FROM Present";
            Query query = session.createQuery(sqlQuery);
            query.setMaxResults(numbers);
            present = (List<Present>) query.list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return present;
    }

    //delete Icicle from db by id
    @Override
    public int deleteById(int id) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try {
            transaction = sessionUtil.openTransaction();
            session = sessionUtil.getSession();

            String sqlDeleteString = "delete Present where IdPresent = :id";
            Query query = session.createQuery(sqlDeleteString);
            query.setParameter("id", id);
            result = query.executeUpdate();

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

}
