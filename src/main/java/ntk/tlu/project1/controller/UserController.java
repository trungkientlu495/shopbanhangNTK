package ntk.tlu.project1.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import ntk.tlu.project1.entity.BillEntity;
import ntk.tlu.project1.entity.UserEntity;
import ntk.tlu.project1.model.BillModel;
import ntk.tlu.project1.model.BillitemsModel;
import ntk.tlu.project1.model.CommentModel;
import ntk.tlu.project1.model.PageDTO;
import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.repository.UserRepo;
import ntk.tlu.project1.services.BillItemsServices;
import ntk.tlu.project1.services.BillServices;
import ntk.tlu.project1.services.CommentServices;
import ntk.tlu.project1.services.EmailServices;
import ntk.tlu.project1.services.ProductCartServices;
import ntk.tlu.project1.services.ProductServices;
import ntk.tlu.project1.services.UserServices;
//Mỗi product có nhiều Comment(id, content, User createdBy, Date createdDate, Product product)
//Mỗi product có nhiều Review(id, int startCounter, User createdBy, Date createdDate, Product product)
@Controller
@RequestMapping("/User")
//implements UserDetailsService
public class UserController implements UserDetailsService  {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	ProductServices productServices;
	@Autowired
	UserServices userServices;
	@Autowired
	ProductCartServices productCartServices;
	@Autowired
	EmailServices emailServices;
	@Autowired
	BillServices billServices;
	@Autowired
	BillItemsServices billItemsServices;
	@Autowired
	CommentServices commentServices;
	@Autowired
	UserRepo userRepo;
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		// search san pham ban chay nhat
		List<ProductModel> productModel = productServices.searchProductQuantityBuyBig();
		model.addAttribute("productEntity", productModel);
		// search toan bo san pham
		List<ProductModel> productModel1 = productServices.searchAll();
		model.addAttribute("productEntity2", productModel1);
		// search product type (quan,ao) && fix cung
		List<ProductModel> productModel3s = productServices.searchProductType("Quần");
		List<ProductModel> productModel4s = productServices.searchProductType("Áo");
		for (ProductModel productModel4 : productModel4s) {
			productModel3s.add(productModel4);
		}
		model.addAttribute("productEntities3s", productModel3s);
		// search product type(trang suc)
		List<ProductModel> productModel5s = productServices.searchProductType("Trang Sức");
		model.addAttribute("productEntities5s", productModel5s);

		// search product type ( giày)
		List<ProductModel> productModel6s = productServices.searchProductType("Giày");
		model.addAttribute("productEntities6s", productModel6s);

		// in ra name
		String email = (String) session.getAttribute("email");
		if(email == null) return "redirect:/api/login";
		session.setAttribute("name", userServices.showUser(email).getName());
		session.setAttribute("idUser", userServices.showUser(email).getId());
//		if (kien == null)
//			return "redirect:/api/login";
		model.addAttribute("kien", userServices.showUser(email).getName());
		
