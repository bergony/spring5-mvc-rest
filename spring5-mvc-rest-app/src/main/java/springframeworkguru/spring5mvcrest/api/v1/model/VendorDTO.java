package springframeworkguru.spring5mvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {

    private long id;

    @ApiModelProperty(value = "This is a first Name", required = true)
    private String name;

    @ApiModelProperty(value = "Url do add", required = true)
    private String vendor_url;
}
