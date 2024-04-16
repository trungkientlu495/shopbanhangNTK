package ntk.tlu.project1.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import ntk.tlu.project1.entity.UserEntity;
@Data
public class ProductModel {
	private Integer id;
	private String name;
	private String localBuy;
	private String shipping;
	private String brand;
	private String imageUrl;
	private String tinhtrang; // con hang or het hang
	private String price;
	private String priceBegin;
	private int quantitySold;
	private String productType;
	private List<BillitemsModel> billItems;
	// user and productWishlist
	private List<UserModel> users;
	private String giaNhapSP;
}
