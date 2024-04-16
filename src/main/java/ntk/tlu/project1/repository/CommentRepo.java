package ntk.tlu.project1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ntk.tlu.project1.entity.CommentEntity;
import ntk.tlu.project1.entity.ProductEntity;

public interface CommentRepo extends JpaRepository<CommentEntity, Integer>{
	@Query("SELECT u FROM CommentEntity u WHERE u.productEntity.id = :idProduct")
	Page<CommentEntity> showComment(@Param("idProduct") int idProduct,Pageable pageable);
	
	@Query("SELECT u FROM CommentEntity u WHERE u.productEntity.id = :idProduct AND u.review.startCounter = :starCounter")
	List<CommentEntity> showStar(@Param("idProduct") int idProduct,@Param("starCounter") int starCounter);
}
