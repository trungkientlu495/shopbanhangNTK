package ntk.tlu.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import ntk.tlu.project1.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
//	@Query("SELECT u FROM UserEntity u")
//	Page<UserEntity> showAllUser(Pageable pageable);
	// Select email , password
	@Query("SELECT u From UserEntity u WHERE u.email LIKE :email")
	UserEntity checkLogin(@Param("email") String email);

	// Search User theo email
	@Query("SELECT u From UserEntity u WHERE u.email LIKE :email")
	UserEntity showUser(@Param("email") String email);

	// Search User theo emai Or Phone
	@Query("SELECT u From UserEntity u WHERE u.email LIKE :x OR u.phone LIKE :x")
	UserEntity searchEmailOrPhoneUser(@Param("x") String x);

	// search theo id
	@Query("SELECT u From UserEntity u WHERE u.id = :id")
	UserEntity showUserid(@Param("id") Integer id);

	@Modifying
	@Transactional 
	@Query("DELETE FROM UserEntity u WHERE u.id = :userId")
	void deleteUserById(@Param("userId") int userId);
}
