package auto_ckz.domain.address;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> { }