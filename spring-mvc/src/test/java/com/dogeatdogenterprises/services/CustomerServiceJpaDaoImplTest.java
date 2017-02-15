package com.dogeatdogenterprises.services;

/**
 * Created by donaldsmallidge on 1/6/17.
 */

import com.dogeatdogenterprises.domain.Customer;
//import com.dogeatdogenterprises.domain.Product;
import com.dogeatdogenterprises.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by donaldsmallidge on 12/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(JpaIntegrationConfig.class) <-- DEPRECATED
// https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
// not webEnvironment=WebEnvironment.RANDOM_PORT; DEFINED_PORT seems to default to 8080[?])
// http://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.WebEnvironment.html
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {

        this.customerService = customerService;
    }

    @Test
    public void testListMethod() throws Exception {
        List<Customer> customers = (List<Customer>)customerService.listAll();
        assert customers.size() == 5;
    }

    @Test
    public void testGetById() throws Exception {
        Integer id = 1;

        Customer retrievedCustomer = customerService.getById(id);
        assert retrievedCustomer.getFirstName().equalsIgnoreCase("Alfred");
    }

    @Test
    public void testSaveWithUser() {
        Customer customer = new Customer();
        User user = new User();
        user.setUsername("This is my username");
        user.setPassword("MyAwesomePassword");
        customer.setUser(user);

        Customer savedCustomer1 = customerService.saveOrUpdate(customer);

        assert savedCustomer1.getUser().getId() != null;
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String newEmail = "alfrednewman@gmail.com";
        Customer retrievedCustomer = customerService.getById(id);
        retrievedCustomer.setEmail(newEmail);

        Customer modifiedCustomer = customerService.saveOrUpdate(retrievedCustomer);

        assertEquals(id, modifiedCustomer.getId());
        assertEquals(newEmail, modifiedCustomer.getEmail());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 2 ;

        List<Customer> customers = (List<Customer>)customerService.listAll();
        //System.out.println("Number of customers before delete: "+customers.size());
        //for (Customer c: customers) {
        //    System.out.println("Customer number: "+c.getId());
        //}
        customerService.delete(id);

        customers = (List<Customer>)customerService.listAll();
        assert customers.size() == 5;
    }
}
