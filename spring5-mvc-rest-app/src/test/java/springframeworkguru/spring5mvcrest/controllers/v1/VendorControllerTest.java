package springframeworkguru.spring5mvcrest.controllers.v1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springframeworkguru.spring5mvcrest.api.v1.model.VendorDTO;
import springframeworkguru.spring5mvcrest.domain.Vendor;
import springframeworkguru.spring5mvcrest.services.VendorService;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static springframeworkguru.spring5mvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;

public class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();

    }

    @Test
    public void getAllVendors() throws Exception {

        VendorDTO vendorDTO = new VendorDTO();
        VendorDTO vendorDTO1 = new VendorDTO();

        List<VendorDTO> vendors = Arrays.asList( vendorDTO, vendorDTO1);

        when(vendorService.getAllVendors()).thenReturn(vendors);


        //Check values
        mockMvc.perform(get(VendorController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));


    }

    @Test
    public void getVendorByiD() throws Exception {

        VendorDTO vendorDTO = new VendorDTO();
        long id = 1L;
        vendorDTO.setId(id);

        when(vendorService.getVendorByiD(anyLong())).thenReturn(vendorDTO);

        VendorDTO vendorDTO1 = vendorService.getVendorByiD(1L);

        mockMvc.perform(get(VendorController.BASE_URL+"/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        assertEquals(vendorDTO.getId(), vendorDTO1.getId());



    }

    @Test
    public void createNewVendor() throws Exception {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("bergony");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendor_url(VendorController.BASE_URL+"/1");

        when(vendorService.createNewVendor(any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(post(VendorController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("bergony")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL+"/1")));


    }

    @Test
    public void updateCustomer() throws Exception {


        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("bergony");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendor_url(VendorController.BASE_URL+"/1");

        when(vendorService.saveVendorByDTO(anyLong(),any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(VendorController.BASE_URL+"/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("bergony")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL+"/1")));

    }

    @Test
    public void patchVendor() throws Exception {


        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("bergony");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendor_url(VendorController.BASE_URL+"/1");

        when(vendorService.patchVendor(anyLong(),any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(VendorController.BASE_URL+"/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("bergony")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL+"/1")));

    }

    @Test
    public void deleteVendorById() throws Exception {

        mockMvc.perform(delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());

    }
}