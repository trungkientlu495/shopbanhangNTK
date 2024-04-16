package ntk.tlu.project1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntk.tlu.project1.entity.ProductCartEntity;
import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.entity.UserEntity;
import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.repository.ProductCartRepo;
import ntk.tlu.project1.repository.ProductRepo;
import ntk.tlu.project1.repository.UserRepo;

@Service
public class ProductCartServices {
	@Autowired
	ProductCartRepo productCartRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	ModelMapper modelMapper;
	// create product User (Them sp vao Cart)
	public void createProductCart(int idUser, int idProduct, String quantitys) {
		ProductEntity productEntity = productRepo.searchById(idProduct);
		UserEntity userEntity = userRepo.showUserid(idUser);
		ProductCartEntity productExists = productCartRepo.searchProductCartbyIdUandIdP(idUser, idProduct);
		if (productExists == null) {
			ProductCartEntity productCart = new ProductCartEntity();
			productCart.setProductEntity(productEntity);
			productCart.setUserEntity(userEntity);
			productCart.setQuanlitys(quantitys);
			productCartRepo.save(productCart);
		}else {
			productExists.setQuanlitys(quantitys);
			productCartRepo.save(productExists);
		}
	}
	
	// hienthitoanbo san pham
	public List<ProductModel> findAllProduct() {
		List<ProductCartEntity> productCartEntity = productCartRepo.findAll();
		List<ProductEntity> productEntity = new ArrayList<>();
		for (ProductCartEntity productCart2 : productCartEntity) {
			productEntity.add(productCart2.getProductEntity());
		}
		List<ProductModel> productModels = productEntity.stream().map(entity -> modelMapper.map(entity, ProductModel.class))
	            .collect(Collectors.toList());
		return productModels;
	}
	

	// list product in productCart
	public List<ProductModel> showlistPinPC(int idUser) {
		List<ProductCartEntity> productCartEntity = productCartRepo.searchProductCartbyIdU(idUser);
		List<ProductEntity> productEntity = new ArrayList<>();
		for (ProductCartEntity productCart2 : productCartEntity) {
			productEntity.add(productCart2.getProductEntity());
		}
		List<ProductModel> productModels = productEntity.stream().map(entity -> modelMapper.map(entity, ProductModel.class))
	            .collect(Collectors.toList());
		return productModels;
	}

	// list quantitys in ProductCart
	public List<String> showlistQinPC(int idUser) {
		List<ProductCartEntity> productCart = productCartRepo.searchProductCartbyIdU(idUser);
		List<String> soluongs = new ArrayList<>();
		for (ProductCartEntity productCart2 : productCart) {
			soluongs.add(productCart2.getQuanlitys());
		}
		return soluongs;
	}
	
	//update ProductCart
	public void updateProduct(List<String> soluong,int idUser) {
		List<ProductCartEntity> productCarts = productCartRepo.searchProductCartbyIdU(idUser);
		for(int i=0;i<productCarts.size();i++) {
			productCarts.get(i).setQuanlitys(soluong.get(i));
			productCartRepo.save(productCarts.get(i));
		}
		
	}

	// remove product co trong product khong Cart
	public void removeProductCart(int idUser, int idProduct) {
		productCartRepo.removeProductCartbyIdUandIdP(idUser, idProduct);
	}
}
