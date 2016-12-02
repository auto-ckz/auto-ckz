package auto_ckz.domain.memberofcustomerservice;

import auto_ckz.domain.abstracts.PersonRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MemberOfCustomerServiceRepository extends PersonRepository<MemberOfCustomerService> { }
