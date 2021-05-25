package springframeworkguru.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private long id;

    @ApiModelProperty(value = "This is  the name", required = true)
    private String firstname;

    @ApiModelProperty(value = "Last Name name", required = true)
    private String lastname;

    @JsonProperty("customer_url")
    private String costumerUrl;
}
