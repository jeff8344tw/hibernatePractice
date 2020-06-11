package com.jeff.hibernatetest;

import com.jeff.entity.Customer;
import com.jeff.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

public class HibernateHQL {

    // 演示查詢所有
    @Test
    public void testSelectAll(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.創建query對象
            Query query = session.createQuery("from Customer");
            // 2.調用方法得到結果
            List<Customer> list = query.list();

            for(Customer customer : list){
                System.out.println(customer);
            }

            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }

    // 演示條件查詢
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
            Query query = session.createQuery("from Customer  where cid = ? and custName = ?");
            // 2.設置條件值
            // setParameter方法兩個參數
            // 第一個參數,int類型是?位置，?位置從0開始
            // 第二個參數，具體參數值
            query.setParameter(0,1);
            query.setParameter(1, "test");

            List<Customer> list = query.list();

            for(Customer customer : list){
                System.out.println(customer);
            }

            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }

    // 演示條件查詢模糊查詢
    @Test
    public void testSelect3(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.創建query對象
            Query query = session.createQuery("from Customer where custName like ?");
            // 2.設置條件值
            // setParameter方法兩個參數
            // 第一個參數,int類型是?位置，?位置從0開始
            // 第二個參數，具體參數值
            query.setParameter(0,"%%");

            List<Customer> list = query.list();

            for(Customer customer : list){
                System.out.println(customer);
            }

            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }

    // 演示排序查詢
    @Test
    public void testSelect4(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.創建query對象
            Query query = session.createQuery("from Customer order by cid asc");

            List<Customer> list = query.list();

            for(Customer customer : list){
                System.out.println(customer);
            }

            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }
}
