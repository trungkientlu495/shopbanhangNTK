package ntk.tlu.project1.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name = "Review")
@Data
public class ReviewEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int startCounter;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;
}
