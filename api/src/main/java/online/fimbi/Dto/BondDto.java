package online.fimbi.Dto;

import java.util.Date;

import lombok.Data;

@Data
public class BondDto {
	Long id;
	String issuer_identifier;
	Date due_date; // Fecha limite del prestamo
	Long nominal_value; // Monto que se requiere prestar
	float interest_rate; // interes
	float capitalization_rate; // tasa de capitalizacion (anual, diario, etc)
	String external_interest_rate; // nombre de la tasa externa si existiese
	int splits; // cantidad de instancias de este bono
}
