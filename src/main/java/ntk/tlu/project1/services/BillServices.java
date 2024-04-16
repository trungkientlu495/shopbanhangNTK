package ntk.tlu.project1.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntk.tlu.project1.entity.BillEntity;
import ntk.tlu.project1.model.BillModel;
import ntk.tlu.project1.repository.BillRepo;

@Service
public class BillServices implements DateServices {
	@Autowired
	BillRepo billRepo;
	@Autowired
	ModelMapper modelMapper;
	// create bill
	public BillEntity createBill(BillModel billModel) {
		BillEntity billEntity = modelMapper.map(billModel, BillEntity.class);
		billEntity.setBuyDate(formattedDate);
		return billRepo.save(billEntity);
	}
	
	// show bill
	public List<BillModel> showBill(int idUser) {
		List<BillEntity> billEntities = billRepo.searchBill(idUser);
		List<BillModel> billModels = billEntities.stream()
			    .map(billEntity -> modelMapper.map(billEntity, BillModel.class))
			    .collect(Collectors.toList());
		return billModels;
	}
	
	// show toan bo bill
	public List<BillModel> showAllBill() {
		List<BillEntity> billEntities = billRepo.searchBill();
		List<BillModel> billModels = billEntities.stream()
			    .map(billEntity -> modelMapper.map(billEntity, BillModel.class))
			    .collect(Collectors.toList());
		return billModels;
	}
}
