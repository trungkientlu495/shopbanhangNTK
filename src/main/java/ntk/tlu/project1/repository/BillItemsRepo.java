package ntk.tlu.project1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ntk.tlu.project1.entity.BillitemsEntity;
import ntk.tlu.project1.entity.ProductEntity;

public interface BillItemsRepo extends JpaRepository<BillitemsEntity, Long> {
	@Query("SELECT u FROM BillitemsEntity u")
	List<BillitemsEntity> showBillitemsEntity();
	
	@Query("SELECT u FROM BillitemsEntity u JOIN u.billEntity WHERE u.billEntity.id = :idBill")
	List<BillitemsEntity> detailBill(@Param("idBill") int idBill);
	
	@Query("SELECT u.productEntity From BillitemsEntity u")
	ProductEntity showProduct();
	
}
