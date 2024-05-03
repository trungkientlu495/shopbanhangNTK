//package ntk.tlu.project1.controller;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.gson.Gson;
//
//import jakarta.servlet.http.HttpSession;
//import ntk.tlu.project1.model.BillModel;
//import ntk.tlu.project1.services.BillServices;
//import ntk.tlu.project1.services.CommentServices;
//
//@RestController
//@RequestMapping("/Admin")
//public class RestAdminController {
//	@Autowired
//	BillServices billServices;
//	private static final Logger logger = LoggerFactory.getLogger(RestAdminController.class);
//
//	@PostMapping("/thongke")
//	public String tkHoaDon(@RequestParam("nam") String nam, @RequestParam("thang") String thang,
//			@RequestParam("ngay") String ngay, HttpSession session) {
//		String a = "25/04/2024";
//		List<BillModel> billModels = billServices.showHoaDon(a);
//		logger.info("BILLDD:"+billModels);
//		if(billModels!=null) session.setAttribute("thongkehoadon", billModels);
//		return "1";
//	}
//}
