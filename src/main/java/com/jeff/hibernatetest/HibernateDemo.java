package com.jeff.hibernatetest;

import com.jeff.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

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
}
