package ntk.tlu.project1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.repository.ProductRepo;

@Service
public class ProductServices {
	@Autowired
	ProductRepo productRepo;
	@Autowired
	ModelMapper modelMapper;
	// services search
	public List<ProductModel> searchProduct(String x) {
		List<ProductEntity> productEntity = productRepo.searchByKeyword(x);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModel.add(productModela);
		}
		return productModel;
	}
	
	//search name product
	public Page<ProductModel> searchNameProduct(String timkiem,Pageable pageable) {
		Page<ProductEntity> productEntity =  productRepo.searchNameProduct(timkiem,pageable);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModela.setGiaNhapSP(productEntitya.getGiaNhapSP());
			productModela.setBrand(productEntitya.getBrand());
			productModela.setQuantitySold(productEntitya.getQuantitySold());
			productModela.setLocalBuy(productEntitya.getLocalBuy());
			productModel.add(productModela);
		}
		return new PageImpl<>(productModel, pageable, productEntity.getTotalElements());
	}

	// search id
	public ProductModel searchId(int id) {
		ProductEntity productEntity = productRepo.searchById(id);
		ProductModel productModel = modelMapper.map(productEntity, ProductModel.class);
		return productModel;
	}

	// search all
	public Page<ProductModel> searchAllProduct(Pageable pageable) {
		Page<ProductEntity> productEntity = productRepo.searchAllProduct(pageable);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModela.setGiaNhapSP(productEntitya.getGiaNhapSP());
			productModela.setBrand(productEntitya.getBrand());
			productModela.setQuantitySold(productEntitya.getQuantitySold());
			productModela.setLocalBuy(productEntitya.getLocalBuy());
			productModel.add(productModela);
		}
		return new PageImpl<>(productModel, pageable, productEntity.getTotalElements());
	}
	
	public List<ProductModel> searchAll() {
		List<ProductEntity> productEntity = productRepo.findAll();
		List<ProductModel> productModel = productEntity.stream()
	            .map(entity -> modelMapper.map(entity, ProductModel.class))
	            .collect(Collectors.toList());
		return productModel;
	}

	// search product type(quần áo)
	public List<ProductModel> searchProductType(String productType) {
		List<ProductEntity> productEntity = productRepo.searchByProductType(productType);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModel.add(productModela);
		}
		return productModel;
	}

	// search product sản phẩm được mua nhiều nhất ( lấy 6 sp )

	public List<ProductModel> searchProductQuantityBuyBig() {
		List<ProductEntity> productEntity = productRepo.findAll();
		for (int i = 0; i < productEntity.size(); i++) {
			for (int j = i + 1; j < productEntity.size(); j++) {
				if (productEntity.get(j).getQuantitySold() > productEntity.get(i).getQuantitySold()) {
					ProductEntity c = productEntity.get(j);
					productEntity.set(j, productEntity.get(i));
					productEntity.set(i, c);

				}
			}
		}
		List<ProductEntity> productEntity2 = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			productEntity2.add(productEntity.get(i));
		}
		List<ProductModel> productModel = productEntity2.stream().map(entity -> modelMapper.map(entity, ProductModel.class))
	            .collect(Collectors.toList());
		return productModel;
	}

	// search localbuy

	public List<ProductModel> searchLocalBuy(String search, String hanoi, String thanhhoa, String haiphong) {
		List<ProductEntity> productEntity = productRepo.searchLocalBuy(search, hanoi, thanhhoa, haiphong);
		/*
		 * List<ProductModel> productModel = productEntity.stream().map(entity ->
		 * modelMapper.map(entity, ProductModel.class)) .collect(Collectors.toList());
		 */
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModel.add(productModela);
		}
		return productModel;
	}

	// search shipping

	public List<ProductModel> searchShipping(String search, String hoatoc, String nhanh, String tietkiem) {
		List<ProductEntity> productEntity = productRepo.searchShipping(search, hoatoc, nhanh, tietkiem);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModel.add(productModela);
		}
		return productModel;
	}

	// search brand

	public List<ProductModel> searchBrand(String search, String unisex, String tlu, String yody) {
		List<ProductEntity> productEntity = productRepo.searchBrand(search, unisex, tlu, yody);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModel.add(productModela);
		}
		return productModel;
	}

	// search localBuy and Shipping

	public List<ProductModel> searchLocalBuyandShipping(String search, String hanoi, String thanhhoa, String haiphong,
			String hoatoc, String nhanh, String tietkiem) {
		List<ProductEntity> productEntity = productRepo.searchLocalBuyandShipping(search, hanoi, thanhhoa, haiphong, hoatoc, nhanh, tietkiem);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModel.add(productModela);
		}
		return productModel;
	}

	// search localBuy and Brand

	public List<ProductModel> searchLocalBuyandBrand(String search, String hanoi, String thanhhoa, String haiphong,
			String unisex, String tlu, String yody) {
		List<ProductEntity> productEntity = productRepo.searchLocalBuyandBrand(search, hanoi, thanhhoa, haiphong, unisex, tlu, yody);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModel.add(productModela);
		}
		return productModel;
	}

	// search Shipping and Brand

	public List<ProductModel> searchShippingandBrand(String search, String hoatoc, String nhanh, String tietkiem,
			String unisex, String tlu, String yody) {
		List<ProductEntity> productEntity = productRepo.searchShippingandBrand(search, hoatoc, nhanh, tietkiem, unisex, tlu, yody);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModel.add(productModela);
		}
		return productModel;
	}

	// search LocalBuy and Shipping and Brand

	public List<ProductModel> searchLocalBuyandShippingandBrand(String search, String hanoi, String thanhhoa,
			String haiphong, String hoatoc, String nhanh, String tietkiem, String unisex, String tlu, String yody) {
		List<ProductEntity> productEntity = productRepo.searchLocalBuyandShippingandBrand(search, hanoi, thanhhoa, haiphong, hoatoc, nhanh, tietkiem,
				unisex, tlu, yody);
		List<ProductModel> productModel = new ArrayList<>();
		for (ProductEntity productEntitya : productEntity) {
			ProductModel productModela = new ProductModel();
			productModela.setId(productEntitya.getId());
			productModela.setName(productEntitya.getName());
			productModela.setImageUrl(productEntitya.getImageUrl());
			productModela.setPrice(productEntitya.getPrice());
			productModela.setPriceBegin(productEntitya.getPriceBegin());
			productModel.add(productModela);
		}
		return productModel;
	}

}
