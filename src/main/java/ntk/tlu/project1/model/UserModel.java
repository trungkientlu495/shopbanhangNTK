package ntk.tlu.project1.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import ntk.tlu.project1.entity.ProductEntity;
@Data
public class UserModel {
	private Integer id;
	@Pattern(regexp = "^[a-zA-Z\\p{L}\\s-]+$", message = "Vui lòng nhập tên hợp lệ")
	private String name;
	@Email
	private String email;
	@NotBlank(message = "Vui lòng nhập địa chỉ hợp lệ")
	private String address;
	@Pattern(regexp = "^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$", message = "Số điện thoại không hợp lệ")
	private	String phone;
	private String password;
	private String repassword;
	// danh sach san pham nguoi dung yeu thich user va productWishlist
    private List<ProductModel> products;
    private List<BillModel> bills;
    private String role="User";
}
