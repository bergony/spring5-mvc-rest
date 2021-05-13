package springframeworkguru.spring5mvcrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springframeworkguru.spring5mvcrest.api.v1.mapper.CustomerMapper;
import springframeworkguru.spring5mvcrest.api.v1.model.CustomerDTO;
import springframeworkguru.spring5mvcrest.domain.Customer;
import springframeworkguru.spring5mvcrest.repositories.CustomerRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private static final Long ID = 1L;
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        //noinspection deprecation
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE );
    }

    @Test
    public void getAllCustomers() {

        Customer customer = new Customer();
        Customer customer1 = new Customer();

        List<Customer> customers = Arrays.asList(customer, customer1);

        when(customerRepository.findAll()).thenReturn(customers);


        List<CustomerDTO> customerList = customerService.getAllCustomers();


        assertEquals(customerList.size(), 2);

    }

    @Test
    public void getCustomerById() {

        Customer customer = new Customer();
        customer.setId(ID);

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(java.util.Optional.of(customerDTO.getId()),
                java.util.Optional.of(ID));


    }
}