package springframeworkguru.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springframeworkguru.spring5mvcrest.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
