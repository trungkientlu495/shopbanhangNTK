package ntk.tlu.project1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.services.ProductServices;

@Controller
@RequestMapping("/Anonymous")
public class AnonymousController {
	@Autowired
	ProductServices productServices;

	@GetMapping("/home")
	public String home(Model model) {
		// search san pham ban chay nhat
		List<ProductModel> productModels = productServices.searchProductQuantityBuyBig();
		model.addAttribute("productEntity", productModels);
		// search toan bo san pham
		List<ProductModel> productModel2s = productServices.searchAll();
		model.addAttribute("productEntity2", productModel2s);
		// search product type (quan,ao) && fix cung
		List<ProductModel> productModel3s = productServices.searchProductType("Quần");
		List<ProductModel> productModel4s = productServices.searchProductType("Áo");
		List<ProductModel> mergedProductEntities = new ArrayList<>(productModel3s);
		mergedProductEntities.addAll(productModel4s);
		model.addAttribute("productEntities3s", mergedProductEntities);
//		// search product type(trang suc)
		List<ProductModel> productModel5s = productServices.searchProductType("Trang Sức");
		model.addAttribute("productEntities5s", productModel5s);

		// search product type ( giày)
		List<ProductModel> productModel6s = productServices.searchProductType("Giày");
		model.addAttribute("productEntities6s", productModel6s);
		model.addAttribute("kien", "Đăng nhập");
		return "/Anonymous/home";
	}

	// search theo User tim kiem
	@GetMapping("/search_product")
	public String searchProduct(@RequestParam(value = "search", defaultValue = "") String search, Model model) {
		model.addAttribute("search", search);
		model.addAttribute("productEntity", productServices.searchProduct(search));
		return "Anonymous/category";
	}

	@GetMapping("/detail")
	public String detail(@RequestParam("id") int id, Model model) {
		model.addAttribute("productEntity", productServices.searchId(id));
		return "Anonymous/detail";
	}
	
	//Dieu Khoan va Dieu Kien
		@GetMapping("/dieukhoanvadichvu")
		public String dieukhoanvadk() {
			return "Anonymous/dieukhoanvadieukien";
		}
		
		//cau hoi thuong gap
		@GetMapping("/cauhoithuonggap")
		public String cauhoithuonggap() {
			return "Anonymous/faq";
		}
}
