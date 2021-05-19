package springframeworkguru.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import springframeworkguru.spring5mvcrest.api.v1.model.VendorDTO;
import springframeworkguru.spring5mvcrest.domain.Vendor;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);


    @Mapping(target = "id", source = "id")
    VendorDTO vendorToVendoDTO(Vendor vendor);

    Vendor vendorDTOtoVendo(VendorDTO vendorDTO);


}
