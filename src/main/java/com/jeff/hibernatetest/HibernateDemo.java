package com.jeff.hibernatetest;

import com.jeff.entity.Customer;
import com.jeff.entity.LinkMan;
import com.jeff.entity.User;
import com.jeff.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HibernateDemo {

    @Test
    public void testAdd() {
        // 第一步 加載hibernate核心配置文件
        // 到resources下面找到名稱是hibernate.cfg.xml
        // 在hibernate裡面封裝對象
        Configuration cfg = new Configuration();
        cfg.configure();

        // 第二步 創建SessionFactory對象
        // 讀取hibernate核心配置文件內容，創建sessionFactory
        // 在過程中，根據映射關係，在配置數據庫把表創建
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // 第三部 使用SessionFactory創建session對象
        // 類似於連接
        Session session = sessionFactory.openSession();

        // 第四部 開啟事務
        Transaction tx = session.beginTransaction();

        // 第五部 寫具體邏輯crud操作
        // 添加功能
        User user = new User();
        user.setUsername("jeff");
        user.setPassword("123");
        user.setAddress("456");
        // 調用session的方法實現添加
        session.save(user);

        // 第六部 提交事務
        tx.commit();

        // 第七部 關閉資源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testGet() {
        // 1.調用工具類得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        // 2.獲取session
        Session session = sessionFactory.openSession();
        // 3.開啟事務
        Transaction tx = session.beginTransaction();
        // 4.根據id查詢
        // 調用session裡面的get方法
        // 第一個參數，實體類的class
        // 第二個參數，id值
        User user = session.get(User.class, 1);
        System.out.println(user);

        // 5.提交事務
        tx.commit();
        // 6.關閉
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testUpdate() {
        // 1.調用工具類得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        // 2.獲取session
        Session session = sessionFactory.openSession();
        // 3.開啟事務
        Transaction tx = session.beginTransaction();
        // 4.修改操作
        // 修改uid = 1 紀錄的username值
        // 根據id查詢
        User user = session.get(User.class, 1);
        user.setUsername("jeff123");
        // 調用session的方法update修改
        // 執行過程，到user對象裡查找到uid值，根據uid進行修改
        session.update(user);

        // 5.提交事務
        tx.commit();
        // 6.關閉
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testDelete() {
        // 1.調用工具類得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        // 2.獲取session
        Session session = sessionFactory.openSession();
        // 3.開啟事務
        Transaction tx = session.beginTransaction();
        // 4.刪除操作
        // 第一種根據id查詢對象
        User user = session.get(User.class, 1);
        session.delete(user);

        // 第二種
//        User user = new User();
//        user.setUid(1);
//        session.delete(user);

        // 5.提交事務
        tx.commit();
        // 6.關閉
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testSaveOrUpdate() {
        // 1.調用工具類得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        // 2.獲取session
        Session session = sessionFactory.openSession();
        // 3.開啟事務
        Transaction tx = session.beginTransaction();
        // 4.

//        瞬時態
//        User user = new User();
//        user.setUid(1);
//        user.setUsername("jeff");
//        user.setPassword("124");
//        user.setAddress("tw");

//        託管態
//        User user = new User();
//        user.setUid(1);
//        user.setUsername("jeff");
//        user.setPassword("124");
//        user.setAddress("tw");

        // 持久態
        User user = session.get(User.class, 1);
        user.setUsername("jeff1234");

        // 實體類對象實體類對象是瞬時態，做添加
        // 實體類對象實體類對象是託管態，做修改
        // 實體類對象實體類對象是持久態，做修改
        session.saveOrUpdate(user);


        // 5.提交事務
        tx.commit();
        // 6.關閉
        session.close();
        sessionFactory.close();
    }

    // 事務規範代碼
    @Test
    public void testTx() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            // 開啟事務
            tx = session.beginTransaction();

            // 添加
            User user = new User();
            user.setUsername("jeff");
            user.setPassword("123");
            user.setAddress("tw");
            session.save(user);

            // 提交事務
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // 回滾事務
            tx.rollback();
        } finally {
            // 關閉操作
            session.close();
            sessionFactory.close();
        }
    }

    @Test
    public void testTx2() {
        Session session = null;
        Transaction tx = null;

        try {
            // 與本地線程綁定的session
            session = HibernateUtils.getSessionObject();
            // 開啟事務
            tx = session.beginTransaction();

            // 添加
            User user = new User();
            user.setUsername("jeff");
            user.setPassword("123");
            user.setAddress("tw");
            session.save(user);

            // 提交事務
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // 回滾事務
            tx.rollback();
        } finally {
            // 獲取與本地線程綁定session，不需要手動關閉線程
//            session.close() ;
        }
    }

    // 演示對象導航查詢
    @Test
    public void testSelect1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 查詢cid = 1的客戶，再查詢這個客戶裡所有的聯繫人
            Customer customer = session.get(Customer.class, 1);
            // 再查詢這個客戶裡面所有聯繫人
            Set<LinkMan> linkMEN = customer.getSetLinkMan();

            System.out.println(linkMEN.size());

            tx.commit();
        } catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }

}
