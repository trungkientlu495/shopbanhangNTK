package ntk.tlu.project1.model;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.entity.ReviewEntity;
import ntk.tlu.project1.entity.UserEntity;
@Data
public class CommentModel {
	private Integer id;
	private String content;
	private UserModel userEntity;
	private ProductModel productEntity;
    private ReviewModel review;
    private String createDate;
}