		return "User/home";
	}

	@GetMapping("/search_product")
	public String searchProduct(@RequestParam(value = "search", defaultValue = "") String search, Model model,
			HttpSession session) {
		model.addAttribute("search", search);
		model.addAttribute("productEntity", productServices.searchProduct(search));
		String kien = (String) session.getAttribute("name");
		if (kien == null)
			return "redirect:/api/login";
		model.addAttribute("kien", kien);
		return "User/category";
	}

	@GetMapping("/detail")
	public String detail(@RequestParam("id") int id, Model model,
			HttpSession session,PageDTO pageDTO) {
		Pageable pageable = PageRequest.of(pageDTO.getNumberPage(), pageDTO.getSizePage());
		model.addAttribute("productEntity", productServices.searchId(id));
		String kien = (String) session.getAttribute("name");
		if (kien == null) return "redirect:/api/login";
		model.addAttribute("kien", kien);
		session.setAttribute("idProduct", id);
		Page<CommentModel> commentModels = commentServices.showComment(id,pageable);
		//logger.info("Comment: "+ commentModels.getContent().getReview().getStartCounter());
		model.addAttribute("commentModels",commentModels);
		model.addAttribute("tranghientai",commentModels.getNumber());
		model.addAttribute("tongsotrang",commentModels.getTotalPages());
		logger.info("totalpage: "+commentServices.showStart(id, 5));
		model.addAttribute("tongstar", commentModels.getContent().size());
		logger.info("Tongsp: "+commentModels.getContent().size());
		model.addAttribute("five",commentServices.showStart(id, 5));
		model.addAttribute("four",commentServices.showStart(id, 4));
		model.addAttribute("three",commentServices.showStart(id, 3));
		model.addAttribute("two",commentServices.showStart(id, 2));
		model.addAttribute("one",commentServices.showStart(id, 1));
		return "User/detail";
	}

	// danh sach yeu thich
	@GetMapping("/wishlist")
	public String wishList(Model model, HttpSession session) {
		String name = (String) session.getAttribute("name");
		Integer idUser = (Integer) session.getAttribute("idUser");
		if (name == null || idUser == null)
			return "redirect:/api/login";
		model.addAttribute("name", name);
		model.addAttribute("wishList", userServices.showUserid(idUser).getProducts());
		return "/User/my-wishlist";
	}

	// create product vao danh sach yeu thich
	@GetMapping("/createWishlist")
	public String createWishList(HttpSession session, @RequestParam("id") int idProduct, HttpServletRequest request) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if (idUser == null)
			return "redirect:/api/login";
		userServices.createProductUser(idUser, idProduct);
		return "redirect:" + request.getHeader("Referer");
	}

	// remove product khoi danh sach yeu thich
	@GetMapping("/removeWishlist")
	public String removeWishlist(HttpSession session, @RequestParam("id") int id) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if (idUser == null)
			return "redirect:/api/login";
		userServices.removeWishList(idUser, id);
		return "redirect:/User/wishlist";
	}

	// Dieu Khoan va Dieu Kien
	@GetMapping("/dieukhoanvadichvu")
	public String dieukhoanvadk(HttpSession session, Model model) {
		String name = (String) session.getAttribute("name");
		if (name == null)
			return "redirect:/api/login";
		model.addAttribute("name", name);
		return "User/dieukhoanvadieukien";
	}

	// cau hoi thuong gap
	@GetMapping("/cauhoithuonggap")
	public String cauhoithuonggap(HttpSession session, Model model) {
		String name = (String) session.getAttribute("name");
		if (name == null)
			return "redirect:/api/login";
		model.addAttribute("name", name);
		return "User/faq";
	}

	// Account User
	@GetMapping("/account")
	public String accountUser(Model model, HttpSession session) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		String name = (String) session.getAttribute("name");
		if (name == null || idUser == null)
			return "redirect:/api/login";
		model.addAttribute("userModel", userServices.showUserid(idUser));
		model.addAttribute("name", name);
		return "User/account-user";
	}

