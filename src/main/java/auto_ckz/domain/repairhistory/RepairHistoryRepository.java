package auto_ckz.domain.repairhistory;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface RepairHistoryRepository extends PagingAndSortingRepository<RepairHistory, Long> {

    RepairHistory findByDate(@Param("date") Date date);
    List<RepairHistory> findByCarId(@Param("carId") Long carId);
}
