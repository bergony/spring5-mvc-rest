package springframeworkguru.spring5mvcrest.api.v1.model;

import lombok.Data;

@Data
public class VendorDTO {

    private long id;
    private String name;
    private String vendor_url;
}
