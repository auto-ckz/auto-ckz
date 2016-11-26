package auto_ckz.domain.repairorder;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.sql.Date;
import java.util.List;

@RepositoryRestResource
public interface RepairOrderRepository extends PagingAndSortingRepository<RepairOrder, Long> {

    List<RepairOrder> findByClientId(@Param("clientId") Long clientId);
    List<RepairOrder> findByClientIdAndCarId(@Param("clientId") Long clientId,
                                             @Param("carId") Long carId);
    List<RepairOrder> findByMemberOfCustomerServiceId(@Param("memberOfCustomerServiceId") Long memberOfCustomerServiceId);
    RepairOrder findByCarId(@Param("carId") Long carId);
    List<RepairOrder> findByDate(@Param("date") Date date);
}
