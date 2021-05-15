package springframeworkguru.spring5mvcrest.services;

import org.springframework.stereotype.Service;
import springframeworkguru.spring5mvcrest.api.v1.mapper.CustomerMapper;
import springframeworkguru.spring5mvcrest.api.v1.model.CustomerDTO;
import springframeworkguru.spring5mvcrest.domain.Customer;
import springframeworkguru.spring5mvcrest.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDOT(customer);
                    customerDTO.setCostumerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDOT)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDTOtoCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {


        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDOT(savedCustomer);

        returnDto.setCostumerUrl("/api/v1/customers/" + savedCustomer.getId());

        return returnDto;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO pathCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {

            if(customerDTO.getFirstname() != null){
                customer.setFirstname(customerDTO.getFirstname());
            }

            if(customerDTO.getLastname() != null){
                customer.setLastname(customerDTO.getLastname());
            }

            CustomerDTO returnDTO = customerMapper.customerToCustomerDOT(customerRepository.save(customer));
            returnDTO.setCostumerUrl("/api/v1/customers/" + id);
            return returnDTO;

        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteCustomerById(long id) {
        customerRepository.deleteById(id);
    }


}
