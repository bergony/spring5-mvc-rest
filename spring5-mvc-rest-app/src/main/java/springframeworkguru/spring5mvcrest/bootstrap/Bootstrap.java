package springframeworkguru.spring5mvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframeworkguru.spring5mvcrest.domain.Category;
import springframeworkguru.spring5mvcrest.domain.Customer;
import springframeworkguru.spring5mvcrest.domain.Vendor;
import springframeworkguru.spring5mvcrest.repositories.CategoryRepository;
import springframeworkguru.spring5mvcrest.repositories.CustomerRepository;
import springframeworkguru.spring5mvcrest.repositories.VendorRepostitory;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepostitory vendorRepostitory;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepostitory vendorRepostitory) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepostitory = vendorRepostitory;
    }

    @Override
    public void run(String... args) {

        LoadCategories();
        LoadCustomers();
        LoadVendors();


    }

    private void LoadVendors(){
        Vendor vendor = new Vendor();
        vendor.setName("Bergony");
        vendorRepostitory.save(vendor);

        Vendor vendor1 = new Vendor();
        vendor1.setName("Bergony1");
        vendorRepostitory.save(vendor1);

        System.out.println("Data Loaded vendors = " + vendorRepostitory.count());

    }




    private void LoadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruis");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exocit = new Category();
        exocit.setName("Exocit");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exocit);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded Categories = " + categoryRepository.count());
    }

    private void LoadCustomers() {

        Customer customer1 = new Customer();
        customer1.setFirstname("Bergony");
        customer1.setLastname("Bandeira");

        Customer customer2 = new Customer();
        customer2.setFirstname("Jessica");
        customer2.setLastname("Bandeira");

        Customer customer3 = new Customer();
        customer3.setFirstname("Bruno");
        customer3.setLastname("Bandeira");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        System.out.println("Data Loaded Customer = " + customerRepository.count());
    }
}
