package ntk.tlu.project1.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class BillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String buyDate;
	private String address;

	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
	
	@OneToMany(mappedBy = "billEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BillitemsEntity> billitemsEntities;
	private String tongHoaDon;
	
	
	
	

	
	
}
