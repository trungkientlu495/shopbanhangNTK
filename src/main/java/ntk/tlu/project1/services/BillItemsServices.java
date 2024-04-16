package ntk.tlu.project1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import ntk.tlu.project1.entity.BillitemsEntity;
import ntk.tlu.project1.entity.ProductEntity;
import ntk.tlu.project1.model.BillitemsModel;
import ntk.tlu.project1.model.ProductModel;
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
}
