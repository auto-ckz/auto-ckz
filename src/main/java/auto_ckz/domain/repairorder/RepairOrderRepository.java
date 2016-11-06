package auto_ckz.domain.repairorder;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface RepairOrderRepository extends PagingAndSortingRepository<RepairOrder, Long> {

    List<RepairOrder> findByClientId(@Param("clientId") Long clientId);
    List<RepairOrder> findByMemberOfCustomerServiceId(@Param("memberOfCustomerServiceId") Long memberOfCustomerServiceId);
    RepairOrder findByCarId(@Param("CarId") Long carId);
    List<RepairOrder> findByDate(@Param("date") Date date);
}
