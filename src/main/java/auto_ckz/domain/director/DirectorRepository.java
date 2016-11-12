package auto_ckz.domain.director;

import auto_ckz.domain.abstracts.PersonRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DirectorRepository extends PersonRepository<Director> { }
