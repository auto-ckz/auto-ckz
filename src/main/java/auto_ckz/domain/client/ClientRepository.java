package auto_ckz.domain.client;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
	List<Client> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	Client findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
