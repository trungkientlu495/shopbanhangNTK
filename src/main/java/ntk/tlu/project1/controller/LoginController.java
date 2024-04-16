package ntk.tlu.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.services.UserServices;

@Controller
@RequestMapping("/api/")
public class LoginController {
	@Autowired
	UserServices userServices;
    // chuyển đến trang đăng ký
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "/Anonymous/sign_up";
    }

    // nhận dữ liệu từ form đăng ký khi người dùng đăng ký tài khoản và chuyển đến api /Anonymous/home
    @PostMapping("/register")
    public String signUp(@Valid @ModelAttribute("userModel") UserModel userModel, BindingResult bindingResult, Model model) {
    	model.addAttribute("userModel", userModel);
        if (bindingResult.hasErrors()) {
            // Xử lý lỗi xác thực
            return "Anonymous/sign_up";
        }
        
        if(!userModel.getPassword().equals(userModel.getRepassword())) {
        	model.addAttribute("kien", "Vui lòng nhập lại mật khẩu, xác nhận mật khẩu 2 lần không khớp");
        	return "Anonymous/sign_up";
        }

        // Xử lý logic khi dữ liệu hợp lệ
        
        // Create User
        userServices.createUser(userModel);
        return "redirect:/api/login";
    }
    
    // chuyển đến trang đăng nhập
    @GetMapping("/login")
    public String login() {
        return "Anonymous/sign-in";
    }
    
    // xu ly du lieu tu form dang nhap 
//    @PostMapping("/login")
//    public void loginHome(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session) {
////    	if(userServices.checkLogin(email, password)) {
////    		session.setAttribute("name", userServices.showUser(email).getName());
////    		session.setAttribute("idUser", userServices.showUser(email).getId());
////    		return "redirect:/User/home";
////    	}
////    	return "Anonymous/sign-in";
//    	session.setAttribute("name", userServices.showUser(email).getName());
//		session.setAttribute("idUser", userServices.showUser(email).getId());
//    }
}