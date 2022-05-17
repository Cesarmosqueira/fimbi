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

	private void gen_dates(Random rand) {
		int emission_year = 1995 + rand.nextInt(23);
		int due_year = 2022 + rand.nextInt(23);

		try {
			this.emission_date = new SimpleDateFormat("dd/MM/yyyy")
					.parse(String.format("01/01/%d", emission_year));

			this.due_date = new SimpleDateFormat("dd/MM/yyyy")
					.parse(String.format("01/01/%d", due_year));

		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	}

	public Bond() {
		Random rand = new Random();
		this.id = 1 + rand.nextLong(1000);
		this.issuer_id = 1 + rand.nextLong(1000);
		gen_dates(rand);

		nominal_value = 100 + rand.nextLong(200000);
		interest_rate = 0.2f + rand.nextFloat(15);
		capitalization_rate = 0.2f + rand.nextFloat(15);
		external_interest_rate = "None";
		available = rand.nextBoolean();
	}
}
