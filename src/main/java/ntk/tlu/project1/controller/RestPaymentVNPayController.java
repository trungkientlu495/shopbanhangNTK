package ntk.tlu.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ntk.tlu.project1.services.VNPayServices;


//test vnpay:
//(vnp_TmnCode): CGXZLS0Z
//(vnp_HashSecret): XNBCJFAKAZQSGTARRLGCHVZWCIOIGSHN
//Ngân hàng: NCB
//Số thẻ: 9704198526191432198
//Tên chủ thẻ:NGUYEN VAN A
//Ngày phát hành:07/15
//Mật khẩu OTP:123456
@RestController
public class RestPaymentVNPayController {
	@Autowired
	VNPayServices vnPayServices;
	@GetMapping("/submitOrder")
    public RedirectView submidOrder(
                            @RequestParam(defaultValue = "a") String orderInfo,
                            HttpServletRequest request,HttpSession session){
		String tong = (String) session.getAttribute("tong");
		int orderTotal = Integer.parseInt(tong.replace(".", ""));
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayServices.createOrder(orderTotal, orderInfo, baseUrl);
        return new RedirectView(vnpayUrl, true);
    }
	
//	@GetMapping("/vnpay-payment")
//    public String GetMapping(HttpServletRequest request, Model model){
//        int paymentStatus =vnPayServices.orderReturn(request);
//
//        String orderInfo = request.getParameter("vnp_OrderInfo");
//        String paymentTime = request.getParameter("vnp_PayDate");
//        String transactionId = request.getParameter("vnp_TransactionNo");
//        String totalPrice = request.getParameter("vnp_Amount");
//
//        model.addAttribute("orderId", orderInfo);
//        model.addAttribute("totalPrice", totalPrice);
//        model.addAttribute("paymentTime", paymentTime);
//        model.addAttribute("transactionId", transactionId);
//
//        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
//    }
}
