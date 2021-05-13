package springframeworkguru.spring5mvcrest.controllers.v1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springframeworkguru.spring5mvcrest.api.v1.model.CustomerDTO;
import springframeworkguru.spring5mvcrest.services.CustomerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;
    MockMvc mockMvc;

    @Before
    public void setUp() {

        //noinspection deprecation
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @Test
    public void getALLCategories() throws Exception {

        //Crair dados de teste Givem
        CustomerDTO customerDTO = new CustomerDTO();

        CustomerDTO customerDTO1 = new CustomerDTO();

        List<CustomerDTO> customers = Arrays.asList(customerDTO, customerDTO1);

        //When quando tiver chamada usar os dados
        when(customerService.getAllCustomers()).thenReturn(customers);

        //Check values
        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    public void getCustomerById() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        long ID = 1L;
        customerDTO.setId(ID);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        CustomerDTO customerDTO1 = customerService.getCustomerById(1L);

        mockMvc.perform(get("/api/v1/customers/1").contentType(
                MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        assertEquals(customerDTO1.getId(), customerDTO.getId());

    }
}