package springframeworkguru.spring5mvcrest.services;

import springframeworkguru.spring5mvcrest.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    void deleteVendorById(long id);

    VendorDTO getVendorByiD( long id);

    VendorDTO patchVendor(long id, VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(long id, VendorDTO vendorDTO);
}
