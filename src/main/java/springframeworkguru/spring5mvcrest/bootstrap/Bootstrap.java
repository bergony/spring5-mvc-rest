package springframeworkguru.spring5mvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframeworkguru.spring5mvcrest.domain.Category;
import springframeworkguru.spring5mvcrest.domain.Customer;
import springframeworkguru.spring5mvcrest.repositories.CategoryRepository;
import springframeworkguru.spring5mvcrest.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {

        LoadCategories();
        LoadCustomers();


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
        customer1.setFirstName("Bergony");
        customer1.setLastName("Bandeira");

        Customer customer2 = new Customer();
        customer2.setFirstName("Jessica");
        customer2.setLastName("Bandeira");

        Customer customer3 = new Customer();
        customer3.setFirstName("Bruno");
        customer3.setLastName("Bandeira");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        System.out.println("Data Loaded Customer = " + customerRepository.count());
    }
}
