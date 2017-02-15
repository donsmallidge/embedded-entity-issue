package com.dogeatdogenterprises.controllers;

import com.dogeatdogenterprises.controllers.CustomerController;
import com.dogeatdogenterprises.domain.Address;
import com.dogeatdogenterprises.domain.Customer;
import com.dogeatdogenterprises.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by donaldsmallidge on 11/21/16.
 */
public class CustomerControllerTest {

    //Mockito Mock object
    @Mock
    private CustomerService customerService;

    // sets up controller, and injects mock objects into it
    @InjectMocks
    private CustomerController customerController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); // initializes controller and mocks

        // Note: the customerController will be "mocked" here so it can be found in the tests.
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        // specific Mockito interaction, tell stub to return list of products
        when(customerService.listAll()).thenReturn((List)customers);
        // Note: need to strip generics to keep Mockito happy.

        // URL mapping #1 - Spring should find listCustomers() in the controller
        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));

        // URL mapping #2 - Spring should find listCustomers() in the controller
        mockMvc.perform(get("/customer/"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {

        Integer id = 1;

        // Mockito stub to return new product for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {

        Integer id = 1;

        // Mockito stub to return new product for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {

        Integer id = 1;

        // should not call service
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {

        Integer id = 1;
        String firstName = "Don";
        String lastName = "Smallidge";
        String email = "donaldsmallidge@mac.com";
        String phoneNumber = "207.861.1272";
        String addressLine1 = "25 Eustis Parkway";
        String addressLine2 = "Apt 2";
        String city = "Waterville";
        String state = "ME";
        String zipCode = "04901";

        Customer returnCustomer = new Customer();
        returnCustomer.setId(id);
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setEmail(email);
        returnCustomer.setPhoneNumber(phoneNumber);


        returnCustomer.setBillingAddress(new Address()); // refactor to use embedded Address object
        returnCustomer.getBillingAddress().setAddressLine1(addressLine1);
        returnCustomer.getBillingAddress().setAddressLine2(addressLine2);
        returnCustomer.getBillingAddress().setCity(city);
        returnCustomer.getBillingAddress().setState(state);
        returnCustomer.getBillingAddress().setZipCode(zipCode);

        /*
        returnCustomer.setAddressLine1(addressLine1);
        returnCustomer.setAddressLine2(addressLine2);
        returnCustomer.setCity(city);
        returnCustomer.setState(state);
        returnCustomer.setZipCode(zipCode);
        */

        // Mockito stub to return new product for ID 1
        // Note: I had to specify org.mockito instead of Hamcrest.
        when(customerService.saveOrUpdate( org.mockito.Matchers.<Customer>any())).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("email", email)
                .param("phoneNumber", phoneNumber)
                .param("addressLine1", addressLine1)
                .param("addressLine2", addressLine2)
                .param("city", city)
                .param("state", state)
                .param("zipCode", zipCode))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/customer/show/1"))
                    .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                    .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                    .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                    .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                    .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                    .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                    .andExpect(model().attribute("customer", hasProperty("addressLine1", is(addressLine1))))
                    .andExpect(model().attribute("customer", hasProperty("addressLine2", is(addressLine2))))
                    .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                    .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                    .andExpect(model().attribute("customer", hasProperty("zipCode", is(zipCode))));

        // verify properties of bound object
        ArgumentCaptor<Customer> boundProduct = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(firstName, boundProduct.getValue().getFirstName());
        assertEquals(lastName, boundProduct.getValue().getLastName());
        assertEquals(email, boundProduct.getValue().getEmail());
        assertEquals(phoneNumber, boundProduct.getValue().getPhoneNumber());

        assertEquals(addressLine1, boundProduct.getValue().getBillingAddress().getAddressLine1());
        assertEquals(addressLine2, boundProduct.getValue().getBillingAddress().getAddressLine2());
        assertEquals(city, boundProduct.getValue().getBillingAddress().getCity());
        assertEquals(state, boundProduct.getValue().getBillingAddress().getState());
        assertEquals(zipCode, boundProduct.getValue().getBillingAddress().getZipCode());
        /*
        assertEquals(addressLine1, boundProduct.getValue().getAddressLine1());
        assertEquals(addressLine2, boundProduct.getValue().getAddressLine2());
        assertEquals(city, boundProduct.getValue().getCity());
        assertEquals(state, boundProduct.getValue().getState());
        assertEquals(zipCode, boundProduct.getValue().getZipCode());
        */
    }

    @Test
    public void testDelete() throws Exception {

        Integer id = 1;

        // Mockito stub to return new product for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
    }

}
