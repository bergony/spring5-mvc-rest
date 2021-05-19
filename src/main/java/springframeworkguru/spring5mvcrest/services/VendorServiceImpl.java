package springframeworkguru.spring5mvcrest.services;

import org.springframework.stereotype.Service;
import springframeworkguru.spring5mvcrest.api.v1.mapper.VendorMapper;
import springframeworkguru.spring5mvcrest.api.v1.model.VendorDTO;
import springframeworkguru.spring5mvcrest.controllers.v1.CustomerController;
import springframeworkguru.spring5mvcrest.domain.Vendor;
import springframeworkguru.spring5mvcrest.repositories.VendorRepostitory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepostitory vendorRepostitory;

    private final VendorMapper vendorMapper;



    public VendorServiceImpl(VendorRepostitory vendorRepostitory, VendorMapper vendorMapper) {
        this.vendorRepostitory = vendorRepostitory;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepostitory.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendoDTO(vendor);
                    vendorDTO.setVendor_url(getVendorUrl(vendor.getId()));

                    return vendorDTO;

                }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOtoVendo(vendorDTO));
    }

    @Override
    public void deleteVendorById(long id) {
        vendorRepostitory.deleteById(id);
    }

    @Override
    public VendorDTO getVendorByiD(long id) {
        return vendorRepostitory.findById(id)
                .map(vendorMapper::vendorToVendoDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO patchVendor(long id, VendorDTO vendorDTO) {
        return vendorRepostitory.findById(id).map(vendor -> {

            if(vendorDTO.getName() != null){
                vendor.setName(vendorDTO.getName());
            }


            VendorDTO returnDTO = vendorMapper.vendorToVendoDTO(vendorRepostitory.save(vendor));
            returnDTO.setVendor_url(getVendorUrl(id));
            return returnDTO;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO saveVendorByDTO(long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendo(vendorDTO);
        vendor.setId(id);
        vendor.setVendor_url(getVendorUrl(id));

        return saveAndReturnDTO(vendor);
    }

    private String getVendorUrl(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }


    private VendorDTO saveAndReturnDTO(Vendor vendor) {

        Vendor savedVendor = vendorRepostitory.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendoDTO(savedVendor);

        returnDto.setVendor_url(getVendorUrl(savedVendor.getId()));

        return returnDto;
    }

}
