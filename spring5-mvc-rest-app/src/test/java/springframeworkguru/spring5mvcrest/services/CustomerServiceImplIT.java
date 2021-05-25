package springframeworkguru.spring5mvcrest.services;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import springframeworkguru.spring5mvcrest.api.v1.mapper.CustomerMapper;
import springframeworkguru.model.CustomerDTO;
import springframeworkguru.spring5mvcrest.bootstrap.Bootstrap;
import springframeworkguru.spring5mvcrest.domain.Customer;
import springframeworkguru.spring5mvcrest.repositories.CategoryRepository;
import springframeworkguru.spring5mvcrest.repositories.CustomerRepository;
import springframeworkguru.spring5mvcrest.repositories.VendorRepostitory;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepostitory vendorRepostitory;

    CustomerService customerService;

    @Before
    public void setUP() {
        System.out.println("Loading Customer DATA");
        System.out.println(customerRepository.findAll().size());

        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepostitory);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);

    }

    @Test
    public void pathCustomerUpdateFirstName() {
        String updateName = "UpdateDName";
        long id = getCustomerIDvalue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(updateName);

        customerService.pathCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).orElse(null);

        assertNotNull(updatedCustomer);
        assertEquals(updateName, updatedCustomer.getFirstname());
        assertThat(originalFirstName, Matchers.not(equalTo(updatedCustomer.getFirstname())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastname()));

    }

    @Test
    public void pathCustomerUpdateLastName() {
        String updateName = "UpdateDName";
        long id = getCustomerIDvalue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(updateName);

        customerService.pathCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).orElse(null);

        assertNotNull(updatedCustomer);
        assertEquals(updateName, updatedCustomer.getLastname());
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstname()));
        assertThat(originalLastName,  Matchers.not(equalTo(updatedCustomer.getLastname())));

    }

    private long getCustomerIDvalue() {
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers found" + customers.size());

        return customers.get(0).getId();

    }
}