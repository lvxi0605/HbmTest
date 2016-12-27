package edu.jxufe.lvxi.dao;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by lvxi on 2016/12/23.
 */
public class BasicDao {
    public static SessionFactory sessionFactory ;
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    static {
        //配置log4j
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/target/classes/log4j.properties");
        //配置hibernate
        Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
