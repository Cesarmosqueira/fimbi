package online.fimbi.Dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import lombok.Data;

@Data
public class Bond {
	Long id;
	Long issuer_id;
	Date emission_date;
	Date due_date;
	Long nominal_value;
	float interest_rate;
	float capitalization_rate;
	String external_interest_rate;
	boolean available;

	public static Bond random_bond() {
		Bond bond = new Bond();
		Random rand = new Random();
		bond.id = 1 + rand.nextLong(1000);
		bond.issuer_id = 1 + rand.nextLong(1000);
		int emission_year = 1995 + rand.nextInt(23);
		int due_year = 2022 + rand.nextInt(23);

		try {
			bond.emission_date = new SimpleDateFormat("dd/MM/yyyy")
					.parse(String.format("01/01/%d", emission_year));

			bond.due_date = new SimpleDateFormat("dd/MM/yyyy")
					.parse(String.format("01/01/%d", due_year));

		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		bond.nominal_value = 100 + rand.nextLong(200000);
		bond.interest_rate = 0.2f + rand.nextFloat(15);
		bond.capitalization_rate = 0.2f + rand.nextFloat(15);
		bond.external_interest_rate = "None";
		bond.available = rand.nextBoolean();
		return bond;
	}
}
