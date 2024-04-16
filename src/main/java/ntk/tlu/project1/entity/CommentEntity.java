package ntk.tlu.project1.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Comment")
@Data
//@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String content;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
	@MapsId
    private ReviewEntity review;
	private String createDate;
	
	
	
}
