package springframeworkguru.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import springframeworkguru.model.CustomerDTO;
import springframeworkguru.spring5mvcrest.domain.Customer;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", source = "id")
    CustomerDTO customerToCustomerDOT(Customer customer);

    Customer customerDTOtoCustomer(CustomerDTO customerDTO);
}
