package ntk.tlu.project1.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.entity.UserEntity;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.repository.ProductCartRepo;
import ntk.tlu.project1.repository.ProductRepo;
import ntk.tlu.project1.repository.UserRepo;

@Service
public class UserServices {
	@Autowired
	UserRepo userRepo;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	ProductCartRepo productCartRepo;
	@Autowired
	private ModelMapper modelMapper;

	// CHECK LOGIN
//	public boolean checkLogin(String email, String password) {
//		UserEntity userEntity = userRepo.checkLogin(email);
//		logger.info("USERENTITY: "+userEntity.getEmail());
//		if (userEntity != null && userEntity.getPassword().equals(password)) {
//			logger.info("USERENTITY: "+userEntity.getEmail());
//			return true;
//		}
//		return false;
//	}

	// SHOW ALL USER
	@Cacheable(cacheNames = "showAllUser")
	public Page<UserModel> showAllUser(Pageable pageable) {
		Page<UserEntity> userEntities = userRepo.findAll(pageable);
		List<UserModel> userModels = userEntities.stream().map(entity -> modelMapper.map(entity, UserModel.class))
				.collect(Collectors.toList());
		return new PageImpl<>(userModels, pageable, userEntities.getTotalElements());
	}

	// Create User
	@CacheEvict(cacheNames = "showAllUser")
	public void createUser(UserModel userModel) {
		UserEntity userEntity = modelMapper.map(userModel, UserEntity.class);
		userEntity.setPassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));
		userEntity.setRepassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));
		userRepo.save(userEntity);
	}

	@CacheEvict(cacheNames = "showAllUser",key = "#idUser")
	public void deleteUser(int idUser) {
		UserEntity userEntity = userRepo.showUserid(idUser);
		userRepo.deleteUserById(idUser);
	}

	// Search User theo Email
	// @Cacheable(cacheNames = "showUser",key = "#email")
	public UserModel showUser(String email) {
		UserEntity userEntity = userRepo.showUser(email);
		if (userEntity == null)
			throw new NoResultException();
		UserModel userModel = modelMapper.map(userEntity, UserModel.class);
		return userModel;
	}

	// search theo id
	// @Cacheable(cacheNames = "showUserid",key = "#id")
	public UserModel showUserid(int id) {
		UserEntity userEntity = userRepo.showUserid(id);
		UserModel userModel = modelMapper.map(userEntity, UserModel.class);
		return userModel;
	}

	// search theo Email OR Phone
	// @Cacheable(cacheNames = "searchEmailOrPhoneUser",key = "#x")
	public UserModel searchEmailOrPhoneUser(String x) {
		UserEntity userEntity = userRepo.searchEmailOrPhoneUser(x);
		if (userEntity == null)
			throw new NoResultException();
		UserModel userModel = modelMapper.map(userEntity, UserModel.class);
		return userModel;
	}

	// create product vào list<products> User them vao wishlist
	public void createProductUser(int idUser, int idProduct) {
		if (!userRepo.showUserid(idUser).getProducts().contains(productRepo.searchById(idProduct))) {
			userRepo.showUserid(idUser).getProducts().add(productRepo.searchById(idProduct));
			userRepo.save(userRepo.showUserid(idUser));
		}
	}

	// remove product tu field wishlist list<product> in User
	public void removeWishList(int idUser, int idProduct) {
		// search product theo id
		ProductEntity product = productRepo.searchById(idProduct);
		// search user theo id
		UserEntity user = userRepo.showUserid(idUser);
		// remove product tu field list<product> in User
		for (int i = 0; i < user.getProducts().size(); i++) {
			if (user.getProducts().get(i).getId() == idProduct) {
				user.getProducts().remove(i);
				userRepo.save(user);
			}
		}
	}

}
