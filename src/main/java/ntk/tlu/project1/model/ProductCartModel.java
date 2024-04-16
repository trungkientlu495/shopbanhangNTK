package ntk.tlu.project1.model;

import lombok.Data;

@Data
public class ProductCartModel {
	private Long id;
	private UserModel userEntity;
	private ProductModel productEntity;
	private String quanlitys;
}
