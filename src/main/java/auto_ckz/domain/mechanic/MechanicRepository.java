package auto_ckz.domain.mechanic;

import auto_ckz.domain.abstracts.PersonRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MechanicRepository extends PersonRepository<Mechanic> { }
