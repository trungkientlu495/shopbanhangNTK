package ntk.tlu.project1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import ntk.tlu.project1.entity.CommentEntity;
import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.entity.UserEntity;
@Data
public class ReviewModel {
	private Integer id;
	private int startCounter;
	private UserModel userEntity;
	private ProductModel productEntity;
    
}
