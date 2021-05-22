package springframeworkguru.spring5mvcrest.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springframeworkguru.spring5mvcrest.api.v1.model.VendorDTO;
import springframeworkguru.spring5mvcrest.api.v1.model.VendorListDTO;
import springframeworkguru.spring5mvcrest.services.VendorService;


@Api(description = "Vendor Api")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {


    private final VendorService vendorService;

    public static final String BASE_URL = "/api/v1/vendors";


    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "List of all vendors")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {return new VendorListDTO(vendorService.getAllVendors());
    }

    @ApiOperation(value = "Take a vendor by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorByiD(@PathVariable Long id) {
        return vendorService.getVendorByiD(id);
    }

    @ApiOperation(value = "Create a vendor pass in the body a json")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendor(vendorDTO);
    }


    @ApiOperation(value = "Create or update a vendor")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @ApiOperation(value = "Add parsing modification on the vendor")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Delete vendor by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendorById(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }




}
