package online.fimbi.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssuerDtoRes {
	Long id;
	String market_identifier; // (ISO 10383)
	String description;
	Date date_joined;
	String image_url;

}
