package springframeworkguru.spring5mvcrest.api.v1.mapper;

import org.junit.Test;
import springframeworkguru.spring5mvcrest.api.v1.model.VendorDTO;
import springframeworkguru.spring5mvcrest.controllers.v1.VendorController;
import springframeworkguru.spring5mvcrest.domain.Vendor;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    private final VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendoDTO() {
        Vendor vendor = new Vendor();
        vendor.setName("Bergony");
        vendor.setId(1L);
        vendor.setVendor_url(VendorController.BASE_URL + "/" + 1L);

        VendorDTO vendorDTO = vendorMapper.vendorToVendoDTO(vendor);



        assertEquals(vendor.getId(),vendorDTO.getId());
        assertEquals(vendor.getName(), vendorDTO.getName());
        assertEquals(vendor.getVendor_url(), vendorDTO.getVendor_url());




    }

    @Test
    public void vendorDTOtoVendo() {

        VendorDTO vendorDTo = new VendorDTO();
        vendorDTo.setName("Bergony");
        vendorDTo.setId(1L);
        vendorDTo.setVendor_url(VendorController.BASE_URL + "/" + 1L);

        Vendor vendor = vendorMapper.vendorDTOtoVendo(vendorDTo);

        assertEquals(vendorDTo.getId(),vendor.getId());
        assertEquals(vendorDTo.getName(), vendor.getName());
        assertEquals(vendorDTo.getVendor_url(), vendor.getVendor_url());


    }
}