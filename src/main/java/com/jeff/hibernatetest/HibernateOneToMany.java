package com.jeff.hibernatetest;

import com.jeff.entity.Customer;
import com.jeff.entity.LinkMan;
import com.jeff.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateOneToMany {

    // 一對多級聯保存:方法1
    @Test
    public void testAddDemo1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 添加一個客戶，為這個客戶添加一個聯繫人
            // 1.創建客戶和聯繫人對象
            Customer customer = new Customer();
            customer.setCustName("test");
            customer.setCustLevel("vip");
            customer.setCustSource("12");
            customer.setCustPhone("123");
            customer.setCustMobile("999");

            LinkMan linkMan = new LinkMan();
            linkMan.setLkn_name("test_link");
            linkMan.setLkn_gender("male");
            linkMan.setLkn_phone("999");

            // 2.建立客戶對象和聯繫人對象關係
            // 2.1 把聯繫人對象放到客戶對象的set集合裡面
            customer.getSetLinkMan().add(linkMan);
            // 2.2 把客戶對象放到聯繫人裡面
            linkMan.setCustomer(customer);

            // 3.保存到數據庫
            session.save(customer);
            session.save(linkMan);

         tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    // 一對多級聯保存:方法2
    @Test
    public void testAddDemo2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 添加一個客戶，為這個客戶添加一個聯繫人
            // 1.創建客戶和聯繫人對象
            Customer customer = new Customer();
            customer.setCustName("test");
            customer.setCustLevel("vip");
            customer.setCustSource("12");
            customer.setCustPhone("123");
            customer.setCustMobile("999");

            LinkMan linkMan = new LinkMan();
            linkMan.setLkn_name("test_link");
            linkMan.setLkn_gender("male");
            linkMan.setLkn_phone("999");

            // 2. 把聯繫人對象放到客戶對象的set集合裡面
            customer.getSetLinkMan().add(linkMan);

            // 3.保存到數據庫
            session.save(customer);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    // 一對多級聯刪除
    @Test
    public void testDeleteDemo(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.根據id查詢客戶對象
            Customer customer = session.get(Customer.class, 1);
            // 2.調用刪除方法
            session.delete(customer);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    // 一對多級聯修改
    @Test
    public void testUpdateDemo(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1.根據id查詢聯繫人及客戶
            Customer customer = session.get(Customer.class, 1);
            LinkMan linkMan = session.get(LinkMan.class, 2);
            // 2.設置持久態對象值
            // 把聯繫人放到客戶裡
            customer.getSetLinkMan().add(linkMan);
            // 把客戶放到聯繫人裡
            linkMan.setCustomer(customer);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
