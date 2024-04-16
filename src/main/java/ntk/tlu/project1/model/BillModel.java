package ntk.tlu.project1.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import ntk.tlu.project1.entity.BillitemsEntity;
import ntk.tlu.project1.entity.UserEntity;
@Data
public class BillModel {
	private Long id;
	private String buyDate;
	private UserModel userEntity;
	private List<BillitemsModel>  billitemsEntities;
	private String address;
	private String tongHoaDon;
}
