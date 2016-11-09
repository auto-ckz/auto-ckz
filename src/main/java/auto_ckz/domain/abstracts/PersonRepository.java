package auto_ckz.domain.abstracts;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface PersonRepository<T extends AbstractPersonEntity>  extends PagingAndSortingRepository<T, Long>{
    List<T> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
    T findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    T findByPesel(@Param("pesel") Long pesel);
}