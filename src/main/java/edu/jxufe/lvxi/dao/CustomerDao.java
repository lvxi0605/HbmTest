package edu.jxufe.lvxi.dao;

import edu.jxufe.lvxi.entity.Customer;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lvxi on 2016/12/23.
 */
public class CustomerDao extends BasicDao{
    public void addCustomer(Customer customer){
        logger.debug("保存Customer");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        session.save(customer);
        tx.commit();
        logger.debug("保存Customer 事务提交");
    }

    public Customer getCustomer(Serializable id){
        logger.debug("加载Customer");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class,id);
        tx.commit();
        logger.debug("加载Customer 事务提交");
        session.close();
        return customer;
    }
    public Customer loadCustomer(Serializable id){
        logger.debug("加载Customer");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Customer customer = (Customer) session.load(Customer.class,id);
        tx.commit();
        logger.debug("加载Customer 事务提交");
        if(!Hibernate.isInitialized(customer)) Hibernate.initialize(customer);
        session.close();
        return customer;
    }
    public List<Customer> queryCustomer(){
        Session session = sessionFactory.openSession();
        String hql="from Customer as c where c.id=:id";
        Query query = session.createQuery(hql).setInteger("id",4);
        List list = query.list();
        return list;
    }

    public List<Customer> queryCustomerWithQBC(String name,int id){
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Customer.class);
        if(name!=null) criteria.add(Restrictions.ilike("name",name.toLowerCase(), MatchMode.ANYWHERE));
        if(id>0) criteria.add(Restrictions.eq("id",id));
        //criteria.setFetchMode("homeAddress",FetchMode.JOIN);
        List list = criteria.list();
        session.close();
        return list;
    }

}
