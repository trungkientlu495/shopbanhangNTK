package ntk.tlu.project1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ntk.tlu.project1.entity.CommentEntity;
import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.entity.ReviewEntity;
import ntk.tlu.project1.entity.UserEntity;
import ntk.tlu.project1.model.CommentModel;
import ntk.tlu.project1.model.ProductModel;
import ntk.tlu.project1.model.ReviewModel;
import ntk.tlu.project1.model.UserModel;
import ntk.tlu.project1.repository.CommentRepo;
import ntk.tlu.project1.repository.ReviewRepo;

@Service
public class CommentServices {
	// luu comment
	@Autowired
	CommentRepo commentRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ReviewRepo reviewRepo;
	private static final Logger logger = LoggerFactory.getLogger(CommentServices.class);
	public Page<CommentModel> showComment(int idProduct,Pageable pageable) {
		Page<CommentEntity> commentEntities = commentRepo.showComment(idProduct,pageable);
		List<CommentModel> commentModels = commentEntities.stream()
			    .map(commentModel -> modelMapper.map(commentModel, CommentModel.class))
			    .collect(Collectors.toList());
		return new PageImpl<>(commentModels, pageable, commentEntities.getTotalElements());
	}
	
	public int showStart(int idProduct,int startCounter) {
		return commentRepo.showStar(idProduct, startCounter).size();
	}
}
