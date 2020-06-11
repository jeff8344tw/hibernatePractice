package com.jeff.hibernatetest;

import com.jeff.entity.Customer;
import com.jeff.entity.LinkMan;
import com.jeff.manytomany.Role;
import com.jeff.manytomany.User2;
import com.jeff.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateManytoMany {

    // 多對多級聯刪除
    @Test
    public void testDelete(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            User2 user = session.get(User2.class, 1);
            session.delete(user);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    // 多對多級聯保存
    @Test
    public void testSave(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 添加兩個用戶，為每個用戶添加兩個角色
            // 1.創建對象
            User2 user1 = new User2();
            user1.setUser_name("test");
            user1.setUser_password("123");

            User2 user2 = new User2();
            user2.setUser_name("test2");
            user2.setUser_password("1");

            Role r1 = new Role();
            r1.setRole_name("test1");
            r1.setRole_memo("111");

            Role r2 = new Role();
            r2.setRole_name("test2");
            r2.setRole_memo("222");

            Role r3 = new Role();
            r3.setRole_name("test3");
            r3.setRole_memo("333");

            // 建立關係，把角色放到用戶裡面
            // user1 --> r1 r2
            user1.getSetRole().add(r1);
            user1.getSetRole().add(r2);
            // user2 --> r2 r3
            user2.getSetRole().add(r2);
            user2.getSetRole().add(r3);

            session.save(user1);
            session.save(user2);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    // 演示維護第三張表
    @Test
    public void testTable(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 讓某個用戶有某個角色
            // 1.查詢user and role
            User2 user = session.get(User2.class, 1);
            Role role = session.get(Role.class, 1);
            // 2.把角色放到用戶的set集合裡面
            user.getSetRole().add(role);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    // 演示維護第三張表
    @Test
    public void testTable2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 讓某個用戶有沒有某個角色
            // 1.查詢user and role
            User2 user = session.get(User2.class, 1);
            Role role = session.get(Role.class, 1);
            // 2.把角色從用戶的set集合裡面去掉
            user.getSetRole().remove(role);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
