package ntk.tlu.project1.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ntk.tlu.project1.model.BillModel;
import ntk.tlu.project1.model.BillitemsModel;
import ntk.tlu.project1.model.PageDTO;
import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.services.BillItemsServices;
import ntk.tlu.project1.services.BillServices;
import ntk.tlu.project1.services.ProductServices;
import ntk.tlu.project1.services.UserServices;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	UserServices userServices;
	@Autowired
	ProductServices productServices;
	@Autowired
	BillServices billServices;
	@Autowired
	BillItemsServices billItemsServices;

	@GetMapping("/dashboard")
	public String dashboard() {
		return "Admin/dashboard";
	}

	// show danh sach nguoi dung
	@GetMapping("/client")
	public String qlClient(Model model, PageDTO pageDTO) {
		// phan trang
		Pageable pageable = PageRequest.of(pageDTO.getNumberPage(), pageDTO.getSizePage());
		Page<UserModel> userModels = userServices.showAllUser(pageable);
		model.addAttribute("listUserModel", userModels);
		model.addAttribute("tranghientai", userModels.getNumber());
		model.addAttribute("tongsotrang", userModels.getTotalPages());
		return "Admin/quanlikhachhang";
	}

	// show ds nguoi dung ma admin timkiem
	@PostMapping("/client")
	public String postplClient(Model model, @RequestParam("timkiem") String timkiem, PageDTO pageDTO) {
		Pageable pageable = PageRequest.of(pageDTO.getNumberPage(), pageDTO.getSizePage());
		UserModel userModel = userServices.searchEmailOrPhoneUser(timkiem);
		List<UserModel> userModels = new ArrayList<UserModel>();
		userModels.add(userModel);
		model.addAttribute("listUserModel", userModel);
		model.addAttribute("timkiem", timkiem);
		model.addAttribute("tranghientai", 0);
		model.addAttribute("tongsotrang", 1);
		return "Admin/quanlikhachhang";
	}

	// xoa nguoi dung
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("idUser") int idUser) {
		userServices.deleteUser(idUser);
		return "redirect:/Admin/client";
	}

	// show danh sach sp co trong shop
	@GetMapping("/product")
	public String qlProduct(Model model, PageDTO pageDTO) {
		Pageable pageable = PageRequest.of(pageDTO.getNumberPage(), pageDTO.getSizePage());
		Page<ProductModel> productModels = productServices.searchAllProduct(pageable);
		model.addAttribute("listProductModel", productModels);
		model.addAttribute("tranghientai", productModels.getNumber());
		model.addAttribute("tongsotrang", productModels.getTotalPages());
		return "Admin/quanlisanpham";
	}

	// api tim kiem name
	@PostMapping("/product")
	public String searchProduct(@RequestParam("timkiem") String timkiem, Model model, PageDTO pageDTO) {
		Pageable pageable = PageRequest.of(pageDTO.getNumberPage(), pageDTO.getSizePage());
		Page<ProductModel> productModels = productServices.searchNameProduct(timkiem, pageable);
		model.addAttribute("listProductModel", productModels);
		model.addAttribute("tranghientai", productModels.getNumber());
		model.addAttribute("tongsotrang", productModels.getTotalPages());
		model.addAttribute("timkiem", timkiem);
		return "Admin/quanlisanpham";
	}

	@GetMapping("/createProduct")
	public String createProduct() {
		return "/Admin/themsanpham";
	}

	// show hoa don
	@GetMapping("/bill")
	public String showBill(Model model) {
		List<BillModel> billModels = billServices.showAllBill();
		model.addAttribute("listBillModel", billModels);
		return "Admin/quanlydonhang";
	}

	// chi tiet don hang trong hoa don
	@GetMapping("/detailBill")
	public String detailBill(Model model, @RequestParam("idBill") int idBill) {
		List<BillitemsModel> billitemsModel = billItemsServices.detailBill(idBill);
		model.addAttribute("listBillitemsModel", billitemsModel);
		return "Admin/detailBill";
	}

	@GetMapping("/thongke")
	public String thongke(Model model, PageDTO pageDTO, HttpSession session) {
		Pageable pageable = PageRequest.of(pageDTO.getNumberPage(), pageDTO.getSizePage());
		Page<UserModel> userModels = userServices.showAllUser(pageable);
		Page<ProductModel> productModels = productServices.searchAllProduct(pageable);
		model.addAttribute("slUser", userModels.getContent().size());
		model.addAttribute("slProduct", productModels.getContent().size());
		model.addAttribute("slAo", billItemsServices.slCategoryBuy("Áo"));
		model.addAttribute("slQuan", billItemsServices.slCategoryBuy("Quần"));
		model.addAttribute("slGiay", billItemsServices.slCategoryBuy("Giày"));
		model.addAttribute("productSell", billItemsServices.showBillitemsModels().size());
		Double tonghoadon = (Double) session.getAttribute("tonghoadon");
		if(tonghoadon==null) {
			tonghoadon = 0.0;
		}
		model.addAttribute("tonghoadon",tonghoadon);
		Integer day = (Integer) session.getAttribute("day");
		Integer month = (Integer) session.getAttribute("month");
		Integer year = (Integer) session.getAttribute("year");
		if(day == null || month == null || year == null) {
			day = 1;
			month = 1;
			year = 2021;
		}
		model.addAttribute("day",day);
		model.addAttribute("month",month);
		model.addAttribute("year",year);
		return "Admin/thongke";
	}

	@PostMapping("/thongke")
	public String showHoaDon(HttpSession session,@RequestParam("day") int day,@RequestParam("month") int month,
			@RequestParam("year") int year){
		LocalDate date = LocalDate.of(year, month, day);
		List<BillModel> billModels = billServices.showHoaDon(date);
		Double a = 0.0;
		if(billModels != null && !billModels.isEmpty()) {
			for (BillModel billModel : billModels) {
				a = Double.parseDouble(billModel.getTongHoaDon()) + a;
			}
		}
		session.setAttribute("tonghoadon", a);
		session.setAttribute("day", day);
		session.setAttribute("month", month);
		session.setAttribute("year", year);
		return "redirect:/Admin/thongke";
	}

}
