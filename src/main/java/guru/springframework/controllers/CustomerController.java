package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j2
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customers")
    public String getListCustomers(Model model) {
        log.info("getListCustomers");
        model.addAttribute("customers", customerService.listAllCustomer());
        return "customer/customers";
    }

    @RequestMapping(value = "/customer/{id}")
    public String showCustomers(@PathVariable Integer id, Model model) {
        log.info("showCustomers");
        model.addAttribute("customer", customerService.getCustomer(id));
        return "customer/customer";
    }

    @RequestMapping(value = "/customer/new")
    public String customerFrom(Model model) {
        log.info("customerFrom");
        model.addAttribute("customer", new Customer());
        return "customer/customerform";
    }

    @RequestMapping(value = "/customer/edit/{id}")
    public String customerFromEdit(@PathVariable Integer id,Model model) {
        log.info("customerFromEdit");
        model.addAttribute("customer", customerService.getCustomer(id));
        return "customer/customerform";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveOrUpdateCustomer(Customer customer, Model model) {
        log.info("saveOrUpdateCustomer");
        customerService.saveOrUpdateCustomer(customer);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/customer/delete/{id}")
    public String deleteCustomers(@PathVariable Integer id) {
        log.info("deleteCustomers");
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
