package online.fimbi.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FimbiResponse {
	String description;
	int code; // 1: Suceed; 0: Failed
}
