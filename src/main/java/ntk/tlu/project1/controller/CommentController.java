package ntk.tlu.project1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ntk.tlu.project1.model.CommentModel;
import ntk.tlu.project1.model.ReviewModel;
import ntk.tlu.project1.services.CommentServices;
import ntk.tlu.project1.services.ProductServices;
import ntk.tlu.project1.services.ReviewServices;
import ntk.tlu.project1.services.UserServices;

@Controller
@RequestMapping("/User")
public class CommentController {
	@Autowired
	ProductServices productServices;
	@Autowired
	UserServices userServices;
	@Autowired
	ReviewServices reviewServices;
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	@PostMapping("/comment")
	public String postComment(@RequestParam("optradio") String start,@RequestParam("content") String content,
			@RequestParam("idProduct") int idProduct,HttpSession session,HttpServletRequest request) {
		//lay idUser
		Integer idUser = (Integer) session.getAttribute("idUser");
		if(idUser == null) return "redirect:/api/login";
		logger.info("idProduct: "+idProduct);
		reviewServices.saveReview(start,content,productServices.searchId(idProduct),userServices.showUserid(idUser));
		return "redirect:" + request.getHeader("Referer");
	}
}
