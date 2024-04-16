package ntk.tlu.project1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntk.tlu.project1.entity.CommentEntity;
import ntk.tlu.project1.entity.ReviewEntity;
import ntk.tlu.project1.model.CommentModel;
import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.model.ReviewModel;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.repository.CommentRepo;
import ntk.tlu.project1.repository.ReviewRepo;

@Service
public class ReviewServices implements DateServices {
	@Autowired
	ReviewRepo reviewRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	CommentRepo commentRepo;
	public void saveReview(String start, String content, ProductModel productModel,
			UserModel userModel) {
//		ReviewEntity reviewEntity = modelMapper.map(reviewModel, ReviewEntity.class);
//		reviewRepo.save(reviewEntity);
		ReviewModel reviewModel = new ReviewModel();
		int startInt = Integer.parseInt(start);
		reviewModel.setStartCounter(startInt);
		reviewModel.setProductEntity(productModel);
		reviewModel.setUserEntity(userModel);
		ReviewEntity reviewEntity = modelMapper.map(reviewModel, ReviewEntity.class);
		CommentModel commentModel = new CommentModel();
		commentModel.setContent(content);
		commentModel.setProductEntity(productModel);
		commentModel.setUserEntity(userModel);
		commentModel.setReview(reviewModel);
		commentModel.setCreateDate(formattedDate);
		CommentEntity commentEntity = modelMapper.map(commentModel, CommentEntity.class);
		reviewRepo.save(reviewEntity);
		commentRepo.save(commentEntity);
	}
	
	public List<ReviewModel> showReview() {
		List<ReviewEntity> reviewEntities = reviewRepo.findAll();
		List<ReviewModel> reviewModels = reviewEntities.stream()
			    .map(reviewEntitie -> modelMapper.map(reviewEntitie, ReviewModel.class))
			    .collect(Collectors.toList());
		return reviewModels;
	}
}
