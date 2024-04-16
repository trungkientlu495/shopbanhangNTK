package ntk.tlu.project1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ntk.tlu.project1.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
	//search all product
	@Query("SELECT u FROM ProductEntity u")
	Page<ProductEntity> searchAllProduct(Pageable pageable);
	//search name
	@Query("SELECT u From ProductEntity u WHERE u.name LIKE :timkiem%")
	Page<ProductEntity> searchNameProduct(@Param("timkiem") String timkiem,Pageable pageable);
	
	// search theo User timkiem (theo name)
	@Query("SELECT u FROM ProductEntity u WHERE u.name LIKE :search%")
	List<ProductEntity> searchByKeyword(@Param("search") String search);
	
	// search theo id
	@Query("SELECT u FROM ProductEntity u WHERE u.id = :id")
	ProductEntity searchById(@Param("id") int id);
	
	//search theo product_type 
	@Query("SELECT u FROM ProductEntity u WHERE u.productType LIKE :productType%")
	List<ProductEntity> searchByProductType(@Param("productType") String productType);
	
	// search localBuy
	
	@Query("SELECT u FROM ProductEntity u " + "WHERE"
			+ "(u.name LIKE :search% AND (u.localBuy LIKE :hanoi% OR u.localBuy LIKE :thanhhoa% OR u.localBuy LIKE :haiphong%))")
	List<ProductEntity> searchLocalBuy(@Param("search") String search, @Param("hanoi") String hanoi,
			@Param("thanhhoa") String thanhhoa, @Param("haiphong") String haiphong);
	
	// search shipping
	
	@Query("SELECT u FROM ProductEntity u " + "WHERE"
			+ "(u.name LIKE :search% AND (u.shipping LIKE :hoatoc% OR u.shipping LIKE :nhanh% OR u.shipping LIKE :tietkiem%))")
	List<ProductEntity> searchShipping(@Param("search") String search, @Param("hoatoc") String hoatoc,
			@Param("nhanh") String nhanh, @Param("tietkiem") String tietkiem);
	
	// search brand
	
	@Query("SELECT u FROM ProductEntity u " + "WHERE"
			+ "(u.name LIKE :search% AND (u.brand LIKE :unisex% OR u.brand LIKE :tlu% OR u.brand LIKE :yody%))")
	List<ProductEntity> searchBrand(@Param("search") String search, @Param("unisex") String unisex,
			@Param("tlu") String tlu, @Param("yody") String yody);
	
	// searchLocalBuyandShipping
	@Query("SELECT u FROM ProductEntity u " + "WHERE"
			+ "(u.name LIKE :search% AND (u.localBuy LIKE :hanoi% OR u.localBuy LIKE :thanhhoa% OR u.localBuy LIKE :haiphong%)) AND"
			+ "(u.name LIKE :search% AND (u.shipping LIKE :hoatoc% OR u.shipping LIKE :nhanh% OR u.shipping LIKE :tietkiem%))")
	List<ProductEntity> searchLocalBuyandShipping(@Param("search") String search, @Param("hanoi") String hanoi,
			@Param("thanhhoa") String thanhhoa, @Param("haiphong") String haiphong,@Param("hoatoc") String hoatoc,@Param("nhanh") String nhanh,@Param("tietkiem") String tietkiem);
	
	// search LocalBuy and Brand
	
	@Query("SELECT u FROM ProductEntity u " + "WHERE"
			+ "(u.name LIKE :search% AND (u.localBuy LIKE :hanoi% OR u.localBuy LIKE :thanhhoa% OR u.localBuy LIKE :haiphong%)) AND"
			+ "(u.name LIKE :search% AND (u.brand LIKE :unisex% OR u.brand LIKE :tlu% OR u.brand LIKE :yody%))")
	List<ProductEntity> searchLocalBuyandBrand(@Param("search") String search, @Param("hanoi") String hanoi,
			@Param("thanhhoa") String thanhhoa, @Param("haiphong") String haiphong,@Param("unisex") String unisex,@Param("tlu") String tlu,@Param("yody") String yody);
	
	// search Shipping and Brand
	
	@Query("SELECT u FROM ProductEntity u " + "WHERE"
			+ "(u.name LIKE :search% AND (u.shipping LIKE :hoatoc% OR u.shipping LIKE :nhanh% OR u.shipping LIKE :tietkiem%)) AND"
			+ "(u.name LIKE :search% AND (u.brand LIKE :unisex% OR u.brand LIKE :tlu% OR u.brand LIKE :yody%))")
	List<ProductEntity> searchShippingandBrand(@Param("search") String search, @Param("hoatoc") String hoatoc,
			@Param("nhanh") String nhanh, @Param("tietkiem") String tietkiem,@Param("unisex") String unisex,@Param("tlu") String tlu,@Param("yody") String yody);
	
	// search LocalBuy and Shipping and Brand 
	
	@Query("SELECT u FROM ProductEntity u " + "WHERE"
			+ "(u.name LIKE :search% AND (u.shipping LIKE :hoatoc% OR u.shipping LIKE :nhanh% OR u.shipping LIKE :tietkiem%)) AND"
			+ "(u.name LIKE :search% AND (u.brand LIKE :unisex% OR u.brand LIKE :tlu% OR u.brand LIKE :yody%)) AND"
			+ "(u.name LIKE :search% AND (u.localBuy LIKE :hanoi% OR u.localBuy LIKE :thanhhoa% OR u.localBuy LIKE :haiphong%))")
	List<ProductEntity> searchLocalBuyandShippingandBrand(@Param("search") String search, 
			@Param("hanoi") String hanoi,@Param("thanhhoa") String thanhhoa, @Param("haiphong") String haiphong,
			@Param("hoatoc") String hoatoc,@Param("nhanh") String nhanh, @Param("tietkiem") String tietkiem,
			@Param("unisex") String unisex,@Param("tlu") String tlu,@Param("yody") String yody);
	//remove product in list<product> theo id
//	@Query("DELETE FROM ProductEntity u WHERE u.id = :id")
//	void removeproductWishlist(@Param("id") int id);
}
