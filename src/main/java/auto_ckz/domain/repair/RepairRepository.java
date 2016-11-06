package auto_ckz.domain.repair;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RepairRepository extends PagingAndSortingRepository<Repair, Long> {
    List<Repair> findByRepairOrderId(@Param("repairOrderId") Long repairOrderId);
    List<Repair> findByMechanicId(@Param("mechanicId") Long mechanicId);
    List<Repair> findByRepairOrderIdAndMechanicId(@Param("repairOrderId") Long repairOrderId, @Param("mechanicId") Long mechanicId);
}