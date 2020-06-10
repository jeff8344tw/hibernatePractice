package com.jeff.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static Configuration cfg = null;
    private static SessionFactory sessionFactory = null;

    //　靜態代碼塊實現
    static {
        // 加載核心配置文件
        cfg  = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    // 提供返回與本地線程綁定的session方法
    public static Session getSessionObject() {
        return sessionFactory.getCurrentSession();
    }

    // 提供方法返回sessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
