package com.jeff.hibernatetest;

import com.jeff.entity.Customer;
import com.jeff.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

public class HibernateHQLManyTable {

    // 演示hql內連接查詢
    @Test
    public void testSelect1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.創建query對象
            Query query = session.createQuery("from Customer c inner join c.setLinkMan");
            // 2.調用方法得到結果
            List list = query.list();


            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    // 演示hql左外連接連接查詢
    @Test
    public void testSelect2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.創建query對象
            Query query = session.createQuery("from Customer c left outer join c.setLinkMan");
            // 2.調用方法得到結果
            List list = query.list();


            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }


}