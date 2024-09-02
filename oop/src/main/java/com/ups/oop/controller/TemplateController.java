package com.ups.oop.controller;

import com.ups.oop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private final PersonService personService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final ProductService productService;
    private final PaymentMethService paymentMethService;
    private final ReceiptService receiptService;
    private final DetailsService detailsService;
    private final StoreService storeService;
    private final SupplierService supplierService;

    public TemplateController(PersonService personService, CustomerService customerService,
                              EmployeeService employeeService,
                              ProductService productService, PaymentMethService paymentMethService,
                              ReceiptService receiptService, DetailsService detailsService, StoreService
                              storeService, SupplierService supplierService) {
        this.personService = personService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.productService = productService;
        this.paymentMethService = paymentMethService;
        this.receiptService = receiptService;
        this.detailsService = detailsService;
        this.storeService = storeService;
        this.supplierService = supplierService;
    }

    @GetMapping("/template")
    public String getTemplate(Model model) {
        return "template";
    }

    @GetMapping("/people")
    public String getPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "person/list";
    }

    @GetMapping("/customers")
    public String getCustomer(Model model) {
        model.addAttribute("customers", customerService.getCustomer());
        return "customer/list";
    }

    @GetMapping("/employees")
    public String getEmployee(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "employee/list";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("clients", productService.getProduct());
        return "client/list";
    }

    @GetMapping("/stores")
    public String getStores(Model model) {
        model.addAttribute("stores", storeService.getStore());
        return "store/list";
    }

 @GetMapping("/suppliers")
    public String getSupplier(Model model) {
        model.addAttribute("suppliers", supplierService.getSupplier());
        return "store/list";
    }

    @GetMapping("/paymentmethod")
    public String getPaymentMethod(Model model) {
        model.addAttribute("suppliers", paymentMethService.getPayment());
        return "payment/list";
    }

    @GetMapping("/receipts")
    public String getReceipts(Model model) {
        model.addAttribute("receipts", receiptService.getReceipts());
        return "receipt/list";
    }

    @GetMapping("/receipt-details")
    public String getDetails(Model model) {
        model.addAttribute("receiptDetails", detailsService.getDetails());
        return "details/list";
    }
}