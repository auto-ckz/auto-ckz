package auto_ckz.domain.car;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
    List<Car> findByMake(@Param("make") String make);
    Car findByRegistrationNumber(@Param("registrationNumber") String registrationNumber);
    Car findByOc(@Param("oc") String oc);
    Car findByVin(@Param("vin") String vin);
}
