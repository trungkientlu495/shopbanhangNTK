package ntk.tlu.project1.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	@CacheEvict(cacheNames = "showBill")
	public BillEntity createBill(BillModel billModel) {
		BillEntity billEntity = modelMapper.map(billModel, BillEntity.class);
		billEntity.setBuyDate(LocalDate.now());
		return billRepo.save(billEntity);
	}
	
	// show bill
	//@Cacheable(cacheNames = "showBill")
	public List<BillModel> showBill(int idUser) {
		List<BillEntity> billEntities = billRepo.searchBill(idUser);
		List<BillModel> billModels = billEntities.stream()
			    .map(billEntity -> modelMapper.map(billEntity, BillModel.class))
			    .collect(Collectors.toList());
		return billModels;
	}
	
	// show toan bo bill
	@Cacheable(cacheNames = "showBill")
	public List<BillModel> showAllBill() {
		List<BillEntity> billEntities = billRepo.searchBill();
		List<BillModel> billModels = billEntities.stream()
			    .map(billEntity -> modelMapper.map(billEntity, BillModel.class))
			    .collect(Collectors.toList());
		return billModels;
	}
	
	//tim theo ngay
	public List<BillModel> showHoaDon(LocalDate dateString) {
		List<BillEntity> billEntities = billRepo.searchBillTKhoadon(dateString);
		List<BillModel> billModels = billEntities.stream()
			    .map(billEntity -> modelMapper.map(billEntity, BillModel.class))
			    .collect(Collectors.toList());
		
		return billModels;
	}
}
