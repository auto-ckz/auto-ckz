package auto_ckz.domain.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	List<Customer> findByFirstNameAndLastName(@Param("fistName") String firstName,
											  @Param("lastName") String lastName);
	Customer findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
