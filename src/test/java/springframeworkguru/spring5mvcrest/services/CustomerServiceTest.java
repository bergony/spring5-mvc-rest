package springframeworkguru.spring5mvcrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springframeworkguru.spring5mvcrest.api.v1.mapper.CustomerMapper;
import springframeworkguru.spring5mvcrest.api.v1.model.CustomerDTO;
import springframeworkguru.spring5mvcrest.domain.Customer;
import springframeworkguru.spring5mvcrest.repositories.CustomerRepository;
import sun.security.x509.OtherName;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
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

    @Test
    public void createNewCustomer() {

        //Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("bergony");

        Customer saveCustomer = new Customer();
        saveCustomer.setFirstname(customerDTO.getFirstname());
        saveCustomer.setLastname(customerDTO.getLastname());
        saveCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(saveCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customers/1", savedDto.getCostumerUrl());


    }

    @Test
    public void saveCustomerByDTO() {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("bergony");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customers/1", savedDto.getCostumerUrl());
    }
}