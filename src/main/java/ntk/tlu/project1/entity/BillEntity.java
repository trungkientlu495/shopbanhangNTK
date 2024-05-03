package ntk.tlu.project1.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Bill")
@Data
//@EntityListeners(AuditingEntityListener.class)
public class BillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate buyDate;
	private String address;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

	@JsonManagedReference
	@OneToMany(mappedBy = "billEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BillitemsEntity> billitemsEntities;
	private String tongHoaDon;

}
