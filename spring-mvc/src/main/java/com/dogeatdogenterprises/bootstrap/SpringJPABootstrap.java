package com.dogeatdogenterprises.bootstrap;

import com.dogeatdogenterprises.domain.Address;
import com.dogeatdogenterprises.domain.Customer;
import com.dogeatdogenterprises.domain.Product;
import com.dogeatdogenterprises.services.CustomerService;
import com.dogeatdogenterprises.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by donaldsmallidge on 12/6/16. supersedes loadDomainObjects in mapservices
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public void setProductService(ProductService productService) {

        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {

        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        loadProducts();
        loadCustomers();
    }

    public void loadProducts() {

        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("11.99"));
        product1.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("12.99"));
        product2.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("13.99"));
        product3.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("14.99"));
        product4.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("15.99"));
        product5.setImageUrl("http://example.com/productx");
        productService.saveOrUpdate(product5);
    }

    public void loadCustomers() {

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Alfred");
        customer1.setLastName("Newman");
        customer1.setBillingAddress(new Address()); // refactor to use embedded Address object
        customer1.getBillingAddress().setAddressLine1("1 Alfred Lane");
        customer1.getBillingAddress().setAddressLine2("Apt. 101");
        customer1.getBillingAddress().setCity("Alfred");
        customer1.getBillingAddress().setState("Maine");
        customer1.getBillingAddress().setZipCode("04291");
        customer1.setEmail("alfred@gmail.com");
        customer1.setPhoneNumber("207-101-1001");
        customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Brad");
        customer2.setLastName("Schuster");
        customer2.setBillingAddress(new Address()); // refactor to use embedded Address object
        customer2.getBillingAddress().setAddressLine1("2 Bradford Street");
        customer2.getBillingAddress().setAddressLine2("Apt. 202");
        customer2.getBillingAddress().setCity("Waterville");
        customer2.getBillingAddress().setState("Maine");
        customer2.getBillingAddress().setZipCode("04902");
        customer2.setEmail("bschuster@yahoo.com");
        customer2.setPhoneNumber("207-202-2002");
        customerService.saveOrUpdate(customer2);

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("Claire");
        customer3.setLastName("Anthracks");
        customer3.setBillingAddress(new Address()); // refactor to use embedded Address object
        customer3.getBillingAddress().setAddressLine1("3 Claire Avenue");
        customer3.getBillingAddress().setAddressLine2("Apt. 303");
        customer3.getBillingAddress().setCity("Portland");
        customer3.getBillingAddress().setState("Maine");
        customer3.getBillingAddress().setZipCode("04033");
        customer3.setEmail("claireax@ygmail.com");
        customer3.setPhoneNumber("207-302-3102");
        customerService.saveOrUpdate(customer3);

        Customer customer4 = new Customer();
        customer4.setId(4);
        customer4.setFirstName("Dennis");
        customer4.setLastName("LeFleur");
        customer4.setBillingAddress(new Address()); // refactor to use embedded Address object
        customer4.getBillingAddress().setAddressLine1("4 Dentist Road");
        customer4.getBillingAddress().setAddressLine2("");
        customer4.getBillingAddress().setCity("Bangor");
        customer1.getBillingAddress().setState("Maine");
        customer1.getBillingAddress().setZipCode("04734");
        customer4.setEmail("claireax@ygmail.com");
        customer4.setPhoneNumber("207-402-4000");
        customerService.saveOrUpdate(customer4);

        Customer customer5 = new Customer();
        customer5.setId(5);
        customer5.setFirstName("Enid");
        customer5.setLastName("Frommage");
        customer5.setBillingAddress(new Address()); // refactor to use embedded Address object
        customer5.getBillingAddress().setAddressLine1("5 Cheese Way");
        customer5.getBillingAddress().setAddressLine2("Carriage House Room 3");
        customer5.getBillingAddress().setCity("South Paris");
        customer5.getBillingAddress().setState("Maine");
        customer5.getBillingAddress().setZipCode("04255");
        customer5.setEmail("cheezy@ygmail.com");
        customer5.setPhoneNumber("207-502-5500");
        customerService.saveOrUpdate(customer5);
    }
}
