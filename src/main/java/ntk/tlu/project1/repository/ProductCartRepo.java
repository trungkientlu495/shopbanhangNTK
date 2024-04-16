package ntk.tlu.project1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import ntk.tlu.project1.entity.ProductCartEntity;

public interface ProductCartRepo extends JpaRepository<ProductCartEntity, Long> {
	// tim ProductCart theo idUser
	@Query("SELECT u FROM ProductCartEntity u WHERE u.userEntity.id = :idUser")
	List<ProductCartEntity> searchProductCartbyIdU(@Param("idUser") int idUser);

	// tim idProduct va idUser trong ProductCart
	@Query("SELECT u FROM ProductCartEntity u WHERE u.userEntity.id = :idUser AND u.productEntity.id = :idProduct")
	ProductCartEntity searchProductCartbyIdUandIdP(@Param("idUser") int idUser, @Param("idProduct") int idProduct);

	// remove Product khoi Cart
	@Modifying
	@Transactional
	@Query("DELETE FROM ProductCartEntity u WHERE u.userEntity.id = :idUser AND u.productEntity.id = :idProduct")
	void removeProductCartbyIdUandIdP(@Param("idUser") int idUser, @Param("idProduct") int idProduct);
	
	@Modifying
	@Transactional
	@Query("SELECT u FROM ProductCartEntity u WHERE u.quanlitys = :soluong AND u.productEntity.id = :idProduct")
	void updateProductCartsoluong(@Param("soluong") String soluong,@Param("id") int idProduct);
}
