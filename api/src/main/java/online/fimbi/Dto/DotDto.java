package online.fimbi.Dto;

import java.util.Date;

import lombok.Data;

@Data
public class DotDto {
	Date date;
	float value;
	public DotDto(Date date, float value) {
		this.date = date;
		this.value = value;
	}
}
