package springframeworkguru.spring5mvcrest.services;



import springframeworkguru.model.CustomerDTO;
import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO pathCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomerById( long id);
}
