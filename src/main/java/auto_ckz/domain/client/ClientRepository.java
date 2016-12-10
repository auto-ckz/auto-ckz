package auto_ckz.domain.client;

import auto_ckz.domain.abstracts.PersonRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends PersonRepository<Client> {
}
