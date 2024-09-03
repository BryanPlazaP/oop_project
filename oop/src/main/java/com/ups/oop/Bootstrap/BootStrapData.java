package com.ups.oop.Bootstrap;
import com.ups.oop.entity.*;
import com.ups.oop.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import java.util.Date;

@Component
public class BootStrapData  implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final PaymentMethRepository paymentMethRepository;
    private final ReceiptRepository receiptRepository;
    private final DetailsRepository detailsRepository;
    private final StoreRepository storeRepository;
    private final SupplierRepository supplierRepository;

    public BootStrapData(PersonRepository personRepository, CustomerRepository customerRepository,
                         EmployeeRepository employeeRepository, ProductRepository productRepository,
                         PaymentMethRepository paymentMethRepository, ReceiptRepository receiptRepository,
                         DetailsRepository detailsRepository, StoreRepository storeRepository, SupplierRepository
                                 supplierRepository) {
        this.personRepository = personRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.paymentMethRepository = paymentMethRepository;
        this.receiptRepository = receiptRepository;
        this.detailsRepository = detailsRepository;
        this.storeRepository = storeRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        PaymentMeth pay1 = new PaymentMeth();
        pay1.setMethod("Credit Card");

        PaymentMeth pay2 = new PaymentMeth();
        pay2.setMethod("Cash");

        paymentMethRepository.save(pay1);
        paymentMethRepository.save(pay2);

        Store  sto1 = new Store();
        sto1.setBranch_name("Sucursal Sur");

        Store  sto2 = new Store();
        sto2.setBranch_name("Sucursal Norte");

        Store  sto3 = new Store();
        sto2.setBranch_name("Sucursal Via la costa");

        Store  sto4 = new Store();
        sto2.setBranch_name("Sucursal Samborondon");

        storeRepository.save(sto1);
        storeRepository.save(sto2);
        storeRepository.save(sto3);
        storeRepository.save(sto4);

        Employee emp1 = new Employee();
        emp1.setEmployeeCode("E-001");
        emp1.setName("Madeleine");
        emp1.setLastName("Ortiz");
        emp1.setAge(19);

        Employee emp2 = new Employee();
        emp2.setEmployeeCode("E-002");
        emp2.setName("Michelle");
        emp2.setLastName("Camino");
        emp2.setAge(19);

        employeeRepository.save(emp1);
        employeeRepository.save(emp2);

        Customer cus1 = new Customer();
        cus1.setClientCode("C-001");
        cus1.setName("Julian");
        cus1.setLastName("Casablancas");
        cus1.setAge(48);

        Customer cus2 = new Customer();
        cus2.setClientCode("C-002");
        cus2.setName("Nicolas");
        cus2.setLastName("Maduro");
        cus2.setAge(59);

        customerRepository.save(cus1);
        customerRepository.save(cus2);

        Product pro1 = new Product();
        pro1.setProductId("C-001");
        pro1.setName("Dark Chocolate Cookies");
        pro1.setPrice(5.00);

        Product pro2 = new Product();
        pro2.setProductId("K-001");
        pro2.setName("Ketchup Los Andes");
        pro2.setPrice(7.00);

        Product pro3 = new Product();
        pro3.setProductId("M-001");
        pro3.setName("Mayonnaise Los Andes");
        pro3.setPrice(10.00);

        Product pro4 = new Product();
        pro4.setProductId("C-002");
        pro4.setName("White Chocolate Cookies");
        pro4.setPrice(6.00);

        productRepository.save(pro1);
        productRepository.save(pro2);
        productRepository.save(pro3);
        productRepository.save(pro4);

        Supplier supp1 = new Supplier();
        supp1.setName("Nestlé");
        supp1.getProducts().add(pro1);

        Supplier supp2 = new Supplier();
        supp2.setName("Los andes");
        supp2.getProducts().add(pro2);

        productRepository.save(pro1);
        productRepository.save(pro2);
        supplierRepository.save(supp1);
        supplierRepository.save(supp2);


        Receipt rec1 = new Receipt();
        rec1.setCustomer(cus1);
        rec1.setBranches(sto1);
        rec1.setEmployee(emp1);
        rec1.setSerial("R-001");
        rec1.setTotal_price(38);
        rec1.setPaymentMeth(pay2);
        rec1.setReceiptDate(new Date());
        receiptRepository.save(rec1);

        Details det1 = new Details();
        det1.setReceipt(rec1);
        det1.setProduct(pro4);
        det1.setQuantity(3);
        detailsRepository.save(det1);

        rec1.getDetailList().add(det1);
        receiptRepository.save(rec1);

        Details det3 = new Details();
        det3.setReceipt(rec1);
        det3.setProduct(pro3);
        det3.setQuantity(2);
        detailsRepository.save(det3);

        rec1.getDetailList().add(det3);
        receiptRepository.save(rec1);


        Receipt rec2 = new Receipt();
        rec2.setCustomer(cus2);
        rec2.setBranches(sto4);
        rec2.setEmployee(emp2);
        rec2.setSerial("R-002");
        rec2.setTotal_price(29);
        rec2.setPaymentMeth(pay1);
        rec2.setReceiptDate(new Date());
        receiptRepository.save(rec2);

        Details det2 = new Details();
        det2.setReceipt(rec2);
        det2.setProduct(pro2);
        det2.setQuantity(2);
        detailsRepository.save(det2);

        rec2.getDetailList().add(det2);
        receiptRepository.save(rec2);

        Details det4 = new Details();
        det4.setReceipt(rec2);
        det4.setProduct(pro1);
        det4.setQuantity(3);
        detailsRepository.save(det4);

        rec2.getDetailList().add(det4);
        receiptRepository.save(rec2);

    }
}