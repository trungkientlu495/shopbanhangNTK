package ntk.tlu.project1.services;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface DateServices {
	Date dateBuy = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	String formattedDate = dateFormat.format(dateBuy);
}
