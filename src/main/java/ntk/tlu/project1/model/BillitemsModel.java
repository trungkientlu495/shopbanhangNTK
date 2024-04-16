package ntk.tlu.project1.model;

import java.util.List;

import lombok.Data;
@Data
public class BillitemsModel {
	private Long id;
	private BillModel billEntity;
	private ProductModel productEntity;
	private String quantity;
	private String buyPrice;
}
