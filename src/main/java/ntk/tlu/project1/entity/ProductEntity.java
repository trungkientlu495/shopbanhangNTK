package ntk.tlu.project1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Product")
@Data
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String localBuy;
	private String shipping;
	private String brand;
	private String imageUrl;
	// gia ban gia
	private String price;
	// gia ban gia ban dau va dang sale ve price
	private String priceBegin;
	// gia nhap sp vao
	private String giaNhapSP;

	private int quantitySold;
	private String productType;
	// user and productWishlist
	@ManyToMany(mappedBy = "products")
	private List<UserEntity> users;

	@OneToMany(mappedBy = "productEntity")
	private List<BillitemsEntity> billItems;

}
