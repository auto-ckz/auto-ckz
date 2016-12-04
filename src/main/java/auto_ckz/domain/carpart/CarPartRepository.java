package auto_ckz.domain.carpart;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarPartRepository extends PagingAndSortingRepository<CarPart, Long> { }
