package online.fimbi.Dto;

import java.util.Date;

import lombok.Data;

@Data
public class BondDtoRes {
	Long id;
	String issuer_identifier;
	Date due_date; // Fecha limite del prestamo
	Long total_value; // Monto que se requiere prestar
	Long nominal_value; // Valor unitario
	float interest_rate; // interes
	float capitalization_rate; // tasa de capitalizacion (anual, diario, etc)
	String external_interest_rate; // nombre de la tasa externa si existiese
	int splits; // cantidad de instancias de este bono

	// Long public_value; // valor comercial
	// float income_tax; // FIJO
	float prime_vcto; // ni idea
	float struct; // ni idea
	float colocacion; // ni idea
	float floating; // ni idea
	float cavali; // ni idea
	float tea_coupon; // ni idea
	float yield_to_mat; // ni idea
	float coupon_val; // ni idea
	float return_rate; // IRR
	float present_value; // NPV
	int periods;
}
