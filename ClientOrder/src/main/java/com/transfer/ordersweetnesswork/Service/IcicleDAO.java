/*
 * several method for work with table ChocolateCandy and class Chocolate Candy
 */
package com.transfer.ordersweetnesswork.Service;

import com.transfer.ordersweetnesswork.DAO.IIcicleDAO;
import com.transfer.ordersweetnesswork.entity.Icicle;
import com.transfer.ordersweetnesswork.hibernateUtil.SessionUtil;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class IcicleDAO implements IIcicleDAO {

    private SessionUtil sessionUtil = new SessionUtil();

    //add new object Icicle in table Icicle
    @Override
    public boolean addIcicle(Icicle icicle) {
        Session session = null;
        boolean flag = false;
        Transaction transaction = null;
        try {
            transaction = sessionUtil.openTransaction();
            session = sessionUtil.getSession();

            session.save(icicle);

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

    //get object Icicle from  table Icicle by id
    @Override
    public Icicle getById(Integer id) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        Icicle icicle = null;
        try {
            transaction = sessionUtil.openTransaction();
            session = sessionUtil.getSession();

            String sqlQuery = "FROM Icicle WHERE IdIcicle = :id";
            Query query = session.createQuery(sqlQuery);
            query.setParameter("id", id);
            icicle = (Icicle) query.uniqueResult();
            System.out.println(icicle.getIdIcicle());
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return icicle;
    }

    //get Icicle by from db by testy desired amount
    @Override
    public List<Icicle> getByTestyDesiredAmount(String taste, int numbers) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        List<Icicle> icicle = null;
        try {
            transaction = sessionUtil.openTransaction();
            session = sessionUtil.getSession();

            String sqlQuery = "FROM Icicle WHERE taste = :taste";
            Query query = session.createQuery(sqlQuery);
            query.setParameter("taste", taste);
            query.setMaxResults(numbers);
            icicle = (List<Icicle>) query.list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return icicle;
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

            String sqlDeleteString = "delete Icicle where IdIcicle = :id";
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
