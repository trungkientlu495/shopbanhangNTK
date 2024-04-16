package ntk.tlu.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ntk.tlu.project1.entity.ReviewEntity;

public interface ReviewRepo extends JpaRepository<ReviewEntity, Integer>{
	@Query("SELECT u FROM ReviewEntity u WHERE u.id = :idComment")
	ReviewEntity showReviewEntity(@Param("idComment") int idComment);
}
