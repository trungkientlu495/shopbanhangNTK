package ntk.tlu.project1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Entity
@Table(name = "User")
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Pattern(regexp = "^[a-zA-Z\\p{L}\\s-]+$", message = "Vui lòng nhập tên hợp lệ")
	private String name;
	@Email
	@Column(unique = true)
	private String email;
	@NotBlank(message = "Vui lòng nhập địa chỉ hợp lệ")
	private String address;
	@Column(unique = true)
	@Pattern(regexp = "^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$", message = "Số điện thoại không hợp lệ")
	private	String phone;
	//@Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "Vui lòng nhập mật khẩu hợp lệ")
	private String password;
	private String repassword;
	
	// danh sach san pham nguoi dung yeu thich user va productWishlist
	@JsonBackReference
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "User_ProductWist",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<ProductEntity> products;
	// user and productCart
	 // Một User có nhiều Bill
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<BillEntity> bills;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<ProductCartEntity> productCartEntities;
    private String role="User";
    
}
