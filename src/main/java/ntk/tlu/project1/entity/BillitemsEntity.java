package ntk.tlu.project1.entity;

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
@Table(name = "BillItems")
@Data
public class BillitemsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "bill_id")
	private BillEntity billEntity;
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
	
	private String quantity;
	private String buyPrice;
	
	
	
}
