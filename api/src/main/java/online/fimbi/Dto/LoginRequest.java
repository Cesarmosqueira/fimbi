package online.fimbi.Dto;

import lombok.Data;

@Data
public class LoginRequest {
	String username;
	String email;
	String password;
}
