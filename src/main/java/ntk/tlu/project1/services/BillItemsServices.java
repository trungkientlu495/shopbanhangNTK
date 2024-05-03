package ntk.tlu.project1.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntk.tlu.project1.entity.BillitemsEntity;
import ntk.tlu.project1.model.BillitemsModel;
import ntk.tlu.project1.repository.BillItemsRepo;
import ntk.tlu.project1.repository.BillRepo;
import ntk.tlu.project1.repository.ProductRepo;

@Service
public class BillItemsServices {
	@Autowired
	BillRepo billRepo;
	@Autowired
	BillItemsRepo billItemsRepo;
	@Autowired
	BillServices billServices;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	ModelMapper modelMapper;
	private static final Logger logger = LoggerFactory.getLogger(CommentServices.class);
	public void createBillItems(BillitemsModel billitemsModel) {
		BillitemsEntity billitemsEntity = modelMapper.map(billitemsModel, BillitemsEntity.class);
		billItemsRepo.save(billitemsEntity);
	}
	
	public List<BillitemsModel> showBillitemsModels() {
		List<BillitemsEntity> billitemsEntities = billItemsRepo.showBillitemsEntity();
		List<BillitemsModel> billitemsModels = billitemsEntities.stream()
			    .map(entity -> modelMapper.map(entity, BillitemsModel.class))
			    .collect(Collectors.toList());
		return billitemsModels;
	}
	
	//detail Bill
	public List<BillitemsModel> detailBill(int idBill) {
		List<BillitemsEntity> billitemsEntities = billItemsRepo.detailBill(idBill);
		 List<BillitemsModel> billitemsModels = billitemsEntities.stream()
		            .map(entity -> modelMapper.map(entity, BillitemsModel.class))
		            .collect(Collectors.toList());
		return billitemsModels;
	}
	
	//SOLUONGDANHMUC
	public Integer slCategoryBuy(String danhmuc) {
		List<BillitemsEntity> billitemsEntities = billItemsRepo.showBillitemsEntities(danhmuc);
		 List<BillitemsModel> billitemsModels = billitemsEntities.stream()
		            .map(entity -> modelMapper.map(entity, BillitemsModel.class))
		            .collect(Collectors.toList());
		 int tong = 0;
		 for (BillitemsModel billitemsModel : billitemsModels) {
			int a = Integer.parseInt(billitemsModel.getQuantity());
			tong+=a;
		}
		logger.info("Tong: "+tong);
		return tong;
	}
}
