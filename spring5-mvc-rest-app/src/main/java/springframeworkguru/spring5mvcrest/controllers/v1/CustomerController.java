package springframeworkguru.spring5mvcrest.controllers.v1;

import io.swagger.annotations.Api;
import springframeworkguru.model.CustomerListDTO;
import springframeworkguru.model.CustomerDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springframeworkguru.spring5mvcrest.services.CustomerService;

@Api(description = "This my customer Controller")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    private final CustomerService customerService;

    public static final String BASE_URL = "/api/v1/customers";


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Thiw will get a list of custumores.", notes = "There are some notes about the API.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getALLCategories() {
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.getCustomers().addAll(customerService.getAllCustomers());
        return customerListDTO;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.pathCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomerById(id);

    }
}
