package com.jeff.hibernatetest;

import com.jeff.entity.User;
import com.jeff.utils.HibernateUtils;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HibernateQueryData {

    // 使用Query對象
    @Test
    public void testQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();

            tx = session.beginTransaction();

            // 創建Query對象
            Query query = session.createQuery("from User");

            List<User> list = query.list();

            for(User user : list){
                System.out.println(user);
            }
            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
    }

    // 使用Criteria對象
    @Test
    public void testCriteria(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();

            tx = session.beginTransaction();

            // 創建Criteria對象
            Criteria criteria = session.createCriteria(User.class);
            List<User> list = criteria.list();

            for(User user : list){
                System.out.println(user);
            }

            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
    }

    // 使用SQLQuery對象
    @Test
    public void testSQLQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();

            tx = session.beginTransaction();

            // 創建SQLQuery對象
            SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
//            List<Object[]> list = sqlQuery.list();
//
//            for(Object[] objects : list){
//                System.out.println(Arrays.toString(objects));
//            }
            sqlQuery.addEntity(User.class);

            List<User> list = sqlQuery.list();

            for(User user : list){
                System.out.println(user);
            }

            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
    }
}
