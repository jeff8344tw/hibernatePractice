package com.jeff.hibernatetest;

import com.jeff.entity.Customer;
import com.jeff.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

public class HibernateQBC {

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

            // 1.創建對象
            Criteria criteria = session.createCriteria(Customer.class);
            // 2.調用方法得到結果
            List<Customer> list = criteria.list();

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

            // 1.創建對象
            Criteria criteria = session.createCriteria(Customer.class);
            // 2.設置條件值
            // cid = 1
            criteria.add(Restrictions.eq("cid", 1));
            criteria.add(Restrictions.like("custName", "%%"));

            // 3.調用方法得到結果
            List<Customer> list = criteria.list();

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
    public void testSelect3(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.創建對象
            Criteria criteria = session.createCriteria(Customer.class);
            // 2.設置對哪個屬性排序，設置排序規則
            // cid = 1
            criteria.addOrder(Order.desc("cid"));

            // 3.調用方法得到結果
            List<Customer> list = criteria.list();

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

    // 演示分頁查詢
    @Test
    public void testSelect4(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.創建對象
            Criteria criteria = session.createCriteria(Customer.class);
            // 2.設置分頁數據
            // 2.1.設置開始位置
            criteria.setFirstResult(0);
            // 2.2.每頁顯示紀錄數
            criteria.setMaxResults(3);

            // 3.調用方法得到結果
            List<Customer> list = criteria.list();

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

    // 演示統計查詢
    @Test
    public void testSelect5(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.創建對象
            Criteria criteria = session.createCriteria(Customer.class);
            // 2.設置操作
            criteria.setProjection(Projections.rowCount());

            // 3.調用方法得到結果
           Object obj = criteria.uniqueResult();

           System.out.println(obj);

            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }

    // 演示離線查詢
    @Test
    public void testSelect6(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.創建對象
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
            // 2.最終執行時才需要用到session
            Criteria criteria = detachedCriteria.getExecutableCriteria(session);

            // 3.調用方法得到結果
            List<Customer> list = criteria.list();

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
