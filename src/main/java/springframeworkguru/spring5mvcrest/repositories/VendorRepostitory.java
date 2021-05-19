package springframeworkguru.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springframeworkguru.spring5mvcrest.domain.Vendor;

public interface VendorRepostitory extends JpaRepository<Vendor, Long> {
}
