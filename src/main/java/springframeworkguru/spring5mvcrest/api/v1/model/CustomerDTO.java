package springframeworkguru.spring5mvcrest.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String costumerUrl;
}
