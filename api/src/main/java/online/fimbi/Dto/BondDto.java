package online.fimbi.Dto;

import java.util.Date;

import lombok.Data;

@Data
public class BondDto {
	String issuer_identifier;
	Date due_date;
	Long nominal_value;
	float interest_rate;
	float capitalization_rate;
	String external_interest_rate;
	int splits;
}
