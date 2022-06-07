package online.fimbi.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PurchaseDto {
	UserDto userDto;
	BondDto bondDto;
	Date purchase_date;
}
