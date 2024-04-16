package ntk.tlu.project1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ntk.tlu.project1.entity.BillEntity;

public interface BillRepo extends JpaRepository<BillEntity, Long> {
	@Query("SELECT a FROM BillEntity a WHERE a.userEntity.id = :idUser ORDER BY a.id DESC")
	List<BillEntity> searchBill(@Param("idUser") int idUser);
	
	@Query("SELECT a FROM BillEntity a ORDER BY a.id DESC")
	List<BillEntity> searchBill();
}
