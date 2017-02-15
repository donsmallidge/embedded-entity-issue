package com.dogeatdogenterprises.controllers;

import com.dogeatdogenterprises.domain.Customer;
import com.dogeatdogenterprises.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by donaldsmallidge on 9/20/16.
 *
 * Create a controller. Implement the ability to
 * list,
 * show one customer,
 * add a customer,
 * update a customer, and
 * delete a customer by id.
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {

        this.customerService = customerService;
    }

    @RequestMapping({"/list", "/"})
    public String listCustomers(Model model) {

        model.addAttribute("customers", customerService.listAll());

        return "customer/list";
    }

    @RequestMapping("/show/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {

        model.addAttribute("customer", customerService.getById(id));

        return "customer/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("customer", customerService.getById(id));

        return "customer/customerform";
    }

    @RequestMapping("/new")
    public String newCustomer(Model model) {
        // Note: This method does NOT use customerService.
        model.addAttribute("customer", new Customer());
        return "customer/customerform";
    }

    @RequestMapping(method= RequestMethod.POST)
    public String saveOrUpdate(Customer customer) {
        // Note: This method returns a REDIRECT. And a POST.
        Customer newCustomer = customerService.saveOrUpdate(customer);
        return "redirect:/customer/show/" + newCustomer.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // Note: This method returns a REDIRECT.
        customerService.delete(id);
        return "redirect:/customer/list";
    }

}
