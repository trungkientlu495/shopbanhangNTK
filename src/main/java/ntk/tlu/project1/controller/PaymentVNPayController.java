package ntk.tlu.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import ntk.tlu.project1.services.VNPayServices;

@Controller
public class PaymentVNPayController {
	@Autowired
	VNPayServices vnPayServices;

	@GetMapping("/vnpay-payment")
	public String GetMapping(HttpServletRequest request, Model model) {
		int paymentStatus = vnPayServices.orderReturn(request);

		String orderInfo = request.getParameter("vnp_OrderInfo");
		String paymentTime = request.getParameter("vnp_PayDate");
		String transactionId = request.getParameter("vnp_TransactionNo");
		String totalPrice = request.getParameter("vnp_Amount");

		model.addAttribute("orderId", orderInfo);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("paymentTime", paymentTime) ;
		model.addAttribute("transactionId", transactionId);

		return paymentStatus == 1 ? "redirect:/User/hoadon" : "THANH TOÁN THẤT BẠI VUI LÒNG THỬ LẠI";
	}
}
