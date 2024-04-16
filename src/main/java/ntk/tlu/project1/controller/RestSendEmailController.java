package ntk.tlu.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.services.EmailServices;
import ntk.tlu.project1.services.UserServices;

@RestController	
@RequestMapping("/sendEmail")
public class RestSendEmailController {
	@Autowired UserServices userServices;
	@Autowired EmailServices emailServices;
	
	// send email User khi dat hang
	@GetMapping("/dangkythanhcong")
	public void sendEmail(HttpSession session) {
		int idUser = (Integer) session.getAttribute("idUser");
		UserModel userModel = userServices.showUserid(idUser);
		String to = userModel.getEmail();
		String subject = "Bạn Đã Đặt Đơn Hàng Thành Công";
		String text = "Chào "+userModel.getName()+ " , chúng tôi đã nhận được thông tin về đơn hàng bạn đã đặt . "
				+ "Chúng tôi sẽ sớm liên hệ đến bạn và giao đơn hàng đến bạn trong thời gian sớm nhất"
				+ "CẢM ƠN BẠN ĐÃ TIN TƯỞNG SẢN PHẨM CHÚNG TÔI , NTK XIN CHÂN THÀNH CẢM ƠN";
		emailServices.sendEmail(to, subject, text);
		
	}
}