//	@GetMapping("/edit_account")
//	public String editAccount() {
//		return "/User/editinfomationuser";
//	}

	// show shopping cart
	@GetMapping("/cart")
	public String shoppingCart(Model model, HttpSession session) {
		// LAY idUser TU SESSION
		Integer idUser = (Integer) session.getAttribute("idUser");
		if (idUser == null)
			return "redirect:/api/login";
		List<ProductModel> productModels = productCartServices.showlistPinPC(idUser);
		List<String> soluongs = productCartServices.showlistQinPC(idUser);
		List<String> tamtinhs = new ArrayList<String>();
		// TRA VE NAME CUA USER
		model.addAttribute("name", userServices.showUserid(idUser).getName());
		// TRA VE LIST PRODUCT TRONG GIO HANG
		model.addAttribute("listProducts", productCartServices.showlistPinPC(idUser));
		// TRA VE SO LUONG SP TRONG GIO HANG
		model.addAttribute("listQuantitis", productCartServices.showlistQinPC(idUser)); // 2 4 6
		double tong = 0;
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		for (int i = 0; i < soluongs.size(); i++) {
			int soluonga = Integer.parseInt(soluongs.get(i));
			Double price = Double.parseDouble(productModels.get(i).getPrice().replace(".", ""));
			tamtinhs.add(decimalFormat.format(soluonga * price).replace(",", "."));
			tong = tong + price * soluonga;
		}
		// GIA TONG SP TRONG GIO HANG
		model.addAttribute("tong", decimalFormat.format(tong).replace(",", "."));
		// TRA VE LIST GIA MOI SP * SL SP
		model.addAttribute("tamtinhs", tamtinhs);
		// SESSION TONG CAC SP TRONG GIO HANG
		session.setAttribute("tong", decimalFormat.format(tong).replace(",", "."));
		// SESSION PRICE CUA MOI SP X SO LUONG SP
		session.setAttribute("tamtinhs", tamtinhs);
		return "User/shopping-cart";
	}

	// create product vao gio hang
	@GetMapping("/createCart")
	public String createProductCart(Model model, HttpServletRequest request, @RequestParam("id") int id,
			@RequestParam(value = "soluong", defaultValue = "1") String soluong, HttpSession session) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if (idUser == null)
			return "redirect:/api/login";
		productCartServices.createProductCart(idUser, id, soluong);
		return "redirect:" + request.getHeader("Referer");
	}

	// remove product khoi Cart
	@GetMapping("/removeProductCart")
	public String removeProductCart(HttpServletRequest request, @RequestParam("idProduct") int idProduct,
			HttpSession session) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		if (idUser == null)
			return "redirect:/api/login";
		productCartServices.removeProductCart(idUser, idProduct);
		return "redirect:" + request.getHeader("Referer");
	}

	// checkout
	@GetMapping("/checkout")
	public String checkout(HttpSession session, Model model) {
		// LAY idUser
		Integer idUser = (Integer) session.getAttribute("idUser");
		// LAY LIST<GIA SP * SO LUONG SP TRONG GIO HANG>
		List<String> tamtinhs = (List<String>) session.getAttribute("tamtinhs");
		// LAY TONG GIA SP TRONG GIO HANG
		String tong = (String) session.getAttribute("tong");
		if (idUser == null || tamtinhs == null || tong == null)
			return "redirect:/api/login";
		model.addAttribute("name", userServices.showUserid(idUser).getName());
		// TRA VE UserModel len VIEW
		model.addAttribute("userModel", userServices.showUserid(idUser));
		// TRA VE LIST PRODUCT TRONG PRODUCCART LEN VIEW
		model.addAttribute("listProducts", productCartServices.showlistPinPC(idUser));
		session.setAttribute("listProducts", productCartServices.showlistPinPC(idUser));
		// TRA VE LIST SO LUONG TRONG PRODUCT CART LEN VIEW
		model.addAttribute("listQuantitis", productCartServices.showlistQinPC(idUser)); // 2 4 6
		// TRA LIST VE GIA SP * SO LUONG SP CUA MOI SP TRONG PRODUCTCART
		model.addAttribute("tamtinhs", tamtinhs);
		// TRA VE TONG GIA SP TRONG GIO HANG
		model.addAttribute("tong", tong);
		session.setAttribute("soluongs", productCartServices.showlistQinPC(idUser));
		return "/User/checkout";
	}

	@GetMapping("/muangayrender")
	public String muangayrender(HttpSession session, Model model, @RequestParam("idProduct") int idProduct) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		String soluong = (String) session.getAttribute("soluong");
		if (idUser == null || soluong == null)
			return "redirect:/api/login";
		UserModel userModel = userServices.showUserid(idUser);
		model.addAttribute("userModel", userModel);
		ProductModel productModel = productServices.searchId(idProduct);
		List<ProductModel> listProducts = new ArrayList<ProductModel>();
		listProducts.add(productModel);
		model.addAttribute("listProducts", listProducts);
		List<String> listQuantitis = new ArrayList<String>();
		listQuantitis.add(soluong);
		model.addAttribute("listQuantitis", listQuantitis);
		List<String> tong = new ArrayList<String>();
		int soluongInt = Integer.parseInt(soluong);
		int priceDouble = Integer.parseInt(productModel.getPrice().replace(".", ""));
		int tonga = soluongInt * priceDouble;
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		String tongb = decimalFormat.format(tonga).replace(",", ".");
		tong.add(tongb);
		model.addAttribute("tong", tong.get(0));
		model.addAttribute("tamtinhs", tong);
		session.setAttribute("listProducts", listProducts);
		session.setAttribute("soluongs", listQuantitis);
		session.setAttribute("tamtinhs", tong);
		session.setAttribute("tong", tong.get(0));
		return "/User/checkout";
	}

	@GetMapping("/hoadon")
	public String billShow(HttpSession session, Model model) {
		Integer idUser = (Integer) session.getAttribute("idUser");
		List<String> soluongs = (List<String>) session.getAttribute("soluongs");
		List<String> tamtinhs = (List<String>) session.getAttribute("tamtinhs");
		List<ProductModel> listProducts = (List<ProductModel>) session.getAttribute("listProducts");
		String tong = (String) session.getAttribute("tong");
		if (idUser == null || soluongs == null || tamtinhs == null || tong == null || listProducts == null)
			return "redirect:/api/login";
		UserModel userModel = userServices.showUserid(idUser);
		model.addAttribute("userModel", userModel);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("tamtinhs", tamtinhs);
		model.addAttribute("tong", tong);
		model.addAttribute("soluongs", soluongs);
		// send Email thông báo cho người dùng
		String to = userModel.getEmail();
		String subject = "Bạn Đã Đặt Đơn Hàng Thành Công";
		String text = "Chào " + userModel.getName() + " , chúng tôi đã nhận được thông tin về đơn hàng bạn đã đặt . "
				+ "Chúng tôi sẽ sớm liên hệ đến bạn và giao đơn hàng đến bạn trong thời gian sớm nhất"
				+ "CẢM ƠN BẠN ĐÃ TIN TƯỞNG SẢN PHẨM CHÚNG TÔI , NTK XIN CHÂN THÀNH CẢM ƠN";
		emailServices.sendEmail(to, subject, text);
		// create bill
		BillModel billModel = new BillModel();
		billModel.setUserEntity(userModel);
		billModel.setAddress(userServices.showUserid(idUser).getAddress());
		billModel.setTongHoaDon(tong);
		BillEntity billEntity = billServices.createBill(billModel);
		billModel.setId(billEntity.getId());
		//
		BillitemsModel billitemsModel = new BillitemsModel();
		for (int i = 0; i < listProducts.size(); i++) {
			billitemsModel.setBillEntity(billModel);
			billitemsModel.setProductEntity(listProducts.get(i));
			billitemsModel.setBuyPrice(listProducts.get(i).getPrice());
			billitemsModel.setQuantity(soluongs.get(i));
			billItemsServices.createBillItems(billitemsModel);
		}
		return "/User/Bill";
	}

	@GetMapping("/historyOrder")
	public String historyOrder(HttpSession session, Model model) {
		String name = (String) session.getAttribute("name");
		int idUser = (Integer) session.getAttribute("idUser");
		model.addAttribute("name", name);
		logger.info("So luong: " + billServices.showBill(idUser).size());
		model.addAttribute("billModels", billServices.showBill(idUser));
		model.addAttribute("listBillitemsModel", billItemsServices.showBillitemsModels());
		return "/User/historyOrder";
	}

	@GetMapping("/detailBill")
	public String detailBill(Model model, @RequestParam("idBill") int idBill) {
		model.addAttribute("listBillitemsModel", billItemsServices.detailBill(idBill));
		return "User/detailBill";
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.checkLogin(email);
		if(userEntity == null) {
			throw new UsernameNotFoundException("Not found");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		List<String> userRole = new ArrayList<String>();
		userRole.add(userEntity.getRole());
		for(String role : userRole) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), authorities);
	}

}
