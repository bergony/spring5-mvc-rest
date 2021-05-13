package springframeworkguru.spring5mvcrest.api.v1.mapper;

import org.junit.Test;
import springframeworkguru.spring5mvcrest.api.v1.model.CustomerDTO;
import springframeworkguru.spring5mvcrest.domain.Customer;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDOT() {

        //Criar Ojecto para teste GIVEM
        Customer customer = new Customer();
        String FIRSTNAME = "Bergony";
        customer.setFirstName(FIRSTNAME);
        String LASTANAME = "BANDERA";
        customer.setLastName(LASTANAME);
        long ID = 1L;
        customer.setId(ID);

        //Quando for chamando o evento when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDOT(customer);


        //Verficar os valores
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());

    }
}