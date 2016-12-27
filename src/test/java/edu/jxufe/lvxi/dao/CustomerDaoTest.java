package edu.jxufe.lvxi.dao;

import edu.jxufe.lvxi.entity.Address;
import edu.jxufe.lvxi.entity.Customer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

/**
 * Created by lvxi on 2016/12/23.
 */
public class CustomerDaoTest {
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    static {
        //配置log4j
        PropertyConfigurator.configure(System.getProperty("user.dir")+"\\target\\test-classes\\log4j.properties");
    }

   @Test
    public void test( ){
        logger.debug("测试");

        Session session = BasicDao.getSessionFactory().openSession();
        Transaction tx =session.beginTransaction();
        List<Address> list = session.createQuery("select new Address(c.name) from Customer c where c.id=6").setComment("fdsfsafdsf").list();
        Address address =  list.size()>0?list.get(0):null;
        address.setProvince("dsfsadfdsa");
        tx.commit();
        session.close();
        logger.debug(address);
    }

    //@Test
    public void testSaveCustomer(){
        logger.debug("测试");
        CustomerDao dao = new CustomerDao();
        Customer customer = new Customer();
        customer.setName("name1");
        Address ad = new Address();
        ad.setCountry("樟树市");
        ad.setProvince("江西省");
        ad.setStreet("观上镇");
        ad.setCity("宜春市");
        customer.setHomeAddress(ad);
        dao.addCustomer(customer);
        logger.info(customer);
    }
    @Test
    public void testGetCustomer( ){
        logger.debug("测试 加载Customer");
        CustomerDao dao = new CustomerDao();
        logger.debug(dao.getCustomer(4));
    }
   @Test
    public void testloadCustomer( ){
        logger.debug("测试load 加载Customer");
        CustomerDao dao = new CustomerDao();
        Customer customer = dao.loadCustomer(4);
        logger.debug(customer);
        customer.setName("lvxi");
        logger.debug(customer);
    }

    @Test
    public void testQueryCustomerWithQBC( ){
        logger.debug("测试load 加载Customer");
        CustomerDao dao = new CustomerDao();
        List<Customer> list = dao.queryCustomerWithQBC(null,-1);
        logger.debug("数目:"+list.size()+" \n"+ list);
    }

}
